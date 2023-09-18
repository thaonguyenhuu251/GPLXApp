package com.htnguyen.gplxapp.view.fragment.trafficsigns

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.htnguyen.gplxapp.base.BaseFragment
import com.htnguyen.gplxapp.base.adapter.BaseRecyclerViewAdapter
import com.htnguyen.gplxapp.base.utils.parseJsonToListTrafficSigns
import com.htnguyen.gplxapp.base.utils.readJSONFromAsset
import com.htnguyen.gplxapp.databinding.FragmentTurnForbiddenBinding
import com.htnguyen.gplxapp.model.TrafficSigns

class TurnForbiddenFragment : BaseFragment<FragmentTurnForbiddenBinding>() {

    private val viewModel by viewModels<TurnForbiddenViewModel>()
    private val adapter = BaseRecyclerViewAdapter<TrafficSigns>()
    var type = 0

    override fun getViewBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?
    ): FragmentTurnForbiddenBinding {
        return FragmentTurnForbiddenBinding.inflate(layoutInflater)
    }

    override fun initData() {
        binding.lifecycleOwner = activity
        binding.viewModel = viewModel
        val json = readJSONFromAsset(requireContext(), "traffigs_signs.json" )
        val list = parseJsonToListTrafficSigns(json)
        binding.rcvTasks.adapter = adapter
        adapter.setItems(list.toList().filter { it.type == type })

    }

    override fun initEvent() {

    }

    override fun initView(savedInstanceState: Bundle?, binding: FragmentTurnForbiddenBinding) {

    }
    companion object {
        fun newInstance(type: Int): TurnForbiddenFragment {
            val args: Bundle = Bundle()
            val fragment = TurnForbiddenFragment()
            fragment.type = type
            return fragment
        }
    }

    override fun onBackPress() {}
}