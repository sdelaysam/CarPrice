package org.sdelaysam.carprice.util.log

import timber.log.Timber

/**
 * Created on 2019-12-11.
 * @author sdelaysam
 */

class TimberPrintTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        val msg = message.replace("\n", System.getProperty("line.separator")!!)
        if (t != null) {
            System.err.println(msg)
        } else {
            println(msg)
        }
    }
}