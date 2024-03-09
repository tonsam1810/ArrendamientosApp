package pe.cibertec.dam.arrendamientosapp.proxy.interfaces

import pe.cibertec.dam.arrendamientosapp.models.Inquilino
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
interface InquilinoService {

    @GET("inquilino")
    // http://10.0.2.2:8080/inquilinos
    //http://34.176.37.52:8080/inquilinos
    suspend fun getInquilinos(): Response<List<Inquilino>>

    @GET("inquilinos/{id}")
    // http://34.176.37.52:8080/inquilinos/{id}
    suspend fun getInquilinos(@Path("id") id: Int): Response<Inquilino>

    @POST("inquilinos")
    // http://34.176.37.52:8080/inquilinos
    suspend fun saveInquilinos(@Body inquilino: Inquilino): Response<Inquilino>

    @DELETE("inquilinos/{id}")
    // http://34.176.37.52:8080/inquilinos/{id}
    suspend fun deleteInquilinos(@Path("id") id: Int): Response<Int>


    @PATCH("inquilinos")
    // http://34.176.37.52:8080/inquilinos
    suspend fun updateInquilinos(@Body inquilino: Inquilino): Response<Inquilino>

    @GET("inquilinos/nombre/{nombre}")
    // http://34.176.37.52:8080/inquilinos/nombre{nombre}
    suspend fun getInquilinosxnombre(@Path("nombre") nombre: String): Response<Inquilino>

    @GET("inquilinos/dni/{dni}")
    // http://34.176.37.52:8080/inquilinos/dni{dni}
    suspend fun getInquilinosxdni(@Path("dni") dni: String): Response<Inquilino>
}