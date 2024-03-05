import org.jetbrains.compose.ComposePlugin.CommonComponentsDependencies.resources
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.21"
    id("org.jetbrains.compose") version "1.5.11"
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile>().configureEach {
    jvmTargetValidationMode.set(org.jetbrains.kotlin.gradle.dsl.jvm.JvmTargetValidationMode.WARNING)
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation("com.mysql:mysql-connector-j:8.3.0")
    implementation("com.darkrockstudios:mpfilepicker:3.1.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "21"
        freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
    }
}
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}
compose.desktop {
    application {
        mainClass = "AppKt"
        jvmArgs("-Dskiko.rendering.laf.global=true")
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            modules("java.instrument" , "java.sql", "jdk.unsupported")
            packageName = "PearlTeeth"
            packageVersion = "1.0.0"
        }
    }
}
compose.desktop {
    application {
        mainClass = "AppKt" // Asegúrate de que la ruta de la clase principal sea correcta.
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb) // Define los formatos de distribución que necesitas.

            // Configuración específica para Windows
            windows {
                // Aquí puedes especificar configuraciones adicionales específicas de Windows
            }

            // Configuración específica para macOS
            macOS {
                // Aquí puedes especificar configuraciones adicionales específicas de macOS
            }

            // Configuración específica para Linux
            linux {
                // Aquí puedes especificar configuraciones adicionales específicas de Linux
            }

            // Añade la configuración del JVM que se incluirá con tu aplicación
            jvmArgs += listOf(
                // Argumentos del JVM que deseas incluir
                "-Xmx8G", // Ejemplo: Establecer el tamaño máximo del heap a 4 GB
                "-Xms2G"  // Ejemplo: Establecer el tamaño inicial del heap a 2 GB
            )

            modules("java.instrument", "java.sql", "jdk.unsupported") // Asegúrate de que los módulos requeridos estén incluidos.

            packageName = "PearlTeeth" // Define el nombre del paquete de tu aplicación.
            packageVersion = "1.0.0" // Define la versión del paquete de tu aplicación.
        }
    }
}
