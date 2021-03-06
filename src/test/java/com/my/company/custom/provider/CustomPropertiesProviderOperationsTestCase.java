package com.my.company.custom.provider;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;

import javax.inject.Inject;

import org.junit.Test;

public class CustomPropertiesProviderOperationsTestCase extends MuleArtifactFunctionalTestCase {

  /**
   * Specifies the mule config xml with the flows that are going to be executed in the tests, this file lives in the test
   * resources.
   */
  @Override
  protected String getConfigFile() {
	System.setProperty("truststore.jks", "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");
    return "test-mule-config.xml";
  }

  
  @Test
  public void customPropertyProviderSuccessfullyConfigured() {
    assertThat("test", is("test"));
  }

}
