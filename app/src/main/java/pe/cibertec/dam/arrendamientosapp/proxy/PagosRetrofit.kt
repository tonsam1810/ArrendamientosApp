package pe.cibertec.dam.arrendamientosapp.proxy

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.threeten.bp.LocalDate
import com.google.gson.JsonDeserializer

class PagosRetrofit {

    companion object{
        fun getRetrofit(): Retrofit {
            val gson = GsonBuilder()
                .registerTypeAdapter(LocalDate::class.java, JsonDeserializer<LocalDate> { json, _, _ ->
                    LocalDate.parse(json.asJsonPrimitive.asString)
                })
                .create()
            return Retrofit.Builder()
                .baseUrl("http://192.168.1.7:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

}