package com.ynov.kotlin.rickmorty.presentation.listscreen.viewholder

import android.view.View
import com.squareup.picasso.Picasso
import com.ynov.kotlin.rickmorty.data.entity.ResultEpisode
import kotlinx.android.synthetic.main.episode_list_row.view.*

/**
 * This class is the ViewHolder of weather forecast, allows to fill data
 */
class EpisodeViewHolder(itemView: View) : GenericBaseViewHolder<ResultEpisode>(itemView){

    /**
     * This method allows to fill the model
     * @param genericModel is the model give in parameter class in GenericBaseViewHolder which is implemented by the current class
     */
    override fun fill(genericModel: ResultEpisode, onClick: ((String) -> Unit)?) {
        itemView.episode_name_text.text = genericModel.name
        itemView.episode_number_text.text = genericModel.episode
        Picasso.get()
            .load("https://avatarfiles.alphacoders.com/104/104391.png")
            .into(itemView.episode_image)
    }
}