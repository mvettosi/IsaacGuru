import com.diffplug.gradle.spotless.SpotlessExtension

plugins {
  // this is necessary to avoid the plugins to be loaded multiple times
  // in each subproject's classloader
  alias(libs.plugins.androidApplication) apply false
  alias(libs.plugins.androidLibrary) apply false
  alias(libs.plugins.compose.compiler) apply false
  alias(libs.plugins.jetbrainsCompose) apply false
  alias(libs.plugins.kotlinMultiplatform) apply false
  alias(libs.plugins.ksp) apply false
  alias(libs.plugins.room) apply false
  alias(libs.plugins.spotless)
  alias(libs.plugins.google.services) apply false
  alias(libs.plugins.crashlytics) apply false
}

configure<SpotlessExtension> {
  java {
    target("**/src/**/*.java")
    googleJavaFormat()
  }
  kotlin {
    target("**/src/**/*.kt")
    ktfmt("0.30")
  }
  kotlinGradle {
    target("**/*.gradle.kts")
    ktfmt()
  }
}

tasks.register("regenerateRes") { dependsOn(":composeApp:generateResourceAccessorsForCommonMain") }
