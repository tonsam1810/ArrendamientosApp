package pe.cibertec.dam.arrendamientosapp.proxy.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InquilinoRetrofit {

    companion object {
        fun getRetrofit(): Retrofit {
            val url:String = "http://34.176.37.52:8080/"
            return Retrofit.Builder()
                .baseUrl(url) // 10.0.2.2 = localhost
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}