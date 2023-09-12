package com.htnguyen.gplxapp.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.base.adapter.BaseRecyclerViewAdapter
import com.htnguyen.gplxapp.model.TrafficsLearnDetail
import com.htnguyen.gplxapp.model.TrafficsLearnResult

class TrafficsLearnDetailAdapter : BaseRecyclerViewAdapter<TrafficsLearnDetail>() {
    var sendDataItem: (position: Int, trafficsLearn: TrafficsLearnDetail?) -> Unit =
        { _: Int, _: TrafficsLearnDetail? -> }

    var nextItem: (position: Int, trafficsLearn: TrafficsLearnDetail, result: Int, indexAnswer: Int,) -> Unit =
        { _: Int, _: TrafficsLearnDetail, _: Int, _: Int -> }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model: TrafficsLearnDetail = getItems(position)
        sendDataItem(position, model)
        val baseViewHolder: BaseViewHolder = holder as BaseViewHolder
        baseViewHolder.bind(model)

        baseViewHolder.viewDataBinding.root.setOnClickListener { p0 ->
            notifyItemChanged(position)
            onClickItem(position, p0)
        }

        val adapter = TrafficLearnAnswerAdapter()
        val list = arrayListOf<TrafficsLearnResult>()
        model.answer_list?.forEachIndexed { index, result ->
            if (result.isNotEmpty()) {
                list.add(TrafficsLearnResult(index + 1, result, model.isSelected == index))
            }
        }
        val recyclerView =
            baseViewHolder.viewDataBinding.root.findViewById<RecyclerView>(R.id.listResult)

        recyclerView.adapter = adapter
        adapter.setItems(list)

        adapter.onClickItem = { index, view ->
            if (adapter.getItems(index).answer.equals(model.result)) {
                nextItem(position, model, 1, index)
            } else {
                nextItem(position, model, -1, index)
            }
        }
    }

    fun setItems(listItems: ArrayList<TrafficsLearnDetail>) {
        super.setItems(listItems)
    }
}