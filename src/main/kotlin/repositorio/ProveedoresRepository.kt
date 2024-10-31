package org.example.repositorio

import org.example.Model.Productos
import org.example.Model.Proveedor
import org.example.maganerFactory.EntityManagerFactory

class ProveedoresRepository {
    //getProveedorProducto: Este método está diseñado para obtener el
    //proveedor asociado a un producto específico, identificado por su
    //ID. El método devuelve el proveedor que suministra dicho
    //producto. La respuesta debe incluir el proveedor si la operación
    //fue exitosa.
    //o Parámetro:
    //▪ idProducto: Identificador del producto del cual se
    //desean obtener el proveedor.
    fun getProveedorProducto(idProducto: Int): Proveedor? {
        val em = EntityManagerFactory.generar()
        em.transaction.begin()
        val proveedor = em.find(Proveedor::class.java, idProducto)
        em.close()
        return proveedor
    }

    fun getTodosProveedorProducto(): MutableList<Proveedor>? {
        val em = EntityManagerFactory.generar()
        //try {
        em.transaction.begin()
        val producto = em.createQuery("FROM Proveedor WHERE", Proveedor::class.java).resultList
        em.transaction.commit()
        em.close()
        return producto
        //}catch (e:Exception){
        //    em.transaction.rollback()
        //    return null
        //}
    }
}