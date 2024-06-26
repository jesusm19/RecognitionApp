package com.example.recognitionapp.viewModel

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import java.io.IOException

class ScannerViewModel: ViewModel() {
    var recognizedText by mutableStateOf("")
        private set

    /**
     * Metodo que limpia los campos
     */
    fun cleanText(){
        recognizedText = ""
    }

    /**
     * Metodo generico que permite mostrar el toast
     */
    fun showToast(context: Context, message: String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * Metodo que permite leer el texto de una imagen
     */
    fun onRecognizedText(text: Any?, context: Context){
        var image: InputImage? = null
        try {
            image = InputImage.fromFilePath(context, text as Uri)

        } catch(ex: IOException) {
            ex.printStackTrace()
        }

        image?.let {
            TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS).process(it)
                .addOnSuccessListener { text ->
                    recognizedText = text.text
                }.addOnFailureListener {
                    showToast(context, "Error al leer la imagen")
                }
        }

    }


}