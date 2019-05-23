package com.ynov.kotlin.rickmorty.presentation.listscreen

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ynov.kotlin.rickmorty.R

class ListRMActivity : AppCompatActivity() {

    lateinit var toolbar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayCharacterListFragment()

        toolbar = supportActionBar!!
        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationView)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_character -> {
                toolbar.title = "Character"
                displayCharacterListFragment()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_episode -> {
                toolbar.title = "Episode"
                displayEpisodeListFragment()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun displayCharacterListFragment(){
        val fragmentCharacterListFragment : CharacterListFragment = CharacterListFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.character_activity_list_fragment_recyclerView, fragmentCharacterListFragment, CharacterListFragment::class.toString()).commit();
    }

    private fun displayEpisodeListFragment(){
        val fragmentEpisodeListFragment : EpisodeListFragment = EpisodeListFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.character_activity_list_fragment_recyclerView, fragmentEpisodeListFragment, EpisodeListFragment::class.toString()).commit();
    }

}
