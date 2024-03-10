package pe.cibertec.dam.arrendamientosapp.model

data class Inmueble(
    val id: Long,
    val nombre: String,
    val tipo: String,
    val piso: Int,
    val maxPersonas: Int,
    val cuartos: Int,
    val precio: Double,
    val estado: String,
    val descripcion: String
)

