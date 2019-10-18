package br.com.monsterapi.config.security;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import br.com.monsterapi.config.ApplicationConfig;
import br.com.monsterapi.config.multitenancy.DataSourceProperty;
import br.com.monsterapi.config.multitenancy.TenantLocalStorage;
import br.com.monsterapi.config.multitenancy.TenantRoutingDataSource;
import br.com.monsterapi.service.security.JwtUserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableTransactionManagement
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String[] AUTH_WHITELIST = {
			"/auth/**",
            // -- swagger ui
			"/v2/api-docs", "/configuration/**", "/swagger-resources/**",  "/swagger-ui.html", "/webjars/**", "/api-docs/**"
    };
	
	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	private JwtUserDetailsServiceImpl jwtUserDetailsService;
	
	@Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }
	
	@Override
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(this.jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	 public AuthenticationManager authenticationManagerBean() throws Exception {
	      return super.authenticationManagerBean();
	}  
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.csrf().disable().exceptionHandling().authenticationEntryPoint(this.authenticationEntryPoint).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers(AUTH_WHITELIST).permitAll().anyRequest()
				.authenticated();

		httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		httpSecurity.headers().cacheControl();
	}
	
	@Bean
    @Primary
    public DataSource dataSource(ApplicationConfig applicationConfig) {
        AbstractRoutingDataSource tenantRoutingDataSource = new TenantRoutingDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
     
        for(DataSourceProperty dataSourceProperty : applicationConfig.getDatasources()) {
        	
        	DataSource dataSource = DataSourceBuilder.create()
    				.url(dataSourceProperty.getUrl())
    				.username(dataSourceProperty.getUsername())
    				.password(dataSourceProperty.getPassword())
    				.driverClassName(dataSourceProperty.getDriverClassName())
    				.build();
        	
        	if (dataSourceProperty.getName().equalsIgnoreCase("db_apiresttenancy")) {
    			tenantRoutingDataSource.setDefaultTargetDataSource(dataSource);
    			TenantLocalStorage.setTenantName(dataSourceProperty.getName());
    		}
    		targetDataSources.put(dataSourceProperty.getName(), dataSource);
        }	
        	
        tenantRoutingDataSource.setTargetDataSources(targetDataSources);
        tenantRoutingDataSource.afterPropertiesSet();
        return tenantRoutingDataSource;
    }
	
	
}
