package com.htnguyen.gplxapp.view.fragment.webview
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.base.BaseFragment
import com.htnguyen.gplxapp.base.utils.BaseConst
import com.htnguyen.gplxapp.base.utils.observe
import com.htnguyen.gplxapp.databinding.FragmentLearningBinding
import com.htnguyen.gplxapp.databinding.FragmentSearchWebviewBinding
import com.htnguyen.gplxapp.view.adapter.TrafficLearnAdapter


class WebViewFragment : BaseFragment<FragmentSearchWebviewBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?
    ): FragmentSearchWebviewBinding {
        return FragmentSearchWebviewBinding.inflate(layoutInflater)
    }

    override fun initData() {
        binding.lifecycleOwner = activity

    }

    override fun initEvent() {
        binding.imgBack.setOnClickListener {
            onClickBack()
        }

        binding.txtBack.setOnClickListener {
            onClickBack()
        }
        binding.webView.webViewClient = WebViewClient()

        // this will load the url of the website
        binding.webView.loadUrl("https://thuvienphapluat.vn")

        // this will enable the javascript settings, it can also allow xss vulnerabilities
        binding.webView.settings.javaScriptEnabled = true

        // if you want to enable zoom feature
        binding.webView.settings.setSupportZoom(true)

        binding.txtBack.text = arguments?.getString(BaseConst.ARG_TITLE_FROM_HOME)

    }

    override fun initView(savedInstanceState: Bundle?, binding: FragmentSearchWebviewBinding) {

    }

    override fun onBackPress() {}

}