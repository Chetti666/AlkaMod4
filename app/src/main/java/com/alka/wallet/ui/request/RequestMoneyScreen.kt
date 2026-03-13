package com.alka.wallet.ui.request

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
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
fun RequestMoneyScreen(onBack: () -> Unit = {}) {
    var amount by remember { mutableStateOf("") }
    var reason by remember { mutableStateOf("") }
    var showConfirmation by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Solicitar Dinero") },
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
                text = "¿Cuánto dinero necesitas solicitar?",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Campo de Monto
            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("Monto a solicitar") },
                placeholder = { Text("0.00") },
                prefix = { Text("$ ") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de Motivo
            OutlinedTextField(
                value = reason,
                onValueChange = { reason = it },
                label = { Text("Motivo") },
                placeholder = { Text("Ej: Pago de almuerzo") },
                prefix = { Icon(Icons.Default.Edit, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                minLines = 3
            )

            Spacer(modifier = Modifier.height(32.dp))

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
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Enviar Solicitud", fontSize = 18.sp)
            }

            if (showConfirmation) {
                AlertDialog(
                    onDismissRequest = { showConfirmation = false },
                    title = { Text("Solicitud Enviada") },
                    text = { Text("Has solicitado $$amount por el motivo: \"$reason\". Se notificará al destinatario.") },
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
