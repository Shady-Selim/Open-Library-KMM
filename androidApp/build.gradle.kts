plugins {
    id("com.android.application")
    kotlin("android")
}

val compose_version = "1.0.5"

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "com.shady.openlibrary.android"
        minSdk = 25
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = compose_version
        kotlinCompilerVersion = "1.5.21"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.4.0")
    // Integration with activities
    implementation ("androidx.activity:activity-compose:1.4.0")
    // Compose Material Design
    implementation ("androidx.compose.material:material:$compose_version")
    // Animations
    implementation ("androidx.compose.animation:animation:$compose_version")
    // Tooling support (Previews, etc.)
    implementation ("androidx.compose.ui:ui:1.0.5")
    implementation ("androidx.compose.ui:ui-tooling-preview:$compose_version")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    debugImplementation ("androidx.compose.ui:ui-tooling:$compose_version")
    // Integration with ViewModels
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0")
    // Livedata
    implementation ("androidx.compose.runtime:runtime-livedata:$compose_version")
    // Lifecycle
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
    // Coil Library for image loading
    implementation("io.coil-kt:coil-compose:1.3.2")
    // Coroutines Library
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")
    // Navigation Library
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha07")
    // Testing Libraries
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:$compose_version")
}