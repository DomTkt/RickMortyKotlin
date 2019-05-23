package com.ynov.kotlin.rickmorty.presentation.listscreen.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * This class allow to fill viewholder regardless of the model
 */
abstract class GenericBaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    /**
     * fill the model that you want
     */
    abstract fun fill(genericModel: T, onClick: ((String) -> Unit)?)
}