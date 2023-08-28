package com.htnguyen.gplxapp.view.fragment.tips

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.databinding.ItemTipsBinding
import com.htnguyen.gplxapp.model.Tips
import com.htnguyen.gplxapp.view.base.adapter.BaseItem
import com.htnguyen.gplxapp.view.base.adapter.BaseRecyclerViewAdapter

class TipsExpendAdapter<T> : BaseRecyclerViewAdapter<Tips>(){


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model: Tips = getItems(position)
        val baseViewHolder: BaseViewHolder = holder as BaseViewHolder
        baseViewHolder.bind(model)

        baseViewHolder.viewDataBinding.root.setOnClickListener { p0 ->
            model.isSelected =  !model.isSelected
            notifyItemChanged(position)
            onClickItem(position, p0)
        }
    }

    fun setItems(listItems: ArrayList<Tips>) {
        super.setItems(listItems)
    }
}