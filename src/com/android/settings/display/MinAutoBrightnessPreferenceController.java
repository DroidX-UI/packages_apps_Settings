/*
 * Copyright (C) 2024 Yet Another AOSP Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.android.settings.display;

import android.content.Context;
import android.os.Process;
import android.os.UserManager;
import android.provider.Settings;

import androidx.preference.Preference;

import com.android.settings.core.BasePreferenceController;

public class MinAutoBrightnessPreferenceController extends BasePreferenceController {
    private static final String KEY = Settings.Global.USER_MIN_AUTO_BRIGHTNESS;

    public MinAutoBrightnessPreferenceController(Context context) {
        super(context, KEY);
    }

    @Override
    public int getAvailabilityStatus() {
        if (!mContext.getResources().getBoolean(
                com.android.internal.R.bool.config_automatic_brightness_available)) {
            return UNSUPPORTED_ON_DEVICE;
        }
        return AVAILABLE;
    }

    @Override
    public void updateState(Preference preference) {
        if (preference.isEnabled() && UserManager.get(mContext).hasBaseUserRestriction(
                UserManager.DISALLOW_CONFIG_BRIGHTNESS, Process.myUserHandle())) {
            preference.setEnabled(false);
        }
    }
}
