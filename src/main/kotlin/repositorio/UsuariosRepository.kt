package org.example.repositorio

import org.example.Model.Usuarios
import org.example.maganerFactory.EntityManagerFactory

class UsuariosRepository {
    fun validarRegistro(user: String, pass: String): Usuarios? {
        try {
            val em = EntityManagerFactory.generar()
            val query = em.createQuery(
                "FROM Usuarios u WHERE u.nombreUsuario = :nombreUsuario and u.password = :password",
                Usuarios::class.java
            )
            query.setParameter("nombreUsuario", user)
            query.setParameter("password", pass)
            val count = query.singleResult
            em.close()
            return count
        } catch (e: Exception) {
            return null
        }
    }
}