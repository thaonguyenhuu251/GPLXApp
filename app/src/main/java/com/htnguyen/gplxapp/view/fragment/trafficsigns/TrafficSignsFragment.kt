package com.htnguyen.gplxapp.view.fragment.trafficsigns

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.htnguyen.gplxapp.databinding.FragmentTrafficSignsBinding
import com.htnguyen.gplxapp.view.base.BaseFragment
import com.htnguyen.gplxapp.view.base.BasePager
import com.htnguyen.gplxapp.view.fragment.home.HomeFragment

class TrafficSignsFragment : BaseFragment<FragmentTrafficSignsBinding>() {
    private val titles = arrayOf("BIẾN BÁO CẤM", "BIỂN HIỆU LỆNH", "BIỂN CHỈ DẪN", "BIỂN BÁO NGUY HIỂM VÀ CẢNH BÁO", "BIẾN PHỤ")
    private var pagerAdapter: BasePager? = null
    private val listFragment: List<BaseFragment<*>> = listOf(
        TurnForbiddenFragment(),
        TurnForbiddenFragment(),
        TurnForbiddenFragment(),
        TurnForbiddenFragment(),
        TurnForbiddenFragment()
    )

    override fun getViewBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?
    ): FragmentTrafficSignsBinding {
        return FragmentTrafficSignsBinding.inflate(layoutInflater)
    }

    override fun initData() {

    }

    override fun initEvent() {

    }

    override fun initView(savedInstanceState: Bundle?, binding: FragmentTrafficSignsBinding) {
        pagerAdapter = BasePager(titles, requireActivity(), listFragment)
        binding.viewPager.adapter = pagerAdapter
        setTabLayout()
    }
    private fun setTabLayout() {
        TabLayoutMediator(binding.tbLayout, binding.viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()

        binding.tbLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }


}