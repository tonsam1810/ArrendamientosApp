package pe.cibertec.dam.arrendamientosapp.Inquilinos

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.cibertec.dam.arrendamientosapp.R
import pe.cibertec.dam.arrendamientosapp.models.Inquilino
import pe.cibertec.dam.arrendamientosapp.proxy.interfaces.InquilinoService
import pe.cibertec.dam.arrendamientosapp.proxy.retrofit.InquilinoRetrofit
import pe.cibertec.dam.arrendamientosapp.util.LoadingDialog
import java.util.Arrays

class AddInquilinoActivity : AppCompatActivity() {
    private lateinit var loading: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_inquilino)

        val btnGuardar : Button = findViewById(R.id.btnGuardarInquilino)
        val btnCancelar : Button = findViewById(R.id.btnCancelarGuardarInquilino)

        val txtNombresInq : TextView = findViewById(R.id.txtNombresInquilino)
        val txtApellidoPatInq : TextView = findViewById(R.id.txtApellidoPaternoInquilino)
        val txtApellidoMatInq : TextView = findViewById(R.id.txtApellidoMaternoInquilino)
        val txtDniInq : TextView = findViewById(R.id.txtDniInquilino)
        val txtTelefonoInq : TextView = findViewById(R.id.txtTelefonoInquilino)
        val txtFechaNacInq : TextView = findViewById(R.id.txtFechaNacInquilino)
        val spnEstadoInq : Spinner = findViewById(R.id.spnEstadoInquilino)

        var indexEstadosInquilinos= -1
        val estadosInquilinos =
            ArrayList<String>(Arrays.asList(*resources.getStringArray(R.array.spnEstadosInquilinos)))
        val arrayAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, estadosInquilinos)
        spnEstadoInq.adapter = arrayAdapter

        spnEstadoInq.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                indexEstadosInquilinos = p2 // 0 = nulo, 1 = parcial, 2 = completo
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        //muestro cargar
        loading = LoadingDialog(this)

        var maxInqId = 0

        //Intento obtener los parámetros enviados por el anterior activity
        val extras = intent.extras
        if (extras != null) {
            maxInqId = extras.getInt("MaxInqId") + 1
        }

        btnGuardar.setOnClickListener {

            val pnombres = txtNombresInq.text.toString().trim()
            val papepat = txtApellidoPatInq.text.toString().trim()
            val papemat = txtApellidoMatInq.text.toString().trim()
            val pdni = txtDniInq.text.toString().trim()
            val ptelefono = txtTelefonoInq.text.toString().trim()
            val pfechanac = txtFechaNacInq.text.toString().trim()


            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder
                .setMessage("¿Estás seguro que quieres agregar el nuevo inquilino?")
                .setTitle("Guardar Nuevo Inquilino")
                .setPositiveButton("Si") { dialog, which ->

                    val inquilino = Inquilino(
                        id_inquilino = maxInqId,
                        nombre = pnombres,
                        apellido_paterno = papepat,
                        apellido_materno = papemat,
                        dni = pdni,
                        telefono = ptelefono,
                        fecha_nacimiento = pfechanac,
                        estado = spnEstadoInq.getSelectedItem().toString()//trae el valor, y no el id
                    )

                    CoroutineScope(Dispatchers.IO).launch {
                        val retrofit =
                            InquilinoRetrofit.getRetrofit().create(InquilinoService::class.java)
                                .saveInquilinos(inquilino)
                        val data = retrofit.body()
                        runOnUiThread {
                            Toast.makeText(this@AddInquilinoActivity,inquilino.toString(), Toast.LENGTH_LONG).show()
                            startActivity(Intent(this@AddInquilinoActivity, InquilinoActivity::class.java))
                        }

                    }

                }
                .setNegativeButton("No") { dialog, which ->
                    finish()
                }

            val dialog: AlertDialog = builder.create()
            dialog.show()




        }

        btnCancelar.setOnClickListener {
            txtNombresInq.text = ""
            txtApellidoMatInq.text = ""
            txtApellidoPatInq.text = ""
            txtDniInq.text = ""
            txtTelefonoInq.text = ""
            txtFechaNacInq.text = ""
            spnEstadoInq.setSelection(0)
        }


    }
}