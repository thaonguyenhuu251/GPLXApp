package com.htnguyen.gplxapp.view.adapter

import android.graphics.Color
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.Resource
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.base.adapter.BaseRecyclerViewAdapter
import com.htnguyen.gplxapp.model.*

class ExamHistoryAnswerAdapter : BaseRecyclerViewAdapter<ExamHistoryAnswer>(){


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model: ExamHistoryAnswer = getItems(position)
        val baseViewHolder: BaseViewHolder = holder as BaseViewHolder
        baseViewHolder.bind(model)
        baseViewHolder.viewDataBinding.root.setOnClickListener {}
        if(model.isSelected == true && model.isAnswer == true){
            baseViewHolder.viewDataBinding.root.findViewById<TextView>(R.id.tvAnswer).setTextColor(Color.BLUE)
        } else if (model.isSelected == true && model.isAnswer == false) {
            baseViewHolder.viewDataBinding.root.findViewById<TextView>(R.id.tvAnswer).setTextColor(Color.RED)
        } else if (model.isSelected == false && model.isAnswer == true) {
            baseViewHolder.viewDataBinding.root.findViewById<TextView>(R.id.tvAnswer).setTextColor(Color.BLUE)
        } else {
            baseViewHolder.viewDataBinding.root.findViewById<TextView>(R.id.tvAnswer).setTextColor(Color.BLACK)
        }

    }

    fun setItems(listItems: ArrayList<ExamHistoryAnswer>) {
        super.setItems(listItems)
    }
}