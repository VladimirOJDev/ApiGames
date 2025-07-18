import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp") //Necesario para hilt
    id("com.google.dagger.hilt.android") //hilt
    kotlin("plugin.serialization") version "2.0.21" //necesario para  la serializacion denavigation
}

android {
    namespace = "com.example.apigames"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.apigames"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        //Usar local propierties para que no se suba al repositorio
        val properties = Properties()
        val localPropertiesFile = project.rootProject.file("local.properties")
        if (localPropertiesFile.exists()) {
            properties.load(localPropertiesFile.inputStream())
        } else {
            // Opcional: Puedes loggear una advertencia si el archivo no se encuentra
            println("ADVERTENCIA: local.properties no encontrado. Las claves de API y URLs sensibles podrían faltar.")
        }

        buildConfigField("String", "API_KEY", "\"${properties.getProperty("API_KEY", "DEFAULT_API_KEY_IF_MISSING")}\"")

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
        buildConfig = true //genera unaclase BuildConfig para acceder a los valores delocal properties
    }
}

dependencies {

    //Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    //Hilt NavigationCompose
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.androidx.hilt.compiler)

    //Navigation compose y serialization
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)


    //Convertidor retrofit a json
    implementation(libs.converter.gson)

    //coild manejo de imagenes mas optimizadas de internet
    implementation(libs.coil.compose)


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
}