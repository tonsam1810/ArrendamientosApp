package pe.cibertec.dam.arrendamientosapp.Inquilinos

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.cibertec.dam.arrendamientosapp.R
import pe.cibertec.dam.arrendamientosapp.proxy.interfaces.InquilinoService
import pe.cibertec.dam.arrendamientosapp.proxy.retrofit.InquilinoRetrofit
import java.util.Arrays
import androidx.appcompat.app.AlertDialog
import pe.cibertec.dam.arrendamientosapp.models.Inquilino

class DetailInquilinoActivity : AppCompatActivity() {
    private lateinit var btnEditar: FloatingActionButton
    private lateinit var btnBorrar: FloatingActionButton

    private lateinit var txtId: TextInputEditText
    private lateinit var txtNombres: TextInputEditText
    private lateinit var txtApellidoPat: TextInputEditText
    private lateinit var txtApellidoMat: TextInputEditText
    private lateinit var txtDni: TextInputEditText
    private lateinit var txtTelefono: TextInputEditText
    private lateinit var txtFechaNac: TextInputEditText
    private lateinit var spnEstado : Spinner

    private lateinit var btnGuardar: Button
    private lateinit var btnCancelar: Button
    private lateinit var lyBotones: LinearLayout
    private var inqId = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_inquilino)

        btnEditar = findViewById(R.id.btnEditarDetInquilino)
        btnBorrar = findViewById(R.id.btnBorrarDetInquilino)

        txtNombres = findViewById(R.id.txtNombresDetInquilino)
        txtApellidoPat = findViewById(R.id.txtApellidoPaternoDetInquilino)
        txtApellidoMat = findViewById(R.id.txtApellidoMaternoDetInquilino)
        txtDni = findViewById(R.id.txtDniDetInquilino)
        txtTelefono = findViewById(R.id.txtTelefonoDetInquilino)
        txtFechaNac = findViewById(R.id.txtFechaNacDetInquilino)
        spnEstado = findViewById(R.id.spnEstadoDetInquilino)

        btnGuardar = findViewById(R.id.btnGuardarDetInquilino)
        btnCancelar = findViewById(R.id.btnCancelarGuardarDetInquilino)
        lyBotones = findViewById(R.id.lyBotonesDetInquilino)


        //Para el combo o spinner
        var indexEstadoInquilino = -1

        val estadoInquilino =
            ArrayList<String>(Arrays.asList(*resources.getStringArray(R.array.spnEstadosInquilinos)))
        val arrayAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, estadoInquilino)
        spnEstado.adapter = arrayAdapter

        spnEstado.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                indexEstadoInquilino = p2
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

        btnEditar.setOnClickListener {
            //Activar los botones
            activarCampos(true)
        }

        btnCancelar.setOnClickListener {
            //descativar los botones
            activarCampos(false)
        }

        val extras = intent.extras
        if (extras != null) {
            //obtenemos el id del carro seleccionado
            inqId = extras.getInt("MaxInqId")
            Toast.makeText(this, "inqId ${inqId}", Toast.LENGTH_LONG).show()

            CoroutineScope(Dispatchers.IO).launch {
                val retrofit = InquilinoRetrofit.getRetrofit().create(InquilinoService::class.java).getInquilinos(inqId)

                val data = retrofit.body()

                runOnUiThread {
                    if (retrofit.isSuccessful) {
                        txtId.setText(data!!.id_inquilino.toString())
                        txtNombres.setText(data!!.nombre)
                        txtApellidoPat.setText(data!!.apellido_paterno)
                        txtApellidoMat.setText(data!!.apellido_materno.toString())
                        txtDni.setText(data!!.dni)
                        txtTelefono.setText(data!!.telefono)
                        txtFechaNac.setText(data!!.fecha_nacimiento)
                        spnEstado.setSelection(indexEstadoInquilino)
                    }
                }
            }

            btnBorrar.setOnClickListener {

                val alertDialog = AlertDialog.Builder(this)

                alertDialog.setMessage("¿Estás seguro que quieres eliminar el Inquilino con ID ${inqId}?")
                    .setCancelable(false)
                    .setPositiveButton(
                        "Si",
                        DialogInterface.OnClickListener { dialogInterface, i ->
                            CoroutineScope(Dispatchers.IO).launch {
                                val retrofit =
                                    InquilinoRetrofit.getRetrofit().create(InquilinoService::class.java)
                                        .deleteInquilinos(inqId)
                                val data = retrofit.body()
                                runOnUiThread {
                                    val intent =
                                        Intent(
                                            this@DetailInquilinoActivity,
                                            InquilinoActivity::class.java
                                        )
                                    Toast.makeText(
                                        this@DetailInquilinoActivity,
                                        "Inquilino eliminado correctamente",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    startActivity(intent)
                                }
                            }

                        }

                    )
                    .setNegativeButton("No",
                        DialogInterface.OnClickListener { dialogInterface, i ->
                            dialogInterface.cancel()
                        })
                alertDialog.create().show()
            }
        }

        btnGuardar.setOnClickListener {

            val inq = Inquilino(
                id_inquilino = inqId,
                nombre = txtNombres.text.toString(),
                apellido_paterno = txtApellidoPat.text.toString(),
                apellido_materno = txtApellidoMat.text.toString(),
                dni = txtDni.text.toString(),
                telefono = txtTelefono.text.toString(),
                fecha_nacimiento = txtFechaNac.toString(),
                estado = spnEstado.getSelectedItem().toString()
            )

            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setMessage("¿Estás seguro que quieres actualizar al inquilino ${inq.nombre} ${inq.apellido_paterno} ${inq.apellido_materno}?")
                .setCancelable(false)
                .setPositiveButton(
                    "Si",
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        CoroutineScope(Dispatchers.IO).launch {
                            val retrofit =
                                InquilinoRetrofit.getRetrofit().create(InquilinoService::class.java)
                                    .saveInquilinos(inq)
                            val data = retrofit.body()
                            runOnUiThread {
                                val intent =
                                    Intent(
                                        this@DetailInquilinoActivity,
                                        InquilinoActivity::class.java
                                    )
                                Toast.makeText(
                                    this@DetailInquilinoActivity,
                                    "Inquilino actualizado correctamente",
                                    Toast.LENGTH_SHORT
                                ).show()
                                startActivity(intent)
                            }
                        }

                    }

                )
                .setNegativeButton("No",
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        dialogInterface.cancel()
                    })
            alertDialog.create().show()
        }

    }

    fun activarCampos(value: Boolean){
        txtNombres.isEnabled = value
        txtApellidoPat.isEnabled = value
        txtApellidoMat.isEnabled = value
        txtDni.isEnabled = value
        txtTelefono.isEnabled = value
        txtFechaNac.isEnabled = value
        spnEstado.isEnabled= value
        if(value){
            lyBotones.visibility = View.VISIBLE
        }
        else{
            lyBotones.visibility = View.INVISIBLE
        }


    }
}