package pe.cibertec.dam.arrendamientosapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemLongClickListener
import androidx.recyclerview.widget.RecyclerView
import pe.cibertec.dam.arrendamientosapp.R
import pe.cibertec.dam.arrendamientosapp.model.Pagos

class PagosAdapter(private val PagosList:List<Pagos>,
    private val clickListener: (Pagos)-> Unit):
RecyclerView.Adapter<PagosViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagosViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        val vh = PagosViewHolder(layoutInflater.inflate(R.layout.layout_pagos,parent,false)){
            clickListener(PagosList[it])
        }
        return vh
        TODO("Not yet implemented")

    }

    override fun onBindViewHolder(holder: PagosViewHolder, position: Int) {
        val itemPagos=PagosList[position]
        holder.render(itemPagos)
    }

    override fun getItemCount(): Int= PagosList.size
}