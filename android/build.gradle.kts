// ┌──────────────────────────────────────────────────────────────┐
// │ Archivo de configuración raíz de Gradle para Android         │
// │                                                              │
// │ ✅ Define repositorios, plugins y configuración global       │
// │ ✅ Incluye el plugin de Google Services para Firebase        │
// └──────────────────────────────────────────────────────────────┘

// ──────────────────────────────────────────────────────────────
// PLUGINS RAÍZ (aplicados con 'apply false' para uso en subproyectos)
// ──────────────────────────────────────────────────────────────
// Estos plugins se declaran aquí para que puedan ser usados
// en los subproyectos (como 'app') sin duplicar versiones.
plugins {
    id("com.android.application") apply false
    id("org.jetbrains.kotlin.android") apply false
    // 🔹 Plugin esencial para integrar Firebase en Android
    id("com.google.gms.google-services") version "4.4.1" apply false
}

// ──────────────────────────────────────────────────────────────
// REPOSITORIOS GLOBALES
// ──────────────────────────────────────────────────────────────
allprojects {
    repositories {
        google()      // Repositorio oficial de Google (Firebase, AndroidX, etc.)
        mavenCentral() // Repositorio central de librerías de Java/Kotlin
    }
}

// ──────────────────────────────────────────────────────────────
// CONFIGURACIÓN PERSONALIZADA DE DIRECTORIOS DE CONSTRUCCIÓN
// ──────────────────────────────────────────────────────────────
// Mueve la carpeta 'build' al nivel raíz del proyecto Flutter
// para mantener la estructura limpia y compatible con Flutter.
val newBuildDir: Directory =
    rootProject.layout.buildDirectory
        .dir("../../build")
        .get()
rootProject.layout.buildDirectory.value(newBuildDir)

subprojects {
    val newSubprojectBuildDir: Directory = newBuildDir.dir(project.name)
    project.layout.buildDirectory.value(newSubprojectBuildDir)
}

// ──────────────────────────────────────────────────────────────
// DEPENDENCIAS ENTRE SUBPROYECTOS
// ──────────────────────────────────────────────────────────────
// Asegura que el subproyecto ':app' se evalúe antes que otros
subprojects {
    project.evaluationDependsOn(":app")
}

// ──────────────────────────────────────────────────────────────
// TAREA DE LIMPIEZA PERSONALIZADA
// ──────────────────────────────────────────────────────────────
// Elimina la carpeta 'build' personalizada al ejecutar './gradlew clean'
tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}