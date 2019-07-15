package tellabs.android.footballclubmvvm.utils

import tellabs.android.footballclubmvvm.data.remote.response.TeamItem
import tellabs.android.footballclubmvvm.ui.detailTeam.TeamDetail
import tellabs.android.footballclubmvvm.ui.main.Team


    fun getTeamsData(teams: List<TeamItem>): List<Team> {

        val list = mutableListOf<Team>()

        teams.forEach {
            list.add(Team(it.idTeam, it.strTeam, it.strTeamBadge))
        }

        return list
    }

fun getTeamsDataDetail(teamsDetail: List<TeamItem>): List<TeamDetail> {

    val list = mutableListOf<TeamDetail>()

    teamsDetail.forEach {
        list.add(TeamDetail(it.idTeam, it.strTeam, it.intFormedYear, it.strDescriptionEN,it.strTeamFanart1))
    }

    return list
}

