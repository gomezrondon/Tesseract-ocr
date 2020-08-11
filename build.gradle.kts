import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.3.2.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	kotlin("jvm") version "1.3.72"
	kotlin("plugin.spring") version "1.3.72"
}

group = "com.gomezrondon"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()


	maven {
		url = uri("https://jitpack.io")
	}


}

dependencies {

	//translator
	//https://www.youtube.com/watch?v=-AMoR_WPV_M
	//https://github.com/goxr3plus/java-google-translator/blob/master/pom.xml
	implementation("com.github.goxr3plus:java-google-speech-api:8.0.0")

	//OCR
	implementation("net.sourceforge.tess4j:tess4j:4.2.1")

	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}
