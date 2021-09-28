# RTF-Secure-File-Provider

## Introduction
This mule plugin allows users to read multiple certificates stored via Runtime Fabric secure properties during application startup.
It reads the certificates which are stored in Base64 format using secure properties and write them in mule application base path.

![RTFScureFileProvider](https://user-images.githubusercontent.com/36458155/132572021-17adc5be-21e0-4146-8f04-bf5b71500bd6.jpeg)

## Prerequisite

Base64 encode the TLS certificate
The tls certificates can be base64 encoded Using openssl(or a Base64 encoding utility) as below:

```
openssl base64 -A -in <<FILE_NAME>>
```

Add the base64 encoded string as a secret (secure properties)

```
./rtfctl apply secure-property --key <<Key Name>> --value <<BASE64 Encoded String from above step>> -n <<Environment ID>>
```

Note - In the above step you may want to keep the key same as the file name you would use in your Mule application.
example - truststore.jks

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
Detailed documentation is present [here](https://docs.google.com/document/d/1k1HSuS14HGzSeCe4we9i8xlAFJTapgOlLvzQBenvxO4/edit?usp=sharing)

