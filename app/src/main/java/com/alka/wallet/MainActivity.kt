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
import com.alka.wallet.ui.profile.ProfileScreen
import com.alka.wallet.ui.request.RequestMoneyScreen
import com.alka.wallet.ui.send.SendMoneyScreen
import com.alka.wallet.ui.theme.AlkaWalletTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Maneja la transición de la splash screen
        installSplashScreen()
        
        super.onCreate(savedInstanceState)
        setContent {
            AlkaWalletTheme {
                // Sistema de navegación simple basado en estados
                var currentScreen by remember { mutableStateOf("auth") }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when (currentScreen) {
                        "auth" -> AuthScreen(onLoginSuccess = { currentScreen = "home" })
                        "home" -> HomeScreen(
                            onNavigateToSend = { currentScreen = "send" },
                            onNavigateToRequest = { currentScreen = "request" },
                            onNavigateToProfile = { currentScreen = "profile" }
                        )
                        "send" -> SendMoneyScreen(onBack = { currentScreen = "home" })
                        "request" -> RequestMoneyScreen(onBack = { currentScreen = "home" })
                        "profile" -> ProfileScreen(onBack = { currentScreen = "home" })
                    }
                }
            }
        }
    }
}
