package com.example.recognitionapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.recognitionapp.viewModel.ScannerViewModel

@Composable
fun TabsView(scannerViewModel: ScannerViewModel) {
    var selectedTab by remember { mutableStateOf(0) }

    val tabs = listOf("Galeria", "Camara", "Colección")

    Column {
        TabRow(
            selectedTabIndex = selectedTab,
            contentColor = Color.Black,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedTab])
                )
            }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = {
                        selectedTab = index
                    },
                    text = { Text(text = title) })
            }
        }
        when(selectedTab) {
            0 -> GalleryView(scannerViewModel).apply { scannerViewModel.cleanText() }
            1 -> CameraView(scannerViewModel).apply { scannerViewModel.cleanText() }
            2 -> CollectionGalleryView()
        }

    }



}