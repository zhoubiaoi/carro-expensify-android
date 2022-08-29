package sg.carro.claims.ui.expense.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import sg.carro.claims.R
import sg.carro.claims.adapter.ExpensesAmountAdapter
import sg.carro.claims.databinding.ViewExpenseAmountBinding
import sg.carro.claims.entity.ExpenseAmountEntity

class ExpenseAmountView : LinearLayout {
    private var viewExpenseAmountBinding : ViewExpenseAmountBinding? = null
    private var amountList : MutableList<ExpenseAmountEntity> = ArrayList()
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
        val root = LayoutInflater.from(context).inflate(R.layout.view_expense_amount, this)
        viewExpenseAmountBinding = ViewExpenseAmountBinding.bind(root)
        amountList.add(ExpenseAmountEntity())
        viewExpenseAmountBinding?.apply {
            recyclerAmount.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)?.apply {
                        isMeasurementCacheEnabled = false
                    }
                adapter = ExpensesAmountAdapter(context , amountList)
                itemAnimator = null
            }
            layoutAddItem.setOnClickListener {
                amountList.add(ExpenseAmountEntity())
                recyclerAmount.adapter?.notifyDataSetChanged()
            }
        }
    }
}