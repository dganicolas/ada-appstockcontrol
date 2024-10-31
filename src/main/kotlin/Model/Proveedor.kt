package org.example.Model

import jakarta.persistence.*

//Tabla Proveedores
//• id: Long -> AutoGenerado. PrimaryKey
//• nombre: String -> único, longitud 50, not null
//• direccion: String -> not null
//• productos: List -> Relacion de @OneToMany

//aqui declaro que esta clase sera una entidad de hibernate y la tabla se llamara Proveedores
@Entity
@Table(name = "Proveedores")
data class Proveedor(

    //aqui declaro que sea la clave principal, donde el numero sera autoincremental
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    //esta columna de la tabla sera que no puede estar vacio y que este un maximo de 50 caracteres
    @Column(name = "nombre", nullable = false, length = 50)
    val nombre: String,

    //esta columna de la tabla sera que no puede estar vacio
    @Column(name = "direccion", nullable = false)
    val direccion: String,

    //esta columna, es un
    @Column(name = "productos")
    //aqui declaro que sea una relacion de uno a muchos
    @OneToMany(mappedBy = "proveedor", fetch = FetchType.EAGER)
    val productos: List<Productos>?


) {
    override fun toString(): String {
        return "ID: $id\n" +
                "Nombre: $nombre\n" +
                "Dirección: $direccion\n" +
                "Productos: ${productos?.joinToString(", ") { it.nombre } ?: "Sin productos"}"
    }
}