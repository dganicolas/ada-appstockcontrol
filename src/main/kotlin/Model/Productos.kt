package org.example.Model

import jakarta.persistence.*
import java.util.*

//Tabla Productos
//• id: String -> PrimaryKey -> Lo generamos nosotros --
//• categoria: String -> not null, longigud 10 --
//• nombre: String -> longitud 50, not null --
//• descripcion: String --
//• precio_sin_iva: float -> not null --
//• precio_con_iva: float -> not null --
//• fecha_alta: Date -> not null --
//• stock: int -> not null --
//• proveedor: Proveedor -> Relación de @ManyToOne --

// todas las demas etiquetas estan explicada en proveedor
@Entity
@Table(name = "Productos")
class Productos(

    @Id
    var id: String,

    @Column(name = "categoria", nullable = false, length = 10)
    var categoria: String,

    @Column(name = "nombre", nullable = false, length = 50)
    var nombre: String,

    @Column(name = "descripcion")
    val descripcion: String,

    @Column(name = "precio_sin_iva", nullable = false)
    val precioSinIva: Float,

    @Column(name = "precio_con_iva", nullable = false)
    var precioConIva: Float,

    @Column(name = "fecha_alta", nullable = false)
    val fechaAlta: Date,

    @Column(name = "stock", nullable = false)
    var stock: Int,

    //esta declaracion indica que muchos productos pueden poseer a un proveedor
    //y el join column es la clave foranea donde apunta
    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "productos")
    var proveedor: Proveedor
) {
    override fun toString(): String {
        return "ID: $id\n" +
                " Nombre: $nombre\n" +
                " Categoría: $categoria\n" +
                " Descripción: $descripcion\n" +
                " Precio Sin IVA: $precioSinIva\n" +
                " Precio Con IVA: $precioConIva\n" +
                " Fecha de Alta: $fechaAlta\n" +
                " Stock: $stock\n" +
                " Proveedor: ${proveedor.nombre}"
    }
}