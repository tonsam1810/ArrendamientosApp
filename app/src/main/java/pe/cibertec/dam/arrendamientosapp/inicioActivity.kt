package pe.cibertec.dam.arrendamientosapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class inicioActivity : AppCompatActivity() {

    lateinit var navegacion: BottomNavigationView

    private val listenerNav = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.nav_home -> {
                // Aquí puedes iniciar la actividad que desees para el elemento "Home"
                startActivity(Intent(this, PagosActivity::class.java))
                return@OnNavigationItemSelectedListener true
            }

            R.id.nav_inmuebles -> {
                // Aquí puedes iniciar la actividad que desees para el elemento "Inmuebles"
                startActivity(Intent(this, PagosActivity::class.java))
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_inquilinos -> {
                // Aquí puedes iniciar la actividad que desees para el elemento "Inquilinos"
                startActivity(Intent(this, PagosActivity::class.java))
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_contratos -> {
                // Aquí puedes iniciar la actividad que desees para el elemento "Contratos"
                startActivity(Intent(this, PagosActivity::class.java))
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_pagos -> {
                // Aquí puedes iniciar la actividad que desees para el elemento "Pagos"
                startActivity(Intent(this, PagosActivity::class.java))
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        navegacion = findViewById(R.id.MenuNav)
        navegacion.setOnNavigationItemSelectedListener(listenerNav)
    }
}