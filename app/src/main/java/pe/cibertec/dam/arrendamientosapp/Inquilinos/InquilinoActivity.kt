package pe.cibertec.dam.arrendamientosapp.Inquilinos

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.cibertec.dam.arrendamientosapp.R
import pe.cibertec.dam.arrendamientosapp.adapters.InquilinoAdapter
import pe.cibertec.dam.arrendamientosapp.proxy.interfaces.InquilinoService
import pe.cibertec.dam.arrendamientosapp.proxy.retrofit.InquilinoRetrofit
import pe.cibertec.dam.arrendamientosapp.util.LoadingDialog

class InquilinoActivity : AppCompatActivity() {

    private var maxInqId = 0
    private lateinit var loading: LoadingDialog
    private lateinit var btnAgregarInq: FloatingActionButton
    private lateinit var recyclerViewInq: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inquilino)

        //Muestro el icono de cargando
        loading = LoadingDialog(this)
        loading.startLoading()

        //Enlazo mis variables con el layout
        recyclerViewInq = findViewById(R.id.recyclerInquilinos)
        btnAgregarInq = findViewById(R.id.btnAddInquilino)

        //Configuro mi recycler y el botón
        setupComponents()

        //Aca cargo los datos desde el servicio del api hacia mi recyclerview
        cargaData()

    }

    fun setupComponents() {
        recyclerViewInq.layoutManager = LinearLayoutManager(this)

        //Configuro que hará mi botón al ser clickeado
        btnAgregarInq.setOnClickListener {
            //Abrira otra activity para Agregar auto Nuevo
            val intent = Intent(this, AddInquilinoActivity::class.java)
            intent.putExtra("MaxInqId", maxInqId)
            startActivity(intent)
        }
    }

    fun cargaData() {
        CoroutineScope(Dispatchers.IO).launch {
            val retrofit = InquilinoRetrofit.getRetrofit().create(InquilinoService::class.java).getInquilinos()
            val data = retrofit.body()

            runOnUiThread {
                if (retrofit.isSuccessful) {
                    recyclerViewInq.adapter = InquilinoAdapter(data!!) {
                        val inqId = it.id_inquilino
                        val intent =
                            Intent(
                                this@InquilinoActivity,
                                DetailInquilinoActivity::class.java
                            ).apply {
                                putExtra("MaxInqId", inqId)
                            }
                        startActivity(intent)
                    }
                    maxInqId = data!!.maxOf { it.id_inquilino }
                    loading.finishLoading()
                }
            }
        }
    }
}