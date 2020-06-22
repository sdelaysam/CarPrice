package org.sdelaysam.carprice.util.ui

import androidx.recyclerview.widget.DiffUtil

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

interface Layout {
    val layoutId: Int
}

interface Identifiable {
    val identity: Int
    val hashCode: Int
}

interface HasFirstLetter {
    val firstLetter: Char
}

interface IdentifiableLayout: Layout, Identifiable

fun <L : IdentifiableLayout> createDiffCallback() = object : DiffUtil.ItemCallback<L>() {
    override fun areItemsTheSame(oldItem: L, newItem: L) = oldItem.identity == newItem.identity
    override fun areContentsTheSame(oldItem: L, newItem: L) = oldItem.hashCode == newItem.hashCode
}

interface AlphabeticLayout: IdentifiableLayout, HasFirstLetter