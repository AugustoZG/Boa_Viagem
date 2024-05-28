package com.senac.boaviagem.ui.theme

import androidx.compose.runtime.*
import androidx.navigation.NavController

class NavControllerState(navController: NavController) {
    var login by mutableStateOf("")
    var senha by mutableStateOf("")
    var email by mutableStateOf("")
}

fun NavController.registerState(): NavControllerState {
    return NavControllerState(this)
}