plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.mymail"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.mymail"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("androidx.appcompat:appcompat:1.X.X") // Reemplaza X.X con la última versión
    implementation("com.google.android.material:material:1.X.X") // Reemplaza X.X con la última versión
    implementation("de.hdodenhof:circleimageview:3.1.0") // Para la imagen circular
}