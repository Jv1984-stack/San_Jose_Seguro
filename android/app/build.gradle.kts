// ┌──────────────────────────────────────────────────────────────┐
// │ Configuración de Gradle para la app Android                  │
// │                                                              │
// │ ✅ Incluye plugins esenciales: Flutter, Kotlin, Google Services │
// │ ✅ Configura Firebase mediante BoM (Bill of Materials)       │
// │ ✅ Define identidad única de la app (applicationId)          │
// └──────────────────────────────────────────────────────────────┘

plugins {
    id("com.android.application")
    id("kotlin-android")
    // 🔹 Plugin de Google Services: procesa google-services.json
    id("com.google.gms.google-services")
    // The Flutter Gradle Plugin must be applied after the Android and Kotlin Gradle plugins.
    id("dev.flutter.flutter-gradle-plugin")
}

android {
    namespace = "com.example.san_jose_seguro"
    compileSdk = flutter.compileSdkVersion
    ndkVersion = flutter.ndkVersion

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    defaultConfig {
        // TODO: Specify your own unique Application ID (https://developer.android.com/studio/build/application-id.html)
        applicationId = "com.example.san_jose_seguro"
        // You can update the following values to match your application needs.
        // For more information, see: https://flutter.dev/to/review-gradle-config.
        minSdk = flutter.minSdkVersion
        targetSdk = flutter.targetSdkVersion
        versionCode = flutter.versionCode
        versionName = flutter.versionName
    }

    buildTypes {
        release {
            // TODO: Add your own signing config for the release build.
            // Signing with the debug keys for now, so `flutter run --release` works.
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}

// ┌──────────────────────────────────────────────────────────────┐
// │ DEPENDENCIAS DE FIREBASE                                     │
// │                                                              │
// │ ✅ Se usa Firebase BoM (Bill of Materials) para garantizar   │
// │    compatibilidad entre versiones de los SDKs.               │
// │ ✅ No se especifican versiones individuales (gestionadas por BoM). │
// └──────────────────────────────────────────────────────────────┘
dependencies {
    // 🔹 Importa el Firebase BoM (Bill of Materials)
    // Esto asegura que todas las bibliotecas de Firebase usen versiones compatibles.
    implementation(platform("com.google.firebase:firebase-bom:34.4.0"))

    // 🔹 Dependencias de Firebase usadas en san_jose_seguro
    // Estas se alinean con los plugins de Flutter: firebase_core, firebase_auth, etc.
    implementation("com.google.firebase:firebase-analytics") // Opcional, pero recomendado
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.firebase:firebase-storage")
    implementation("com.google.firebase:firebase-messaging")
    // implementation("com.google.firebase:firebase-core") // Incluido automáticamente
}

flutter {
    source = "../.."
}