plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.tp_listcontacts"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tp_listcontacts"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlin {
        jvmToolchain(8)
    }


}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // Room
    //implementation(libs.room.runtime)
    // Room
    implementation(libs.androidx.room.runtime.v251)
    implementation(libs.androidx.room.ktx.v251)

    // KSP for Room (instead of kapt if you're using KSP)
    ksp(libs.androidx.room.compiler.v251)

    // Kotlin Symbol Processing (KSP)
    ksp(libs.symbol.processing.api.v18221011)


    // KSP for Room (instead of kapt if you're using KSP)
    //ksp(libs.androidx.room.compiler)

    // Kotlin Symbol Processing (KSP)
    //ksp(libs.symbol.processing.api)


    implementation(libs.androidx.room.ktx)

    // ViewModel et LiveData
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.activity.ktx)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)
}