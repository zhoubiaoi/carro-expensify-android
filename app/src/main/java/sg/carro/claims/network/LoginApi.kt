package sg.carro.claims.network

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Url
import sg.carro.claims.base.BaseApi
import sg.carro.claims.entity.OAuth

object LoginApi : BaseApi() {
    @JvmStatic
    fun loginApi(): LoginApiClient? {
        initialize()
        return NetworkUtil.getInstance()
            ?.getRetrofit()
            ?.create(
                LoginApiClient::class.java
            )
    }

    interface LoginApiClient {
        @FormUrlEncoded
        @POST
        fun getSystemOauthToken(
            @Url url: String?,
            @Field("grant_type") grantType: String?,
            @Field("client_id") clientId: String?,
            @Field("client_secret") clientSecret: String?
        ): Observable<OAuth>

        @FormUrlEncoded
        @POST
        fun emailLogin(
            @Url url: String?,
            @Field("email") email: String?,
            @Field("password") password: String?
        ): Observable<OAuth>
    }
}