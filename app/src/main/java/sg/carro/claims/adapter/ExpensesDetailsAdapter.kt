package sg.carro.claims.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sg.carro.claims.databinding.ItemExpensesDetailBinding
import sg.carro.claims.entity.ExpensesEntity

class ExpensesDetailsAdapter (
    var context: Context,
    var data: MutableList<ExpensesEntity>,
) :
    RecyclerView.Adapter<ExpensesDetailsAdapter.ExpensesDetailsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpensesDetailsViewHolder {
        return ExpensesDetailsViewHolder(ItemExpensesDetailBinding.inflate(
            LayoutInflater.from(context)))
    }

    override fun onBindViewHolder(holder: ExpensesDetailsViewHolder, position: Int) {
        holder.mItemView?.apply {

        }
    }

    override fun getItemCount(): Int = data.size

    class ExpensesDetailsViewHolder(itemView: ItemExpensesDetailBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        var mItemView: ItemExpensesDetailBinding? = itemView
    }
}