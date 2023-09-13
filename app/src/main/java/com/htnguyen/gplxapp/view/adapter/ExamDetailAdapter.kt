package com.htnguyen.gplxapp.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.base.adapter.BaseRecyclerViewAdapter
import com.htnguyen.gplxapp.model.ExamDetail
import com.htnguyen.gplxapp.model.ExamResult

class ExamDetailAdapter : BaseRecyclerViewAdapter<ExamDetail>(){
    var sendDataItem: (position: Int, exam: ExamDetail?) -> Unit = { _: Int, _: ExamDetail? -> }

    var nextItem: () -> Unit = {}
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model: ExamDetail = getItems(position)
        sendDataItem(position, model)
        val baseViewHolder: BaseViewHolder = holder as BaseViewHolder
        baseViewHolder.bind(model)

        baseViewHolder.viewDataBinding.root.setOnClickListener { p0 ->
            notifyItemChanged(position)
            onClickItem(position, p0)
        }

        val adapter = ExamResultAdapter()
        val list = arrayListOf<ExamResult>()
        model.answer_list?.forEachIndexed { index, result ->
            if (result.isNotEmpty()) {
                list.add(ExamResult(index + 1, result))
            }
        }
        baseViewHolder.viewDataBinding.root.findViewById<RecyclerView>(R.id.listResult).adapter = adapter
        adapter.setItems(list)
    }

    fun setItems(listItems: ArrayList<ExamDetail>) {
        super.setItems(listItems)
    }
}