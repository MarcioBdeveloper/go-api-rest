package br.com.monsterapi.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import br.com.monsterapi.config.multitenancy.DataSourceProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "spring")
public class ApplicationConfig {
	
	private List<DataSourceProperty> datasources = new ArrayList<DataSourceProperty>();
	private Jwt jwt = new Jwt();
	
	
	public static class Jwt {
		@Getter
		@Setter
		private String secret;
	}
	

}
