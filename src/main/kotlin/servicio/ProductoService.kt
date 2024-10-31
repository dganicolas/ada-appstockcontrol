package org.example.servicio

import org.example.Model.Productos
import org.example.Model.Proveedor
import org.example.repositorio.ProductosRepository
import org.example.consola.Consola
import java.time.Instant
import java.util.*

//Producto
//• id: El id se compone por: 3 primeras letras de categoria + 3
//primeras letras de nombre + 3 primeras letras de proveedor--
//• categoria: longitud 50, not null--
//• nombre: longitud 50, not null --
//• precio_sin_iva: not null
//• precio_con_iva: not null. Calcular el precio aplicando el IVA
//sobre el precio sin iva
//• fecha_alta: fecha de hoy
class ProductoService(val productosRepository: ProductosRepository) {

    var crearProducto = true

    fun getProducto(id: String): Productos? {
        return productosRepository.getProducto(id)
    }

    fun getProductosConStock(): MutableList<Productos>? {
        return productosRepository.getProductosConStock()
    }

    fun getProductosSinStock(): MutableList<Productos>? {
        return productosRepository.getProductosSinStock()
    }

    fun productoStockModificar(id: String, stock: Int): Boolean {
        return productosRepository.productoStockModificar(id, stock)
    }

    fun productoNombreModificar(id: String, nombre: String): Boolean {
        return productosRepository.productoNombreModificar(id, nombre)
    }

    fun deleteProducto(id: String): Boolean {
        return productosRepository.deleteProducto(id)
    }

    private fun validarNombre(nombre: String): Boolean {
        if (
            nombre.isBlank() ||
            nombre.length > 50
        ) {
            return false
        }
        return true
    }

    private fun validarCategoria(categoria: String): Boolean {
        if (
            categoria.isBlank() ||
            categoria.length > 10
        ) {
            return false
        }
        return true
    }

    private fun validarPrecio(precio: Float?): Boolean {
        if (precio != null) {
            return true
        }
        return false
    }

    private fun validarStock(stocks: Int?): Boolean {
        if (stocks == null) {
            return false
        }
        return true
    }

    private fun crearNombre(consola: Consola): String {
        var nombre: String
        if (crearProducto) {
            do {
                consola.imprimir("¿Ingrese el nombre del producto?")
                nombre = consola.input()
                if (!validarNombre(nombre)) {
                    consola.imprimir("***ERROR** EL NOMBRE DEBE DE CONSTAR DE MAXIMO DE 50 CARACTERES Y NO PUEDE ESTAR VACIO")
                    noCrearProducto(consola)
                }
            } while (!validarNombre(nombre) && crearProducto)
            return nombre
        } else return ""
    }

    private fun crearPrecio(consola: Consola): Float? {
        var precio: Float?
        if (crearProducto) {
            do {
                consola.imprimir("¿Ingrese el precio del producto?")
                precio = consola.input().toFloatOrNull()
                if (!validarPrecio(precio)) {
                    consola.imprimir("***ERROR** EL PRECIO DEBE DE SER UN NUMERO")
                    noCrearProducto(consola)
                }
            } while (!validarPrecio(precio) && crearProducto)
            return precio
        } else return 0.0f

    }

    private fun crearDescripcion(consola: Consola): String {
        var estado = true
        var descripcion: String
        if (crearProducto) {
            do {
                consola.imprimir("¿Ingrese la descripcion del producto?")
                descripcion = consola.input()
                if (descripcion.isBlank()) {
                    consola.imprimir("seguro que no quieres que el producto\n no tenga descripcion\n(1)si\n(2)no")
                    val respuesta = consola.input()
                    if (respuesta.lowercase() == "no" || respuesta == "2") {
                        estado = false
                    }
                } else {
                    estado = false
                }
            } while (estado && crearProducto)
            return descripcion
        } else return ""

    }

    private fun crearCategoria(consola: Consola): String {
        var categoria: String
        if (crearProducto) {
            do {
                consola.imprimir("¿Ingrese la categoria del producto?")
                categoria = consola.input()
                if (!validarCategoria(categoria)) {
                    consola.imprimir("***ERROR** LA CATEGORIA DEBE DE SER MENOR DE 10 CARACTERES Y NO PUEDE ESTAR VACIO")
                    noCrearProducto(consola)
                }
            } while (!validarCategoria(categoria) && crearProducto)
            return categoria
        } else return ""
    }

    private fun crearStocks(consola: Consola): Int? {
        var stocks: Int?
        if (crearProducto) {
            do {
                consola.imprimir("¿Ingrese el stocks del producto?")
                stocks = consola.input().toIntOrNull()
                if (!validarStock(stocks)) {
                    consola.imprimir("***ERROR** EL STOCK DEBE DE SER UN NUMERO")
                    noCrearProducto(consola)
                }
            } while (!validarStock(stocks) && crearProducto)
            return stocks
        } else return 0
    }

    private fun noCrearProducto(consola: Consola) {
        consola.imprimir("si desea omitir el alta producto, escriba exit")
        val salir = consola.input()
        if (salir == "exit") {
            crearProducto = false
        }
    }

    fun crearProducto(consola: Consola, proveedor: Proveedor): Productos? {
        consola.imprimir("Vamos a dar de alta un producto")
        var nombre: String = crearNombre(consola)
        var precio: Float? = crearPrecio(consola)
        val descripcion: String = crearDescripcion(consola)
        val categoria: String = crearCategoria(consola)
        var stocks: Int? = crearStocks(consola)
        val idProducto = categoria.take(3) + nombre.take(3) + proveedor.nombre.take(3)
        precio = precio ?: 0.0f
        stocks = stocks ?: 0

        if (crearProducto) {
            return productosRepository.anadirProducto(
                Productos(
                    idProducto,
                    categoria,
                    nombre,
                    descripcion,
                    precio,
                    precio * 1.21f,
                    Date.from(Instant.now()),
                    stocks,
                    proveedor
                )
            )
        } else {
            crearProducto = true
            return Productos(
                "ERROR000x20x3NDG2",
                categoria,
                nombre,
                descripcion,
                precio,
                precio * 1.21f,
                Date.from(Instant.now()),
                stocks,
                proveedor
            )
        }

    }
}