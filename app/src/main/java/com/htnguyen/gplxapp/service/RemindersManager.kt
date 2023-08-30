package com.htnguyen.gplxapp.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.*


object RemindersManager {
    const val REMINDER_NOTIFICATION_REQUEST_CODE = 123
    fun startReminder(
        context: Context,
        reminderTime: String = "10:00",
        reminderId: Int = REMINDER_NOTIFICATION_REQUEST_CODE
    ) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context.applicationContext, AlarmReceiver::class.java).let { intent ->
                        PendingIntent.getBroadcast(
                            context.applicationContext,
                            reminderId,
                            intent,
                            PendingIntent.FLAG_IMMUTABLE
                        )
                    }
        val (hours, min) = reminderTime.split(":").map { it.toInt() }

        val calendar = Calendar.getInstance()
        calendar[Calendar.HOUR_OF_DAY] = hours
        calendar[Calendar.MINUTE] = min
        calendar[Calendar.SECOND] = 0

        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, intent)
    }

    fun stopReminder(
        context: Context,
        reminderId: Int = REMINDER_NOTIFICATION_REQUEST_CODE
    ) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(
                context,
                reminderId,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )
        }
        alarmManager.cancel(intent)
    }
}