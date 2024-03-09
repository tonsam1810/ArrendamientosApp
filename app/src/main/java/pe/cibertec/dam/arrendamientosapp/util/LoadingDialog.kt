package pe.cibertec.dam.arrendamientosapp.util

import android.app.Activity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pe.cibertec.dam.arrendamientosapp.R

class LoadingDialog(val myActivity: Activity) {

    private lateinit var dialog: AlertDialog

    fun startLoading() {
        val inflater = myActivity.layoutInflater
        val dialogView = inflater.inflate(R.layout.activity_loading_dialog, null)
        dialogView.alpha = 0.99F

        val builder = AlertDialog.Builder(myActivity)
        builder.setView(dialogView)

        builder.setTitle("Cargando datos")

        dialog = builder.create()
        dialog.show()
    }

    fun finishLoading() {
        dialog.dismiss()
    }

}