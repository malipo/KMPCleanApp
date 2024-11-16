plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }



    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.androidx.lifecycle.viewmodel.ktx )
            implementation(libs.koin.core)
            implementation(libs.koin.compose.viewmodel)
            api(projects.kmpCore.network)

            implementation(projects.kmpCore.room)

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            api(libs.kotlinx.coroutines.test)
            api(libs.koin.test)
        }

    }
}

android {
    namespace = "com.jetbrains.museumlist"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
