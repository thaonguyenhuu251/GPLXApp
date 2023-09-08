package com.htnguyen.gplxapp.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.base.adapter.BaseRecyclerViewAdapter
import com.htnguyen.gplxapp.model.TrafficsLearn
import com.htnguyen.gplxapp.model.TrafficsLearnDetail
import com.htnguyen.gplxapp.model.TrafficsLearnResult

class TraffigLearnAdapter : BaseRecyclerViewAdapter<TrafficsLearn>(){
    var sendDataItem: (position: Int, trafficsLearn: TrafficsLearn?) -> Unit = { _: Int, _: TrafficsLearn? -> }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model: TrafficsLearn = getItems(position)
        val baseViewHolder: BaseViewHolder = holder as BaseViewHolder
        baseViewHolder.bind(model)

        baseViewHolder.viewDataBinding.root.setOnClickListener { p0 ->
            notifyItemChanged(position)
            sendDataItem(position, model)
        }
    }

    fun setItems(listItems: ArrayList<TrafficsLearn>) {
        super.setItems(listItems)
    }
}