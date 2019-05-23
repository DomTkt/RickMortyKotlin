package com.ynov.kotlin.rickmorty.presentation.detailcharacter

import android.annotation.SuppressLint
import android.content.Context
import com.ynov.kotlin.rickmorty.data.manager.ApiManager
import com.ynov.kotlin.rickmorty.data.entity.ResultCharacter
import io.reactivex.subjects.PublishSubject

class CharacterDetailsFragmentPresenter(val context : Context) {

    val characterDetails: PublishSubject<ResultCharacter?> = PublishSubject.create()

    @SuppressLint("CheckResult")
    fun updateCharacterDetails(characterId : Int?){
        ApiManager(context).getSingleCharacterDetails(characterId)
            .subscribe(
                { character : ResultCharacter ->
                    characterDetails.onNext(character)
                }, { throwable ->
                    characterDetails.onError(throwable)
                })
    }
}