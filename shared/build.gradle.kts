import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.squareup.sqldelight")
    id("kotlinx-serialization")
}

kotlin {
    targets {
        android()
        iosArm64 {
            binaries {
                framework {
                    baseName = "shared"
                }
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}") {
                    version {
                        strictly(Versions.kotlinCoroutines)
                    }
                }
                implementation(Serialization.core)
                implementation(SqlDelight.runtime)
                implementation(Ktor.core)
                implementation(Ktor.json)
                implementation(Ktor.clientLogging)
                implementation(Ktor.clientSerialization)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Ktor.android)
                implementation("com.google.android.material:material:1.3.0")
                implementation(SqlDelight.android)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13")
            }
        }
        val iosArm64Main by getting {
            dependencies {
                implementation(Ktor.ios)
                implementation(SqlDelight.native)
            }
        }
        val iosArm64Test by getting
    }
}

android {
    compileSdkVersion(30)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(30)
    }
}

sqldelight {
    database("VideoPlaylistDatabase") {
        packageName = "com.bobbyprabowo.videoplaylist.db"
        sourceFolders = listOf("sqldelight")
        linkSqlite = true
    }
}


val packForXcode by tasks.creating(Sync::class) {
    group = "build"
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
//    val sdkName = System.getenv("SDK_NAME") ?: "iphonesimulator"
//    val targetName = "ios" + if (sdkName.startsWith("iphoneos")) "Arm64" else "X64"
    val targetName = "iosArm64"
    val framework = kotlin.targets.getByName<KotlinNativeTarget>(targetName).binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)
    val targetDir = File(buildDir, "xcode-frameworks")
    from({ framework.outputDirectory })
    into(targetDir)
}

tasks.getByName("build").dependsOn(packForXcode)