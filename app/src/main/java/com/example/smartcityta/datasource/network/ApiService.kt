import com.example.smartcityta.datasource.network.models.AuthResponseItem
import com.example.smartcityta.datasource.network.models.PerpustakaanResponseItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

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
}