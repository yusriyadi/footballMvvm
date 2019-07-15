package tellabs.android.footballclubmvvm.di


import org.koin.dsl.module
import tellabs.android.footballclubmvvm.data.remote.repository.DetailTeamRepository
import tellabs.android.footballclubmvvm.data.remote.repository.LeagueRepository
import tellabs.android.footballclubmvvm.data.remote.repository.TeamsRepository

val repositoryModule = module {
    single { LeagueRepository(get()) }
    single { TeamsRepository(get()) }
    single { DetailTeamRepository(get()) }
}