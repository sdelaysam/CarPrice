package org.sdelaysam.carprice.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration
import kotlinx.android.synthetic.main.fragment_list.*
import org.sdelaysam.carprice.R
import org.sdelaysam.carprice.data.model.LoadingType
import org.sdelaysam.carprice.util.ui.AppPagedAdapter
import org.sdelaysam.carprice.util.ui.IdentifiableLayout
import org.sdelaysam.carprice.util.ui.RxFragment
import org.sdelaysam.carprice.util.ui.showError

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

abstract class BaseListFragment<VM: BaseListViewModel>: RxFragment() {

    abstract val viewModel: VM

    abstract val adapter: AppPagedAdapter<IdentifiableLayout>

    private var skeleton: SkeletonScreen? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRecyclerView()
        configureRefresher()
        viewModel.observeList()
            .subscribe(adapter::submitData)
            .untilDestroy()
        viewModel.observeLoadingType()
            .subscribe(::showLoading)
            .untilDestroy()
        viewModel.observeErrors()
            .subscribe(::showError)
            .untilDestroy()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        rv.adapter = null
        skeleton = null
    }

    private fun configureRecyclerView() {
        (adapter as? StickyRecyclerHeadersAdapter<*>)?.let {
            rv.addItemDecoration(StickyRecyclerHeadersDecoration(it))
        }
        val layoutManager = LinearLayoutManager(context)
        rv.layoutManager = layoutManager
        rv.adapter = adapter
        rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                viewModel.firstVisiblePosition = maxOf(layoutManager.findFirstVisibleItemPosition(), 0)
            }
        })
    }

    private fun configureRefresher() {
        refresher.setColorSchemeResources(R.color.colorPrimaryDark, R.color.colorPrimary, R.color.colorAccent)
        refresher.setOnRefreshListener { viewModel.refresh(true) }
    }

    private fun showInitialLoading() {
        if (skeleton == null) {
            skeleton = Skeleton.bind(rv)
                .adapter(adapter)
                .shimmer(true)
                .color(R.color.colorShimmer)
                .count(20)
                .load(R.layout.layout_text_skeleton)
                .show()
        } else {
            skeleton?.show()
        }
        refresher.isEnabled = false
    }

    private fun showRefreshing() {
        refresher.isRefreshing = true
    }

    private fun hideLoading() {
        refresher.isRefreshing = false
        refresher.isEnabled = true
        skeleton?.hide()
    }

    private fun showLoading(loadingType: LoadingType) {
        when (loadingType) {
            LoadingType.Initial -> showInitialLoading()
            LoadingType.RefreshManual -> showRefreshing()
            LoadingType.RefreshAuto, LoadingType.None -> hideLoading()
        }
    }

}