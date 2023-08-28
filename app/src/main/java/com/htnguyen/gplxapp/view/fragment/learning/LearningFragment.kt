package com.htnguyen.gplxapp.view.fragment.learning
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.htnguyen.gplxapp.database.TrafficLearnDatabase
import com.htnguyen.gplxapp.databinding.FragmentLearningBinding
import com.htnguyen.gplxapp.model.TrafficsLearn
import com.htnguyen.gplxapp.view.base.BaseFragment
import com.htnguyen.gplxapp.view.base.adapter.BaseRecyclerViewAdapter
import com.htnguyen.gplxapp.view.base.utils.observe
import com.htnguyen.gplxapp.view.fragment.trafficsigns.TurnForbiddenViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LearningFragment : BaseFragment<FragmentLearningBinding>() {
    private val adapter = BaseRecyclerViewAdapter<TrafficsLearn>()
    private val learningViewModel by viewModels<LearningViewModel>()

    override fun getViewBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?
    ): FragmentLearningBinding {
        return FragmentLearningBinding.inflate(layoutInflater)
    }

    override fun initData() {
        binding.lifecycleOwner = activity
        binding.rcvLearn.adapter = adapter

        learningViewModel.addData()

        with(learningViewModel) {
            observe(responseTrafficLearn) {
                it?.let {
                    adapter.setItems(it)
                }
            }
        }
    }

    override fun initEvent() {
        binding.imgBack.setOnClickListener {
            onClickBack()
        }

        binding.txtBack.setOnClickListener {
            onClickBack()
        }
    }

    override fun initView(savedInstanceState: Bundle?, binding: FragmentLearningBinding) {

    }

}