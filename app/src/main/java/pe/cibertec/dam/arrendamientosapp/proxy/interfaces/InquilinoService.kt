package pe.cibertec.dam.arrendamientosapp.proxy.interfaces

interface InquilinoService {

    @GET("inquilino")
    // http://10.0.2.2:8080/renting-car/v1/cars
    suspend fun getCars(): Response<List<Auto>>

    @GET("car/{carId}")
    // http://10.0.2.2:8080/renting-car/v1/car/{carId}
    suspend fun getCars(@Path("carId") carId: Int): Response<Auto>

    @POST("car")
    // http://10.0.2.2:8080/renting-car/v1/car
    suspend fun saveCar(@Body car: Auto): Response<Auto>

    @DELETE("car/{carId}")
    // http://10.0.2.2:8080/renting-car/v1/car/{carId}
    suspend fun deleteCar(@Path("carId") carId: Int): Response<Int>


    @PATCH("car")
    // http://10.0.2.2:8080/renting-car/v1/car
    suspend fun updateCar(@Body car: Auto): Response<Auto>
}