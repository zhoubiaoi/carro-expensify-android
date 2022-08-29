package sg.carro.claims.view

import android.content.Context
import android.graphics.Typeface
import java.util.HashMap

object FontCache {
    private val fontCache =
        HashMap<String, Typeface?>()

    @JvmStatic
    fun getTypeface(
        fontname: String,
        context: Context
    ): Typeface? {
        var typeface = fontCache[fontname]
        if (typeface == null) {
            typeface = try {
                Typeface.createFromAsset(context.assets, fontname)
            } catch (e: Exception) {
                return null
            }
            fontCache[fontname] = typeface
        }
        return typeface
    }
}