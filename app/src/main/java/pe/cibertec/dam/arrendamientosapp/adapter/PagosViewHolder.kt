package pe.cibertec.dam.arrendamientosapp.adapter

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pe.cibertec.dam.arrendamientosapp.R
import pe.cibertec.dam.arrendamientosapp.model.Pagos
import java.text.SimpleDateFormat
import java.util.Locale

class PagosViewHolder(view:View, position:(Int) ->Unit): RecyclerView.ViewHolder(view) {

    val lblIdPagos : TextView= view.findViewById(R.id.lblIdPago)
    val lblMontoPago : TextView=view.findViewById(R.id.lblMontoPago)
    val lblFechaPago : TextView=view.findViewById(R.id.lblFechaPago)
    val lblEstadoPago : TextView=view.findViewById(R.id.lblEstado)

    init {
        itemView.setOnClickListener{
            position(adapterPosition)
        }
    }

    fun render(pagos: Pagos){
        lblIdPagos.text = pagos.idPago.toString()
        lblMontoPago.text = pagos.monto.toString()

        // Verificar si la fecha de pago es nula o no se puede convertir
        if (pagos.fechaPago != null) {
            try {
                // Intentamos convertir la fecha de pago en un formato legible
                val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()) // Cambio en el formato de fecha
                val formattedDate = dateFormat.format(pagos.fechaPago)
                lblFechaPago.text = formattedDate
            } catch (e: Exception) {
                // Si hay un error al convertir la fecha, mostramos un mensaje de registro
                Log.e("PagosViewHolder", "Error al formatear la fecha de pago para el ID de pago ${pagos.idPago}: ${e.message}")
                // También podrías asignar un valor predeterminado en lugar de dejarlo en blanco
                lblFechaPago.text = "Fecha de pago no válida"
            }
        } else {
            // Si la fecha de pago es nula, mostramos un mensaje indicando que no está disponible
            lblFechaPago.text = "Fecha de pago no disponible"
        }

        lblEstadoPago.text = pagos.estadoPago
    }
}
