/*
 * (c) 2003-2018 MuleSoft, Inc. This software is protected under international copyright
 * law. All use of this software is subject to MuleSoft's Master Subscription Agreement
 * (or other master license agreement) separately entered into in writing between you and
 * MuleSoft. If such an agreement is not in place, you may not use the software.
 */
package com.mule.custom.provider.api;

import static com.mule.custom.provider.api.CustomConfigurationPropertiesExtensionLoadingDelegate.CONFIG_ELEMENT;
import static com.mule.custom.provider.api.CustomConfigurationPropertiesExtensionLoadingDelegate.EXTENSION_NAME;
import static org.mule.runtime.api.component.ComponentIdentifier.builder;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;

import org.mule.runtime.api.component.ComponentIdentifier;
import org.mule.runtime.config.api.dsl.model.ConfigurationParameters;
import org.mule.runtime.config.api.dsl.model.ResourceProvider;
import org.mule.runtime.config.api.dsl.model.properties.ConfigurationPropertiesProvider;
import org.mule.runtime.config.api.dsl.model.properties.ConfigurationPropertiesProviderFactory;
import org.mule.runtime.config.api.dsl.model.properties.ConfigurationProperty;

import com.mule.custom.provider.factory.CustomFileProvider;
import com.mule.custom.provider.factory.CustomFileProviderFactory;

/**
 * Builds the provider for a custom-properties-provider:config element.
 *
 * @since 1.0
 */
public class CustomConfigurationPropertiesProviderFactory implements ConfigurationPropertiesProviderFactory {

	public static final String EXTENSION_NAMESPACE = EXTENSION_NAME.toLowerCase().replace(" ", "-");
	private static final ComponentIdentifier CUSTOM_PROPERTIES_PROVIDER = builder().namespace(EXTENSION_NAMESPACE)
			.name(CONFIG_ELEMENT).build();
	// TODO change to meaningful prefix
	private final static String CUSTOM_PROPERTIES_PREFIX = "rtf-secure-file-provider::";
	private static final String TEST_KEY = "testKey";

	@Override
	public ComponentIdentifier getSupportedComponentIdentifier() {
		return CUSTOM_PROPERTIES_PROVIDER;
	}

	@Override
	public ConfigurationPropertiesProvider createProvider(ConfigurationParameters parameters,
			ResourceProvider externalResourceProvider) {

		String fileShare = parameters.getStringParameter("fileShareLocation");

		String customParameterValue = parameters.getStringParameter("secureFileKeys");

		String[] customParameterValues = customParameterValue.split(",");
		String baseFolder = System.getProperty("mule.home") + "/apps";
		String appFolderName = new File(baseFolder).list()[0];
		String target = baseFolder + "/" + appFolderName;
		
		CustomFileProvider provider = CustomFileProviderFactory.getFileProvider(fileShare);
		if(provider != null) {
			provider.copyFileToTarget(Arrays.asList(customParameterValues), target);
		}
		
		


		return new ConfigurationPropertiesProvider() {

			@Override
			public Optional<ConfigurationProperty> getConfigurationProperty(String configurationAttributeKey) {

				return Optional.empty();
			}

			@Override
			public String getDescription() {
				return "RTF Secure File Provider";
			}
		};
	}


}
