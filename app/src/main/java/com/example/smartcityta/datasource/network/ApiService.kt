import com.example.smartcityta.datasource.network.models.AuthResponseItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("auths/{id}")
    fun getAuth(
        @Path("id") id:String
    ): Call<AuthResponseItem>

    @POST("auths")
    fun createAuth(
        @Path("usename") username:String,
        @Path("password") password:String,
        @Path("email") email:String,
        @Path("nama") nama:String,
        @Path("alamat") alamat:String
    ):Call<AuthResponseItem>
}