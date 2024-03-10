package pe.cibertec.dam.arrendamientosapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.cibertec.dam.arrendamientosapp.model.Pagos
import pe.cibertec.dam.arrendamientosapp.proxy.PagosRetrofit
import pe.cibertec.dam.arrendamientosapp.proxy.interfaces.PagoService
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

class DetallePagosActivity : AppCompatActivity() {

    private lateinit var txtIdPagosdt: TextInputEditText
    private lateinit var txtIdContratoPagosdt: TextInputEditText
    private lateinit var txtFechaPagodt: TextInputEditText
    private lateinit var txtMontoPagodt: TextInputEditText
    private lateinit var spnEstado: Spinner
    private lateinit var btnCambiarEstado: FloatingActionButton
    private lateinit var btnGuardar: Button
    private lateinit var btnCancelar: Button
    private var idPago = 0

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pagos)

        txtIdPagosdt = findViewById(R.id.txtIdPagodt)
        txtIdContratoPagosdt = findViewById(R.id.txtIdContratodt)
        txtFechaPagodt = findViewById(R.id.txtFechaPagodt)
        txtMontoPagodt = findViewById(R.id.txtMontodt)
        btnCambiarEstado = findViewById(R.id.btnCambiarEstado)
        btnGuardar = findViewById(R.id.btnGuardar)
        btnCancelar = findViewById(R.id.btnCancelar)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        spnEstado = findViewById(R.id.spnEstado)
        spnEstado.isEnabled = false

        val estadosPago = arrayOf("PENDIENTE", "PAGADO", "VENCIDO")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, estadosPago)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnEstado.adapter = adapter

        btnCambiarEstado.setOnClickListener {
            enableFields(true)
        }

        btnCancelar.setOnClickListener {
            enableFields(false)
        }

        btnGuardar.setOnClickListener {
            val fechaString = txtFechaPagodt.text.toString()

            try {
                val fecha = dateFormat.parse(fechaString)
                // Log para imprimir la fecha antes de enviarla al servidor
                Log.d("DetallePagosActivity", "Fecha a enviar al servidor: $fecha")
                val pago = Pagos(
                    idPago = txtIdPagosdt.text.toString().toInt(),
                    idContrato = txtIdContratoPagosdt.text.toString().toInt(),
                    fechaPago = fecha,
                    monto = txtMontoPagodt.text.toString().toDouble(),
                    estadoPago = spnEstado.selectedItem.toString()
                )

                showUpdateConfirmationDialog(pago)
            } catch (e: ParseException) {
                Toast.makeText(
                    this,
                    "Error al ingresar la fecha",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        val extras = intent.extras
        if (extras != null) {
            idPago = extras.getInt("idPago")

            CoroutineScope(Dispatchers.IO).launch {
                val retrofit = PagosRetrofit.getRetrofit().create(PagoService::class.java).getPago(idPago)
                val data = retrofit.body()

                runOnUiThread {
                    if (retrofit.isSuccessful) {
                        data?.let {
                            txtIdPagosdt.setText(it.idPago.toString())
                            txtIdContratoPagosdt.setText(it.idContrato.toString())
                            txtFechaPagodt.setText(dateFormat.format(it.fechaPago))
                            txtMontoPagodt.setText(it.monto.toString())

                            val posicion = estadosPago.indexOf(it.estadoPago)
                            if (posicion != -1) {
                                spnEstado.setSelection(posicion)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun enableFields(enable: Boolean) {
        spnEstado.isEnabled = enable
        btnGuardar.visibility = if (enable) View.VISIBLE else View.INVISIBLE
        btnCancelar.visibility = if (enable) View.VISIBLE else View.INVISIBLE
    }

    private fun showUpdateConfirmationDialog(pago: Pagos) {
        AlertDialog.Builder(this)
            .setMessage("¿Estás seguro que quieres actualizar el estado del pago a ${pago.estadoPago}?")
            .setCancelable(false)
            .setPositiveButton("Si") { dialogInterface, i ->
                actualizarPago(pago)
            }
            .setNegativeButton("No") { dialogInterface, i ->
                dialogInterface.cancel()
            }
            .create()
            .show()
    }

    private fun actualizarPago(pago: Pagos) {


        // Actualizar el objeto Pagos con LocalDate
        val pagoActualizado = Pagos(
            idPago = pago.idPago,
            idContrato = pago.idContrato,
            fechaPago = pago.fechaPago,
            monto = pago.monto,
            estadoPago = pago.estadoPago
        )
        // Imprimir los detalles del pago antes de enviarlo al servidor
        Log.d("DetallePagosActivity", "Pago a enviar al servidor: $pagoActualizado")

        CoroutineScope(Dispatchers.IO).launch {
            val retrofit = PagosRetrofit.getRetrofit().create(PagoService::class.java).savePagos(pagoActualizado)

            runOnUiThread {
                val intent = Intent(this@DetallePagosActivity, PagosActivity::class.java)
                Toast.makeText(this@DetallePagosActivity, "Estado de Pago actualizado correctamente", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }
        }
    }
}
