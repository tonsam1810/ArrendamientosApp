package pe.cibertec.dam.arrendamientosapp
import android.content.Context
import android.content.Intent
import androidx.annotation.IdRes


object NavigationHelper {

    fun navigateToMenuItem(context: Context, @IdRes menuItemId: Int) {
        when (menuItemId) {
            R.id.nav_home -> {
                navigateToActivity(context, InicioActivity::class.java)
            }
            R.id.nav_inquilinos -> {
                navigateToActivity(context, InquilinosActivity::class.java)
            }
            R.id.nav_inmuebles -> {
                navigateToActivity(context, InmueblesActivity::class.java)
            }
            R.id.nav_contratos -> {
                navigateToActivity(context, ContratosActivity::class.java)
            }
            R.id.nav_pagos -> {
                navigateToActivity(context, PagosActivity::class.java)
            }
            // Agrega más casos según sea necesario
        }
    }

    private fun navigateToActivity(context: Context, activityClass: Class<*>) {
        context.startActivity(Intent(context, activityClass))
    }
}
