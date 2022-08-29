package sg.carro.claims.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import sg.carro.claims.R
import sg.carro.claims.databinding.ViewCustomEmptyBinding
import sg.carro.claims.databinding.ViewLoginBinding

class EmptyView : LinearLayout {
    private var viewCustomEmptyBinding : ViewCustomEmptyBinding? = null
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
        val root = LayoutInflater.from(context).inflate(R.layout.view_custom_empty, this)
        viewCustomEmptyBinding = ViewCustomEmptyBinding.bind(root)
        visibility = GONE
    }

    fun displayState(display : Boolean){
        visibility = if(display) VISIBLE else GONE
    }
}