package tellabs.android.footballclubmvvm.ui.detailTeam

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import tellabs.android.footballclubmvvm.data.remote.UiState
import tellabs.android.footballclubmvvm.data.remote.repository.DetailTeamRepository
import tellabs.android.footballclubmvvm.data.remote.repository.LeagueRepository
import tellabs.android.footballclubmvvm.data.remote.repository.TeamsRepository
import tellabs.android.footballclubmvvm.data.remote.response.TeamDetailResponse
import tellabs.android.footballclubmvvm.utils.scheduler.SchedulerProvider
import tellabs.android.footballclubmvvm.utils.scheduler.with

class DetailTeamVIewModel(
    val detailTeamRepository: DetailTeamRepository,
    val schedulerProvider: SchedulerProvider
) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val detailTeamResponseliveData = MutableLiveData<UiState<TeamDetailResponse>>()

    fun getTeamDetail() : LiveData<UiState<TeamDetailResponse>> = detailTeamResponseliveData



    fun getTeam(idTeam : String)
    {
        detailTeamResponseliveData.value = UiState.Loading(true)
        detailTeamRepository.getTeamDetail(idTeam)
            .with(schedulerProvider)
            .subscribeBy(
                onSuccess = {
                    detailTeamResponseliveData.value= UiState.Success(it)
                },
                onError = {
                    detailTeamResponseliveData.value= UiState.Error(it)

                }).addTo(disposable)

    }


}