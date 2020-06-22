package org.sdelaysam.carprice.util.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter
import kotlinx.android.synthetic.main.layout_alphabetic_header.view.*
import org.sdelaysam.carprice.R

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

class AlphabeticAdapter<L: IdentifiableLayout> : AppPagedAdapter<L>(), StickyRecyclerHeadersAdapter<AlphabeticAdapter.ViewHolderHeader> {

    override fun getHeaderId(position: Int): Long {
        return getFirstLetter(position)?.toLong() ?: -1L
    }

    override fun onCreateHeaderViewHolder(parent: ViewGroup?): ViewHolderHeader {
        val inflater = LayoutInflater.from(parent!!.context)
        return ViewHolderHeader(inflater.inflate(R.layout.layout_alphabetic_header, parent, false))
    }

    override fun onBindHeaderViewHolder(holder: ViewHolderHeader, position: Int) {
        getFirstLetter(position)?.let {
            holder.view.header.text = it.toString()
        }
    }

    override fun onHeaderOverscroll(holder: ViewHolderHeader?, overscroll: Int) {
    }

    private fun getFirstLetter(position: Int): Char? {
        return (getItem(position) as? AlphabeticLayout)?.firstLetter
    }

    class ViewHolderHeader(val view: View) : RecyclerView.ViewHolder(view)
}