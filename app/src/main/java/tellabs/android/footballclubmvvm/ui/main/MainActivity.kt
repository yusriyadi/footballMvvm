package tellabs.android.footballclubmvvm.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import tellabs.android.footballclubmvvm.R
import tellabs.android.footballclubmvvm.data.remote.UiState
import tellabs.android.footballclubmvvm.data.remote.response.TeamItem
import tellabs.android.footballclubmvvm.ui.detailTeam.DetailTeamActivity
import tellabs.android.footballclubmvvm.utils.EXTRA_TEAM_ID
import tellabs.android.footballclubmvvm.utils.getTeamsData

class MainActivity : AppCompatActivity() {


    private val viewModel: MainViewModel by inject()
    private val leaguesName = mutableListOf<String>()
    private var leagueNameSelected: String = ""

    private val teams = mutableListOf<TeamItem>()
    lateinit var adapter: TeamAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                Toast.makeText(this@MainActivity, leaguesName[position], Toast.LENGTH_SHORT).show()
                leagueNameSelected = leaguesName[position]
                getTeams()
            }

        }

        initRv()

        viewModel.leaugesResponse().observe(this, Observer {
            when (it) {
                is UiState.Loading -> {
                    Toast.makeText(this, "lagi loading gan", Toast.LENGTH_SHORT).show()
                }
                is UiState.Success -> {
                    if (it != null) {
                        it.data.leagues.forEach {
                            leaguesName.add(it.strLeague)
                        }
                        setSpiner(leaguesName)
                    }
                }
                is UiState.Error -> {
                    Toast.makeText(this, "${it.throwable}", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    fun getTeams()
    {

        initRv()
        viewModel.getTeams(leagueNameSelected)
        viewModel.teamsResponse().observe( this, Observer {
            when(it)
            {
                is UiState.Loading->
                {
                    Toast.makeText(this, "lagi loading gan", Toast.LENGTH_SHORT).show()
                }
                is UiState.Success ->
                {
                   // Toast.makeText(this, it.data.teams[0].strTeam, Toast.LENGTH_SHORT).show()
                    if (it!=null)
                    {
                        val team : List<TeamItem> = it.data.teams

                        teams.addAll(teams)
                        adapter.setTeams(getTeamsData(team))
                    }
                }
                is UiState.Error->
                {
                    Toast.makeText(this, "error cu : ${it.throwable}", Toast.LENGTH_SHORT).show()
                    Log.d("error", "${it.throwable}")
                }
            }
        })
    }

    fun initRv()
    {
        val recyclerView = rvTeam
         adapter= TeamAdapter(this, object : TeamAdapter.onMenuListener{
             override fun onClick(t: Team) {


                 val intent = Intent(this@MainActivity, DetailTeamActivity::class.java)
                 intent.putExtra(EXTRA_TEAM_ID, t.teamId)
                 startActivity(intent)
             }

         })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        }

    fun setSpiner(data: List<String>) {
        val adapter = ArrayAdapter(this, R.layout.spinner_item, data)

        adapter.setDropDownViewResource(R.layout.spinner_dropdown)
        spinner.adapter = adapter
    }
}
