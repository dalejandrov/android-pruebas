package com.udec.cajica.data.repository

import com.udec.cajica.data.api.ApiService
import com.udec.cajica.data.model.Usuario
import com.udec.cajica.data.model.UsuarioDto
import javax.inject.Inject

class UsuarioRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun listarUsuarios(): List<Usuario> = apiService.listarUsuarios()
    suspend fun guardarUsuario(usuario: UsuarioDto): Usuario = apiService.guardarUsuario(usuario)
    suspend fun buscarUsuarioPorId(id: Long): Usuario = apiService.buscarUsuarioPorId(id)
    suspend fun eliminarUsuario(id: Long) = apiService.eliminarUsuario(id)
}
