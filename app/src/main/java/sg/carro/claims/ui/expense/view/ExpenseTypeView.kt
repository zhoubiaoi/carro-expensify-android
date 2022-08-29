package sg.carro.claims.ui.expense.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import sg.carro.claims.R
import sg.carro.claims.databinding.ViewExpenseTypeBinding

class ExpenseTypeView : LinearLayout{
    private var viewExpenseTypeBinding : ViewExpenseTypeBinding? = null
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
        val root = LayoutInflater.from(context).inflate(R.layout.view_expense_type, this)
        viewExpenseTypeBinding = ViewExpenseTypeBinding.bind(root)
    }
}