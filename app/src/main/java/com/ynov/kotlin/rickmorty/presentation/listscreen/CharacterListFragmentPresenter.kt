package com.ynov.kotlin.rickmorty.presentation.listscreen

import android.annotation.SuppressLint
import android.content.Context
import com.ynov.kotlin.rickmorty.data.manager.ApiManager
import com.ynov.kotlin.rickmorty.data.entity.CharacterListRemoteEntity
import io.reactivex.subjects.PublishSubject

@SuppressLint("CheckResult")
class CharacterListFragmentPresenter(val context: Context) {

    val characterList: PublishSubject<CharacterListRemoteEntity?> = PublishSubject.create()

    fun updateCharacterList(){
        ApiManager(context).getSingleCharacterList()
            .subscribe(
                { character : CharacterListRemoteEntity ->
                    characterList.onNext(character)
                }, { throwable ->
                    characterList.onError(throwable)
                })
    }
}