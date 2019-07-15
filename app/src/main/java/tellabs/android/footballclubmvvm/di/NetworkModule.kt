package tellabs.android.footballclubmvvm.di


import org.koin.dsl.module
import tellabs.android.footballclubmvvm.data.remote.ApiClient

val networkModule = module {

    single { ApiClient.provideApiClient(get()) }
    single { ApiClient.provideOkHttpClient() }
}