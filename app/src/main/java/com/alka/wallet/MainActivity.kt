package com.alka.wallet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.alka.wallet.ui.auth.AuthScreen
import com.alka.wallet.ui.home.HomeScreen
import com.alka.wallet.ui.theme.AlkaWalletTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Maneja la transición de la splash screen
        installSplashScreen()
        
        super.onCreate(savedInstanceState)
        setContent {
            AlkaWalletTheme {
                // Estado simple para simular la navegación
                var isLoggedIn by remember { mutableStateOf(false) }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (isLoggedIn) {
                        HomeScreen()
                    } else {
                        AuthScreen(onLoginSuccess = { isLoggedIn = true })
                    }
                }
            }
        }
    }
}
