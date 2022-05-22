package uz.transport.githubclone.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPref(context: Context) {
    private var preference = context.getSharedPreferences(PREF_NAME, PREF_MODE)

    companion object{
        private const val PREF_NAME = "GithubApp"
        private const val PREF_MODE = Context.MODE_PRIVATE
        private val PREF_ACCESS_TOKEN = Pair("ACCESS_TOKEN", "")
    }
    fun saveString(key:String,token:String){
        val prefsEditor=preference.edit()
        prefsEditor.putString(key,token)
        prefsEditor.apply()
    }
    fun  getTokenList(key:String):String{
        val token:String?=preference.getString(key,null)
        return token!!

    }

    var accessToken: String?
        get() = preference.getString(PREF_ACCESS_TOKEN.first, PREF_ACCESS_TOKEN.second)
        set(value) = preference.edit{
            it.putString(PREF_ACCESS_TOKEN.first, value)
        }
}


private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit){
    val editor = edit()
    operation(editor)
    editor.apply()


}