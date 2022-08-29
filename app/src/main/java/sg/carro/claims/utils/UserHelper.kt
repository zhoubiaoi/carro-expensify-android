package sg.carro.claims.utils

import android.content.Context
import android.content.SharedPreferences

object UserHelper {
    private const val USER_HELPER = "user_helper"
    @Volatile
    private var accessToken: String? = null
    private val saveLock = Any()
    private val accessLock = Any()
    private const val ACCESS_TOKEN = "access_token"
    private var sPref: SharedPreferences? = null
    fun initialize(context: Context?) {
        if (sPref == null) {
            sPref = context?.getSharedPreferences(USER_HELPER, Context.MODE_PRIVATE)
        }
    }

    @JvmStatic
    fun saveAccessToken(token: String?) {
        synchronized(saveLock) {
            val editor = sPref!!.edit()
            editor.putString(
                ACCESS_TOKEN,
                token
            )
            editor.apply()
            editor.commit()
            accessToken = token
        }
    }

    @JvmStatic
    fun getAccessToken(): String? {
        synchronized(accessLock) {
            return if (!accessToken.isNullOrEmpty()) {
                accessToken
            } else {
                accessToken = sPref?.getString(
                    ACCESS_TOKEN, ""
                )
                accessToken
            }
        }
    }
}