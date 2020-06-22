package org.sdelaysam.carprice.util.ui

import androidx.paging.PagedList
import java.util.*

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

data class PagedListData<T>(
    val list: PagedList<T>,
    val customItems: SortedMap<Int, T>? = null
)