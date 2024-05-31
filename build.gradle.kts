buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.4.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.21")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")
    }
}
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}