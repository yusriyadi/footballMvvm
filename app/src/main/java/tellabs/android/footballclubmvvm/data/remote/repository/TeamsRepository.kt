package tellabs.android.footballclubmvvm.data.remote.repository

import io.reactivex.Single
import tellabs.android.footballclubmvvm.data.remote.ApiService
import tellabs.android.footballclubmvvm.data.remote.response.TeamsResponse

class TeamsRepository(val apiService: ApiService) {

    fun getTeams(league : String): Single<TeamsResponse> = apiService.getTeamsByLeague(league)
}