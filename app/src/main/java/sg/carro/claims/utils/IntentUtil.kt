package sg.carro.claims.utils

import android.app.Activity
import android.content.Intent
import kotlinx.coroutines.MainScope
import sg.carro.claims.MainActivity
import sg.carro.claims.ui.expense.NewExpenseActivity
import sg.carro.claims.ui.login.LoginActivity

class IntentUtil {
    companion object {
        fun launchLoinActivity(activity: Activity) {
            activity.startActivity(Intent(activity , LoginActivity::class.java))
            activity.finish()
        }
        fun launchMainActivity(activity: Activity) {
            activity.startActivity(Intent(activity , MainActivity::class.java))
            activity.finish()
        }
        fun launchNewExpenseActivity(activity: Activity) {
            activity.startActivity(Intent(activity , NewExpenseActivity::class.java))
        }
    }
}