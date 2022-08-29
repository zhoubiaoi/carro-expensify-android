package sg.carro.claims

import android.app.Application
import com.facebook.stetho.Stetho
import sg.carro.claims.BuildConfig.DEBUG
import sg.carro.claims.utils.UserHelper

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        UserHelper.initialize(this)
        if (DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}