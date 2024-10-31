plugins {
    kotlin("jvm") version "1.9.23"
    // su funcion o proposito es faciitar la integracion entre Kotlin y JPA
    kotlin("plugin.jpa") version "1.7.22"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    // esta dependencia, nos permite mapear una clase y tener un crud en la base de datos sin tener que escribir sql
    //es decir podemos agregar eliminar o modificar, entidades de la base de datos sin tener que escribir instrucciones sql
    implementation("org.hibernate:hibernate-core:6.6.0.Final")
    // es la API estándar en Java para conectar aplicaciones con bases de datos.
    implementation("com.mysql:mysql-connector-j:8.3.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(18)
}