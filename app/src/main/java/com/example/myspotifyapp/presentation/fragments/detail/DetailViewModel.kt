package com.example.myspotifyapp.presentation.fragments.detail

import android.app.Application
import androidx.lifecycle.*
import com.example.myspotifyapp.base.BaseState
import com.example.myspotifyapp.base.NetworkManager
import com.example.myspotifyapp.base.NoInternetConnectivity
import com.example.myspotifyapp.data.SpotifyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DetailViewModel(app: Application): AndroidViewModel(app) {

    private val state = MutableLiveData<BaseState>()
    fun getState(): LiveData<BaseState> = state


    fun requestInformation(id:String) {
        val hasInternet: Boolean = NetworkManager().isNetworkAvailable(getApplication())

        if (hasInternet) {
            //Corutinas
            viewModelScope.launch(Dispatchers.IO) {
                //Peticion al Servidor
                try {
                    state.postValue(BaseState.Loading())

                    val song = SpotifyRepository().getSong(id)
                    state.postValue(BaseState.Normal(SongState(song)))

                } catch (e: Exception) {

                    state.postValue(BaseState.Error(e))

                }
            }
        } else {
            state.postValue(BaseState.Error(NoInternetConnectivity()))
        }
    }
}