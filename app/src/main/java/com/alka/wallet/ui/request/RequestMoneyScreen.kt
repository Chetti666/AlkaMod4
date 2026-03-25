package com.alka.wallet.ui.request

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alka.wallet.R
import com.alka.wallet.ui.components.BackgroundWrapper
import com.alka.wallet.ui.theme.AlkaWalletTheme

@Composable
fun RequestMoneyScreen(onBack: () -> Unit = {}) {
    var amount by remember { mutableStateOf("") }
    var reason by remember { mutableStateOf("") }
    var showConfirmation by remember { mutableStateOf(false) }

    BackgroundWrapper(drawableResId = R.drawable.requestmoney) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Botón de volver sobre el icono del PNG
            Box(modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = onBack, modifier = Modifier.padding(top = 8.dp)) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Volver", tint = Color.White)
                }
            }

            // Espacio para saltar el diseño de cabecera del PNG
            Spacer(modifier = Modifier.height(280.dp))

            // Campo de Monto
            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                placeholder = { Text("Monto a solicitar", color = Color.Gray) },
                prefix = { Text("$ ", color = Color.Black) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White.copy(alpha = 0.7f),
                    unfocusedContainerColor = Color.White.copy(alpha = 0.5f),
                    focusedBorderColor = Color(0xFF1E88E5)
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de Motivo
            OutlinedTextField(
                value = reason,
                onValueChange = { reason = it },
                placeholder = { Text("Motivo (Ej: Préstamo)", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                minLines = 3,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White.copy(alpha = 0.7f),
                    unfocusedContainerColor = Color.White.copy(alpha = 0.5f),
                    focusedBorderColor = Color(0xFF1E88E5)
                )
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Botón de Solicitar
            Button(
                onClick = { 
                    if (amount.isNotBlank() && reason.isNotBlank()) {
                        showConfirmation = true 
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E88E5))
            ) {
                Text("SOLICITAR DINERO")
            }

            if (showConfirmation) {
                AlertDialog(
                    onDismissRequest = { showConfirmation = false },
                    title = { Text("Solicitud Enviada") },
                    text = { Text("Has solicitado $$amount por el motivo: \"$reason\".") },
                    confirmButton = {
                        TextButton(onClick = { 
                            showConfirmation = false
                            onBack()
                        }) {
                            Text("Aceptar")
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RequestMoneyScreenPreview() {
    AlkaWalletTheme {
        RequestMoneyScreen()
    }
}
