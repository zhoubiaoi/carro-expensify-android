package sg.carro.claims.ui.expense.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import org.greenrobot.eventbus.EventBus
import sg.carro.claims.R
import sg.carro.claims.databinding.ViewExpenseImageBinding
import sg.carro.claims.event.UploadImageEvent
import sg.carro.claims.utils.ImageService

class ExpenseImageView : RelativeLayout {
    private var viewExpenseImageBinding: ViewExpenseImageBinding? = null

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
        val root = LayoutInflater.from(context).inflate(R.layout.view_expense_image, this)
        viewExpenseImageBinding = ViewExpenseImageBinding.bind(root)
        viewExpenseImageBinding?.apply {
            layoutCamera.setOnClickListener {
                EventBus.getDefault().post(UploadImageEvent())
            }
            ivDelete.setOnClickListener {
                isHasPath(false)
            }
        }
    }

    fun displayImage(image: String) {
        isHasPath(true)
        ImageService.loadImageCache(
            context,
            image,
            ImageService.getBannerOptions(),
            viewExpenseImageBinding?.imageFile
        )
    }

    fun isHasPath(path: Boolean) {
        viewExpenseImageBinding?.apply {
            layoutImage.visibility = if (path) VISIBLE else GONE
            ivDelete.visibility = if (path) VISIBLE else GONE
            layoutCamera.visibility = if (path) GONE else VISIBLE
        }
    }
}