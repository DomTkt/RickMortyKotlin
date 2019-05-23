package com.ynov.kotlin.rickmorty.presentation.listscreen

import android.annotation.SuppressLint
import android.content.Context
import com.ynov.kotlin.rickmorty.data.entity.CharacterListRemoteEntity
import com.ynov.kotlin.rickmorty.data.entity.EpisodeListRemoteEntity
import com.ynov.kotlin.rickmorty.data.manager.ApiManager
import io.reactivex.subjects.PublishSubject

@SuppressLint("CheckResult")
class EpisodeListFragmentPresenter(val context: Context) {

    val episodeList: PublishSubject<EpisodeListRemoteEntity?> = PublishSubject.create()

    fun updateEpisodeList(){
        ApiManager(context).getSingleEpisodeList()
            .subscribe(
                { episode : EpisodeListRemoteEntity ->
                    episodeList.onNext(episode)
                }, { throwable ->
                    episodeList.onError(throwable)
                })
    }
}