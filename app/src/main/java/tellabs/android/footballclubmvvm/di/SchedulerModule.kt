package tellabs.android.footballclubmvvm.di

import org.koin.dsl.module
import tellabs.android.footballclubmvvm.utils.scheduler.SchedulerProvider
import tellabs.android.footballclubmvvm.utils.scheduler.AppSchedulerProvider


val schedulerModule = module {
    single<SchedulerProvider> { AppSchedulerProvider() }
}