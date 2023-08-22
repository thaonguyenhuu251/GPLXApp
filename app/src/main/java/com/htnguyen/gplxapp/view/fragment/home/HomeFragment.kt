package com.htnguyen.gplxapp.view.fragment.home

import android.os.Bundle
import android.view.*
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.material.navigation.NavigationView
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.databinding.FragmentHomeBinding
import com.htnguyen.gplxapp.view.base.BaseFragment
import com.htnguyen.gplxapp.view.fragment.chagevoice.ChangeVoiceFragment
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
    navHeader.menu.getItem(0).isChecked = true
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

      }

      R.id.nav_study_guide -> {

      }

    }


    return true
  }
}