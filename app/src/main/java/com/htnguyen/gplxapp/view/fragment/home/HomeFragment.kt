package com.htnguyen.gplxapp.view.fragment.home

import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.provider.MediaStore.Images
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.htnguyen.gplxapp.databinding.FragmentHomeBinding
import com.htnguyen.gplxapp.view.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

  private val homeViewModel: HomeViewModel by viewModel()

  override fun getViewBinding(
    inflater: LayoutInflater?,
    container: ViewGroup?
  ): FragmentHomeBinding {
    binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
    return binding!!
  }

  override fun initView(savedInstanceState: Bundle?, binding: ViewBinding) {

  }


  override fun initData() {

  }

  override fun initEvent() {

  }
}