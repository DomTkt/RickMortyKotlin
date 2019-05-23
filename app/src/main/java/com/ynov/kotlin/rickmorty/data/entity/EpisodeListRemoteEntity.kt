package com.ynov.kotlin.rickmorty.data.entity

import com.google.gson.annotations.SerializedName

data class EpisodeListRemoteEntity(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<ResultEpisode>
)