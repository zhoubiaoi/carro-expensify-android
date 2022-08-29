package sg.carro.claims.ui.login

import android.os.Bundle
import android.os.Handler
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import sg.carro.claims.R
import sg.carro.claims.base.BaseActivity
import sg.carro.claims.databinding.ActivityLoginBinding
import sg.carro.claims.event.LoginEvent
import sg.carro.claims.utils.DeviceUtil.hideSoftKeyboard
import sg.carro.claims.utils.IntentUtil.Companion.launchMainActivity

class LoginActivity : BaseActivity() , ILoginView {
    private var activityLoginBinding: ActivityLoginBinding? = null
    private var presenter: LoginPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this)
        }
        activityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(activityLoginBinding?.root)
        presenter = LoginPresenter(this)
        presenter?.getSystemOauthToken(
            "client_credentials",
            "2",
            "HjporA2OU0KJOPCKpC0nj4aGCYTrUX5iO5B0rYee"
        )
        initView()
    }
    private fun initView(){
        activityLoginBinding?.apply {
            customToolbar.tvTitle.text = getString(R.string.login_page_title)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun btnLoginEvent(loginEvent: LoginEvent){
        hideSoftKeyboard(this)
        activityLoginBinding?.apply {
            layoutLogin.hideView()
            layoutLoginSuccess.showSuccessView()
        }
        val handler = Handler(mainLooper)
        handler.postDelayed({ launchMainActivity(this) }, 2000)
    }

    override fun onDestroy() {
        super.onDestroy()
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this)
        }
    }
}