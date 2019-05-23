package com.ynov.kotlin.rickmorty.data.manager

import android.content.Context
import com.ynov.kotlin.rickmorty.data.entity.CharacterListRemoteEntity
import com.ynov.kotlin.rickmorty.data.entity.EpisodeListRemoteEntity
import com.ynov.kotlin.rickmorty.data.entity.ResultCharacter
import com.ynov.kotlin.rickmorty.data.interfaces.ApiService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


private const val API_BASE_URL = "https://rickandmortyapi.com/"

class ApiManager(context : Context) {

    private val service: ApiService

    /**
     * the size of the maximum cache
     */
    private val cacheSize = 10 * 1024 * 1024
    /**
     * directory of the cache
     */
    private val httpCacheDirectory = File(context.cacheDir, "http-cache")
    /**
     * cache
     */
    private val cache = Cache(httpCacheDirectory, cacheSize.toLong())

    /**
     * Intercept the network cache
     */
    private val networkCacheInterceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())

        val cacheControl = CacheControl.Builder()
            .maxAge(1, TimeUnit.MINUTES)
            .build()

        response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .build()
    }

    private val httpClient: OkHttpClient = OkHttpClient.Builder()
        .cache(cache)
        .addNetworkInterceptor(networkCacheInterceptor)
        .build()

    init {
        service = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .client(httpClient)
            .build()
            .create(ApiService::class.java)
    }

    fun getSingleCharacterList() : Single<CharacterListRemoteEntity> {
        return getSingleGenericSearchFromResponse(service.retrieveCharacterListSingle())
    }

    fun getSingleEpisodeList() : Single<EpisodeListRemoteEntity> {
        return getSingleGenericSearchFromResponse(service.retrieveEpisodeListSingle())
    }

    fun getSingleCharacterDetails(characterId : Int?) : Single<ResultCharacter> {
        return getSingleGenericSearchFromResponse(service.retrieveCharacterDetails(characterId))
    }

    /**
     * get Single type from a specific response
     * @param T the type of the model that we want the response
     */
    private fun <T> getSingleGenericSearchFromResponse(observable: Single<Response<T>>?) : Single<T>{

        return Single.create { emitter ->
            observable?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeOn(Schedulers.io())
                ?.map { result ->
                    result.body()
                }
                ?.subscribe({ t: T? ->
                    if (t != null) {
                        emitter.onSuccess(t)
                    }
                }, { throwable -> println(throwable.message)
                }
                )
        }
    }
}