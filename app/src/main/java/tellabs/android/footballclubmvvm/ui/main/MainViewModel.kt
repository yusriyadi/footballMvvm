package tellabs.android.footballclubmvvm.ui.main

import androidx.lifecycle.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import tellabs.android.footballclubmvvm.data.remote.UiState
import tellabs.android.footballclubmvvm.data.remote.repository.LeagueRepository
import tellabs.android.footballclubmvvm.data.remote.repository.TeamsRepository
import tellabs.android.footballclubmvvm.data.remote.response.LeaguesResponse
import tellabs.android.footballclubmvvm.data.remote.response.TeamsResponse
import tellabs.android.footballclubmvvm.utils.scheduler.SchedulerProvider
import tellabs.android.footballclubmvvm.utils.scheduler.with

class MainViewModel(
    val leagueRepository: LeagueRepository,
    val teamsRepository: TeamsRepository,
    val schedulerProvider: SchedulerProvider
) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val leaguesResponseLiveData = MutableLiveData<UiState<LeaguesResponse>>()
    private val teamsResponseLiveData = MutableLiveData<UiState<TeamsResponse>>()

    fun leaugesResponse(): LiveData<UiState<LeaguesResponse>> = leaguesResponseLiveData
    fun teamsResponse(): LiveData<UiState<TeamsResponse>> = teamsResponseLiveData


    init {
        getLeagues()
    }

    private fun getLeagues() {
        leaguesResponseLiveData.value = UiState.Loading(true)
        leagueRepository.getLeagues()
            .with(schedulerProvider)
            .subscribeBy(
                onSuccess = {
                    leaguesResponseLiveData.value = UiState.Success(it)
                },
                onError = {
                    leaguesResponseLiveData.value = UiState.Error(it)
                }
            ).addTo(disposable)

    }

    fun getTeams(idLeagues: String) {
        teamsResponseLiveData.value = UiState.Loading(true)
        teamsRepository.getTeams(idLeagues)
            .with(schedulerProvider)
            .subscribeBy(
                onSuccess = {
                    teamsResponseLiveData.value = UiState.Success(it)
                },
                onError = {
                    teamsResponseLiveData.value = UiState.Error(it)
                }
            ).addTo(disposable)
    }


}