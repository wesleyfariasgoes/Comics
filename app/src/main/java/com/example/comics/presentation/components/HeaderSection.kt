package com.example.comics.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comics.R
import com.example.comics.ui.theme.ComicsTheme

@Composable
fun HeaderSection(
    title: String = "",
    showBackButton: Boolean = true,
    onBackClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (showBackButton) {
            Image(
                painter = painterResource(R.drawable.ic_arrow_left),
                contentDescription = "Voltar",
                modifier = Modifier
                    .clickable { onBackClick() }
                    .padding(end = 16.dp),
            )
        }
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderSectionPreview() {
    ComicsTheme {
        HeaderSection(title = "Comics App", onBackClick = {})
    }
}