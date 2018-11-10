package br.com.monsterapi.config.multitenancy;

public class TenantLocalStorage {

	
	private static ThreadLocal<String> tenant = new ThreadLocal<>();
	 
    public static void setTenantName(String tenantName) {
        tenant.set(tenantName);
    }
 
    public static String getTenantName() {
        return tenant.get();
    }
    
    public static void clear() {
    	tenant.remove();
    }
	
}
