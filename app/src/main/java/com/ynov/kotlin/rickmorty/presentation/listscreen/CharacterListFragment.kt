package com.ynov.kotlin.rickmorty.presentation.listscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar

import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.listscreen.adapter.CharacterAdapter
import com.ynov.kotlin.rickmorty.data.entity.CharacterListRemoteEntity
import com.ynov.kotlin.rickmorty.data.entity.ResultCharacter
import com.ynov.kotlin.rickmorty.presentation.detailcharacter.CharacterDetailsActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_character_list.*
import java.util.ArrayList

@SuppressLint("CheckResult")
class CharacterListFragment : Fragment(){

    private var characterItemList : MutableList<ResultCharacter>? = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_character_list, container, false)
        //J'ai déclarer ici en val, car il n'arrive pas a trouver mon id quand je l'utilise directement dans le layout je comprends pas pourquoi
        val swipeLayout: SwipeRefreshLayout? = view.findViewById(R.id.character_list_fragment_swipe_refresh)
        loadCharacter(view)

        swipeLayout?.setOnRefreshListener {
            loadCharacter(view)
            swipeLayout.isRefreshing = false
        }

        return view
    }

    companion object {
        fun newInstance(): CharacterListFragment {
            return CharacterListFragment()
        }

        const val CHARACTER_ID = "CHARACTER_ID_BUNDLE"
    }

    private fun loadCharacter(view : View){
        val presenter = CharacterListFragmentPresenter(requireContext())
        presenter.updateCharacterList()
        presenter.characterList
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe( { characterResult ->
                setCharacterListData(characterResult)
            }, { throwable ->
                Snackbar.make(view,throwable.message.toString(),Snackbar.LENGTH_LONG).show()
            })
    }

    /**
     * set the data character to the view
     */
    private fun setCharacterListData(characterListData: CharacterListRemoteEntity?) {
        this.characterItemList?.clear()
        this.characterItemList?.addAll(characterListData!!.results)
        character_list_fragment_recyclerView.layoutManager = LinearLayoutManager(this@CharacterListFragment.context)
        val characterAdapter = CharacterAdapter(characterListData!!.results)
        characterAdapter.onClick = {
            val intent = Intent(context, CharacterDetailsActivity::class.java).apply {
                putExtra(CHARACTER_ID, it.toInt())
            }
            startActivity(intent)
        }
        character_list_fragment_recyclerView.adapter = characterAdapter


    }

}
