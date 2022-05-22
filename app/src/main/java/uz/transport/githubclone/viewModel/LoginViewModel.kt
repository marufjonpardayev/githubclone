package uz.transport.githubclone.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.transport.githubclone.model.AccessToken
import uz.transport.githubclone.network.GithubApi
import uz.transport.githubclone.utils.Constants.TAG
import uz.transport.githubclone.utils.Constants.clientID
import uz.transport.githubclone.utils.Constants.clientSecret
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val githubApi: GithubApi
): ViewModel() {

    private val _accessToken = MutableLiveData<AccessToken>()
    val accessToken: LiveData<AccessToken>
        get() = _accessToken

    fun getAccessToken(code: String) {
        viewModelScope.launch {
            try {
                _accessToken.value = githubApi.getAccessToken(clientID, clientSecret, code)
                Log.d(TAG, "AccessToken: ${_accessToken.value?.accessToken}")
            } catch (e: Exception) {
                Log.d(TAG, "getAccessToken: error $e")
            }
        }
    }
}