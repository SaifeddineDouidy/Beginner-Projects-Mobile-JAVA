plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.login_signup"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.login_signup"
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
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // ViewPager2 and Fragment dependencies
    implementation("androidx.viewpager2:viewpager2:1.0.0") // For ViewPager2
    implementation("androidx.fragment:fragment:1.6.1") // For Fragment support

    // Lifecycle components
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2") // ViewModel support
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2") // LiveData support

    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
