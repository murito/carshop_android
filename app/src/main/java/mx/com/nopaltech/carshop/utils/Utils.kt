package mx.com.nopaltech.carshop.utils

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

class Utils {
    companion object {
        fun currencyFormat(data: Double): String {
            val usLocale = Locale("en", "US")
            val usCurrencyFormatter = NumberFormat.getCurrencyInstance(usLocale)
            return usCurrencyFormatter.format(data)
        }

        fun thousandsFormat(data : Double): String {
            val formatterDefaultLocale = DecimalFormat("#,##0.00")
            return formatterDefaultLocale.format(data)
        }
    }
}