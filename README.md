# RTF-Secure-File-Provider

## Introduction
This repository involves custom developed components that is not part of MuleSoft product suite and therefore not supported by MuleSoft support. Technical assistance for these components are limited to this documentation.This is an UNLICENSED utility, please review the considerations. If you need assistance on extending this application, contact your MuleSoft Customer Success representative or MuleSoft Professional Services

![RTFScureFileProvider](https://user-images.githubusercontent.com/36458155/132572021-17adc5be-21e0-4146-8f04-bf5b71500bd6.jpeg)


## Installation
Users can install the mule plugin into local maven repository, Exchange or your enterprise artifact repository like artificatory

### Local
To install the mule plugin locally download or clone the github repo to your computer.
Go to the folder which contains pom.xml and execute below command - 
```
mvn clean install
```

### Exchange
To install the mule plugin into exchange use steps mentioned [here](https://docs.mulesoft.com/exchange/to-publish-assets-maven)

## Add Dependency
Add this dependency to your application pom.xml locally

```
        <dependency>
        	<groupId>com.mule.rtf.custom.provider</groupId>
		<artifactId>mule-rtf-secure-file-provider-module</artifactId>
		<version>1.0.0-SNAPSHOT</version>
		<classifier>mule-plugin</classifier>
        </dependency>

```

If you are adding dependency from exchange the groupId should match your Organization Id.

```
        <dependency>
        	<groupId><<yourOrgIdHere>></groupId>
		<artifactId>mule-rtf-secure-file-provider-module</artifactId>
		<version>1.0.0</version>
		<classifier>mule-plugin</classifier>
        </dependency>

```
Note - Maven steps are detailed [here](https://docs.mulesoft.com/mule-runtime/4.3/maven-reference)

## Using the module

### Base 64 encode TLS certificates

Base64 encode the TLS certificate
The tls certificates can be base64 encoded Using openssl(or a Base64 encoding utility) as below:

```
openssl base64 -A -in <<FILE_NAME>>
```

### Add the base64 encoded string as a secret (secure properties)

```
./rtfctl apply secure-property --key <<Key Name>> --value <<BASE64 Encoded String from above step>> -n <<Environment ID>>
```

Note - In the above step you may want to keep the key same as the file name you would use in your Mule application.
example - truststore.jks

### Configure Mule application to use the TLS certificate
In this step add the certificate file name to Mule configuration file the same you would have configured before.

![](https://user-images.githubusercontent.com/36458155/139069738-f61154fc-89b5-481f-9797-e77a53becf74.png)

### Configure RTF secure file provider custom component
This is a custom component that needs to be configured in the Mule application. This component loads the base64 encoded file that was added to secure properties before to application classpath during startup.

![](https://user-images.githubusercontent.com/36458155/139069760-eae019b9-e7e8-4d6d-b4e5-b04b9d2786cf.png)


### Running application on Studio
In order to run the application in the studio without adding tls certificates to /src folder all you need to do is add the secret key (file name) and base64 encoded value to program arguments under run configurations.

Example: 

-Dkeystore.jks=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

