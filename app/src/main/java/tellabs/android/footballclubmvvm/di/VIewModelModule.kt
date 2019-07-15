package tellabs.android.footballclubmvvm.di


import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import tellabs.android.footballclubmvvm.ui.detailTeam.DetailTeamVIewModel
import tellabs.android.footballclubmvvm.ui.main.MainViewModel

val viewmodelModule = module{
    viewModel { MainViewModel(get(),get(),get()) }
    viewModel { DetailTeamVIewModel(get(),get()) }
}