package org.example.servicio

import org.example.Model.Usuarios
import org.example.repositorio.UsuariosRepository

class UsuarioService(val repositoryUsuarios: UsuariosRepository) {

    fun validarRegistro(user: String, pass: String): Usuarios? {
        return repositoryUsuarios.validarRegistro(user, pass)
    }
}