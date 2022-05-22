package uz.transport.githubclone.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import uz.transport.githubclone.R
import uz.transport.githubclone.utils.Constants
import uz.transport.githubclone.utils.Constants.EXTRA_ACCESS_TOKEN
import uz.transport.githubclone.utils.SharedPref

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private var timer: CountDownTimer? = null
    private lateinit var sharedPref: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash2)

        supportActionBar?.hide()

        sharedPref = SharedPref(this)
        val accessToken = sharedPref.accessToken

        setSplashTimer(accessToken.orEmpty())
    }

    private fun setSplashTimer(accessToken: String) {
        timer = object : CountDownTimer(2000, 1000) {
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                if (sharedPref.accessToken.isNullOrBlank()) {
                    val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else {
                    sharedPref.saveString(EXTRA_ACCESS_TOKEN,accessToken)
                    val intent=Intent(this@SplashActivity,MainActivity::class.java)
                    startActivity(intent)
                    //RepositoryActivity.startActivity(this@SplashActivity, accessToken)

                }
                finish()
            }

        }

        timer?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.let {
            it.cancel()
            timer = null
        }
    }



}