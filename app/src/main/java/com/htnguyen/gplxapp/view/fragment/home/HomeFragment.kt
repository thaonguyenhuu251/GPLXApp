package com.htnguyen.gplxapp.view.fragment.home

import android.os.Bundle
import android.view.*
import android.widget.SeekBar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.material.navigation.NavigationView
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.databinding.FragmentHomeBinding
import com.htnguyen.gplxapp.view.base.BaseActivity
import com.htnguyen.gplxapp.view.base.BaseFragment
import com.htnguyen.gplxapp.view.base.utils.BaseConst
import com.htnguyen.gplxapp.view.fragment.chagevoice.ChangeVoiceFragment
import com.htnguyen.gplxapp.view.fragment.learning.LearningFragment
import com.htnguyen.gplxapp.view.fragment.choose_license.ChooseLicenseFragment
import com.htnguyen.gplxapp.view.fragment.introduction_app.IntroductionAppFragment
import com.htnguyen.gplxapp.view.fragment.setting.SettingFragment
import com.htnguyen.gplxapp.view.fragment.tips.TipsFragment
import com.htnguyen.gplxapp.view.fragment.trafficsigns.TrafficSignsFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment<FragmentHomeBinding>(), NavigationView.OnNavigationItemSelectedListener {

  private val homeViewModel: HomeViewModel by viewModel()
  private lateinit var mAppBarConfiguration: AppBarConfiguration
  private lateinit var mDrawerLayout: DrawerLayout
  private lateinit var navHeader: NavigationView
  private lateinit var navFooter: NavigationView
  private lateinit var navController: NavController

  override fun getViewBinding(
    inflater: LayoutInflater?,
    container: ViewGroup?
  ): FragmentHomeBinding {
    binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
    return binding
  }

  override fun initView(savedInstanceState: Bundle?, binding: FragmentHomeBinding) {
    binding.toolbar.setNavigationOnClickListener {
      binding.drawerMain.openDrawer(GravityCompat.START)
    }
    mDrawerLayout = binding.drawerMain
    navHeader = binding.navViewHeader
    navFooter = binding.navViewFooter
    mAppBarConfiguration = AppBarConfiguration(
      setOf(
        R.id.nav_home,
        R.id.nav_choose_grade,
        R.id.nav_study_guide,
        R.id.nav_change_voice
      ), mDrawerLayout
    )

    navHeader.setNavigationItemSelectedListener(this)
    navFooter.setNavigationItemSelectedListener(this)
    navHeader.menu.getItem(0).isChecked = true

    binding.btnHomeStop.setOnClickListener {
      transitFragmentAnimation(TrafficSignsFragment(), R.id.container)
    }

    binding.btnTips.setOnClickListener {
      transitFragmentAnimation(TipsFragment(), R.id.container)
    }

    binding.btnHomeLearn.setOnClickListener {
      transitFragmentAnimation(LearningFragment(), R.id.container)
    }

  }


  override fun onResume() {
    super.onResume()
    navHeader.menu.getItem(0).isChecked = true
  }

  override fun initData() {

  }

  override fun initEvent() {

  }

  override fun onNavigationItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.nav_home -> {

      }

      R.id.nav_change_voice -> {
        mDrawerLayout.closeDrawer(GravityCompat.START)
        transitFragmentAnimation(ChangeVoiceFragment(), R.id.container)
      }

      R.id.nav_choose_grade -> {
        mDrawerLayout.closeDrawer(GravityCompat.START)
        val bundle = Bundle()
        bundle.putString(BaseConst.ARG_FROM_HOME, BaseConst.ARG_FROM_HOME)
        transitFragmentAnimation(ChooseLicenseFragment(), R.id.container,bundle)
      }

      R.id.nav_study_guide -> {
        mDrawerLayout.closeDrawer(GravityCompat.START)
        val bundle = Bundle()
        bundle.putString(BaseConst.ARG_FROM_HOME, BaseConst.ARG_FROM_HOME)
        transitFragmentAnimation(IntroductionAppFragment(), R.id.container,bundle)
      }

      R.id.footerSettingApp -> {
        mDrawerLayout.closeDrawer(GravityCompat.START)
        transitFragmentAnimation(SettingFragment(), R.id.container)
      }

    }

    return true
  }
}