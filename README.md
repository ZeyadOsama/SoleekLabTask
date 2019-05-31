# SoleekTask

<p align="center"><img width=30% src="https://github.com/ZeyadOsama/SoleekTask/blob/master/SoleekLab.png"></p>
<p align="center" text> <b>Authentication form for Android</p>

<p align="center">
  <img src="https://img.shields.io/pypi/status/Django.svg"/>
  <img src="https://img.shields.io/badge/contributions-welcome-orange.svg"/>
  <a href="https://github.com/ChmaraX/logregform-android/blob/master/LICENSE"><img src="https://img.shields.io/badge/license-MIT-blue.svg"></a>
</p>

## Features
 
- [ ] Welcome splash screen
- [x] Facebook login
- [ ] Google login
- [ ] Login activity with email/password, remembering logged user.
- [ ] Wrong inputs errors


<br>

## Screenshots

<br>

## Basic Overview
>Android authentication form based on Firebase.

<br>

## Implementation
#### google-services.json
You need to set up your own [Firebase](https://firebase.google.com/) authenticator + database and download `google-services.json` file, then add it to `YourApp/app` folder:

The dependencies are already set up.

``` Gradle
dependencies {
    .
    .
    .
    
    implementation 'com.google.android.gms:play-services-auth:16.0.1'

    implementation 'com.google.firebase:firebase-core:16.0.9'
    implementation 'com.google.firebase:firebase-auth:17.0.0'
    implementation 'com.google.firebase:firebase-database:17.0.0'
    implementation 'com.google.firebase:firebase-storage:17.0.0'

    implementation 'com.firebaseui:firebase-ui-auth:4.1.0'

    implementation 'com.facebook.android:facebook-login:5.0.1'
    implementation 'com.facebook.android:facebook-android-sdk:5.0.1'
}
```
