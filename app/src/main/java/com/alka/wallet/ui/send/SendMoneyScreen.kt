package com.alka.wallet.ui.send

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alka.wallet.ui.theme.AlkaWalletTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendMoneyScreen(onBack: () -> Unit = {}) {
    var amount by remember { mutableStateOf("") }
    var recipient by remember { mutableStateOf("") }
    var showConfirmation by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Enviar Dinero") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Ingresa los datos del envío",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Campo de Monto
            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("Monto a enviar") },
                placeholder = { Text("0.00") },
                prefix = { Text("$ ") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de Destinatario
            OutlinedTextField(
                value = recipient,
                onValueChange = { recipient = it },
                label = { Text("Destinatario") },
                placeholder = { Text("Nombre, correo o teléfono") },
                prefix = { Icon(Icons.Default.Person, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(32.dp))

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
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Confirmar Envío", fontSize = 18.sp)
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
