package pe.cibertec.dam.arrendamientosapp.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pe.cibertec.dam.arrendamientosapp.R
import pe.cibertec.dam.arrendamientosapp.models.Inquilino

class InquilinoViewHolder (view: View, position: (Int) -> Unit) : RecyclerView.ViewHolder(view) {
    val lblIdInq: TextView = view.findViewById(R.id.lblIdInquilino)
    val lblDniInq: TextView = view.findViewById(R.id.lblDniInquilino)
    val lblNombresInq : TextView = view.findViewById(R.id.lblNombresInquilino)
    val lblEstado: TextView = view.findViewById(R.id.lblEstadoInquilino)


    init {
        itemView.setOnClickListener {
            position(adapterPosition)
        }
    }

    fun render(inq: Inquilino) {

        lblIdInq.text = inq.id_inquilino.toString()
        lblDniInq.text = inq.dni
        lblNombresInq.text = buildString {
            append(inq.nombre)
            append(" ")
            append(inq.apellido_paterno)
            append(" ")
            append(inq.apellido_materno)
        }
        lblEstado.text = inq.estado
    }
}
