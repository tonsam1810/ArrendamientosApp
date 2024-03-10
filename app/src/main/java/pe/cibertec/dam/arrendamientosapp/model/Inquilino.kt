package pe.cibertec.dam.arrendamientosapp.model
import java.util.Date
data class Inquilino(
    val id: Long,
    val nombre: String,
    val apellidoPaterno: String,
    val apellidoMaterno: String,
    val dni: String,
    val telefono: String,
    val fechaNacimiento: Date,
    val estado: String
)