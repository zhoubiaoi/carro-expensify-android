package sg.carro.claims.ui.expense.view

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.jaredrummler.materialspinner.MaterialSpinner
import sg.carro.claims.R
import sg.carro.claims.databinding.ViewExpenseInfoBinding
import sg.carro.claims.utils.DateTimeUtils
import java.util.*

class ExpenseInfoView : LinearLayout {
    private var viewExpenseInfoBinding: ViewExpenseInfoBinding? = null
    private var datePickerDialog: DatePickerDialog? = null
    private var calendar = Calendar.getInstance()

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
        val root = LayoutInflater.from(context).inflate(R.layout.view_expense_info, this)
        viewExpenseInfoBinding = ViewExpenseInfoBinding.bind(root)
        viewExpenseInfoBinding?.apply {
            category.addRequiredIcon(category.text.toString())
            carplate.addRequiredIcon(carplate.text.toString())
            vendor.addRequiredIcon(vendor.text.toString())
            date.addRequiredIcon(date.text.toString())
            ivDate.setOnClickListener {
                showDateDialog(tvDate)
            }
            spinner.setItems(
                "Ice Cream Sandwich",
                "Jelly Bean",
                "KitKat",
                "Lollipop",
                "Marshmallow"
            )
            spinner.setOnItemSelectedListener(object :
                MaterialSpinner.OnItemSelectedListener<String> {
                override fun onItemSelected(
                    view: MaterialSpinner?,
                    position: Int,
                    id: Long,
                    item: String?
                ) {
                    Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                }

            })
        }
    }

    private fun showDateDialog(text: TextView) {
        if (datePickerDialog != null) {
            datePickerDialog = null
        }
        datePickerDialog = DatePickerDialog(
            context,
            DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                calendar[Calendar.YEAR] = year
                calendar[Calendar.MONTH] = month
                calendar[Calendar.DAY_OF_MONTH] = day
                val date = calendar.time
                text.text = DateTimeUtils.convertDate(date, DateTimeUtils.DateFormatType.ddMMMyyyy)
            },
            calendar[Calendar.YEAR],
            calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH]
        )
        datePickerDialog?.show()
        datePickerDialog?.getButton(AlertDialog.BUTTON_NEGATIVE)?.setTextColor(
            ContextCompat.getColor(
                context, R.color.colorPrimary
            )
        )
        datePickerDialog?.getButton(AlertDialog.BUTTON_POSITIVE)?.setTextColor(
            ContextCompat.getColor(
                context, R.color.colorPrimary
            )
        )

    }
}