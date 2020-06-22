package org.sdelaysam.carprice.util.rx

import io.reactivex.Scheduler

/**
 * Created on 6/20/20.
 * @author sdelaysam
 */

object RxSchedulers {
    lateinit var main: Scheduler
    lateinit var network: Scheduler
    lateinit var computation: Scheduler
}