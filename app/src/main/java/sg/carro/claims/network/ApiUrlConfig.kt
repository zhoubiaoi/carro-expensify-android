package sg.carro.claims.network

object ApiUrlConfig {
    private const val STAGING_BX = "https://dx-wsapi.getcarsstaging.com"

    @JvmStatic
    val environment: String
        get() {
            return STAGING_BX
        }

    @JvmStatic
    val systemOauthUrl: String
        get() = "$environment/oauth/token"

    @JvmStatic
    val emailLogin: String
        get() = "$environment/loginByEmail?include=locale"
}