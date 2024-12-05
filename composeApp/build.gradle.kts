import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig
import java.util.regex.Pattern

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.buildKonfig)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    jvm("desktop")

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.navigation.compose)
            implementation(libs.compose.adaptive)
            implementation(libs.kotlinx.coroutines)
            implementation(libs.coil.network.ktor)
            implementation(libs.bundles.ktor)
            implementation(libs.coil.compose)
            api(libs.koin.core)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.okio)
            implementation(libs.multiplatformSettings.no.arg)
        }
        commonTest.dependencies {
            implementation(kotlin("test-annotations-common"))
            implementation(libs.kotlin.test)
            implementation(libs.multiplatformSettings.test)
            @OptIn(ExperimentalComposeLibrary::class) implementation(compose.uiTest)
        }

        val desktopMain by getting

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android)
            implementation(libs.koin.compose)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.androidx.core.splashscreen)
            implementation(libs.androidx.room.runtime)
            implementation(libs.sqlite.bundled)
        }

        androidUnitTest.dependencies {
            implementation(libs.mockk)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.androidx.room.runtime)
            implementation(libs.sqlite.bundled)
        }

        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.androidx.room.runtime)
            implementation(libs.sqlite.bundled)
        }

        dependencies {
            ksp(libs.androidx.room.compiler)
        }

        room {
            schemaDirectory("$projectDir/schemas")
        }
    }
}

android {
    namespace = "compose.project.demo"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "compose.project.demo"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }


    flavorDimensions.add("variant")
    productFlavors {
        create("Dev") {
            dimension = "variant"
            applicationIdSuffix = ".dev"
            versionNameSuffix = " Dev"
            resValue("string", "app_name_variant", "ComposeMultiPlatform")
        }

        create("Prod") {
            isDefault = true
            dimension = "variant"
            resValue("string", "app_name_variant", "ComposeMultiPlatform")
        }
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "compose.project.demo.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "compose.project.demo"
            packageVersion = "1.0.0"
        }
    }
}

// Ref: https://sujanpoudel.me/blogs/managing-configurations-for-different-environments-in-kmp/
project.extra.set("buildkonfig.flavor", currentBuildVariant())
buildkonfig {
    packageName = "ComposeMultiPlatform"
    objectName = "CMPConfig"
    exposeObjectWithName = "CMPConfig"

    defaultConfigs {

    }

    defaultConfigs("Dev") {

    }

    defaultConfigs("Prod") {

    }
}

fun Project.getAndroidBuildVariantOrNull(): String? {
    val variants = setOf("Dev", "Prod")
    val taskRequestsStr = gradle.startParameter.taskRequests.toString()
    val pattern: Pattern = if (taskRequestsStr.contains("assemble")) {
        Pattern.compile("assemble(\\w+)(Release|Debug)")
    } else {
        Pattern.compile("bundle(\\w+)(Release|Debug)")
    }

    val matcher = pattern.matcher(taskRequestsStr)
    val variant = if (matcher.find()) matcher.group(1) else null
    return if (variant in variants) {
        variant
    } else {
        null
    }
}

private fun Project.currentBuildVariant(): String {
    val variants = setOf("Dev", "Prod")
    return getAndroidBuildVariantOrNull() ?: System.getenv()["VARIANT"].toString()
        .takeIf { it in variants } ?: "Dev"
}