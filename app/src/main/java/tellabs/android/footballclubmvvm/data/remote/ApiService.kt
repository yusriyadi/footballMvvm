package tellabs.android.footballclubmvvm.data.remote

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import tellabs.android.footballclubmvvm.data.remote.response.LeaguesResponse
import tellabs.android.footballclubmvvm.data.remote.response.TeamDetailResponse
import tellabs.android.footballclubmvvm.data.remote.response.TeamsResponse
import tellabs.android.footballclubmvvm.utils.ENDPOINT_ALL_LEAGUES
import tellabs.android.footballclubmvvm.utils.ENDPOINT_TEAMS
import tellabs.android.footballclubmvvm.utils.ENDPOINT_TEAM_DETAIL

interface ApiService {

    @GET(ENDPOINT_ALL_LEAGUES)
    fun getAllLeagues(): Single<LeaguesResponse>

    @GET(ENDPOINT_TEAMS)
    fun getTeamsByLeague(
        @Query("l") league: String
    ): Single<TeamsResponse>

    @GET(ENDPOINT_TEAM_DETAIL)
    fun getTeamDetailById(
        @Query("id") idTeam :String
    ):Single<TeamDetailResponse>
}