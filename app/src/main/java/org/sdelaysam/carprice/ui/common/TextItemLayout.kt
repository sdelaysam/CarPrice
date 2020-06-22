package org.sdelaysam.carprice.ui.common

import android.view.View
import kotlinx.android.synthetic.main.layout_text_item.view.*
import org.sdelaysam.carprice.R
import org.sdelaysam.carprice.util.ui.AlphabeticLayout
import org.sdelaysam.carprice.util.ui.ViewBinder

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

class TextItemLayout(
    private val id: String,
    private val name: String,
    private val onItemSelected: () -> Unit
) : AlphabeticLayout, ViewBinder {

    override val layoutId = R.layout.layout_text_item

    override val identity = id.hashCode()

    override val hashCode = name.hashCode()

    override val firstLetter = name.first()

    override fun bind(view: View) {
        view.text.text = name
        view.setOnClickListener { onItemSelected() }
    }
}