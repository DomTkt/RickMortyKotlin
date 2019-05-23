package com.ynov.kotlin.rickmorty.presentation.listscreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.entity.ResultEpisode
import com.ynov.kotlin.rickmorty.presentation.listscreen.viewholder.EpisodeViewHolder


/**
 * @param items contains the elements of type ForecastItem to display
 * @param clickListener allows to get the item click on the list
 *
 */
class EpisodeAdapter(private val items : List<ResultEpisode>) : RecyclerView.Adapter<EpisodeViewHolder>() {

    /**
     * @param parent
     * @param viewType
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.episode_list_row, parent, false))
    }

    /**
     * @return the size of array list which is in parameter in adapter
     */
    override fun getItemCount(): Int {
        return items.size
    }

    /**
     * @param holder
     * @param position position of the cell
     * filled each cell according to the position of the item in the list
     */
    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.fill(items.get(position),null)
    }


}