package pe.cibertec.dam.arrendamientosapp.model

import java.io.Serializable
import java.util.Date

data class Contrato(
    val id: Int,
    val idInquilino: Long,
    val idInmueble: Long,
    val montoTotal: Double,
    val fechaInicio: Date?,
    val periodo: Int,
    val fechaFin: Date?,
    val detalleContrato: String,
    val estado: String
) {
    constructor(id: Int) : this( 0,0,0,0.0,null,0,
        null,"","VIGENTE")
}



