package com.htnguyen.gplxapp.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.htnguyen.gplxapp.base.adapter.BaseRecyclerViewAdapter
import com.htnguyen.gplxapp.model.TrafficsLearnResult

class TrafficLearnAnswerAdapter : BaseRecyclerViewAdapter<TrafficsLearnResult>(){
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model: TrafficsLearnResult = getItems(position)
        val baseViewHolder: BaseViewHolder = holder as BaseViewHolder
        baseViewHolder.bind(model)

        baseViewHolder.viewDataBinding.root.setOnClickListener { p0 ->
            onClickItem(position, p0)
        }
    }

    fun setItems(listItems: ArrayList<TrafficsLearnResult>) {
        super.setItems(listItems)
    }
}