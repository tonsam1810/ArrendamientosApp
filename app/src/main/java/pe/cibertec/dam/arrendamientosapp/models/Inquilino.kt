package pe.cibertec.dam.arrendamientosapp.models

data class Inquilino (
    val id_inquilino : Int,
    val nombre : String,
    val apellido_paterno : String,
    val apellido_materno : String,
    val dni : String,
    val telefono : String,
    val fecha_nacimiento : String,
    val estado: String,
    //val foto : String,
    //val estado_civil: Int
)
