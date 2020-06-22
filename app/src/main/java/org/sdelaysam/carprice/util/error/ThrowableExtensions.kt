package org.sdelaysam.carprice.util.error

import android.content.Context
import org.sdelaysam.carprice.R
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

fun Throwable.getHumanReadableMessage(context: Context): String {
    return when {
        isCausedByNetwork -> context.getString(R.string.connection_error)
        else -> context.getString(R.string.unknown_error)
    }
}

val Throwable.isCausedByNetwork: Boolean
    get() = this is UnknownHostException
            || this is SocketException
            || this is SocketTimeoutException
            || (this as? RuntimeException)?.cause?.isCausedByNetwork == true
