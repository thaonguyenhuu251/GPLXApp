package com.htnguyen.gplxapp.view.base

import android.app.ActionBar
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.databinding.ActivityBaseBinding
import com.htnguyen.gplxapp.view.base.customView.loading.BaseLoadingView
import com.htnguyen.gplxapp.view.base.textview.htext.AnimationListener
import com.htnguyen.gplxapp.view.base.textview.htext.HTextView
import com.htnguyen.gplxapp.view.base.utils.NetworkUtil
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class BaseActivity : AppCompatActivity() {
    private lateinit var binding: ViewBinding
    private lateinit var rootView: ActivityBaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                statusBarColor = resources.getColor(R.color.primary_dark, this@BaseActivity.theme)
            }
        }
        rootView = DataBindingUtil.inflate(layoutInflater, R.layout.activity_base, null, false)
        binding = getBindingView()
        rootView.container.addView(binding.root)
        rootView.loading.addView(getLoadingView())
        setContentView(rootView.root)
        initView(savedInstanceState, binding)
        NetworkUtil(this).observe(this) {
            if (!it){
                Toast.makeText(this, resources.getString(R.string.you_are_offline), Toast.LENGTH_SHORT).show()
            }
        }
    }

    abstract fun getBindingView(): ViewBinding

    abstract fun initView(savedInstanceState: Bundle?, binding: ViewBinding)

    open fun getLoadingView(): View {
        return BaseLoadingView(this).apply {
            layoutParams = ConstraintLayout.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT
            )
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    fun openActivity(destination: Class<*>, canBack: Boolean = true, bundle: Bundle? = null) {
        val intent = Intent(this, destination)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
        this.overridePendingTransition(
            R.anim.enter_from_right, R.anim.exit_to_left
        )
        if (!canBack) {
            finish()
        }
    }

    fun addFragment(fragment: Fragment, containerId: Int) {
        supportFragmentManager.beginTransaction()
            .add(containerId, fragment)
            .addToBackStack(null)
            .commit()
    }

    fun replaceFragment(fragment: Fragment, containerId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(containerId, fragment)
            .commit()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun receiveEvent(event: Any?) {
        handleEvent(event)
    }

    //receiver any event
    open fun handleEvent(event: Any?) {

    }

    fun sendEvent(message: Any?) {
        requireNotNull(message) { "Object message can not be null" }
        EventBus.getDefault().post(message)
    }

    fun showKeyboard(view: EditText) {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }

    fun closeKeyBoard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(this.window.decorView.windowToken, 0)
    }

    fun toggleProgressLoading(isShow: Boolean) {
        rootView.hasLoading = isShow
    }

    internal class SimpleAnimationListener(
        private val context: Context,
    ) : AnimationListener {
        override fun onAnimationEnd(hTextView: HTextView?) {

        }
    }
}