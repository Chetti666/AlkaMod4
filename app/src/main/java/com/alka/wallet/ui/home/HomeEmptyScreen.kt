package com.alka.wallet.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alka.wallet.R
import com.alka.wallet.ui.components.BackgroundWrapper
import com.alka.wallet.ui.theme.AlkaWalletTheme

@Composable
fun HomeEmptyScreen() {
    // Usamos el PNG que ya tiene la cabecera, la tarjeta vacía y la barra inferior
    BackgroundWrapper(drawableResId = R.drawable.homepageempty) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Saltamos la zona del saludo y la tarjeta vacía del PNG
            Spacer(modifier = Modifier.height(280.dp))

            // Reutilizamos las acciones rápidas (botones invisibles/transparentes)
            QuickActions()

            Spacer(modifier = Modifier.height(40.dp))

            // Zona central de "Estado Vacío"
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // El PNG probablemente ya tiene la ilustración, así que solo dejamos el texto y botón
                Text(
                    text = "Aún no tienes movimientos",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Gray
                )
                Text(
                    text = "Tus transacciones aparecerán aquí una vez que comiences a usar tu billetera.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 32.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = { /* TODO: Agregar Tarjeta */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E88E5))
                ) {
                    Text("Agregar mi primera tarjeta")
                }
            }
            
            // Espacio para la barra de navegación inferior del PNG
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeEmptyScreenPreview() {
    AlkaWalletTheme {
        HomeEmptyScreen()
    }
}
