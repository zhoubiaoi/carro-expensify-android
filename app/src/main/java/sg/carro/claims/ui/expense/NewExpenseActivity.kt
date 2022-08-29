package sg.carro.claims.ui.expense

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import com.esafirm.imagepicker.features.ImagePicker
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import sg.carro.claims.R
import sg.carro.claims.base.BaseActivity
import sg.carro.claims.databinding.ActivityNewExpenseBinding
import sg.carro.claims.event.UploadImageEvent

class NewExpenseActivity : BaseActivity(){
    private var activityNewExpenseBinding : ActivityNewExpenseBinding? = null
    private val REQUEST_IMAGE_PICKER = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this)
        }
        activityNewExpenseBinding = ActivityNewExpenseBinding.inflate(layoutInflater)
        setContentView(activityNewExpenseBinding?.root)
        initView()
    }
    private fun initView(){
        activityNewExpenseBinding?.apply {
            cancel.setOnClickListener {
                finish()
            }
            save.setOnClickListener {
                finish()
            }

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun goCamera(uploadImageEvent: UploadImageEvent){
        ImagePicker.create(this)
            .toolbarFolderTitle("Select Image") // folder selection title
            .toolbarImageTitle("Select Image") // image selection title
            .toolbarArrowColor(Color.WHITE) // Toolbar 'up' arrow color
            .multi() // mulit mode
            .limit(1)
            .showCamera(true) // show camera or not (true by default)
            .imageDirectory("Camera") // directory name for captured image  ("Camera" folder by default)
            .theme(R.style.ImagePickerTheme) // must inherit ef_BaseTheme. please refer to sample
            .enableLog(false) // disabling log
            .start(REQUEST_IMAGE_PICKER) // start image picker activity with request code
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_PICKER) {
            val image = ImagePicker.getImages(data)
            image?.let { images ->
                activityNewExpenseBinding?.layoutExpenseImage?.displayImage(images[0].path)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this)
        }
    }
}