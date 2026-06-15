# Overview

As a software engineer working to expand my skills into mobile development, I wanted to learn how to build a real Android application using Java, a language I had just learned. My goal was to understand how Android apps are structured, how screens communicate with each other, and how to integrate a real-world library into a mobile app.

I built a Language Translator Android app that allows users to translate text between ten different languages including English, French, Spanish, German, Italian, Portuguese, Chinese, Arabic, Korean, and Hindi. The app uses Google ML Kit's on-device translation engine, which means translations work without sending data to a server.

To use the app, open it and select your source and target languages from the dropdown menus. Type any text into the input field and tap the Translate button. The translated text appears below. Tap View History to see all translations you have done in the current session, and tap Back to Translator to return to the main screen.

My purpose was to go beyond console applications and understand how real mobile apps work, how layouts are defined in XML, how Java code connects to UI elements, how screens navigate between each other using Intents, and how third-party libraries are added and used in an Android project.

[Software Demo Video](http://youtube.link.goes.hhttps://youtu.be/TdI3NnjvNw4)

# Development Environment

IDE: Android Studio Quail 2026.1.1 

Emulator: Pixel 8 virtual device running Android 16.0 (API 36.1)

Build System: Gradle with Groovy DSL

The app is written entirely in Java for Android. The following libraries were used:

* Google ML Kit Translate (v17.0.3) — provides on-device language translation across 10 languages with automatic model downloading
* AndroidX AppCompat — provides backwards-compatible Activity and UI components
* Android Material Components — used for styled buttons and UI elements
* ConstraintLayout / LinearLayout — used for building responsive screen layouts

# Useful Websites
* [Android Developer Documentation](https://developer.android.com/docs)
* [Build Your First Android App](https://developer.android.com/training/basics/firstapp)
* [Google ML Kit Translation Guide](https://developers.google.com/ml-kit/language/translation)
* [W3Schools Java Tutorial](https://www.w3schools.com/java)

# Future Work
* Save translation history to local storage so it still shows even after the app is closed
* Add a text-to-speech button so users can hear the translated text spoken aloud
* Add support for translating text captured from the camera using ML Kit's text recognition