package pe.cibertec.dam.arrendamientosapp.Security

import android.content.Context
import android.content.SharedPreferences

class LoginManager(private val  context:Context) {

    private val sharedPreferences: SharedPreferences by lazy{
        context.getSharedPreferences("login_pref", Context.MODE_PRIVATE)
    }

    fun isLoggedIn(): Boolean{
        return  sharedPreferences.getBoolean("isLoggedIn", false)
    }

    fun login (username: String, password: String): Boolean{
        // Aquí puedes implementar la lógica de autenticación
        // Por ejemplo, validar el nombre de usuario y la contraseña
        val isValidCredentials =username == "admin" && password=="admin"

        // Si las credenciales son válidas, establece isLoggedIn en true en SharedPreferences
        if(isValidCredentials) {
            sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()
            }
        return isValidCredentials

    }

    fun logout(){

        // Al cerrar sesión, establece isLoggedIn en false en SharedPreferences
        sharedPreferences.edit().putBoolean("isLoggedIn", false).apply()

    }
}