package org.example.Model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

//Tabla Usuarios
//• nombre_usuario: String -> PrimaryKey
//• password: String -> not null, longitud 20

// todas las demas etiquetas estan explicada en proveedor
@Entity
@Table(name="Usuarios")
data class Usuarios (
    @Id
    @Column(name="usuario")
    val nombreUsuario: String,

    @Column(name="contraseña", nullable = false, length = 20)
    val password: String
)