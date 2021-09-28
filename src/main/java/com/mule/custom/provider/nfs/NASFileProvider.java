package com.mule.custom.provider.nfs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.emc.ecs.nfsclient.nfs.io.Nfs3File;
import com.emc.ecs.nfsclient.nfs.io.NfsFileInputStream;
import com.emc.ecs.nfsclient.nfs.nfs3.Nfs3;
import com.mule.custom.provider.factory.CustomFileProvider;

public class NASFileProvider implements CustomFileProvider{
	
	private String nfsHost;
	
	private String nfsPath;

	public NASFileProvider(String nfsHost, String nfsPath) {
		super();
		this.nfsHost = nfsHost;
		this.nfsPath = nfsPath;
	}
	
	public void copyFileToTarget(List<String> files, String target) {
		
		Nfs3 nfs3;
		try {
			nfs3 = new Nfs3(this.nfsHost + ":" + this.nfsPath, 0, 0, 3);
			for(String file: files) {
				if(!file.startsWith("/")) {
					file = "/" + file;
				}
				Nfs3File nfsFile = new Nfs3File(nfs3, file);
				
				InputStream is = new NfsFileInputStream(nfsFile);
				
				Files.copy(is, Paths.get(target + "/" + file));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		
	}
	
	public static void main(String[] args) {
		
		NASFileProvider nas = new NASFileProvider("ec2-18-234-192-37.compute-1.amazonaws.com", "/nfsshare");
		List<String> fileList = new ArrayList<String>();
		fileList.add("/test");
		
		nas.copyFileToTarget(fileList, "");
		
	}

}
