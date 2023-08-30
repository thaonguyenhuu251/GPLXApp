package com.htnguyen.gplxapp.view.base

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import androidx.annotation.AnimRes
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.htnguyen.gplxapp.R
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class BaseFragment<VB : ViewBinding> : Fragment() {
    lateinit var binding: VB
    private val TAG = this::class.java.name
    private lateinit var activity: BaseActivity
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root by lazy { binding.root }
        if (!this::binding.isInitialized) {
            binding = getViewBinding(inflater, container)
        } else {
            (root.parent as ViewGroup?)?.endViewTransition(root)
        }
        activity = requireActivity() as BaseActivity
        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(savedInstanceState, binding)
        initData()
        initEvent()
    }

    protected abstract fun getViewBinding(inflater: LayoutInflater?, container: ViewGroup?): VB

    abstract fun initView(savedInstanceState: Bundle?, binding: VB)

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
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
        activity.showKeyboard(view)
    }

    fun closeKeyBoard() {
        activity.closeKeyBoard()
    }

    fun openActivity(destination: Class<*>, canBack: Boolean = true, bundle: Bundle? = null) {
        val intent = Intent(activity, destination)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
        activity.overridePendingTransition(
            R.anim.enter_from_left, R.anim.exit_to_right
        )
        if (!canBack) {
            activity.finish()
        }
    }

    protected fun transitFragment(
        fragment: BaseFragment<*>,
        @IdRes id: Int,
        args: Bundle? = null,
        @AnimRes enterAnim: Int = R.anim.slide_in,
        @AnimRes exitAnim: Int = R.anim.fade_out,
        @AnimRes popEnter: Int = R.anim.fade_in,
        @AnimRes popExit: Int = R.anim.slide_out,
    ) {
        val fragmentManager = activity.supportFragmentManager
        args?.let {
            fragment.arguments = args
        }
        fragmentManager.beginTransaction().setCustomAnimations(
            enterAnim,  // enter
            exitAnim,  // exit
            popEnter,   // popEnter
            popExit  // popExit
        ).add(id, fragment, fragment.javaClass.name).addToBackStack(fragment.TAG).commit()
    }

    protected fun transitFragmentAnimation(
        fragment: BaseFragment<*>,
        @IdRes id: Int,
        args: Bundle? = null,
        @AnimRes enterAnim: Int = R.anim.slide_up,
        @AnimRes exitAnim: Int = R.anim.slide_down,
        @AnimRes popEnter: Int = R.anim.slide_up,
        @AnimRes popExit: Int = R.anim.slide_down,
    ) {
        val fragmentManager = activity.supportFragmentManager
        args?.let {
            fragment.arguments = args
        }
        fragmentManager.beginTransaction().setCustomAnimations(
            enterAnim,  // enter
            exitAnim,  // exit
            popEnter,   // popEnter
            popExit  // popExit
        ).add(id, fragment, fragment.javaClass.name).addToBackStack(fragment.TAG).commit()
    }

    protected fun replaceFragment(fragment: BaseFragment<*>, @IdRes id: Int, args: Bundle? = null) {
        val fragmentManager = activity.supportFragmentManager
        args?.let {
            fragment.arguments = args
        }
        fragmentManager.beginTransaction().setCustomAnimations(
            R.anim.slide_in,  // enter
            R.anim.fade_out,  // exit
            R.anim.fade_in,   // popEnter
            R.anim.slide_out  // popExit
        ).replace(id, fragment, fragment.javaClass.name).commit()
    }

    protected fun transitChildFragment(
        parentFragment: Fragment,
        fragment: BaseFragment<*>,
        @IdRes id: Int,
        args: Bundle? = null
    ) {
        val fragmentManager = parentFragment.childFragmentManager
        fragment.arguments = args

        fragmentManager.beginTransaction().setCustomAnimations(
            R.anim.slide_in,  // enter
            R.anim.fade_out,  // exit
            R.anim.fade_in,   // popEnter
            R.anim.slide_out  // popExit
        ).add(id, fragment, fragment.javaClass.name).addToBackStack(fragment.TAG).commit()
    }

    fun transitFragment(
        fragment: BaseFragment<*>,
        @IdRes id: Int,
        isAddToBackStack: Boolean = true,
        args: Bundle? = null
    ) {
        val fragmentManager = activity.supportFragmentManager
        fragment.arguments = args
        fragmentManager.beginTransaction().setCustomAnimations(
            R.anim.slide_in,  // enter
            R.anim.fade_out,  // exit
            R.anim.fade_in,   // popEnter
            R.anim.slide_out  // popExit
        ).add(id, fragment, fragment.javaClass.name).addToBackStack(fragment.TAG).commit()
    }

    fun transitFragmentNoAnimation(
        fragment: BaseFragment<*>,
        @IdRes id: Int,
        isAddToBackStack: Boolean = true,
        args: Bundle? = null
    ) {
        val fragmentManager = activity.supportFragmentManager
        fragment.arguments = args
        fragmentManager.beginTransaction().add(id, fragment, fragment.javaClass.name).addToBackStack(fragment.TAG).commit()
    }

    fun replaceFragment(
        fragment: BaseFragment<*>,
        @IdRes id: Int,
        isAddToBackStack: Boolean = true,
        args: Bundle? = null
    ) {
        val fragmentManager = activity.supportFragmentManager
        fragment.arguments = args
        fragmentManager.beginTransaction().setCustomAnimations(
            R.anim.slide_in,  // enter
            R.anim.fade_out,  // exit
            R.anim.fade_in,   // popEnter
            R.anim.slide_out  // popExit
        ).replace(id, fragment, fragment.javaClass.name).commit()
    }

    fun onClickBack() {
        activity.onBackPressed()
    }
    protected abstract fun initData()
    protected abstract fun initEvent()


}