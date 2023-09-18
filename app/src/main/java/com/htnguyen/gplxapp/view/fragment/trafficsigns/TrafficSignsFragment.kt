package com.htnguyen.gplxapp.view.fragment.trafficsigns

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.htnguyen.gplxapp.base.BaseFragment
import com.htnguyen.gplxapp.base.BasePager
import com.htnguyen.gplxapp.databinding.FragmentTrafficSignsBinding

class TrafficSignsFragment : BaseFragment<FragmentTrafficSignsBinding>() {
    private val titles = arrayOf("BIẾN BÁO CẤM", "BIỂN BÁO NGUY HIỂM", "BIỂN HIỆU LỆNH", "BIỂN CHỈ DẪN", "BIẾN PHỤ")
    private var pagerAdapter: BasePager? = null
    private val listFragment: List<BaseFragment<*>> = listOf(
        TurnForbiddenFragment.newInstance(1),
        TurnForbiddenFragment.newInstance(2),
        TurnForbiddenFragment.newInstance(3),
        TurnForbiddenFragment.newInstance(5),
        TurnForbiddenFragment.newInstance(6)
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
        binding.imgBack.setOnClickListener {
            onClickBack()
        }

        binding.txtBack.setOnClickListener {
            onClickBack()
        }

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

    override fun onBackPress() {}
}