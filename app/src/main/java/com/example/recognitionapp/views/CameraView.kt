package com.example.recognitionapp.views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.recognitionapp.viewModel.ScannerViewModel

@Composable
fun CameraView(scannerViewModel: ScannerViewModel) {
    Text(text = "Camera")
}