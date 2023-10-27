plugins {
	id("com.android.application")
	id("org.jetbrains.kotlin.android")
	id("kotlin-kapt")
	id("org.jetbrains.kotlin.plugin.serialization") version "1.9.20-RC2"
}

android {
	namespace = "com.example.llapp"
	compileSdk = 34

	defaultConfig {
		applicationId = "com.example.llapp"
		minSdk = 24
		targetSdk = 31
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
	kotlinOptions {
		jvmTarget = "1.8"
	}
	buildFeatures {
		viewBinding = true
		dataBinding = true
	}
}

dependencies {
	val ktor_ver = "1.6.3"
	implementation ("io.ktor:ktor-client-android:$ktor_ver")
	implementation ("io.ktor:ktor-client-core:$ktor_ver")
	implementation ("io.ktor:ktor-client-serialization:$ktor_ver")
	implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

	implementation ("com.google.android.material:material:1.10.0")
	implementation("androidx.core:core-ktx:1.9.0")
	implementation("androidx.appcompat:appcompat:1.6.1")
	implementation("com.google.android.material:material:1.10.0")
	implementation("androidx.constraintlayout:constraintlayout:2.1.4")
	implementation("androidx.datastore:datastore-preferences:1.0.0")
	implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
	implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
	implementation("androidx.navigation:navigation-fragment-ktx:2.7.4")
	implementation("androidx.navigation:navigation-ui-ktx:2.7.4")
	implementation("androidx.navigation:navigation-common:2.7.4")
	testImplementation("junit:junit:4.13.2")
	androidTestImplementation("androidx.test.ext:junit:1.1.5")
	androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}