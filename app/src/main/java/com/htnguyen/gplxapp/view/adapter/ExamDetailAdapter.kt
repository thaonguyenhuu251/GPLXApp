package com.htnguyen.gplxapp.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.base.adapter.BaseRecyclerViewAdapter
import com.htnguyen.gplxapp.model.ExamDetail
import com.htnguyen.gplxapp.model.ExamResult
import com.htnguyen.gplxapp.model.TrafficsLearnDetail

class ExamDetailAdapter : BaseRecyclerViewAdapter<ExamDetail>(){
    var sendDataItem: (position: Int, exam: ExamDetail?) -> Unit = { _: Int, _: ExamDetail? -> }

    var doExamItem: (position: Int, trafficsLearn: ExamDetail, result : Int, answerChoose: String) -> Unit =
        { _: Int, _: ExamDetail, _: Int, _: String -> }

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
                list.add(ExamResult(index + 1, result,model.isSelected == index))
            }
        }
        baseViewHolder.viewDataBinding.root.findViewById<RecyclerView>(R.id.listResult).adapter = adapter
        adapter.setItems(list)
        adapter.onClickItem = { index, view ->
            if (adapter.getItems(index).answer.equals(model.result)){
                adapter.getItems(index).answer?.let { doExamItem(position, model,1, it) }
            } else {
                adapter.getItems(index).answer?.let { doExamItem(position, model,-1, it) }
            }


        }
    }

    fun setItems(listItems: ArrayList<ExamDetail>) {
        super.setItems(listItems)
    }
}