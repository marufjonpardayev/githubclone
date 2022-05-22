package uz.transport.githubclone.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import uz.transport.githubclone.databinding.ActivityProfileBinding
import uz.transport.githubclone.model.User
import uz.transport.githubclone.utils.Constants.EXTRA_USER_DATA
import uz.transport.githubclone.utils.SharedPref

class ProfileActivity : AppCompatActivity() {
    companion object{
        @JvmStatic
        fun startActivity(context: Context, user: User?){
            val intent = Intent(context, ProfileActivity::class.java)
            intent.putExtra(EXTRA_USER_DATA, user)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityProfileBinding
    private lateinit var sharedPref: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogout.setOnClickListener { logout() }

        sharedPref = SharedPref(this)

        setupProfileData()
    }

    private fun logout() {
        sharedPref.accessToken = ""
        Toast.makeText(this, "Logout success!", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun setupProfileData() {
        val user = intent.getParcelableExtra<User>(EXTRA_USER_DATA)
        binding.tvName.text = user?.name
        binding.tvUsername.text = user?.username
        binding.tvEmail.text = user?.email
        binding.tvLocation.text = user?.location
        binding.tvBio.text = user?.bio
        binding.tvFollowers.text = user?.followers.toString()
        binding.tvFollowing.text = user?.following.toString()
    }
}