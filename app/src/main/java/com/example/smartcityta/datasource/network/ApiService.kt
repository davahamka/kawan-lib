import com.example.smartcityta.datasource.network.models.AuthResponseItem
import com.example.smartcityta.datasource.network.models.PerpustakaanResponseItem
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("/perpustakaans")
    fun getPerpustakaans():Call<List<PerpustakaanResponseItem>>

    @GET("/perpustakaans/{id}")
    fun getPerpustaakn(
        @Path("id") id:String
    ): Call<PerpustakaanResponseItem>

    @GET("/auths")
    fun getAuths():Call<List<AuthResponseItem>>

    @GET("auths/{id}")
    fun getAuth(
        @Path("id") id:String
    ): Call<AuthResponseItem>

    @POST("auths")
    fun createAuth(
       @Body authResponseItem: AuthResponseItem
    ):Call<AuthResponseItem>

    @PUT("auths/{id}")
    fun editAuth(
        @Path("id") id:String,
        @Body authResponseItem: AuthResponseItem
    ):Call<AuthResponseItem>
}