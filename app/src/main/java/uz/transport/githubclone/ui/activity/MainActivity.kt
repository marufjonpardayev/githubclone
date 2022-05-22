package uz.transport.githubclone.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.transport.githubclone.R
import uz.transport.githubclone.databinding.ActivityMainBinding import uz.transport.githubclone.model.User
import uz.transport.githubclone.ui.fragment.ProfileFragment
import uz.transport.githubclone.utils.Constants
import uz.transport.githubclone.utils.SharedPref
import uz.transport.githubclone.viewModel.RepositoryViewModel
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var sharedPref: SharedPref
    private val viewModel by viewModels<RepositoryViewModel>()
     private lateinit var binding:ActivityMainBinding
     lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        sharedPref = SharedPref(this)



        //val accessToken = intent?.getStringExtra(EXTRA_ACCESS_TOKEN)
        val accessToken=sharedPref.getTokenList(Constants.EXTRA_ACCESS_TOKEN)
        accessToken.let {
            viewModel.getUserData(it)
        }


        viewModel.userData.observe(this) {

            Log.d("@@@",it.bio)
            //Toast.makeText(this, "Welcome ${it.name}", Toast.LENGTH_SHORT).show()
        }





    }

    private fun setupUI() {
        navController = findNavController(R.id.nav_host_fragment)
        binding.bottomNavigationView.setupWithNavController(navController)
    }

}