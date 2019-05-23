package com.ynov.kotlin.rickmorty.presentation.detailcharacter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.listscreen.CharacterListFragment

class CharacterDetailsActivity : AppCompatActivity() {

    var characterId : Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)
        characterId = intent?.extras?.getInt(CharacterListFragment.CHARACTER_ID)
        displayFragmentCharacterDetails()
    }

    private fun displayFragmentCharacterDetails(){
        val fragmentCharacterDetails : CharacterDetailsFragment =
            CharacterDetailsFragment.newInstance(characterId = characterId)
        supportFragmentManager.beginTransaction().replace(R.id.character_details_fragment, fragmentCharacterDetails, CharacterDetailsFragment::class.toString()).commit()
    }
}
