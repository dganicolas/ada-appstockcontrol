package org.example.operaciones

import org.example.Model.Productos
import org.example.Model.Proveedor
import org.example.Model.Usuarios
import org.example.consola.Consola
import org.example.maganerFactory.EntityManagerFactory
import org.example.servicio.ProductoService
import org.example.servicio.ProveedorService
import org.example.servicio.UsuarioService
import java.time.Instant
import java.util.*


class Operaciones(
    val productoService: ProductoService,
    val proveedorService: ProveedorService,
    val usuarioService: UsuarioService,
    val consola: Consola
) {
    var appfuncionando = true
    fun cargarBD() {
        val em = EntityManagerFactory.generar()
        val prov1 = Proveedor(null, "Nestlé", "Vevey, Suiza", null)
        val prov2 = Proveedor(null, "Procter & Gamble", "Cincinnati, Estados Unidos", null)
        val prov3 = Proveedor(null, "Unilever", "Londres, Reino Unido", null)
        val prov4 = Proveedor(null, "PepsiCo", "Nueva York, Estados Unidos", null)
        val prov5 = Proveedor(null, "Coca-Cola", "Atlanta, Estados Unidos", null)
        val prod1 = Productos(
            "prod001",
            "Alimentos",
            "KitKat",
            "Barra",
            1.2f,
            1.44f,
            Date.from(Instant.now()),
            50,
            prov1
        )
        val prod2 = Productos(
            "prod002",
            "Cuidado",
            "Pampers",
            "Pañales",
            5.0f,
            6.0f,
            Date.from(Instant.now()),
            30,
            prov2
        )
        val prod3 = Productos(
            "prod003",
            "Cuidado",
            "Dove",
            "Jabón",
            2.0f,
            2.4f,
            Date.from(Instant.now()),
            100,
            prov3
        )
        val prod4 =
            Productos(
                "prod004",
                "Bebidas",
                "Pepsi",
                "Bebida",
                1.5f,
                1.8f,
                Date.from(Instant.now()),
                200,
                prov4
            )
        val prod5 = Productos(
            "prod005",
            "Bebidas",
            "Coca-Cola",
            "Bebida",
            1.5f,
            1.8f,
            Date.from(Instant.now()),
            250,
            prov5
        )
        val prod6 = Productos(
            "prod006",
            "Alimentos",
            "Maggi",
            "Sopa",
            0.8f,
            0.96f,
            Date.from(Instant.now()),
            150,
            prov1
        )
        val prod7 = Productos(
            "prod007",
            "Cuidado",
            "Head & Shoulders",
            "Shampoo anticaspa",
            3.5f,
            4.2f,
            Date.from(Instant.now()),
            80,
            prov2
        )
        val prod8 = Productos(
            "prod008",
            "Cuidado",
            "Axe",
            "Desodorante",
            4.0f,
            4.8f,
            Date.from(Instant.now()),
            60,
            prov3
        )
        val prod9 = Productos(
            "prod009",
            "Bebidas",
            "Gatorade",
            "Bebida isotónica",
            1.8f,
            2.16f,
            Date.from(Instant.now()),
            90,
            prov4
        )
        val prod10 = Productos(
            "prod010",
            "Bebidas",
            "Fanta",
            "Bebida gaseosa",
            1.4f,
            1.68f,
            Date.from(Instant.now()),
            120,
            prov5
        )
        val usu = Usuarios("nico", "1234")
        em.transaction.begin()
        em.persist(prov1)
        em.persist(prov2)
        em.persist(prov3)
        em.persist(prov4)
        em.persist(prov5)
        em.persist(prod1)
        em.persist(prod2)
        em.persist(prod3)
        em.persist(prod4)
        em.persist(prod5)
        em.persist(prod6)
        em.persist(prod7)
        em.persist(prod8)
        em.persist(prod9)
        em.persist(prod10)
        em.persist(usu)
        em.transaction.commit()
        em.close()
    }

    fun login() {
        cargarBD()
        var users: Usuarios?
        do {
            consola.imprimir(
                "Bienvenido al programa control de stock\n" +
                        "por favor verifiquese"
            )

            consola.imprimir("dime tu usuario:", false)
            val usuario = consola.input()

            consola.imprimir("dime tu contraseña:", false)
            val pass = consola.input()

            users = usuarioService.validarRegistro(usuario, pass)

            if (users == null) {
                finalizarAccion("Usuario o contraseña incorrectos\n escribe exit para terminar el programa")
            } else {
                consola.imprimir("Bienvenido señor ${users.nombreUsuario}\n Cargando su base de datos...")
            }
        } while (users == null && appfuncionando)
        menu()
    }

    fun finalizarAccion(mensaje: String) {
        consola.imprimir(mensaje)
        val salir = consola.input()
        if (salir == "exit") {
            appfuncionando = false
        }
    }

    fun menu() {
        while (appfuncionando) {
            consola.imprimir(
                "¿Qué quieres hacer?\n" +
                        "(1) Crear un nuevo producto\n" +
                        "(2) Eliminar un producto\n" +
                        "(3) Modificar el nombre de un producto\n" +
                        "(4) Modificar el stock de un producto\n" +
                        "(5) Obtener información de un producto\n" +
                        "(6) Obtener productos con stock\n" +
                        "(7) Obtener productos sin stock\n" +
                        "(8) Obtener el proveedor de un producto\n" +
                        "(9) Obtener todos los proveedores\n" +
                        "(0) Salir"
            )
            val opcion = consola.input()
            when (opcion) {
                "1" -> altaProducto()
                "2" -> bajaProducto()
                "3" -> modificarNombreProducto()
                "4" -> modificarStockProducto()
                "5" -> getProducto()
                "6" -> getProductosConStock()
                "7" -> getProductosSinStock()
                "8" -> getProveedorProducto()
                "9" -> getTodosProveedores()
                "0" -> appfuncionando = false
                else -> consola.imprimir("Opcion desconocida")
            }
        }
        if (!appfuncionando) {
            consola.imprimir("programa terminado...")
        }
    }

    private fun obtenerProveedor(): Proveedor? {
        var proveedor: Proveedor?
        do {
            consola.imprimir("Dime el Id del proveedor del producto nuevo a registrar")
            val id = consola.input().toIntOrNull()
            proveedor = proveedorService.getProveedor(id ?: 0)
            if (proveedor == null) {
                consola.imprimir(
                    "ERROR ese id de proveedor no existe\n" +
                            "si desea omitir el alta producto, escriba exit"
                )
                val salir = consola.input()
                if (salir == "exit") {
                    return null
                }
            }
        } while (id == null || proveedor == null)
        return proveedor
    }

    private fun resultadoAltaProducto(producto: Productos) {
        if (producto.id != "ERROR000x20x3NDG2") {
            consola.imprimir("Se ha registrado un nuevo producto")
        } else {
            consola.imprimir("se omitio la accion de añadir producto")
        }
    }

    fun altaProducto() {
        val proveedor: Proveedor? = obtenerProveedor()
        if (proveedor != null) {
            val producto = productoService.crearProducto(consola, proveedor)
            if (producto != null) {
                resultadoAltaProducto(producto)
            } else {
                consola.imprimir("ERROR*** No se registro el producto comprueba si la BBDD esta operativa")
            }
        } else {
            consola.imprimir("se anulo la accion alta producto")
        }
    }

    fun bajaProducto() {
        var producto: Productos?
        var estado = true
        var id: String
        do {
            consola.imprimir("Dime el Id del producto")
            id = consola.input()
            producto = productoService.getProducto(id)
            if (producto == null) {
                consola.imprimir(
                    "ERROR ese id de proveedor no existe\n" +
                            "si desea omitir el eliminar un  producto, escriba exit"
                )
                val salir = consola.input()
                if (salir == "exit") {
                    estado = false
                    break
                }
            }
        } while (producto == null)
        if (estado) {
            if (productoService.deleteProducto(id)) {
                consola.imprimir("Se ha eliminado un producto")
            } else {
                consola.imprimir("ERROR*** No se eliminado el producto comprueba si la BBDD esta operativa")
            }
        } else {
            consola.imprimir("se omitio la accion de eliminar producto")
        }
    }

    fun modificarNombreProducto() {
        var producto: Productos?
        var id: String
        var estado = true
        do {
            consola.imprimir("Dime el Id del producto a modificar el nombre")
            id = consola.input()
            producto = productoService.getProducto(id)
            if (producto == null) {
                consola.imprimir(
                    "ERROR ese id de producto no existe\n" +
                            "si desea omitir el eliminar un  producto, escriba exit"
                )
                val salir = consola.input()
                if (salir == "exit") {
                    estado = false
                    break
                }
            }
        } while (producto == null)
        if (estado) {
            consola.imprimir("Dime el nombre del producto")
            val nombre = consola.input()
            if (productoService.productoNombreModificar(id, nombre)) {
                consola.imprimir("Se ha modificado un producto")
            } else {
                consola.imprimir("ERROR*** No se modificado el producto comprueba si la BBDD esta operativa")
            }
        } else {
            consola.imprimir("se omitio la accion de modificar nombre")
        }
    }

    fun modificarStockProducto() {
        var producto: Productos?
        var id: String
        var estado = true
        do {
            consola.imprimir("Dime el Id del producto a modificar el stock")
            id = consola.input()
            producto = productoService.getProducto(id)
            if (producto == null) {
                consola.imprimir(
                    "ERROR ese id de producto no existe\n" +
                            "si desea omitir el modifcar stock, escriba exit"
                )
                val salir = consola.input()
                if (salir == "exit") {
                    estado = false
                    break
                }
            }
        } while (producto == null)
        if (estado) {
            var stock: Int?
            do {
                consola.imprimir("Dime el stock del producto")
                stock = readln().toIntOrNull()
                if (stock == null) {
                    consola.imprimir("ERROR*** DAME UN NUMERO ENTERO")
                    consola.imprimir(
                        "ERROR dame un numero entero\n" +
                                "si desea omitir el emodifcar stock, escriba exit"
                    )
                    val salir = consola.input()
                    if (salir == "exit") {
                        estado = false
                        break
                    }
                }
            } while (stock == null)
            if (estado) {
                if (stock != null)
                    if (productoService.productoStockModificar(id, stock)) {
                        consola.imprimir("Se ha modificado un producto")
                    } else {
                        consola.imprimir("ERROR*** No se modificado el producto comprueba si la BBDD esta operativa")
                    }
            } else {
                consola.imprimir("se omitio la accion de modificar stock")
            }
        } else {
            consola.imprimir("se omitio la accion de modificar stock")
        }
    }

    fun getProducto() {
        consola.imprimir("Dime el Id de un producto")
        val id = consola.input()
        val producto = productoService.getProducto(id)
        if (producto != null) {
            consola.imprimir(" el producto seleccionado es\n $producto")
        } else {
            consola.imprimir("ese producto no existe")
        }
    }

    fun getProductosConStock() {
        val lista = productoService.getProductosConStock()
        if (lista != null) {
            if (lista.isNotEmpty()) {
                var contador = 0
                lista.forEach {
                    consola.imprimir("\n-----Producto (${++contador})-----")
                    consola.imprimir(it.toString())
                }
            } else {
                consola.imprimir("no hay productos con stocks, compra productos")
            }
        } else {
            consola.imprimir("hubo un error")
        }
    }

    fun getProductosSinStock() {
        val lista = productoService.getProductosSinStock()
        if (lista != null) {
            if (lista.isNotEmpty()) {
                var contador = 0
                lista.forEach {
                    consola.imprimir("\n-----Producto sin stock (${++contador})-----")
                    consola.imprimir(it.toString())
                }
            } else {
                consola.imprimir("no hay productos sin stocks")
            }
        } else {
            consola.imprimir("hubo un error")
        }
    }

    fun getProveedorProducto() {
        var producto: Productos?
        do {
            consola.imprimir("dime el id del producto a sacar le proveedor asociado")
            val id = consola.input()
            producto = productoService.getProducto(id)
            if (producto == null) {
                consola.imprimir("el producto no existe")
            }
        } while (producto == null)
        val proveedor = proveedorService.getProveedorProducto(producto.proveedor.id?.toInt() ?: 0)
        if (proveedor != null) {
            consola.imprimir(proveedor.toString())
        }
    }

    fun getTodosProveedores() {
        val proveedores: MutableList<Proveedor>? = proveedorService.getTodosProveedorProducto()
        if (proveedores == null) {
            consola.imprimir("error***")
        } else {
            proveedores.forEach {
                consola.imprimir(it.toString())
            }
        }

    }
}