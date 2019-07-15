package tellabs.android.footballclubmvvm

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import tellabs.android.footballclubmvvm.di.networkModule
import tellabs.android.footballclubmvvm.di.repositoryModule
import tellabs.android.footballclubmvvm.di.schedulerModule
import tellabs.android.footballclubmvvm.di.viewmodelModule

class App : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Android context
            androidContext(this@App)
            // modules
            modules(listOf(networkModule, repositoryModule, viewmodelModule, schedulerModule))

        }

    }
}