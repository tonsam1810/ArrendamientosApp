package pe.cibertec.dam.arrendamientosapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import pe.cibertec.dam.arrendamientosapp.Security.LoginManager

class LoginActivity : AppCompatActivity() {
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var btnLogin: Button
    private lateinit var loginManager: LoginManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_login)

        usernameEditText = findViewById(R.id.txtUsername)
        passwordEditText = findViewById(R.id.txtPassword)
        btnLogin = findViewById(R.id.btnLogin)

        // Inicializar el administrador de inicio de sesión
        loginManager = LoginManager(this)

        // Configurar el click listener del botón de inicio de sesión
        btnLogin.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (loginManager.login(username, password)) {
                // Si el inicio de sesión es exitoso, navegar a la siguiente actividad
                startActivity(Intent(this, InicioActivity::class.java))
                finish()
            } else {
                // Si el inicio de sesión falla, mostrar un mensaje de error al usuario
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }
    }
}



