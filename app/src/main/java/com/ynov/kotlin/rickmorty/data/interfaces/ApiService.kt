package com.ynov.kotlin.rickmorty.data.interfaces

import com.ynov.kotlin.rickmorty.data.entity.CharacterListRemoteEntity
import com.ynov.kotlin.rickmorty.data.entity.EpisodeListRemoteEntity
import com.ynov.kotlin.rickmorty.data.entity.ResultCharacter
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("api/character")
    fun retrieveCharacterListSingle(): Single<Response<CharacterListRemoteEntity>>

    @GET("api/episode")
    fun retrieveEpisodeListSingle(): Single<Response<EpisodeListRemoteEntity>>

    @GET("api/character/{id}")
    fun retrieveCharacterDetails(@Path("id") id : Int?): Single<Response<ResultCharacter>>

}