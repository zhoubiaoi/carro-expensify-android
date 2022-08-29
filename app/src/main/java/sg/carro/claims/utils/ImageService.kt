package sg.carro.claims.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.facebook.stetho.common.LogUtil
import sg.carro.claims.R
import java.lang.Exception

object ImageService {
    private var bannerOptions: RequestOptions? = null
    @JvmStatic
    fun loadImageCache(
        context: Context?, url: String?,
        options: RequestOptions?, imageView: ImageView?
    ) {
        try {
            Glide.with(context!!)
                .load(url)
                .apply(options!!)
                .into(imageView!!)
        } catch (e: Exception) {
            LogUtil.e(e.message)
        }
    }
    @JvmStatic
    fun getBannerOptions(): RequestOptions? {
        if (bannerOptions == null) {
            bannerOptions = RequestOptions()
                .transform(CustomCenterCropTransformation())
                .placeholder(R.drawable.ic_default_photo)
        }
        return bannerOptions
    }
}