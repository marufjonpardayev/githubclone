package uz.transport.githubclone.ui.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import dagger.hilt.android.AndroidEntryPoint
import uz.transport.githubclone.databinding.ActivityLoginBinding
import uz.transport.githubclone.databinding.CustomDialogLoadingBinding
import uz.transport.githubclone.utils.Constants.TAG
import uz.transport.githubclone.utils.Constants.clientID
import uz.transport.githubclone.utils.Constants.oauthLoginURL
import uz.transport.githubclone.utils.SharedPref
import uz.transport.githubclone.viewModel.LoginViewModel

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPref: SharedPref
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        sharedPref = SharedPref(this)

        binding.btnLogin.setOnClickListener { processLogin() }

        viewModel.accessToken.observe(this) { accessToken ->
            RepositoryActivity.startActivity(this, accessToken.accessToken)
            sharedPref.accessToken = accessToken.accessToken
        }
    }

    override fun onResume() {
        super.onResume()
        val uri: Uri? = intent?.data
        if (uri != null){
            val code = uri.getQueryParameter("code")
            if(code != null){
                showDialog()
                viewModel.getAccessToken(code)
                Toast.makeText(this, "Login success!", Toast.LENGTH_SHORT).show()
            } else if((uri.getQueryParameter("error")) != null){
                Log.d(TAG, "error: ${uri.getQueryParameter("error")}")
                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun processLogin() {
        showDialog()
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(
            "$oauthLoginURL?client_id=$clientID&scope=repo"))

        startActivity(intent)
    }

    private fun showDialog(){
        val dialogBinding = CustomDialogLoadingBinding.inflate(layoutInflater)
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder
            .setView(dialogBinding.root)
            .create()
            .show()
    }
}