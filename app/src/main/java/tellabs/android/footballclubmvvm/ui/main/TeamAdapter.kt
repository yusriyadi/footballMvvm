package tellabs.android.footballclubmvvm.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_item_team.view.*
import tellabs.android.footballclubmvvm.R
import tellabs.android.footballclubmvvm.data.remote.response.TeamsResponse
import tellabs.android.footballclubmvvm.utils.loadImageFromUrl
import javax.xml.transform.ErrorListener

class TeamAdapter internal constructor(
    context: Context, val listener: onMenuListener
) : RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

    interface onMenuListener
    {
        fun onClick(t : Team)
    }


    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var teams = emptyList<Team>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val itemView = inflater.inflate(R.layout.activity_item_team, parent, false)
        return TeamViewHolder(itemView)
    }

    inner class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTeam = itemView.tvTeam
        val ivLogo = itemView.ivTeamLogo

        fun bindTeam(team : Team, listener: TeamAdapter.onMenuListener) {

            tvTeam.text = team.teamName
            ivLogo.loadImageFromUrl(team.teamLogo)


            itemView.setOnClickListener {
                listener.onClick(team)
            }

        }

    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val team = teams[position]
        holder.bindTeam(team,listener)


    }

    internal fun setTeams(teams: List<Team>) {
        this.teams = teams
        notifyDataSetChanged()
    }

    override fun getItemCount() = teams.size
}