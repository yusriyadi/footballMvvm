package tellabs.android.footballclubmvvm.ui.detailTeam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.koin.android.ext.android.inject
import tellabs.android.footballclubmvvm.R
import tellabs.android.footballclubmvvm.data.remote.UiState
import tellabs.android.footballclubmvvm.utils.*

class DetailTeamActivity : AppCompatActivity() {

    private val viewModel: DetailTeamVIewModel by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        progressBar.gone()
        scrollView2.gone()

        val id: String = intent.getStringExtra(EXTRA_TEAM_ID)

        viewModel.getTeam(id)
        viewModel.getTeamDetail().observe(this, Observer {

            when (it) {
                is UiState.Loading -> {
                    progressBar.visible()
                }
                is UiState.Success -> {
                    progressBar.gone()
                   scrollView2.visible()

                        toast(getTeamsDataDetail(it.data.teams)[0].teamName)

                        tvTeamName.text = getTeamsDataDetail(it.data.teams)[0].teamName
                        tvDesc.text = getTeamsDataDetail(it.data.teams)[0].teamDescription
                        tvFormed.text = getTeamsDataDetail(it.data.teams)[0].teamFormed
                        imBanner.loadImageFromUrl(getTeamsDataDetail(it.data.teams)[0].teamBanner)


                }
                is UiState.Error -> {
                    Toast.makeText(this, "error cu : ${it.throwable}", Toast.LENGTH_SHORT).show()
                    Log.d("error", "${it.throwable}")
                }
            }

        })






    }



}
