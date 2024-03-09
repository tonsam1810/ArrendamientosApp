package pe.cibertec.dam.arrendamientosapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import pe.cibertec.dam.arrendamientosapp.Inquilinos.InquilinoActivity


class InicioActivity : AppCompatActivity(){
    private lateinit var inqButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        inqButton = findViewById(R.id.btnInquilinos)

        inqButton.setOnClickListener {
            listarInquilinos()
        }
    }

     override fun onCreateOptionsMenu(menu: Menu): Boolean {
         val inflater: MenuInflater = menuInflater
         inflater.inflate(R.menu.barra_navegacion, menu)
         return true
     }

     override fun onOptionsItemSelected(item: MenuItem): Boolean {
         // Handle item selection.
         return when (item.itemId) {
             R.id.nav_inquilinos -> {
                 listarInquilinos()
                 true
             }
             R.id.nav_home -> {
                 inicio()
                 true
             }
             else -> super.onOptionsItemSelected(item)
         }
     }

     private fun inicio() {
         TODO("Not yet implemented")
     }

     private fun listarInquilinos() {
         TODO("Not yet implemented")
         val intent = Intent(this@InicioActivity, InquilinoActivity::class.java)
         startActivity(intent)
     }
 }