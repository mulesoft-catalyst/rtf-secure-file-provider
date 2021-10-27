package com.mule.custom.provider.factory;



import com.mule.custom.provider.secret.SecureSecretFIleProvider;

public class CustomFileProviderFactory {
	
	public static CustomFileProvider getFileProvider(String path) {
		
		if(path == null) {
			path = "secure-secret://";
		}
		
		CustomFileProvider provider = null;
		
		String protocol = path.substring(0, path.indexOf("://"));
		path = path.replaceFirst(".*://", "");
		
		if("secure-secret".equalsIgnoreCase(protocol)) {
			
			provider = new SecureSecretFIleProvider();
			
		}
		
		
		return provider;
	}
	

}
