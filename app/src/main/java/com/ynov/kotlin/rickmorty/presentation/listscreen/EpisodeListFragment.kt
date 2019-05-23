package com.ynov.kotlin.rickmorty.presentation.listscreen


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.entity.CharacterListRemoteEntity
import com.ynov.kotlin.rickmorty.data.entity.EpisodeListRemoteEntity
import com.ynov.kotlin.rickmorty.data.entity.ResultCharacter
import com.ynov.kotlin.rickmorty.data.entity.ResultEpisode
import com.ynov.kotlin.rickmorty.presentation.listscreen.adapter.CharacterAdapter
import com.ynov.kotlin.rickmorty.presentation.listscreen.adapter.EpisodeAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_character_list.*
import kotlinx.android.synthetic.main.fragment_episode_list.*
import java.util.ArrayList

@SuppressLint("CheckResult")
class EpisodeListFragment : Fragment(){


    private var episodeItemList : List<ResultEpisode>? = ArrayList()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_episode_list, container, false)

        val presenter = EpisodeListFragmentPresenter(requireContext())

        presenter.updateEpisodeList()
        presenter.episodeList
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe( { episodeResult ->
                setEpisodeListData(episodeResult)
            }, { throwable ->
                Toast.makeText(requireContext(),"Erreur = " + throwable.message, Toast.LENGTH_SHORT).show()
            })

        return view
    }

    /**
     * set the data episode to the view
     */
    private fun setEpisodeListData(episodeListData: EpisodeListRemoteEntity?) {
        this.episodeItemList = episodeListData?.results
        episode_list_fragment_recyclerView.layoutManager = LinearLayoutManager(this@EpisodeListFragment.context)
        episode_list_fragment_recyclerView.adapter =
            EpisodeAdapter(episodeListData!!.results)

    }

    companion object {
        fun newInstance(): EpisodeListFragment {
            return EpisodeListFragment()
        }

        const val EPISODE_ID = "EPISODE_ID_BUNDLE"
    }

}
