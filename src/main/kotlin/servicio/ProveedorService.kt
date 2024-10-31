package org.example.servicio

import org.example.Model.Proveedor
import org.example.repositorio.ProveedoresRepository

//Proveedor
//• nombre: único, longitud 50, not null
//• direccion: not null
class ProveedorService(val proveedoresRepository: ProveedoresRepository) {

    fun getProveedor(id: Int): Proveedor? {
        return proveedoresRepository.getProveedorProducto(id)
    }

    fun getProveedorProducto(idProducto: Int): Proveedor? {
        return proveedoresRepository.getProveedorProducto(idProducto)
    }

    fun getTodosProveedorProducto(): MutableList<Proveedor>? {
        return proveedoresRepository.getTodosProveedorProducto()
    }
}