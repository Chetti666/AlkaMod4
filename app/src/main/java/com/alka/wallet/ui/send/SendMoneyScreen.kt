package com.alka.wallet.ui.send

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
fun SendMoneyScreen(onBack: () -> Unit = {}) {
    var amount by remember { mutableStateOf("") }
    var recipient by remember { mutableStateOf("") }
    var showConfirmation by remember { mutableStateOf(false) }

    BackgroundWrapper(drawableResId = R.drawable.sendmoney) {
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

            // Campo de Monto (Estilizado para encajar o ser minimalista)
            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                placeholder = { Text("0.00", color = Color.Gray) },
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

            // Campo de Destinatario
            OutlinedTextField(
                value = recipient,
                onValueChange = { recipient = it },
                placeholder = { Text("Nombre o correo", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White.copy(alpha = 0.7f),
                    unfocusedContainerColor = Color.White.copy(alpha = 0.5f),
                    focusedBorderColor = Color(0xFF1E88E5)
                )
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Botón de Confirmación
            Button(
                onClick = { 
                    if (amount.isNotBlank() && recipient.isNotBlank()) {
                        showConfirmation = true 
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E88E5))
            ) {
                Text("ENVIAR DINERO")
            }

            if (showConfirmation) {
                AlertDialog(
                    onDismissRequest = { showConfirmation = false },
                    title = { Text("¡Envío Exitoso!") },
                    text = { Text("Has enviado $$amount a $recipient correctamente.") },
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
fun SendMoneyScreenPreview() {
    AlkaWalletTheme {
        SendMoneyScreen()
    }
}
