package com.alka.wallet.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alka.wallet.R
import com.alka.wallet.ui.components.BackgroundWrapper
import com.alka.wallet.ui.theme.AlkaWalletTheme

@Composable
fun HomeScreen(
    onNavigateToSend: () -> Unit = {},
    onNavigateToRequest: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {}
) {
    // El PNG ya contiene el diseño de fondo, TopAppBar y la Tarjeta de Saldo
    BackgroundWrapper(drawableResId = R.drawable.homepage) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            // Espacio para saltar el TopAppBar y el BalanceCard que ya están en el PNG
            Spacer(modifier = Modifier.height(280.dp))

            // Botones invisibles sobre las acciones rápidas del PNG o botones estilizados
            QuickActions(
                onSendClick = onNavigateToSend,
                onIngresarClick = onNavigateToRequest
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Lista de Movimientos (esta sí debe ser dinámica sobre el fondo)
            Text(
                text = "Movimientos Recientes",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black // Ajustado para visibilidad sobre blanco
            )
            
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(10) { index ->
                    TransactionItem(index)
                }
            }
            
            // Espacio para la barra de navegación que ya está en el PNG
            Spacer(modifier = Modifier.height(60.dp))
        }
        
        // Botón de perfil flotante sobre el icono del PNG
        Box(modifier = Modifier.fillMaxSize()) {
            IconButton(
                onClick = onNavigateToProfile,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 40.dp, end = 16.dp)
                    .size(48.dp)
            ) {
                // Dejamos el icono transparente o invisible si el PNG ya lo tiene
                // Icon(Icons.Default.AccountCircle, contentDescription = "Perfil", tint = Color.Transparent)
            }
        }
    }
}

@Composable
fun QuickActions(
    onSendClick: () -> Unit = {},
    onIngresarClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        // Botones con fondo transparente para que se vea el PNG debajo
        ActionButton(icon = Icons.Default.Send, label = "Enviar", onClick = onSendClick)
        ActionButton(icon = Icons.Default.Add, label = "Solicitar", onClick = onIngresarClick)
        ActionButton(icon = Icons.Default.ShoppingCart, label = "Tarjetas", onClick = {})
    }
}

@Composable
fun ActionButton(icon: ImageVector, label: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FilledTonalIconButton(
            onClick = onClick,
            modifier = Modifier.size(56.dp),
            colors = IconButtonDefaults.filledTonalIconButtonColors(
                containerColor = Color.White.copy(alpha = 0.1f)
            )
        ) {
            Icon(icon, contentDescription = label)
        }
        Text(text = label, style = MaterialTheme.typography.labelMedium)
    }
}

@Composable
fun TransactionItem(index: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White.copy(alpha = 0.8f), RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.LightGray.copy(alpha = 0.2f), RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                if (index % 2 == 0) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = if (index % 2 == 0) Color(0xFFE53935) else Color(0xFF43A047)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "Transacción #$index", fontWeight = FontWeight.SemiBold, color = Color.Black)
            Text(text = "Hoy, 12:45 PM", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        }
        Text(
            text = if (index % 2 == 0) "- $120.00" else "+ $450.00",
            fontWeight = FontWeight.Bold,
            color = if (index % 2 == 0) Color(0xFFE53935) else Color(0xFF43A047)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    AlkaWalletTheme {
        HomeScreen()
    }
}
