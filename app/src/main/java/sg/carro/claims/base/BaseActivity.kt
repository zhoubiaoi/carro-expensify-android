package sg.carro.claims.base

import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import sg.carro.claims.R

open class BaseActivity : AppCompatActivity(){
    @JvmField
    final var progressDialog: MaterialDialog? = null

    override fun onPause() {
        super.onPause()
        if(progressDialog != null && progressDialog!!.isShowing){
            progressDialog?.dismiss()
        }
    }
    private fun initializeLoadingDialog() {
        progressDialog = MaterialDialog.Builder(this)
            .customView(R.layout.dialog_loading, true)
            .cancelable(false)
            .build()
    }

    fun showProgressDialog(){
        if(progressDialog == null){
            initializeLoadingDialog()
        }
        if (!progressDialog!!.isShowing) {
            progressDialog!!.show()
        }
    }

    fun dismissProgressDialog(){
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }
    }
}