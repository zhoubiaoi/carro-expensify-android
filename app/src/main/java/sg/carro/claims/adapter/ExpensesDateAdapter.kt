package sg.carro.claims.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sg.carro.claims.databinding.ItemExpensesDateBinding
import sg.carro.claims.entity.ExpensesEntity

class ExpensesDateAdapter (
    var context: Context,
    var data: MutableList<ExpensesEntity>,
) :
    RecyclerView.Adapter<ExpensesDateAdapter.ExpensesDateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpensesDateViewHolder {
        return ExpensesDateViewHolder(ItemExpensesDateBinding.inflate(LayoutInflater.from(context)))
    }

    override fun onBindViewHolder(holder: ExpensesDateViewHolder, position: Int) {
        holder.mItemView?.apply {
            data[position].let { history ->

                recyclerviewDetail.apply {
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)?.apply {
                            isMeasurementCacheEnabled = false
                        }
                    adapter = ExpensesDetailsAdapter(context , data)
                    itemAnimator = null
                }
            }
        }
    }

    override fun getItemCount(): Int = data.size

    class ExpensesDateViewHolder(itemView: ItemExpensesDateBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        var mItemView: ItemExpensesDateBinding? = itemView
    }
}