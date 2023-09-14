package com.htnguyen.gplxapp.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.base.adapter.BaseRecyclerViewAdapter
import com.htnguyen.gplxapp.model.ExamDetail
import com.htnguyen.gplxapp.model.ExamResult

class ExamResultAdapter : BaseRecyclerViewAdapter<ExamResult>(){

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model: ExamResult = getItems(position)
        val baseViewHolder: BaseViewHolder = holder as BaseViewHolder
        baseViewHolder.bind(model)

        baseViewHolder.viewDataBinding.root.setOnClickListener { p0 ->
            notifyItemChanged(position)
            onClickItem(position, p0)
        }
    }

    fun setItems(listItems: ArrayList<ExamResult>) {
        super.setItems(listItems)
    }
}