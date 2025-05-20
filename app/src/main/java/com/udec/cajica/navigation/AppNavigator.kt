package com.udec.cajica.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.udec.cajica.ui.screens.CrearUsuarioScreen
import com.udec.cajica.ui.screens.HomeScreen
import com.udec.cajica.ui.screens.UsuariosScreen
import com.udec.cajica.viewModel.UsuarioViewModel

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Usuarios : Screen("usuarios")
    object CrearUsuario : Screen("crear-usuario")
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    AppNavGraph(navController = navController)
}

@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Usuarios.route) {
            val usuarioViewModel: UsuarioViewModel = hiltViewModel()
            UsuariosScreen(navController = navController, viewModel = usuarioViewModel)
        }
        composable(Screen.CrearUsuario.route) {
            val usuarioViewModel: UsuarioViewModel = hiltViewModel()
            CrearUsuarioScreen(navController = navController, viewModel = usuarioViewModel)
        }
    }
}
