import com.example.instagramclone.utils.Utils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Utils.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}        