# SoleekTask

<p align="center"><img width=30% src="https://github.com/ZeyadOsama/SoleekLabTask/blob/master/soleek-lab-logo.png"></p>
<p align="center" text> <b>Authentication form for Android</p>

<p align="center">
  <img src="https://img.shields.io/pypi/status/Django.svg"/>
  <img src="https://img.shields.io/badge/contributions-welcome-orange.svg"/>
  <a href="https://github.com/ChmaraX/logregform-android/blob/master/LICENSE"><img src="https://img.shields.io/badge/license-MIT-blue.svg"></a>
</p>

## Features
 
- [ ] Welcome splash screen
- [x] Facebook login
- [x] Google login
- [x] Login activity with email/password, remembering logged user.
- [x] Wrong inputs errors


<br>

## Samples
### Login & Registration
<p align="center">
  <img src="samples/sample-login.png" width="300"/> <img src="samples/sample-registeration.png" width="300"/> 
</p>

#### Using Google
<p align="center">
  <img src="samples/sample-login-google.png" width="300"/>
</p>

#### Using Facebook
<p align="center">
  <img src="samples/sample-login-facebook.png" width="300"/>
</p>

### Home
<p align="center">
  <img src="samples/sample-home.png" width="300"/>
</p>
  
<br>

## Basic Overview
>Android authentication form based on Firebase.

<br>

## Implementation
#### Retrofit
[Retrofit](https://github.com/square/retrofit/tree/master/retrofit-converters/gson) is a Converter which uses Gson for serialization to and from JSON. Used to deserlize data retreived from the [API](https://restcountries.eu/rest/v2/).

#### google-services.json
You need to set up your own [Firebase](https://firebase.google.com/) authenticator + database and download `google-services.json` file, then add it to `YourApp/app` folder:

The dependencies are already set up.

``` Gradle
dependencies {
    .
    .
    .
    // authentication
    implementation 'com.google.firebase:firebase-auth:17.0.0'
    implementation 'com.google.android.gms:play-services-auth:16.0.1'
    implementation 'com.facebook.android:facebook-login:5.0.1'

    // GSON Converter
    implementation 'com.squareup.retrofit2:retrofit:2.5.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.5.0'
}
```
