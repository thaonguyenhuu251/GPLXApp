package com.htnguyen.gplxapp.view.adapter

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.base.adapter.BaseRecyclerViewAdapter
import com.htnguyen.gplxapp.model.StatusLearn

class TrafficLearnResultAdapter : BaseRecyclerViewAdapter<StatusLearn>(){
    var sendDataItem: (position: Int, trafficsLearn: StatusLearn?) -> Unit = { _: Int, _: StatusLearn? -> }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model: StatusLearn = getItems(position)
        val baseViewHolder: BaseViewHolder = holder as BaseViewHolder
        baseViewHolder.bind(model)

        baseViewHolder.viewDataBinding.root.findViewById<TextView>(R.id.txtNumberAsk).text = (position + 1).toString()

        baseViewHolder.viewDataBinding.root.setOnClickListener { p0 ->
            notifyItemChanged(position)
            sendDataItem(position, model)
        }
    }

    fun setItems(listItems: ArrayList<StatusLearn>) {
        super.setItems(listItems)
    }
}