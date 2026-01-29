
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
//    alias(libs.plugins.composeHotReload)

    alias(libs.plugins.ksp)
}


kotlin {
    js(IR){
        browser {
            commonWebpackConfig {
                outputFileName = "openAnimation.js"
            }
        }
        binaries.executable()
    }
    androidLibrary {
        namespace = "org.osg.openanimation.app"
        compileSdk = libs.versions.android.targetSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
        withJava()
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
        androidResources {
            enable = true
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    jvm()
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
        }
        commonMain {
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
            dependencies {
                implementation(projects.core.data)
                implementation(projects.core.ui)
                implementation(libs.runtime)
                implementation(libs.foundation)
                implementation(libs.ui)
                implementation(libs.components.resources)

                implementation(libs.material3)
                implementation(libs.material3.navigation.suite)
                implementation(libs.androidx.navigation.compose)

                implementation(libs.material.icons.core)
                implementation(libs.androidx.lifecycle.viewmodel)
                implementation(libs.androidx.lifecycle.runtime.compose)
                implementation(project.dependencies.platform(libs.koin.bom))
                implementation(libs.koin.core)
                implementation(libs.ui.tooling.preview)
            }
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}

ksp {
    arg("KOIN_USE_COMPOSE_VIEWMODEL","true")
    arg("KOIN_CONFIG_CHECK","true")
    arg("KOIN_DEFAULT_MODULE","true")
}

dependencies {
    add("kspCommonMainMetadata", libs.koin.ksp.compiler)
}


project.tasks.withType(KotlinCompilationTask::class.java).configureEach {
    if(name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}


afterEvaluate {
    // to avoid kotlinStorePackageLock failing
    val kotlinUpgradePackageLock = tasks.findByPath("::kotlinUpgradePackageLock")!!
    tasks.findByPath("::kotlinNpmInstall")!!.finalizedBy(kotlinUpgradePackageLock)
}

compose.desktop {
    application {
        mainClass = "org.osg.openanimation.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "org.osg.openanimation"
            packageVersion = "1.0.0"
        }
    }
}
