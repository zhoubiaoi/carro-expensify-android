package sg.carro.claims.utils

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.hjq.gson.factory.GsonFactory
import java.lang.reflect.Type

object JsonHelper {
    @Volatile
    private var mGson: Gson? = null

    fun getGson(): Gson {
        if (mGson == null) {
            mGson = GsonFactory.getSingletonGson()
        }
        return mGson!!
    }

    @Throws(JsonSyntaxException::class)
    fun <T> parse(json: String?, to: Class<T>?): T {
        return getGson().fromJson(json, to)
    }

    @Throws(JsonSyntaxException::class)
    fun <T> parse(json: String?, to: Type?): T {
        return getGson().fromJson(json, to)
    }

    fun serialize(src: Any?): String? {
        return getGson().toJson(src)
    }
}