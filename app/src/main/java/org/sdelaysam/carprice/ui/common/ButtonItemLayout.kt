package org.sdelaysam.carprice.ui.common

import android.view.View
import androidx.annotation.StringRes
import kotlinx.android.synthetic.main.layout_button_item.view.*
import org.sdelaysam.carprice.R
import org.sdelaysam.carprice.util.ui.IdentifiableLayout
import org.sdelaysam.carprice.util.ui.ViewBinder

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

class ButtonItemLayout(
    @StringRes val nameRes: Int,
    val onClick: () -> Unit
) : IdentifiableLayout, ViewBinder {

    override val layoutId = R.layout.layout_button_item

    override val identity = nameRes

    override val hashCode = nameRes

    override fun bind(view: View) {
        view.button.setText(nameRes)
        view.button.setOnClickListener { onClick() }
    }
}