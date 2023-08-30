package com.htnguyen.gplxapp.view.fragment.introduction_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.databinding.FragmentIntroductionAppBinding
import com.htnguyen.gplxapp.view.base.BaseFragment
import com.htnguyen.gplxapp.view.base.utils.BaseConst
import com.htnguyen.gplxapp.view.fragment.home.HomeFragment
import com.htnguyen.gplxapp.view.base.utils.SharePreference
import org.koin.androidx.viewmodel.ext.android.viewModel

class IntroductionAppFragment : BaseFragment<FragmentIntroductionAppBinding>() {

    private val viewModel: IntroductionAppViewModel by viewModel()
    var fromHome: String = ""

    override fun getViewBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?
    ): FragmentIntroductionAppBinding {
        binding = FragmentIntroductionAppBinding.inflate(layoutInflater, container, false)
        return binding
    }

    override fun initData() {
    }

    override fun initEvent() {
    }

    override fun initView(savedInstanceState: Bundle?, binding: FragmentIntroductionAppBinding) {

        val adapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        adapter.addFragment(FirstIntroductionFragment())
        adapter.addFragment(SecondIntroductionFragment())
        adapter.addFragment(ThirdIntroductionFragment())
        adapter.addFragment(FourthIntroductionFragment())

        binding.apply {
            val bundle = arguments
            if (bundle != null) {
                fromHome = bundle.getString(BaseConst.ARG_FROM_HOME).toString()
                if (fromHome.isNotEmpty()) {
                    btnNext.setOnClickListener {
                        onClickBack()
                    }
                }
            } else {
                btnNext.setOnClickListener {
                    replaceFragment(HomeFragment(), R.id.container, false)
                    SharePreference.isLoginFirst = false
                }
            }

            viewPager.adapter = adapter
            dotsIndicator.attachTo(binding.viewPager)
            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    if (position == 3) {
                        btnNext.visibility = View.VISIBLE
                    } else {
                        btnNext.visibility = View.GONE
                    }
                }
            })
        }
    }


}