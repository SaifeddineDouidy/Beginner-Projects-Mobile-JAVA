plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.tp5"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tp5"
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

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Volley library for networking
    implementation("com.android.volley:volley:1.2.1")

    // Gson library for JSON parsing
    implementation("com.google.code.gson:gson:2.11.0")

    // Glide library for image loading
    implementation("com.github.bumptech.glide:glide:5.0.0-rc01") {
        // To avoid using annotation processor
        exclude(group = "com.github.bumptech.glide", module = "compiler")
    }

    // CircleImageView library for circular image views
    implementation("de.hdodenhof:circleimageview:3.1.0")

    // https://mvnrepository.com/artifact/io.reactivex.rxjava3/rxjava
    implementation("io.reactivex.rxjava3:rxjava:3.1.5")

}
