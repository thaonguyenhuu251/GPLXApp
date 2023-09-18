package com.htnguyen.gplxapp.view.fragment.setting

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.TimePickerDialog
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getColor
import com.htnguyen.gplxapp.MainApp
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.base.BaseFragment
import com.htnguyen.gplxapp.base.utils.SharePreference
import com.htnguyen.gplxapp.databinding.FragmentSettingBinding
import com.htnguyen.gplxapp.service.RemindersManager
import com.htnguyen.gplxapp.view.activity.MainActivity

class SettingFragment : BaseFragment<FragmentSettingBinding>() {

    var hour = 10
    var minutes = 0
    var changeData: Boolean = false
    var progressChange = 4
    override fun getViewBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?
    ): FragmentSettingBinding {
        return FragmentSettingBinding.inflate(layoutInflater)
    }

    override fun initData() {

    }

    override fun initEvent() {
        binding.txtTimeNotification.text = SharePreference.timeNotification
        progressChange =  SharePreference.fontText
        binding.imgBack.setOnClickListener {
            if (changeData) {
                openActivity(MainActivity::class.java, false)
                SharePreference.fontText = progressChange
            } else {
                onClickBack()
            }

        }

        binding.txtBack.setOnClickListener {
            if (changeData) {
                openActivity(MainActivity::class.java, false)
                SharePreference.fontText = progressChange
            } else {
                onClickBack()
            }
        }
        binding.txtTimeNotification.setOnClickListener {
            val timePicker = TimePickerDialog(
                requireContext(),
                timePickerDialogListener,
                hour,
                minutes,
                true
            )

            timePicker.show()
        }

        binding.switchNotification.isChecked = SharePreference.isNotification && SharePreference.isAnswerNotification

        binding.switchNotification.setOnCheckedChangeListener { buttonView, isChecked ->
            if (SharePreference.isNotification) {
                if (isChecked) {
                    RemindersManager.startReminder(requireContext(), "${hour}:${minutes}")
                } else {
                    RemindersManager.stopReminder(requireContext())
                }
            }

        }

        binding.switchNotification.setOnClickListener {
            if (!NotificationManagerCompat.from(requireContext()).areNotificationsEnabled()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    (activity as com.htnguyen.gplxapp.base.BaseActivity).checkNotification()
                } else {
                    (activity as com.htnguyen.gplxapp.base.BaseActivity).dialogSetNotification()
                }
                binding.switchNotification.isChecked =
                    SharePreference.isNotification
                            && SharePreference.isAnswerNotification
                            && NotificationManagerCompat.from(requireContext()).areNotificationsEnabled()

            }
        }

        binding.switchMode.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {

            } else {

            }
        }

        binding.rgMode.check(
            if (SharePreference.isModeLight) R.id.rdLight
            else R.id.rdDark
        )

        binding.rgMode.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId != R.id.rdDark) {
                SharePreference.isModeLight = true
                MainApp.instance.setDayModeOrSth()
                onClickBack()
            } else {
                SharePreference.isModeLight = false
                MainApp.instance.setNightMode()
                onClickBack()
            }
            SharePreference.fontText = progressChange
        }

        binding.seekbarDiscrete.progress = SharePreference.fontText

        binding.seekbarDiscrete.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                changeData = true
                binding.txtTitleChangeFont.textSize = (8.0F + progress * 1.8F)/resources.configuration.fontScale
                binding.switchNotification.textSize = (8.0F + progress * 1.8F)/resources.configuration.fontScale
                binding.switchMode.textSize = (8.0F + progress * 1.8F)/resources.configuration.fontScale
                binding.rdDark.textSize = (8.0F + progress * 1.8F)/resources.configuration.fontScale
                binding.rdLight.textSize = (8.0F + progress * 1.8F)/resources.configuration.fontScale
                binding.txtTimeNotification.textSize = (8.0F + progress * 1.8F)/resources.configuration.fontScale
                binding.txtBack.textSize = (8.0F + progress * 1.8F)/resources.configuration.fontScale
                progressChange = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }

    override fun onResume() {
        super.onResume()
        SharePreference.isNotification = NotificationManagerCompat.from(requireContext()).areNotificationsEnabled()
        binding.switchNotification.isChecked =
            SharePreference.isNotification
                    && SharePreference.isAnswerNotification
                    && NotificationManagerCompat.from(requireContext()).areNotificationsEnabled()
    }

    private fun createNotification() {
        val body = "hello"
        val title = "abc"

        val channelId = "op"
        val notificationId = System.currentTimeMillis()
        val defaultSoundUri =
            RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(requireContext(), channelId)
            .setSmallIcon(R.drawable.ic_check)
            .setColor(getColor(requireContext(), R.color.primary_dark))
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setPriority(NotificationCompat.PRIORITY_MAX or NotificationCompat.PRIORITY_HIGH)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))

        val notificationManager =
            requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Thông báo",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(notificationId.toInt(), notificationBuilder.build())
    }

    override fun initView(savedInstanceState: Bundle?, binding: FragmentSettingBinding) {

    }

    private val timePickerDialogListener: TimePickerDialog.OnTimeSetListener =
        TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            /*val formattedTime: String = when {
                hourOfDay == 0 -> {
                    if (minute < 10) {
                        "${hourOfDay + 12}:0${minute} am"
                    } else {
                        "${hourOfDay + 12}:${minute} am"
                    }
                }
                hourOfDay > 12 -> {
                    if (minute < 10) {
                        "${hourOfDay - 12}:0${minute} pm"
                    } else {
                        "${hourOfDay - 12}:${minute} pm"
                    }
                }
                hourOfDay == 12 -> {
                    if (minute < 10) {
                        "${hourOfDay}:0${minute} pm"
                    } else {
                        "${hourOfDay}:${minute} pm"
                    }
                }
                else -> {
                    if (minute < 10) {
                        "${hourOfDay}:${minute} am"
                    } else {
                        "${hourOfDay}:${minute} am"
                    }
                }
            }*/

            binding.txtTimeNotification.text = "${hour}:${minutes}"
            hour = hourOfDay
            minutes = minute
            SharePreference.timeNotification = "${hour}:${minutes}"
            if (binding.switchNotification.isChecked) {
                RemindersManager.startReminder(requireContext(), "${hour}:${minutes}")
            }
        }

    override fun onBackPress() {
        if (changeData) {
            openActivity(MainActivity::class.java, false)
            SharePreference.fontText = progressChange
        }
    }

}