package tellabs.android.footballclubmvvm.data.remote.response

data class LeaguesResponse(
    val leagues: List<League>
)

data class League(
    val idLeague: String,
    val strLeague: String,
    val strLeagueAlternate: String,
    val strSport: String
)