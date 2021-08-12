package co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitUserFactory {

    companion object{
        fun userApiRetrofit(url: String): RetrofitUsersApi{
            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(60,TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .build().create(RetrofitUsersApi::class.java)
        }
    }
}