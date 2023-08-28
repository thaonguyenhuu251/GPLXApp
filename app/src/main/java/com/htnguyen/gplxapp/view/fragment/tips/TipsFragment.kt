package com.htnguyen.gplxapp.view.fragment.tips
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.htnguyen.gplxapp.databinding.FragmentTipsBinding
import com.htnguyen.gplxapp.model.Tips
import com.htnguyen.gplxapp.model.TrafficSigns
import com.htnguyen.gplxapp.view.base.BaseFragment
import com.htnguyen.gplxapp.view.base.adapter.BaseRecyclerViewAdapter
import com.htnguyen.gplxapp.view.base.utils.parseJsonToListTips
import com.htnguyen.gplxapp.view.base.utils.parseJsonToListTrafficSigns
import com.htnguyen.gplxapp.view.base.utils.readJSONFromAsset
import com.htnguyen.gplxapp.view.fragment.trafficsigns.TurnForbiddenViewModel

class TipsFragment : BaseFragment<FragmentTipsBinding>() {

    private val viewModel by viewModels<TipsViewModel>()
    private val adapter = TipsExpendAdapter<Tips>()

    override fun getViewBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?
    ): FragmentTipsBinding {
        return FragmentTipsBinding.inflate(layoutInflater)
    }

    override fun initData() {
        binding.lifecycleOwner = activity
        binding.viewModel = viewModel
        val json = readJSONFromAsset(requireContext(), "tips.json" )
        val list = parseJsonToListTips(json)
        binding.rcvTips.adapter = adapter
        adapter.setItems(list.toList())
    }

    override fun initEvent() {
        binding.imgBack.setOnClickListener {
            onClickBack()
        }

        binding.txtBack.setOnClickListener {
            onClickBack()
        }
    }

    override fun initView(savedInstanceState: Bundle?, binding: FragmentTipsBinding) {

    }

}