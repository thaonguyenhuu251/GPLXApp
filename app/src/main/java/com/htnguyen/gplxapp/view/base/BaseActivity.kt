package com.htnguyen.gplxapp.view.base

import android.Manifest
import android.app.ActionBar
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.NotificationManagerCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.databinding.ActivityBaseBinding
import com.htnguyen.gplxapp.service.RemindersManager
import com.htnguyen.gplxapp.view.base.customView.loading.BaseLoadingView
import com.htnguyen.gplxapp.view.base.textview.htext.AnimationListener
import com.htnguyen.gplxapp.view.base.textview.htext.HTextView
import com.htnguyen.gplxapp.view.base.utils.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.time.Duration

abstract class BaseActivity : AppCompatActivity() {
    private lateinit var binding: ViewBinding
    private lateinit var rootView: ActivityBaseBinding
    lateinit var fontSizeManager: FontSizeManager
    private val permissionHelper: PermissionsHelper by lazy {
        PermissionsHelper()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = resources.getColor(R.color.primary_dark, this@BaseActivity.theme)
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    var requestLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {

        }
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun checkNotification() {
        var count = SharePreference.countAnswerNotification
        SharePreference.countAnswerNotification = count + 1
        permissionHelper.withActivity(this)
            .check(Manifest.permission.POST_NOTIFICATIONS)
            .onSuccess {
                requestLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                SharePreference.isNotification = true
                SharePreference.isAnswerNotification = true

            }
            .onDenied {
                SharePreference.isNotification = false
                SharePreference.isAnswerNotification = true
            }
            .onNeverAskAgain {

            }
            .run()

        if (SharePreference.countAnswerNotification > 2) {
            dialogSetNotification()
        }

        Log.d("ThaoNH", SharePreference.countAnswerNotification.toString())
    }

    fun createNotificationChannel() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(getString(R.string.reminders_notification_channel_id),
                "Thông báo mặc định",
                NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }
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

    fun adjustFontScale(configuration: Configuration?, scale: Float) {
        configuration?.let {
            it.fontScale = scale
            val metrics: DisplayMetrics = resources.displayMetrics
            val wm: WindowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
            wm.defaultDisplay.getMetrics(metrics)
            metrics.scaledDensity = configuration.fontScale * metrics.density

            baseContext.applicationContext.createConfigurationContext(it)
            baseContext.resources.displayMetrics.setTo(metrics)
            resources.updateConfiguration(configuration, metrics)
        }
    }


    fun dialogSetNotification() {
        val builder = AlertDialog.Builder(this, R.style.FinishActionDialog)

        val mLayoutInflater = LayoutInflater.from(this)
        val mView: View = mLayoutInflater.inflate(R.layout.dialog_alert, null)
        val message: TextView = mView.findViewById(R.id.message)
        val positiveButton = mView.findViewById<Button>(R.id.btn_positive)
        val negativeButton = mView.findViewById<Button>(R.id.btn_negative)

        message.text = "Thêm quyền thông báo cho ứng dụng"
        builder.setCustomTitle(mView)

        val dialog = builder.show()
        positiveButton.setOnClickListener {
            goToSettingDevice()
            dialog.dismiss()
        }

        negativeButton.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun goToSettingDevice(){
        val i = Intent()
        i.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        i.addCategory(Intent.CATEGORY_DEFAULT)
        i.data = Uri.parse("package:" + this.packageName)
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
        this.startActivity(i)
    }

}