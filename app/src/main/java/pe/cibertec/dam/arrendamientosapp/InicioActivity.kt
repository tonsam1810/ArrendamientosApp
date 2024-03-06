package pe.cibertec.dam.arrendamientosapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import  androidx.fragment.app.replace




class InicioActivity : AppCompatActivity(){

    lateinit var navegacion: BottomNavigationView

    private val listenerNav = BottomNavigationView.OnNavigationItemSelectedListener { item->

        when(item.itemId){
            R.id.nav_home->{
                supportFragmentManager.commit {
                    replace<InicioFragment>(R.id.FragmentContainer)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }

            R.id.nav_inmuebles->{
                supportFragmentManager.commit {
                    replace<InmueblesFragment>(R.id.FragmentContainer)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_inquilinos->{
                supportFragmentManager.commit {
                    replace<InquilinosFragment>(R.id.FragmentContainer)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_contratos->{
                supportFragmentManager.commit {
                    replace<ContratosFragment>(R.id.FragmentContainer)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_pagos->{
                supportFragmentManager.commit {
                    replace<PagosFragment>(R.id.FragmentContainer)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        navegacion = findViewById(R.id.MenuNav)
        navegacion.setOnNavigationItemSelectedListener (listenerNav)

        supportFragmentManager.commit {

            replace<InicioFragment>(R.id.FragmentContainer)
            setReorderingAllowed(true)
            addToBackStack("replacement")

        }


    }
}