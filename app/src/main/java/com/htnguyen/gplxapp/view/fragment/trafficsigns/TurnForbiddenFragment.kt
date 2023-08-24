package com.htnguyen.gplxapp.view.fragment.trafficsigns

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.htnguyen.gplxapp.databinding.FragmentTurnForbiddenBinding
import com.htnguyen.gplxapp.view.base.BaseFragment
import com.htnguyen.gplxapp.view.base.utils.loadJSONFromAsset
import com.htnguyen.gplxapp.view.base.utils.parseJsonToListTrafficSigns
import com.htnguyen.gplxapp.view.base.utils.readJSONFromAsset

class TurnForbiddenFragment : BaseFragment<FragmentTurnForbiddenBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?
    ): FragmentTurnForbiddenBinding {
        return FragmentTurnForbiddenBinding.inflate(layoutInflater)
    }

    override fun initData() {
        var list = parseJsonToListTrafficSigns(readJSONFromAsset(requireContext(), "traffigs_signs.json" ) ?: "")
    }

    override fun initEvent() {

    }

    override fun initView(savedInstanceState: Bundle?, binding: FragmentTurnForbiddenBinding) {

    }

}