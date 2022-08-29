package sg.carro.claims.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

object DeviceUtil {
    @JvmStatic
    fun hideSoftKeyboard(activity: Activity?) {
        if (activity == null) {
            return
        }
        // Check if no view has focus:
        val view = activity.currentFocus
        if (view != null) {
            val imm =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
