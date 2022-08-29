package sg.carro.claims.base

import sg.carro.claims.network.ApiUrlConfig
import sg.carro.claims.network.NetworkUtil
import sg.carro.claims.utils.JsonHelper
import sg.carro.claims.utils.UserHelper

abstract class BaseApi {
    companion object {
        @JvmStatic
        fun initialize() {
            if (NetworkUtil.getInstance()?.getRetrofit() == null) {
                val carroToken = UserHelper.getAccessToken()
                carroToken?.let {
                    NetworkUtil.getInstance()
                        ?.createRetrofit(
                            ApiUrlConfig.environment, it, JsonHelper.getGson()
                        )
                }
            }
        }
    }
}