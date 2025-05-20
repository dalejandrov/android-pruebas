package com.udec.cajica.data.api

import com.udec.cajica.data.model.Usuario
import com.udec.cajica.data.model.UsuarioDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

// SUSPEND - FUNCIONES ASINCRONAS - SE PAUSAN SIN BLOQUEAR LA EJECUCION PRINCIPAL

interface ApiService {
    @GET("/usuario")
    suspend fun listarUsuarios(): List<Usuario>

    @POST("/usuario/guardarDto")
    suspend fun guardarUsuario(@Body usuario: UsuarioDto): Usuario

    @GET("/usuario/{id}")
    suspend fun buscarUsuarioPorId(@Path("id") id: Long): Usuario

    @DELETE("/usuario/{id}")
    suspend fun eliminarUsuario(@Path("id") id: Long): retrofit2.Response<Unit>
}
