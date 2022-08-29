package sg.carro.claims.ui.splash

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import sg.carro.claims.R
import sg.carro.claims.databinding.ActivitySplashBinding
import sg.carro.claims.utils.IntentUtil

class SplashActivity : AppCompatActivity() {
    private var activitySplashBinding : ActivitySplashBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.statusBarColor = ContextCompat.getColor(this , R.color.splash_bg)
        activitySplashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(activitySplashBinding?.root)
        activitySplashBinding?.btnLogin?.setOnClickListener{
            IntentUtil.launchLoinActivity(this)
        }
    }
}