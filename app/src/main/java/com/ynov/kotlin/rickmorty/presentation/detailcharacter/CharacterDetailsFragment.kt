package com.ynov.kotlin.rickmorty.presentation.detailcharacter


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.entity.ResultCharacter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_character_details.*

class CharacterDetailsFragment(private val characterId: Int?) : Fragment() {

    @SuppressLint("CheckResult")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_character_details, container, false)
        val presenter =
            CharacterDetailsFragmentPresenter(requireContext())

        presenter.updateCharacterDetails(characterId)
        presenter.characterDetails
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe( { characterResult ->
                setCharacterDetailsData(characterResult)
            }, { throwable ->
                Snackbar.make(view,throwable.message.toString(), Snackbar.LENGTH_LONG).show()
            })

        return view
    }

    companion object {
        fun newInstance(characterId : Int?): CharacterDetailsFragment {
            return CharacterDetailsFragment(characterId)
        }
    }

    private fun setCharacterDetailsData(characterDetailsData: ResultCharacter?) {
        character_details_fragment_name.text = characterDetailsData?.name
        character_details_fragment_gender.text = characterDetailsData?.gender
        character_details_fragment_species.text = characterDetailsData?.species

        Picasso.get()
            .load(characterDetailsData?.image)
            .into(character_details_fragment_image)
    }

}
