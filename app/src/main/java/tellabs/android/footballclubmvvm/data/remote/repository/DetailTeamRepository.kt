package tellabs.android.footballclubmvvm.data.remote.repository

import io.reactivex.Single
import tellabs.android.footballclubmvvm.data.remote.ApiService
import tellabs.android.footballclubmvvm.data.remote.response.TeamDetailResponse

class DetailTeamRepository(val apiService: ApiService)
{
    fun getTeamDetail(id : String) : Single<TeamDetailResponse> = apiService.getTeamDetailById(id)
}