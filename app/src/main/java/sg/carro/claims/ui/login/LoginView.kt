package sg.carro.claims.ui.login

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import org.greenrobot.eventbus.EventBus
import sg.carro.claims.R
import sg.carro.claims.databinding.ViewLoginBinding
import sg.carro.claims.event.LoginEvent

class LoginView : RelativeLayout {
    private var viewLoginBinding: ViewLoginBinding? = null
    private var isBtnLogin: Boolean = false

    constructor(context: Context?) : super(context, null) {
        initView()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        initView()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs, 0) {
        initView()
    }

    private fun initView() {
        val root = LayoutInflater.from(context).inflate(R.layout.view_login, this)
        viewLoginBinding = ViewLoginBinding.bind(root)
        viewLoginBinding?.apply {
            editEmail.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun afterTextChanged(p0: Editable?) {
                    isBtnLogin = if (p0?.toString().isNullOrEmpty()) {
                        tvLogin.setBackgroundResource(R.drawable.button_rounded_corner_login_uncheck)
                        false
                    } else {
                        if (editPassword.text.isNullOrEmpty()) {
                            tvLogin.setBackgroundResource(R.drawable.button_rounded_corner_login_uncheck)
                            false
                        } else {
                            tvLogin.setBackgroundResource(R.drawable.button_rounded_corner_login)
                            true
                        }
                    }
                }

            })

            editPassword.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun afterTextChanged(p0: Editable?) {
                    isBtnLogin = if (p0?.toString().isNullOrEmpty()) {
                        tvLogin.setBackgroundResource(R.drawable.button_rounded_corner_login_uncheck)
                        false
                    } else {
                        if (editEmail.text.isNullOrEmpty()) {
                            tvLogin.setBackgroundResource(R.drawable.button_rounded_corner_login_uncheck)
                            false
                        } else {
                            tvLogin.setBackgroundResource(R.drawable.button_rounded_corner_login)
                            true
                        }
                    }
                }

            })
        }
        viewLoginBinding?.btnLogin?.setOnClickListener {
            if (isBtnLogin) EventBus.getDefault().post(
                LoginEvent(
                    viewLoginBinding?.editEmail?.text?.toString(),
                    viewLoginBinding?.editPassword?.text?.toString()
                )
            )
        }
    }

    fun hideView() {
        visibility = GONE
    }
}