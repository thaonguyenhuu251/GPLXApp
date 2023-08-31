package com.htnguyen.gplxapp.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.base.adapter.BaseRecyclerViewAdapter
import com.htnguyen.gplxapp.model.TrafficsLearnDetail
import com.htnguyen.gplxapp.model.TrafficsLearnResult

class TraffigsLearnDetailAdapter : BaseRecyclerViewAdapter<TrafficsLearnDetail>(){
    var sendDataItem: (position: Int, trafficsLearn: TrafficsLearnDetail?) -> Unit = { _: Int, _: TrafficsLearnDetail? -> }

    var nextItem: () -> Unit = {}
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model: TrafficsLearnDetail = getItems(position)
        sendDataItem(position, model)
        val baseViewHolder: BaseViewHolder = holder as BaseViewHolder
        baseViewHolder.bind(model)

        baseViewHolder.viewDataBinding.root.setOnClickListener { p0 ->
            notifyItemChanged(position)
            onClickItem(position, p0)
        }

        val adapter = BaseRecyclerViewAdapter<TrafficsLearnResult>()
        val list = arrayListOf<TrafficsLearnResult>()
        model.answer_list?.forEachIndexed { index, result ->
            if (result.isNotEmpty()) {
                list.add(TrafficsLearnResult(index + 1, result))
            }
        }
        baseViewHolder.viewDataBinding.root.findViewById<RecyclerView>(R.id.listResult).adapter = adapter
        adapter.setItems(list)

        adapter.onClickItem = { position, view ->
            if (model.answer_list?.get(position)?.equals(model.result) == true) {
                nextItem()
            }
        }
    }

    fun setItems(listItems: ArrayList<TrafficsLearnDetail>) {
        super.setItems(listItems)
    }
}