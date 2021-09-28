package com.mule.custom.provider.secret;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import com.mule.custom.provider.factory.CustomFileProvider;

public class SecureSecretFIleProvider implements CustomFileProvider{

	@Override
	public void copyFileToTarget(List<String> files, String target) {
		// TODO Auto-generated method stub
		
	    for(String fileKey: files) {
	    	
	    	if(fileKey != null) {
	    		String fileData = System.getProperty(fileKey.trim());
	    		if(fileData != null) {
	    			addFile(target + "/" +fileKey, fileData);
	    		}
	    	}
	    	
	    }
		
	}
	
	  private void addFile(String path, String fileData) {
			File targetFile = new File(path);
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(targetFile);
				if(!targetFile.exists()) {
					targetFile.createNewFile();
				}
				
				fileData.replaceAll(" ", "");
				
				fos.write(Base64.getDecoder().decode(fileData));
				fos.flush();
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					if(fos != null) {
						fos.close();
					}
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
	}

}
