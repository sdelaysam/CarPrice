package org.sdelaysam.carprice.util.app

import io.reactivex.Observable

/**
 * Created on 6/23/20.
 * @author sdelaysam
 */

enum class AppState {
    Background,
    Foreground
}

interface AppStateProvider {
    fun observeState(): Observable<AppState>
}