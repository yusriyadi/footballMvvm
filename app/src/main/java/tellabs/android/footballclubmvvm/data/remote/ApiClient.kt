package tellabs.android.footballclubmvvm.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import tellabs.android.footballclubmvvm.BuildConfig

object ApiClient {
    fun provideApiClient(okHttpClient: OkHttpClient): ApiService
    {
        return  Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

//    fun create() : ApiService{
//        val retrofit =Retrofit.Builder()
//            .baseUrl(BuildConfig.BASE_URL)
//            .client(okHttpClient())
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        return retrofit.create(ApiService::class.java)
//    }
     fun provideOkHttpClient() : OkHttpClient{
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
            ).build()

        return httpClient
    }

}