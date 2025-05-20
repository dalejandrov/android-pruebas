package com.udec.cajica.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udec.cajica.data.model.Usuario
import com.udec.cajica.data.model.UsuarioDto
import com.udec.cajica.data.repository.UsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsuarioViewModel @Inject constructor(
    private val usuarioRepository: UsuarioRepository
) : ViewModel() {

    var usuarios by mutableStateOf<List<Usuario>>(emptyList())
        private set

    var loading by mutableStateOf(false)
        private set

    var error by mutableStateOf<String?>(null)
        private set

    var creando by mutableStateOf(false)
        private set
    var crearExitoso by mutableStateOf<Boolean?>(null)
        private set

    fun cargarUsuarios() {
        loading = true
        viewModelScope.launch {
            try {
                usuarios = usuarioRepository.listarUsuarios()
                error = null
            } catch (e: Exception) {
                error = e.message
            } finally {
                loading = false
            }
        }
    }

    fun guardarUsuario(
        usuario: UsuarioDto,
        onSuccess: () -> Unit = {},
        onError: (String) -> Unit = {}
    ) {
        creando = true
        viewModelScope.launch {
            try {
                usuarioRepository.guardarUsuario(usuario)
                crearExitoso = true
                onSuccess()
            } catch (e: Exception) {
                crearExitoso = false
                error = e.message
                onError(e.message ?: "Error desconocido")
            } finally {
                creando = false
            }
        }
    }

    fun resetCrearUsuario() {
        crearExitoso = null
        error = null
    }
}
