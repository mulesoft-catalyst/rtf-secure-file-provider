package com.mule.custom.provider.factory;

import com.mule.custom.provider.nfs.NASFileProvider;
import com.mule.custom.provider.secret.SecureSecretFIleProvider;

public class CustomFileProviderFactory {
	
	public static CustomFileProvider getFileProvider(String path) {
		
		if(path == null) {
			path = "secure-secret://";
		}
		
		CustomFileProvider provider = null;
		
		String protocol = path.substring(0, path.indexOf("://"));
		path = path.replaceFirst(".*://", "");
		
		if("nfs".equalsIgnoreCase(protocol)) {
			
			String host = path.substring(0, path.indexOf("/"));
			String hostPath = path.replace(host, "");
			
			provider = new NASFileProvider(host, hostPath);
			
		}else {
			provider = new SecureSecretFIleProvider();
		}
		
		
		return provider;
	}
	

}
