package sg.carro.claims.network

import android.text.TextUtils
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import okhttp3.*
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import org.greenrobot.eventbus.EventBus
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import sg.carro.claims.BuildConfig
import sg.carro.claims.event.UnauthorizedEvent
import java.util.concurrent.TimeUnit

class NetworkUtil {
    private val TIMEOUT_SECONDS = 60

    private var httpClient: OkHttpClient? = null
    private var retrofit: Retrofit? = null
    private val MEDIA_TYPE_JSON = "application/json"

    private var stethoInterceptor: StethoInterceptor? = null
    private var interceptor: HttpLoggingInterceptor? = null

    constructor() {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
        builder.readTimeout(TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
        builder.writeTimeout(TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
        httpClient = builder.build()
    }

    companion object{
        private var _instance: NetworkUtil? = null

        fun getInstance(): NetworkUtil? {
            if (_instance == null) {
                synchronized(NetworkUtil::class.java) {
                    if (_instance == null) {
                        _instance = NetworkUtil()
                    }
                }
            }
            return _instance
        }
    }

    fun createRetrofit(baseUrl: String?, token: String, mGson: Gson?) {
        interceptHeaders(token)
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(mGson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()
    }


    fun getRetrofit(): Retrofit? {
        return retrofit
    }

    private fun interceptHeaders(token: String) {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .addInterceptor(Interceptor { chain: Interceptor.Chain ->
                val authRequest: Request.Builder = chain.request().newBuilder()
                    .header("Accept", MEDIA_TYPE_JSON)
                    .header("user-agent", "android")
                if (!TextUtils.isEmpty(token)) {
                    authRequest.header("Authorization", "Bearer $token")
                }
                val response = chain.proceed(authRequest.build())
                if (response.code == 401) {
                    EventBus.getDefault().post(UnauthorizedEvent())
                }
                response
            })
            .authenticator(object : Authenticator {
                private fun responseCount(response: Response): Int {
                    var response = response
                    var result = 1
                    while (response.priorResponse.also { response = it!! } != null) {
                        result++
                    }
                    return result
                }

                override fun authenticate(route: Route?, response: Response): Request? {
                    val credential = "Bearer $token"
                    if (credential == response.request.header("Authorization")) {
                        return null // If we already failed with these credentials, don't retry.
                    }
                    return if (responseCount(response) > 3) {
                        null
                    } else response.request.newBuilder()
                        .header("Authorization", credential)
                        .header("Accept", MEDIA_TYPE_JSON)
                        .build()
                }
            })
            .connectionPool(ConnectionPool(0, 1, TimeUnit.NANOSECONDS))

        //reason to add connection pool
        //https://github.com/square/okhttp/issues/3146
        if (BuildConfig.DEBUG) {
            if (stethoInterceptor == null) {
                stethoInterceptor = StethoInterceptor()
            }
            if (interceptor == null) {
                interceptor = HttpLoggingInterceptor()
                interceptor!!.level = HttpLoggingInterceptor.Level.HEADERS
            }
            builder.addInterceptor(interceptor!!)
            builder.addNetworkInterceptor(stethoInterceptor!!)
        }
        httpClient = builder.build()
    }

    fun createHttpClient(clientInterface: HttpClientInterface?): OkHttpClient? {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .addInterceptor(Interceptor { chain: Interceptor.Chain ->
                val authRequest: Request.Builder = chain.request().newBuilder()
                    .header("Accept", MEDIA_TYPE_JSON)
                    .header("user-agent", "android")
                if (clientInterface != null) {
                    val token = clientInterface.token
                    if (!TextUtils.isEmpty(token)) {
                        authRequest.header("Authorization", "Bearer $token")
                    }
                }
                val response = chain.proceed(authRequest.build())
                if (response.code == 401) {
                    EventBus.getDefault().post(UnauthorizedEvent())
                }
                response
            })
            .authenticator(object : Authenticator {
                private fun responseCount(response: Response): Int {
                    var response = response
                    var result = 1
                    while (response.priorResponse.also { response = it!! } != null) {
                        result++
                    }
                    return result
                }

                override fun authenticate(route: Route?, response: Response): Request? {
                    var token = ""
                    if (clientInterface != null) {
                        token = clientInterface.token
                    }
                    val credential = "Bearer $token"
                    if (credential == response.request.header("Authorization")) {
                        return null // If we already failed with these credentials, don't retry.
                    }
                    return if (responseCount(response) > 3) {
                        null
                    } else response.request.newBuilder()
                        .header("Authorization", credential)
                        .header("Accept", MEDIA_TYPE_JSON)
                        .build()
                }
            })
            .connectionPool(ConnectionPool(0, 1, TimeUnit.NANOSECONDS))

        //reason to add connection pool
        //https://github.com/square/okhttp/issues/3146
        if (BuildConfig.DEBUG) {
            if (stethoInterceptor == null) {
                stethoInterceptor = StethoInterceptor()
            }
            if (interceptor == null) {
                interceptor = HttpLoggingInterceptor()
                interceptor!!.level = HttpLoggingInterceptor.Level.HEADERS
            }
            builder.addInterceptor(interceptor!!)
            builder.addNetworkInterceptor(stethoInterceptor!!)
        }
        return builder.build()
    }

    interface HttpClientInterface {
        val token: String
    }
}