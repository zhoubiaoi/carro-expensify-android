package sg.carro.claims

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import sg.carro.claims.adapter.ExpensesDateAdapter
import sg.carro.claims.base.BaseActivity
import sg.carro.claims.databinding.ActivityMainBinding
import sg.carro.claims.entity.ExpensesEntity
import sg.carro.claims.utils.IntentUtil.Companion.launchNewExpenseActivity

class MainActivity : BaseActivity() {
    private var activityMain : ActivityMainBinding? = null
    private var expensesList : MutableList<ExpensesEntity> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMain?.root)
        initView()
    }
    private fun initView(){
        activityMain?.apply {
            customToolbar.apply {
                tvTitle.text = getString(R.string.my_expenses)
                ivRight.setImageResource(R.drawable.ic_profile)
                ivRight.setOnClickListener {  }
            }
            tvPersonal.apply {
                setOnClickListener {
                    setTextColor(ContextCompat.getColor(this@MainActivity , R.color.colorPrimary))
                    tvInventory.setTextColor(ContextCompat.getColor(this@MainActivity , R.color.main_top_uncheck))
                }
            }
            tvInventory.apply {
                setOnClickListener {
                    setTextColor(ContextCompat.getColor(this@MainActivity , R.color.colorPrimary))
                    tvPersonal.setTextColor(ContextCompat.getColor(this@MainActivity , R.color.main_top_uncheck))
                }
            }
            floatingExpensesAdd.setOnClickListener {
                launchNewExpenseActivity(this@MainActivity)
            }
            recyclerExpenses.apply {
                layoutManager =
                    LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)?.apply {
                        isMeasurementCacheEnabled = false
                    }
                adapter = ExpensesDateAdapter(this@MainActivity , expensesList)
                itemAnimator = null
            }
            recyclerExpenses.visibility = View.GONE
            layoutEmpty.displayState(true)
        }
    }
}