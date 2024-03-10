package pe.cibertec.dam.arrendamientosapp.model

import java.util.Date

data class Pagos(
    val idPago: Int,
    val idContrato: Int,
    val fechaPago: Date,
    val monto: Double,
    val estadoPago: String

)



