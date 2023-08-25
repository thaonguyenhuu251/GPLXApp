package com.htnguyen.gplxapp.view.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.htnguyen.gplxapp.BR
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.databinding.ItemLoadingBinding

open class BaseRecyclerViewPagingAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var isLoading = false
    private var listItems = ArrayList<T>()

    var onClickItem: (position: Int, data: T) -> Unit = { _: Int, _: T -> }
    var loadingMoreListener: () -> Unit = {}
    var onClickMore: () -> Unit = {}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_loading -> LoadingViewHolder(
                ItemLoadingBinding.inflate(LayoutInflater.from(
                parent.context),
                parent,
                false))

            else -> BaseViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    viewType,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model: BaseItem = getItems(position) as BaseItem
        when (holder) {
            is BaseRecyclerViewPagingAdapter<*>.BaseViewHolder -> {
                holder.bind(model)
                holder.viewDataBinding.root.setOnClickListener { p0 ->
                    onClickItem(position, model as T)
                }
            }
            is BaseRecyclerViewPagingAdapter<*>.LoadingViewHolder -> {

            }
        }
    }

    open fun setItems(listItems: ArrayList<T>) {
        this.listItems = listItems
        notifyDataSetChanged()
    }

    fun addItems(items: ArrayList<T>) {
        this.listItems.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount() = listItems.size

    override fun getItemViewType(position: Int): Int {
        return getLayoutId(position)
    }

    private fun getLayoutId(position: Int): Int {
        val model: BaseItem = listItems[position] as BaseItem
        return model.layoutResourceId
    }

    open fun getItems(position: Int): T {
        return listItems[position]
    }

    fun getListItems() = listItems

    open fun getListItem(): ArrayList<T> {
        return listItems as ArrayList<T>
    }

    open fun getLoadingState(): Boolean {
        return isLoading
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                when (newState) {
                    RecyclerView.SCROLL_STATE_IDLE -> {
                        var firstVisibleItemPosition = 0
                        var lastVisibleItemPosition = 0
                        if (isLoading) {
                            return
                        }
                        val layoutManager = recyclerView.layoutManager
                        if (layoutManager is LinearLayoutManager) {
                            firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                            lastVisibleItemPosition =
                                layoutManager.findLastCompletelyVisibleItemPosition()
                        } else if (layoutManager is GridLayoutManager) {
                            firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                            lastVisibleItemPosition =
                                layoutManager.findLastCompletelyVisibleItemPosition()
                        }
                        if (lastVisibleItemPosition == itemCount - 1) {
                            isLoading = true
                            loadingMoreListener()
                        }
                    }
                    else -> {
                    }
                }
            }

        })
    }

    fun addLoadingItem(item: T) {
        val size = getListItems().size
        listItems.add(item)
        notifyItemChanged(size)
    }

    fun removeLoadingItem() {
        if (this.getListItems().isNotEmpty()) {
            if (listItems[listItems.size - 1] is LoadingItem) {
                this.getListItems().removeLastOrNull()
                notifyItemRemoved(itemCount)
            }
        }
    }

    fun setIsLoading(isLoading: Boolean) {
        this.isLoading = isLoading
    }

    fun clearData() {
        listItems.clear()
        notifyDataSetChanged()
    }

    inner class BaseViewHolder(var viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(
        viewDataBinding.root
    ) {
        fun bind(model: BaseItem?) {
            viewDataBinding.setVariable(BR.model, model)
            viewDataBinding.executePendingBindings()
        }
    }

    inner class LoadingViewHolder(var itemCell: ItemLoadingBinding) :
        RecyclerView.ViewHolder(itemCell.root)

}