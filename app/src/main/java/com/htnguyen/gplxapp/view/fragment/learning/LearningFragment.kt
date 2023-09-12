package com.htnguyen.gplxapp.view.fragment.learning
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.base.BaseFragment
import com.htnguyen.gplxapp.base.utils.BaseConst
import com.htnguyen.gplxapp.base.utils.observe
import com.htnguyen.gplxapp.databinding.FragmentLearningBinding
import com.htnguyen.gplxapp.view.adapter.TrafficLearnAdapter


class LearningFragment : BaseFragment<FragmentLearningBinding>() {
    private val adapter = TrafficLearnAdapter()
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

        adapter.sendDataItem = { i, e ->
            val bundle = Bundle()
            e?.let { bundle.putInt(BaseConst.ARG_TRAFFIC_LEARN_TYPE, it.id) }
            transitFragmentAnimation(LearningDetailFragment(), R.id.container, bundle)
        }
    }

    override fun initView(savedInstanceState: Bundle?, binding: FragmentLearningBinding) {

    }

}