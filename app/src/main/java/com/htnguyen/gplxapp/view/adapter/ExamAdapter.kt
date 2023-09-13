package com.htnguyen.gplxapp.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.htnguyen.gplxapp.base.adapter.BaseRecyclerViewAdapter
import com.htnguyen.gplxapp.model.Exam

class ExamAdapter : BaseRecyclerViewAdapter<Exam>(){
    var sendDataItem: (position: Int, exam: Exam?) -> Unit = { _: Int, _: Exam? -> }

    var nextItem: () -> Unit = {}
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model: Exam = getItems(position)
        val baseViewHolder: BaseViewHolder = holder as BaseViewHolder
        baseViewHolder.bind(model)

        baseViewHolder.viewDataBinding.root.setOnClickListener { p0 ->
            notifyItemChanged(position)
            onClickItem(position, p0)
            sendDataItem(position, model)
        }
    }

    fun setItems(listItems: ArrayList<Exam>) {
        super.setItems(listItems)
    }
}