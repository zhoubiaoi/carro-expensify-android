package sg.carro.claims.utils

import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {
    @JvmStatic
    fun convertDate(
        time: String?,
        originDateFormatType: DateFormatType,
        resultDateFormatType: DateFormatType
    ): String {
        try {
            val date =
                SimpleDateFormat(originDateFormatType.timeFormat, Locale.getDefault()).parse(time)

            date?.let {
                return SimpleDateFormat(
                    resultDateFormatType.timeFormat,
                    Locale.getDefault()
                ).format(it)
            }

        } catch (e: Exception) {
        }

        return " - "
    }

    @JvmStatic
    fun convertDate(
        date: Date?,
        resultDateFormatType: DateFormatType
    ): String {
        try {
            date?.let {
                return SimpleDateFormat(
                    resultDateFormatType.timeFormat,
                    Locale.getDefault()
                ).format(it)
            }

        } catch (e: Exception) {
        }

        return " - "
    }

    @JvmStatic
    fun convertDate(
        timeStamp: Long?,
        resultDateFormatType: DateFormatType
    ): String {
        try {
            return SimpleDateFormat(
                resultDateFormatType.timeFormat,
                Locale.getDefault()
            ).format(timeStamp)

        } catch (e: Exception) {
        }

        return " - "
    }


    @JvmStatic
    fun getUTCDateFormat(dateFormatType: DateFormatType): SimpleDateFormat {
        val dateFormat =
            SimpleDateFormat(dateFormatType.timeFormat, Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")

        return dateFormat
    }

    @JvmStatic
    fun getLocalDateFormat(dateFormatType: DateFormatType): SimpleDateFormat? {
        return SimpleDateFormat(dateFormatType.timeFormat, Locale.getDefault())
    }


    fun convertUTCToLocalDate(dateFormatType: DateFormatType, time: String?): Date? {
        try {
            return getUTCDateFormat(dateFormatType).parse(time)
        } catch (e: Exception) {

        }
        return null
    }


    enum class DateFormatType(val timeFormat: String) {
        yyyyMMdd("yyyy-MM-dd"),
        yyyyMMdd_HHmmss("yyyy-MM-dd HH:mm:ss"),
        yyyyMMdd_T_HHmmss_SSSSSS("yyyy-MM-dd'T'HH:mm:ss.SSSSSS"),
        HHmma_ddMMMyyyy("HH:mm a, dd MMM yyyy"),
        hhmma_ddMMMyyyy("hh:mm a, dd MMM yyyy"),
        ddMMMyyyy("dd MMM yyyy"),
        ddMMMyyyyHHmm("dd MMM yyyy, HH:mm"),
        ddMMMMyyyyHHmm("dd MMMM yyyy, HH:mm"),
    }
}