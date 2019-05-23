package com.ynov.kotlin.rickmorty.presentation.listscreen.viewholder

import android.view.View
import com.squareup.picasso.Picasso
import com.ynov.kotlin.rickmorty.data.entity.ResultCharacter
import kotlinx.android.synthetic.main.character_list_row.view.*

/**
 * This class is the ViewHolder of weather forecast, allows to fill data
 */
class CharacterViewHolder(itemView: View) : GenericBaseViewHolder<ResultCharacter>(itemView){


    /**
     * This method allows to fill the model
     * @param characterModel is the model give in parameter class in GenericBaseViewHolder which is implemented by the current class
     */
    override fun fill(genericModel: ResultCharacter, onClick: ((String) -> Unit)?) {
        itemView.setOnClickListener { onClick?.let { it1 -> it1(genericModel.id.toString()) } }
        itemView.mainactivity_name_text.text = genericModel.name
        itemView.mainactivity_species_text.text = genericModel.species
        Picasso.get()
            .load(genericModel.image)
            .into(itemView.mainactivity_character_image)
    }

}