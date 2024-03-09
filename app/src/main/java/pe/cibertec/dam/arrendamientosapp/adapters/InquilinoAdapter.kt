package pe.cibertec.dam.arrendamientosapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.cibertec.dam.arrendamientosapp.models.Inquilino
import pe.cibertec.dam.arrendamientosapp.R

class InquilinoAdapter (
    private val inqList: List<Inquilino>,
    private val clickListener: (Inquilino) -> Unit
) :
    RecyclerView.Adapter<InquilinoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InquilinoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val vh = InquilinoViewHolder(layoutInflater.inflate(R.layout.layout_inquilino, parent, false)) {
            clickListener(inqList[it])
        }
        return vh
    }

    override fun onBindViewHolder(holder: InquilinoViewHolder, position: Int) {
        val itemInq = inqList[position]
        holder.render(itemInq)
    }

    override fun getItemCount(): Int = inqList.size

}