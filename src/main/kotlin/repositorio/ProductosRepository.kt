package org.example.repositorio

import org.example.Model.Productos
import org.example.maganerFactory.EntityManagerFactory

class ProductosRepository {
    fun anadirProducto(producto: Productos): Productos? {
        val em = EntityManagerFactory.generar()
        try {
            em.transaction.begin()
            em.persist(producto)
            em.transaction.commit()
            em.close()
            return producto
        } catch (e: Exception) {
            em.transaction.rollback()
            return null
        }

    }

    fun getProducto(idProducto: String): Productos? {
        val em = EntityManagerFactory.generar()
        em.transaction.begin()
        val producto = em.find(Productos::class.java, idProducto)
        em.close()
        return producto
    }

    fun deleteProducto(id: String): Boolean {
        val em = EntityManagerFactory.generar()
        try {
            em.transaction.begin()
            em.remove(em.find(Productos::class.java, id))
            em.transaction.commit()
            em.close()
            return true
        } catch (e: Exception) {
            em.transaction.rollback()
            return false
        }
    }

    fun productoNombreModificar(id: String, nombre: String): Boolean {
        val em = EntityManagerFactory.generar()
        try {
            em.transaction.begin()
            val producto = em.find(Productos::class.java, id)
            producto.nombre = nombre
            em.transaction.commit()
            em.close()
            return true
        } catch (e: Exception) {
            em.transaction.rollback()
            return false
        }
    }

    fun productoStockModificar(id: String, stock: Int): Boolean {
        val em = EntityManagerFactory.generar()
        try {
            em.transaction.begin()
            val producto = em.find(Productos::class.java, id)
            producto.stock = stock
            em.transaction.commit()
            em.close()
            return true
        } catch (e: Exception) {
            em.transaction.rollback()
            return false
        }
    }

    fun getProductosConStock(): MutableList<Productos>? {
        val em = EntityManagerFactory.generar()
        try {
            em.transaction.begin()
            val producto = em.createQuery("FROM Productos WHERE stock > 0", Productos::class.java)
            val productosConStock = producto.resultList;
            em.transaction.commit()
            em.close()
            return productosConStock
        } catch (e: Exception) {
            em.transaction.rollback()
            return null
        }
    }

    fun getProductosSinStock(): MutableList<Productos>? {
        val em = EntityManagerFactory.generar()
        try {
            em.transaction.begin()
            val producto = em.createQuery("FROM Productos WHERE stock = 0", Productos::class.java)
            val productosConStock = producto.resultList;
            em.transaction.commit()
            em.close()
            return productosConStock
        } catch (e: Exception) {
            em.transaction.rollback()
            return null
        }
    }
}