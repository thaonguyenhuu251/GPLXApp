package com.htnguyen.gplxapp.view.fragment.home

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore.Images
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.htnguyen.gplxapp.databinding.FragmentHomeBinding
import com.htnguyen.gplxapp.view.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

  private val homeViewModel: HomeViewModel by viewModel()
  private lateinit var binding: FragmentHomeBinding

  override fun getViewBinding(
    inflater: LayoutInflater?,
    container: ViewGroup?
  ): FragmentHomeBinding {
    binding = FragmentHomeBinding.inflate(inflater!!, container, false)
    return binding
  }

  override fun initView() {
  }

  override fun initData() {
    getCameraImages(requireContext())
    Log.d("TAG", "initData: ")
  }

  fun getCameraImages(context: Context){
    val columns = arrayOf(Images.Media.DATA, Images.Media._ID)
    val orderBy = Images.Media._ID

    val cursor: Cursor? = requireActivity().getContentResolver().query(
      Images.Media.EXTERNAL_CONTENT_URI, columns, null,
      null, orderBy
    )

    val count: Int? = cursor?.getCount()


    val arrPath = count?.let { arrayOfNulls<String>(it) }

    for (i in 0 until count!!) {
      cursor.moveToPosition(i)
      val dataColumnIndex: Int = cursor.getColumnIndex(Images.Media.DATA)

      arrPath?.set(i, cursor.getString(dataColumnIndex))
      Log.i("PATH", arrPath?.get(i)!!)
    }

    cursor.close()
    Log.d("TAG", "getCameraImages: ")
  }

  override fun initEvent() {
    binding.floatingActionButton2.setOnClickListener {

    }
  }
}