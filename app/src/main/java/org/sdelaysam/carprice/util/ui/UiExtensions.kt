package org.sdelaysam.carprice.util.ui

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*

/**
 * Created on 6/23/20.
 * @author sdelaysam
 */

val Int.moneyString: String
    get() {
        val numberFormat = NumberFormat.getCurrencyInstance(Locale.ENGLISH)
        val decimalFormatSymbols: DecimalFormatSymbols = (numberFormat as DecimalFormat).decimalFormatSymbols
        decimalFormatSymbols.currencySymbol = ""
        numberFormat.decimalFormatSymbols = decimalFormatSymbols
        numberFormat.maximumFractionDigits = 0
        return numberFormat.format(this)
    }