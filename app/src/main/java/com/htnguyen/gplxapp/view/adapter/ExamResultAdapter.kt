package com.htnguyen.gplxapp.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.base.adapter.BaseRecyclerViewAdapter
import com.htnguyen.gplxapp.model.ExamDetail
import com.htnguyen.gplxapp.model.ExamResult

class ExamResultAdapter : BaseRecyclerViewAdapter<ExamResult>(){

    var currentSelectedItem: ExamResult? = null
    var currentSelectedPosition: Int? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model: ExamResult = getItems(position)
        val baseViewHolder: BaseViewHolder = holder as BaseViewHolder
        baseViewHolder.bind(model)

        baseViewHolder.viewDataBinding.root.setOnClickListener { p0 ->
            currentSelectedPosition?.let {
                if (it < itemCount) {
                    getItems(it).isSelected = false
                    notifyItemChanged(it)
                }
            }

            model.isSelected = true
            currentSelectedItem = model
            currentSelectedPosition = position
            notifyItemChanged(position)
            onClickItem(position, p0)
        }
    }

    fun setItems(listItems: ArrayList<ExamResult>) {
        for (i in listItems.indices) {
            if (listItems[i].isSelected) {
                currentSelectedPosition = i
                break
            }
        }
        super.setItems(listItems)
    }
}