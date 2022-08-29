package sg.carro.claims.adapter

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sg.carro.claims.databinding.ItemExpenseAmountBinding
import sg.carro.claims.databinding.ItemExpensesDateBinding
import sg.carro.claims.entity.ExpenseAmountEntity
import sg.carro.claims.entity.ExpensesEntity

class ExpensesAmountAdapter (
    var context: Context,
    var data: MutableList<ExpenseAmountEntity>,
) :
    RecyclerView.Adapter<ExpensesAmountAdapter.ExpensesAmountViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpensesAmountViewHolder {
        return ExpensesAmountViewHolder(ItemExpenseAmountBinding.inflate(LayoutInflater.from(context) , parent , false))
    }

    override fun onBindViewHolder(holder: ExpensesAmountViewHolder, position: Int) {
        holder.mItemView?.apply {
            data[position].let { mAmount ->
                if(mAmount.amount.isNullOrEmpty()){
                    editPrice.setText("")
                }else{
                    editPrice.setText(mAmount.amount)
                }
                if(mAmount.description.isNullOrEmpty()){
                    editDescription.setText("")
                }else{
                    editDescription.setText(mAmount.amount)
                }

                editPrice.addTextChangedListener(object : TextWatcher{
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                    override fun afterTextChanged(p0: Editable?) {
                        mAmount.amount = p0.toString()
                    }

                })
                editDescription.addTextChangedListener(object : TextWatcher{
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                    override fun afterTextChanged(p0: Editable?) {
                        mAmount.description = p0.toString()
                    }

                })
            }
        }
    }

    override fun getItemCount(): Int = data.size

    class ExpensesAmountViewHolder(itemView: ItemExpenseAmountBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        var mItemView: ItemExpenseAmountBinding? = itemView
    }
}