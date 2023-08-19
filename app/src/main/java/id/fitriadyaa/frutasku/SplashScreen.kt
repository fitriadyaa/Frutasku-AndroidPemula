package id.fitriadyaa.frutasku

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.ComponentActivity

class SplashScreen : ComponentActivity() {
    private val SPLASH_TIME_OUT:Long = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide the status bar
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            val splashIntent = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(splashIntent)
            finish()
        }, SPLASH_TIME_OUT)
    }
}
