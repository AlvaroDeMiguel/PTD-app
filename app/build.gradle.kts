plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    //FIREBASE
    alias(libs.plugins.googleService)
    //HILT Y KSP
//    alias(libs.plugins.hilt)
//    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.ptdapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.ptdapp"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // default
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Compose Navigation
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // HILT Y KSP
//    implementation(libs.hilt.android)
//    ksp(libs.hilt.compiler)
//    implementation(libs.hilt.navigation.compose)

    // FIREBASE //
    implementation(platform("com.google.firebase:firebase-bom:33.10.0"))
    implementation(libs.firebaseAuth)
    implementation ("com.google.firebase:firebase-firestore-ktx")

    //Leer Json
    implementation ("com.google.code.gson:gson:2.10.1")

    //Funciones asincronas (.await)
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3")


}