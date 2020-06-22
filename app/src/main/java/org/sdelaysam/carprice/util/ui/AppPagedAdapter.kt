package org.sdelaysam.carprice.util.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.paging.PagedListAdapter
import org.sdelaysam.carprice.R
import java.util.*

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

open class AppPagedAdapter<L: IdentifiableLayout>(
    @LayoutRes
    private val placeholderLayout: Int = R.layout.layout_text_item
) : PagedListAdapter<L, ViewHolder>(createDiffCallback<L>()) {

    init {
        stateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY
    }

    private var customItems: SortedMap<Int, L>? = null

    fun submitData(data: PagedListData<L>) {
        submitList(data.list) {
            if (customItems != data.customItems) {
                customItems = data.customItems
                notifyDataSetChanged()
            }
        }
    }

    override fun getItem(position: Int): L? {
        customItems?.let {
            if (it.containsKey(position)) {
                return it[position]
            }
        }

        var newPosition = position
        customItems?.let {
            for ((key, _) in it) {
                if (position > key) {
                    newPosition -= 1
                } else {
                    break
                }
            }
        }
        if (newPosition >= super.getItemCount()) {
            return null
        }

        return super.getItem(newPosition)
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + (customItems?.size ?: 0)
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position)?.layoutId ?: placeholderLayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        holder.clear()
    }

}