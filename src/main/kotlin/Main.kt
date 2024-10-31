package org.example


import org.example.operaciones.Operaciones
import org.example.repositorio.ProductosRepository
import org.example.repositorio.ProveedoresRepository
import org.example.repositorio.UsuariosRepository
import org.example.consola.Consola
import org.example.servicio.ProductoService
import org.example.servicio.ProveedorService
import org.example.servicio.UsuarioService

fun main() {

    val repositoryUsuarios = UsuariosRepository()
    val usuariosService = UsuarioService(repositoryUsuarios)
    val productosRepository = ProductosRepository()
    val productoService = ProductoService(productosRepository)
    val proveedoresRepository = ProveedoresRepository()
    val proveedorService = ProveedorService(proveedoresRepository)
    val consola = Consola()
    val menu = Operaciones(productoService, proveedorService, usuariosService, consola)
    menu.login()

}