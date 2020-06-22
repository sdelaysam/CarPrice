package org.sdelaysam.carprice.util.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.disposables.Disposable

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private var disposable: Disposable? = null

    fun bind(binder: ViewBinder) {
        binder.bind(view)
    }

    fun bind(binder: DisposableViewBinder) {
        disposable?.dispose()
        disposable = binder.bind(view)
    }

    fun clear() {
        disposable?.dispose()
    }
}

fun ViewHolder.bind(item: Layout) {
    when (item) {
        is ViewBinder -> bind(item)
        is DisposableViewBinder -> bind(item)
        else -> {
            when (val model = (item as? HasViewModel)?.viewModel) {
                is ViewBinder -> bind(model)
                is DisposableViewBinder -> bind(model)
            }
        }
    }
}