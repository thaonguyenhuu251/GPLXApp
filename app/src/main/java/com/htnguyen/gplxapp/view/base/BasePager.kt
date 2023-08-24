package com.htnguyen.gplxapp.view.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class BasePager(
    private val arrayTitle: Array<String>,
    fragmentActivity: FragmentActivity,
    private val arrayFragment: List<BaseFragment<*>>,
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return arrayTitle.size
    }

    override fun createFragment(position: Int): Fragment {
        return arrayFragment[position]
    }
}