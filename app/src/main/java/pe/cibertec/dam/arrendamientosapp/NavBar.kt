package pe.cibertec.dam.arrendamientosapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import pe.cibertec.dam.arrendamientosapp.R.id.nav_home

class NavBar : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_bar)




        nav_home.setOnClickListener {
            startActivity(Intent(this, InicioActivity::class.java))
        }

        nav_inquilinos.setOnClickListener {
            startActivity(Intent(this, InicioActivity::class.java))
        }

        nav_inmuebles.setOnClickListener {
            startActivity(Intent(this, InicioActivity::class.java))
        }
    }
}
