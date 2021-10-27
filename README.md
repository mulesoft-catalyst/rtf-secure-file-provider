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

