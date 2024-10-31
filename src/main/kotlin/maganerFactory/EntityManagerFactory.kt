package org.example.maganerFactory

import jakarta.persistence.EntityManager
import jakarta.persistence.Persistence

object EntityManagerFactory {
    private val x= Persistence.createEntityManagerFactory("myunidadsql")
    fun generar(): EntityManager {
        return x.createEntityManager()
    }
}