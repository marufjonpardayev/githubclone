package uz.transport.githubclone.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.core.view.isVisible
import dagger.hilt.android.AndroidEntryPoint
import uz.transport.githubclone.adapter.RepositoryAdapter
import uz.transport.githubclone.databinding.ActivityRepositoryBinding
import uz.transport.githubclone.utils.Constants.EXTRA_ACCESS_TOKEN
import uz.transport.githubclone.utils.SharedPref
import uz.transport.githubclone.viewModel.RepositoryViewModel

@AndroidEntryPoint
class RepositoryActivity : AppCompatActivity() {
    private lateinit var sharedPref: SharedPref

    companion object {
        @JvmStatic
        fun startActivity(context: Context, accessToken: String) {
            val intent = Intent(context, RepositoryActivity::class.java)
            intent.putExtra(EXTRA_ACCESS_TOKEN, accessToken)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityRepositoryBinding
    private val viewModel by viewModels<RepositoryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepositoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPref = SharedPref(this)

        val adapter = RepositoryAdapter()
        binding.rvRepository.adapter = adapter

        //val accessToken = intent?.getStringExtra(EXTRA_ACCESS_TOKEN)
        val accessToken=sharedPref.getTokenList(EXTRA_ACCESS_TOKEN)
        accessToken.let {
            viewModel.getUserData(it)
            viewModel.getRepositories(it)
        }

        viewModel.repositories.observe(this) {
            adapter.submitList(it)
            binding.rvRepository.isVisible = true
            binding.pbRepo.isGone = true
        }

        viewModel.userData.observe(this) {
            Toast.makeText(this, "Welcome ${it.name}", Toast.LENGTH_SHORT).show()
        }
    }


}