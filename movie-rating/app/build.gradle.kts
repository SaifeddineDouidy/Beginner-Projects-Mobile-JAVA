plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.stars"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.stars"
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
    implementation(libs.recyclerview) // androidx.recyclerview:recyclerview:1.3.0

    // CircleImageView
    implementation(libs.circleimageview) // de.hdodenhof:circleimageview:3.1.0

    // Glide for image loading
    implementation(libs.glide) // com.github.bumptech.glide:glide:5.0.0-rc01
    implementation(libs.appcompat) // androidx.appcompat:appcompat:1.6.1
    implementation(libs.material) // com.google.android.material:material:1.10.0
    implementation(libs.activity) // androidx.activity:activity-ktx:1.7.2
    implementation(libs.constraintlayout) // androidx.constraintlayout:constraintlayout:2.1.4
    implementation(libs.support.annotations) // androidx.annotation:annotation:1.6.0

    testImplementation(libs.junit) // junit:junit:4.13.2
    androidTestImplementation(libs.ext.junit) // androidx.test.ext:junit:1.1.5
    androidTestImplementation(libs.espresso.core) // androidx.test.espresso:espresso-core:3.5.1
}