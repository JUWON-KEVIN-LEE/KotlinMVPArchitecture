package com.leopold.mvp.ui.widget.recycler

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * @author Leopold
 */
abstract class EndlessLinearRecyclerListener(private val lm: LinearLayoutManager) : EndlessRecyclerListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        visibleItemCount = recyclerView.childCount
        totalItemCount = lm.itemCount
        firstVisibleItem = lm.findFirstVisibleItemPosition()

        if (isLoadingMore) {
            if (totalItemCount > previousTotalItemCount) {
                isLoadingMore = false
                previousTotalItemCount = totalItemCount
            }
        }

        if (!isLoadingMore && (totalItemCount - visibleItemCount) <= (firstVisibleItem + VISIBLE_THRESHOLD)) {
            onLoadMore()
            isLoadingMore = true
        }
    }

    abstract fun onLoadMore()
}