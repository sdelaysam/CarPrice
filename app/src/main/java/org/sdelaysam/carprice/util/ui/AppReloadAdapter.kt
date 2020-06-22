package org.sdelaysam.carprice.util.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView

/**
 * Created on 6/22/20.
 * @author sdelaysam
 */

class AppReloadAdapter<L: IdentifiableLayout> : RecyclerView.Adapter<ViewHolder>() {

    private val differ = AsyncListDiffer(this, createDiffCallback<L>())

    init {
        stateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY
    }

    fun submitList(list: List<L>) {
        differ.submitList(list)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position)?.layoutId ?: -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    private fun getItem(position: Int): L? {
        return differ.currentList.getOrNull(position)
    }


}