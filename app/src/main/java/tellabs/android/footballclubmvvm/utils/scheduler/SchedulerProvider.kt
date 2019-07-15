package tellabs.android.footballclubmvvm.utils.scheduler

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun io() : Scheduler

    fun computation() : Scheduler

    fun ui() : Scheduler

}