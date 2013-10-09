cordova-plugin-geoservice
=========================

Prerequisites
-------------

* adb, android sdk (v17)

Installation Instructions
-------------------------

* install Google Play Services from adnroid sdk

    android sdk

* copy the library project at <android-sdk>/extras/google/google_play_services/libproject/google-play-services_lib/ to the android app project

* add `android.library.reference.1=google-play-services_lib` to `project.properties`

* copy `local.properties` and `build.xml` from your android project to google-play-services_lib/ folder in your android project
