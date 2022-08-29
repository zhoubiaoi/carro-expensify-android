package sg.carro.claims.ui.login

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import sg.carro.claims.entity.OAuth
import sg.carro.claims.network.ApiUrlConfig.systemOauthUrl
import sg.carro.claims.network.LoginApi.loginApi

class LoginPresenter(private val splashView: ILoginView) {
    fun getSystemOauthToken(
        grantType: String?,
        clientId: String?,
        clientSecret: String?
    ) {
        val url = systemOauthUrl
            loginApi()!!.getSystemOauthToken(url , grantType, clientId, clientSecret)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Observer<OAuth> {
                    override fun onSubscribe(d: Disposable) {}
                    override fun onNext(value: OAuth) {

                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onComplete() {}
                })
        }
}