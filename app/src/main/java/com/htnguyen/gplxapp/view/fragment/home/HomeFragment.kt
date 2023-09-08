package com.htnguyen.gplxapp.view.fragment.home

import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.material.navigation.NavigationView
import com.htnguyen.gplxapp.BuildConfig
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.base.BaseBottomBehavior
import com.htnguyen.gplxapp.base.BaseBottomSheet
import com.htnguyen.gplxapp.base.BaseFragment
import com.htnguyen.gplxapp.base.utils.BaseConst
import com.htnguyen.gplxapp.base.utils.showChangeSizeDialog
import com.htnguyen.gplxapp.databinding.BottomBehaviorListAnswerBinding
import com.htnguyen.gplxapp.databinding.FragmentHomeBinding
import com.htnguyen.gplxapp.view.fragment.chagevoice.ChangeVoiceFragment
import com.htnguyen.gplxapp.view.fragment.exam.ExamFragment
import com.htnguyen.gplxapp.view.fragment.choose_license.ChooseLicenseFragment
import com.htnguyen.gplxapp.view.fragment.introduction_app.IntroductionAppFragment
import com.htnguyen.gplxapp.view.fragment.learning.LearningFragment
import com.htnguyen.gplxapp.view.fragment.setting.SettingFragment
import com.htnguyen.gplxapp.view.fragment.tips.TipsFragment
import com.htnguyen.gplxapp.view.fragment.trafficsigns.TrafficSignsFragment
import org.koin.android.ext.android.bind
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
    navHeader.menu.findItem(R.id.nav_home).isChecked = true

    binding.btnHomeStop.setOnClickListener {
      transitFragmentAnimation(TrafficSignsFragment(), R.id.container)
    }

    binding.btnTips.setOnClickListener {
      transitFragmentAnimation(TipsFragment(), R.id.container)
    }

    binding.btnHomeLearn.setOnClickListener {
      transitFragmentAnimation(LearningFragment(), R.id.container)
    }

    binding.btnHomeExam.setOnClickListener {
      transitFragmentAnimation(ExamFragment(), R.id.container)
    }

    binding.navViewHeader.getHeaderView(0).findViewById<TextView>(R.id.txtVersionName).text = BuildConfig.VERSION_NAME

    context?.showChangeSizeDialog(

    )

  }


  override fun onResume() {
    super.onResume()
    navHeader.menu.findItem(R.id.nav_home).isChecked = true
  }

  override fun initData() {

  }

  override fun initEvent() {

  }

  override fun onNavigationItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.nav_home -> {
        mDrawerLayout.closeDrawer(GravityCompat.START)
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

      R.id.footerPolicy -> {
        mDrawerLayout.closeDrawer(GravityCompat.START)
        PolicyBottomSheet().show(requireActivity().supportFragmentManager, "PolicyBottomSheet")
      }

    }

    return true
  }
}