package com.alka.wallet.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
    BackgroundWrapper(drawableResId = R.drawable.homepage) {
        Box(modifier = Modifier.fillMaxSize()) {
            QuickActionsOverlay(
                onSendClick = onNavigateToSend,
                onRequestClick = onNavigateToRequest,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = 200.dp)
            )
            ProfileButtonOverlay(
                onClick = onNavigateToProfile,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 40.dp, end = 16.dp)
            )
        }
    }
}

@Composable
fun QuickActionsOverlay(
    onSendClick: () -> Unit = {},
    onRequestClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clickable(onClick = onSendClick)
        )
        Box(
            modifier = Modifier
                .size(100.dp)
                .clickable(onClick = onRequestClick)
        )
    }
}

@Composable
fun ProfileButtonOverlay(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.size(48.dp).clickable(onClick = onClick))
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
