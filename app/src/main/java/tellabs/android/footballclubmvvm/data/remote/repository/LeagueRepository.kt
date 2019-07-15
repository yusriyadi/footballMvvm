package tellabs.android.footballclubmvvm.data.remote.repository

import androidx.lifecycle.LiveData
import io.reactivex.Single
import tellabs.android.footballclubmvvm.data.remote.ApiService
import tellabs.android.footballclubmvvm.data.remote.response.League
import tellabs.android.footballclubmvvm.data.remote.response.LeaguesResponse

class LeagueRepository(val apiService: ApiService)
{
//    val leagues : LiveData<List<League>> =apiService.getAllLeagues()
    fun getLeagues() : Single<LeaguesResponse> =apiService.getAllLeagues()
}