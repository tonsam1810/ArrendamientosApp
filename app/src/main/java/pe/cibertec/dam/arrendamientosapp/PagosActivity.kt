package pe.cibertec.dam.arrendamientosapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.cibertec.dam.arrendamientosapp.adapter.PagosAdapter
import pe.cibertec.dam.arrendamientosapp.proxy.PagosRetrofit
import pe.cibertec.dam.arrendamientosapp.proxy.interfaces.PagoService

import pe.cibertec.dam.arrendamientosapp.model.Pagos

class PagosActivity : AppCompatActivity() {

    private lateinit var recyclerViewPagos: RecyclerView

    private var maxIdPagos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagos)
        recyclerViewPagos = findViewById(R.id.recyclerPagos)

        setupComponents()

        loadData()
        //LoadDataFireBase()

    }

    fun setupComponents() {
        recyclerViewPagos.layoutManager = LinearLayoutManager(this)
        //dbFirebase=FirebaseDatabase.getInstance().getReference("Pagos")


    }







    fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            val retrofit =
                PagosRetrofit.getRetrofit().create(PagoService::class.java)
                    .getPagos()
            val data = retrofit.body()

            runOnUiThread {
                if (retrofit.isSuccessful) {
                    recyclerViewPagos.adapter = PagosAdapter(data!!) {
                        goToPagosDetail(it)

                    }
                   maxIdPagos = data!!.maxOf { it.idPago}

                }
            }
        }
    }
    fun goToPagosDetail(pagos: Pagos) {
        val intent =
            Intent(this@PagosActivity, DetallePagosActivity::class.java)
            .apply {
                putExtra("idPago", pagos.idPago)
            }
        startActivity(intent)
    }
}