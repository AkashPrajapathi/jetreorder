plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)

    id("com.vanniktech.maven.publish")
}

android {

    namespace = "io.github.akashprajapathi.jetreorder"

    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 26

        consumerProguardFiles(
            "consumer-rules.pro"
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile(
                    "proguard-android-optimize.txt"
                ),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility =
            JavaVersion.VERSION_11

        targetCompatibility =
            JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(
        platform(
            libs.androidx.compose.bom
        )
    )

    implementation(
        libs.androidx.core.ktx
    )

    implementation(
        libs.androidx.compose.ui
    )

    implementation(
        libs.androidx.compose.foundation
    )

    implementation(
        libs.androidx.compose.runtime
    )

    implementation(
        libs.androidx.compose.animation
    )

    debugImplementation(
        libs.androidx.compose.ui.tooling
    )
}

mavenPublishing {

    publishToMavenCentral()

    signAllPublications()

    coordinates(
        "io.github.akashprajapathi",
        "jetreorder",
        "0.1.1"
    )

    pom {

        name.set("JetReorder")

        description.set(
            "Drag and drop reorder library for Jetpack Compose"
        )

        inceptionYear.set("2026")

        url.set(
            "https://github.com/AkashPrajapathi/jetreorder"
        )

        licenses {
            license {
                name.set("MIT")
                url.set("https://opensource.org/licenses/MIT")
            }
        }

        developers {
            developer {
                id.set("AkashPrajapathi")
                name.set("Akash Prajapathi")
            }
        }

        scm {
            url.set(
                "https://github.com/AkashPrajapathi/jetreorder"
            )
        }
    }
}