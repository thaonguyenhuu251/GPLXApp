package com.htnguyen.gplxapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.htnguyen.gplxapp.BR
import com.htnguyen.gplxapp.base.adapter.BaseRecyclerViewAdapter
import com.htnguyen.gplxapp.databinding.ItemChangeVoiceBinding
import com.htnguyen.gplxapp.model.ChangeVoice

class ChangeVoiceAdapter : BaseRecyclerViewAdapter<ChangeVoice>() {

    var onClickTask: (data: ChangeVoice) -> Unit = {_: ChangeVoice-> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return  CreatorViewHolder(ItemChangeVoiceBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CreatorViewHolder).bind(getItems(position), position)
    }

    inner class CreatorViewHolder(var binding: ItemChangeVoiceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: ChangeVoice, position: Int) {
            binding.setVariable(BR.model, model)
            binding.executePendingBindings()
            binding.txtTryListen.setOnClickListener {
                onClickTask(model)
            }
        }
    }
}