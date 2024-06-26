package com.example.recognitionapp.views

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.recognitionapp.R
import com.example.recognitionapp.viewModel.ScannerViewModel

@Composable
fun GalleryView(scannerViewModel: ScannerViewModel) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    var image: Any? by remember { mutableStateOf(R.drawable.gallery) }

    val photoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()) { item ->
        if(item != null) {
            image = item
            scannerViewModel.onRecognizedText(image, context)
        } else {
            scannerViewModel.showToast(context, "No se ha seleccionado ninguna imagen")
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier
                .clickable {
                    photoPicker.launch(
                        PickVisualMediaRequest(
                            ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                }
                .padding(16.dp, 8.dp),
            painter = rememberAsyncImagePainter(image),
            contentDescription = null
        )
        
        Spacer(modifier = Modifier.height(25.dp))

        val scrollState = rememberScrollState()
        Text(
            text = scannerViewModel.recognizedText,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
                .verticalScroll(scrollState)
                .clickable {
                    clipboardManager.setText(AnnotatedString(scannerViewModel.recognizedText))
                    scannerViewModel.showToast(context, "Copiado")
                }
        )

    }



}