package com.rubiks.mealmonkey.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rubiks.mealmonkey.R

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
    }
}


//The Google services plugin for Gradle loads the google-services.json file you just downloaded. Modify your build.gradle files to use the plugin.
//
//Project-level build.gradle (<project>/build.gradle):

//buildscript {
//    repositories {
//        // Check that you have the following line (if not, add it):
//        google()  // Google's Maven repository
//    }
//    dependencies {
//        ...
//        // Add this line
//        classpath 'com.google.gms:google-services:4.3.8'
//    }
//}
//
//allprojects {
//    ...
//    repositories {
//        // Check that you have the following line (if not, add it):
//        google()  // Google's Maven repository
//        ...
//    }
//}


//apply plugin: 'com.android.application'
//// Add this line
//apply plugin: 'com.google.gms.google-services'
//
//dependencies {
//  // Import the Firebase BoM
//  implementation platform('com.google.firebase:firebase-bom:28.3.0')
//
//  // Add the dependency for the Firebase SDK for Google Analytics
//  // When using the BoM, don't specify versions in Firebase dependencies
//  implementation 'com.google.firebase:firebase-analytics-ktx'
//
//  // Add the dependencies for any other desired Firebase products
//  // https://firebase.google.com/docs/android/setup#available-libraries
//}


//Horizontal Recycler view Items
//http://www.json-generator.com/api/json/get/bTFXTokwfC?indent=2

//Restaurant Recycler View
//http://www.json-generator.com/api/json/get/bTVVjWRhbC?indent=2

//Food Menu
//https://api.npoint.io/eac7a25cc5fb685927a7