package sg.carro.claims.utils

import android.graphics.Bitmap
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.TransformationUtils
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

class CustomCenterCropTransformation : BitmapTransformation() {
    public override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap {

        //check whether bitmap is landscape or portrait
        return if (toTransform.width < toTransform.height) {
            //portrait bitmap transformation
            TransformationUtils.fitCenter(pool, toTransform, outWidth, outHeight)
        } else {
            //landscape bitmap transformation
            TransformationUtils.centerCrop(pool, toTransform, outWidth, outHeight)
        }
    }

    override fun equals(o: Any?): Boolean {
        return o is CustomCenterCropTransformation
    }

    override fun hashCode(): Int {
        return ID.hashCode()
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(ID_BYTES)
    }

    companion object {
        private const val ID =
            "com.bumptech.glide.transformations.CustomCenterCropTransformation"
        private val ID_BYTES =
            ID.toByteArray(StandardCharsets.UTF_8)
    }
}