package sg.carro.claims.ui.login

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import sg.carro.claims.R
import sg.carro.claims.databinding.ViewLoginSuccessBinding

class LoginSuccessView : LinearLayout{
    private var viewLoginSuccessBinding: ViewLoginSuccessBinding? = null
    constructor(context: Context?) : super(context, null){
        initView()
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        initView()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs, 0){
        initView()
    }

    private fun initView(){
        val root = LayoutInflater.from(context).inflate(R.layout.view_login_success, this)
        viewLoginSuccessBinding = ViewLoginSuccessBinding.bind(root)
        visibility = GONE
    }

    fun showSuccessView(){
        visibility = VISIBLE
    }
}