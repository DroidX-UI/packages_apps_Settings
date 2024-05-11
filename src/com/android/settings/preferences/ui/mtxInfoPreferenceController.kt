/*
 * Copyright (C) 2023 the DroidxOS Android Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.preferences.ui

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.SystemProperties
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.view.View
import android.util.Log
import androidx.preference.Preference
import androidx.preference.PreferenceScreen
import com.android.settings.R
import com.android.settingslib.core.AbstractPreferenceController
import com.android.settingslib.DeviceInfoUtils
import com.android.settingslib.widget.LayoutPreference

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class mtxInfoPreferenceController(context: Context) : AbstractPreferenceController(context) {

    private val defaultFallback = mContext.getString(R.string.device_info_default)

    private fun getProp(propName: String): String {
        return SystemProperties.get(propName, defaultFallback)
    }
    
    private fun getPropertyOrDefault(propName: String): String {
        return SystemProperties.get(propName, defaultFallback)
    }

    private fun getProp(propName: String, customFallback: String): String {
        val propValue = SystemProperties.get(propName)
        return if (propValue.isNotEmpty()) propValue else SystemProperties.get(customFallback, "Unknown")
    }
    
    private fun getDroidxRamStorage(): String {
        return SystemProperties.get(PROP_DROIDX_RAM, "0gb") + "/" + SystemProperties.get(PROP_DROIDX_STORAGE, "0gb" + " GB")
    }
    
    private fun getDroidxStorage(): String {
        return getProp(PROP_DROIDX_STORAGE)
    }
    
    private fun getDroidxRam(): String {
        return getProp(PROP_DROIDX_RAM)
    }

    private fun getDroidxChipset(): String {
        return getProp(PROP_DROIDX_CHIPSET)
    }

    private fun getDroidxBattery(): String {
        return getProp(PROP_DROIDX_BATTERY) + " mAh"
    }
    
    private fun getDroidxResolution(): String {
        return getProp(PROP_DROIDX_DISPLAY)
    }
    
    private fun getDroidxBrand(): String {
        return getProp(PROP_DROIDX_BRAND)
        //return mContext.getString(R.string.device_brand, PROP_DROIDX_BRAND)
    }
    
    private fun getDroidxModel(): String {
        return getProp(PROP_DROIDX_MODEL)
        //return mContext.getString(R.string.device_model, PROP_DROIDX_MODEL)
    }
    
    /*private fun getBuildImage(releaseType: String): String {
        return mContext.getString(if (releaseType == "official") R.string.checkmarkImage else R.string.crossmarkImage)
    }*/
    
    private fun getDroidxReleaseType(): String {
        val releaseType = getPropertyOrDefault(PROP_DROIDX_RELEASETYPE)
        return releaseType.substring(0, 1).uppercase() +
               releaseType.substring(1).lowercase()
    }

    private fun getDroidxBuildStatus(releaseType: String): String {
        return mContext.getString(if (releaseType == "official") R.string.official_title else R.string.unofficial_title)
    }
    
    private fun getDroidxMaintainer(releaseType: String): String {
        val droidxMaintainer = getPropertyOrDefault(PROP_DROIDX_MAINTAINER)
        if (droidxMaintainer.equals("Unknown", ignoreCase = true)) {
            return mContext.getString(R.string.unknown_maintainer)
        }
        return mContext.getString(R.string.maintainer_summary, droidxMaintainer)
    }
    
    override fun displayPreference(screen: PreferenceScreen) {
        super.displayPreference(screen)

        val releaseType = getPropertyOrDefault(PROP_DROIDX_RELEASETYPE).lowercase()
        val droidxMaintainer = getDroidxMaintainer(releaseType)
        val isOfficial = releaseType == "OFFICIAL"

        val hwInfoPreference = screen.findPreference<LayoutPreference>(KEY_HW_INFO)!!
        val swInfoPreference = screen.findPreference<LayoutPreference>(KEY_SW_INFO)!!
        //val rsInfoPreference = screen.findPreference<LayoutPreference>(KEY_RS_INFO)!!

        hwInfoPreference.apply {
            findViewById<TextView>(R.id.device_chipset).text = getDroidxChipset()
            findViewById<TextView>(R.id.device_storage).text = getDroidxStorage()
            findViewById<TextView>(R.id.device_ram).text = getDroidxRam()
            findViewById<TextView>(R.id.device_battery_capacity).text = getDroidxBattery()
            findViewById<TextView>(R.id.device_resolution).text = getDroidxResolution()
        }
        
        swInfoPreference.apply {
            findViewById<TextView>(R.id.release_type).text = getDroidxReleaseType()
            val myImageView = findViewById<ImageView>(R.id.build_status_image)
            if (releaseType == "official") myImageView.setImageResource(R.drawable.checkmark) else myImageView.setImageResource(R.drawable.crossmark)
        }
        
        //rsInfoPreference.apply {
          //  findViewById<TextView>(R.id.device_ram_storage).text = getDroidxRamStorage()
            //findViewById<TextView>(R.id.device_brand).text = getDroidxBrand()
            //findViewById<TextView>(R.id.device_model).text = getDroidxModel()
        //}

    }

    override fun isAvailable(): Boolean {
        return true
    }

    override fun getPreferenceKey(): String {
        return KEY_DEVICE_INFO
    }

    companion object {
        
        private const val KEY_HW_INFO = "device_hw_info"
        private const val KEY_SW_INFO = "Updater_info"
        private const val KEY_RS_INFO = "top_device_banner"
        private const val KEY_DEVICE_INFO = "my_device_info_header"
        
        private const val KEY_STORAGE = "device_storage"
        private const val KEY_CHIPSET = "device_chipset"
        private const val KEY_BATTERY = "device_battery_capacity"
        private const val KEY_DISPLAY = "device_resolution"
        private const val KEY_RAM_STORAGE = "device_ram_storage"
        
        private const val PROP_DROIDX_RELEASETYPE = "ro.droidx.releasetype"
        private const val PROP_DROIDX_MAINTAINER = "ro.droidx.maintainer"

        private const val PROP_DROIDX_CHIPSET = "ro.droidx.chipset"
        private const val PROP_DROIDX_STORAGE = "ro.droidx.storage"
        private const val PROP_DROIDX_RAM = "ro.droidx.ram"
        private const val PROP_DROIDX_BATTERY = "ro.droidx.battery"
        private const val PROP_DROIDX_DISPLAY = "ro.droidx.display_resolution"
        
        private const val PROP_DROIDX_BRAND = "ro.product.brand"
        private const val PROP_DROIDX_MODEL = "ro.product.model"
    }
}
