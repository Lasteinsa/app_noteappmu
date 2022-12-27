package com.lasteinsa.noteappmu.ui.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import com.lasteinsa.noteappmu.databinding.ActivitySettingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding
    private val settingViewModel: SettingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSetup()
        initAction()
    }

    private fun initSetup() {
        binding.nightSwitch.isChecked = settingViewModel.getTheme()
    }

    private fun initAction() {
        binding.settingToolbar.setNavigationOnClickListener {
            finish()
        }
        binding.nightSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            when(isChecked) {
                true -> {
                    settingViewModel.setTheme(true)
                }
                else -> {
                    settingViewModel.setTheme(false)
                }
            }
            if(settingViewModel.getTheme()) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}