package com.mule.custom.provider.factory;

import java.util.List;

public interface CustomFileProvider {
	
	public void copyFileToTarget(List<String> files, String target);

}
