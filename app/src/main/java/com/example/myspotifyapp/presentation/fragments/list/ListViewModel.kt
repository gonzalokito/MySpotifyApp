package com.example.myspotifyapp.presentation.fragments.list

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.AndroidViewModel
import com.example.myspotifyapp.base.BaseState
import com.example.myspotifyapp.base.NetworkManager
import com.example.myspotifyapp.base.NoInternetConnectivity
import com.example.myspotifyapp.data.SpotifyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(app: Application):AndroidViewModel(app) {

    private val state = MutableLiveData<BaseState>()
    fun getState(): LiveData<BaseState> = state


    fun requestInformation() {
        val hasInternet: Boolean = NetworkManager().isNetworkAvailable(getApplication())

        if (hasInternet) {
            //Corutinas
            viewModelScope.launch(Dispatchers.IO) {
                //Peticion al Servidor
                try {
                    state.postValue(BaseState.Loading())

                    val songs = SpotifyRepository().getAllTracks()
                    state.postValue(BaseState.Normal(ListState(songs)))

                } catch (e: Exception) {

                    state.postValue(BaseState.Error(e))

                }
            }
        } else {
            state.postValue(BaseState.Error(NoInternetConnectivity()))
        }
    }
}