package com.htnguyen.gplxapp.view.adapter

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.base.adapter.BaseRecyclerViewAdapter
import com.htnguyen.gplxapp.model.*

class ExamHistoryAdapter : BaseRecyclerViewAdapter<ExamHistory>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model: ExamHistory = getItems(position)
        val baseViewHolder: BaseViewHolder = holder as BaseViewHolder
        baseViewHolder.bind(model)
        baseViewHolder.viewDataBinding.root.setOnClickListener {}

        val adapter = ExamHistoryAnswerAdapter()
        val list = arrayListOf<ExamHistoryAnswer>()
        model.answer_list?.forEachIndexed { index, result ->
            if (result.isNotEmpty()) {
                list.add(
                    ExamHistoryAnswer(
                        index + 1,
                        result,
                        index == model.positionChoose,
                        result == model.result
                    )
                )
            }
        }
        baseViewHolder.viewDataBinding.root.findViewById<RecyclerView>(R.id.listResult).adapter = adapter
        adapter.setItems(list)
    }

    fun setItems(listItems: ArrayList<ExamHistory>) {
        super.setItems(listItems)
    }
}