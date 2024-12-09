plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "com.jetbrains.tv"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.jetbrains.tv"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform("androidx.compose:compose-bom:2024.10.01"))
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.core.ktx)

    // Compose dependencies
    implementation(libs.compose.ui)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material3)
    implementation(libs.compose.tv)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)


    // TV foundation and material for Android TV
    implementation(libs.tv.foundation)
    implementation(libs.compose.tv)

    // Koin for dependency injection
    implementation(libs.koin.core)
    implementation(libs.koin.compose)
    implementation(libs.koin.compose.viewmodel)

    // Navigation
    implementation(libs.navigation)

    // Coil for image loading in Compose
    implementation(libs.coil.compose)

    // Other dependencies
    implementation(libs.androidx.activity.compose)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)

//    api(libs.kotlinx.serialization.json)
//    implementation(libs.androidx.core.ktx)
//
//    implementation(libs.coil.compose)
//    implementation(libs.coil.compose)
//
//    implementation(libs.navigation)
//    implementation(libs.koin.compose)
//    implementation(libs.koin.core)
//    implementation(libs.koin.compose.viewmodel)
//
//   implementation(libs.tv.foundation)
//   implementation(libs.compose.material3)
//
//    val composeBom = platform("androidx.compose:compose-bom:2024.10.01")
//    implementation(composeBom)
//
//    // General compose dependencies.
//    implementation("androidx.activity:activity-compose:1.9.2")
//
//    implementation("androidx.compose.ui:ui-tooling-preview")
//    debugImplementation("androidx.compose.ui:ui-tooling")
//
//    // Compose for TV dependencies.
//    implementation("androidx.tv:tv-material:1.0.0")
}