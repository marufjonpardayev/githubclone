package uz.transport.githubclone.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.transport.githubclone.model.User
import uz.transport.githubclone.model.UserResponse
import uz.transport.githubclone.network.RetrofitClient

class SearchViewModel:ViewModel(){
    val listUsers=MutableLiveData<ArrayList<User>>()

    fun setSearchUser(query:String){
        RetrofitClient.apiInstance.getSearchUsers(query)
            .enqueue((object :Callback<UserResponse>{
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful){
                        listUsers.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {

                }

            }))
    }
    fun getSearchUsers():LiveData<ArrayList<User>>{
        return listUsers
    }
}