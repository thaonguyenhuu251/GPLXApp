package com.htnguyen.gplxapp.view.adapter


import androidx.recyclerview.widget.RecyclerView
import com.htnguyen.gplxapp.base.adapter.BaseRecyclerViewAdapter
import com.htnguyen.gplxapp.model.Tips


class TipsExpendAdapter<T> : BaseRecyclerViewAdapter<Tips>(){


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model: Tips = getItems(position)
        val baseViewHolder: BaseViewHolder = holder as BaseViewHolder
        baseViewHolder.bind(model)

        baseViewHolder.viewDataBinding.root.setOnClickListener { p0 ->
            model.isSelected =  !model.isSelected
            notifyItemChanged(position)
            onClickItem(position, p0)
        }
    }

    fun setItems(listItems: ArrayList<Tips>) {
        super.setItems(listItems)
    }
}