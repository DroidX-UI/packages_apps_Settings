/*
 * Copyright (C) 2018 The Android Open Source Project
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

package com.android.settings.deviceinfo.aboutphone;

import android.content.Context;

import android.os.SystemProperties;
import android.text.TextUtils;

import com.android.settings.core.BasePreferenceController;
import com.android.settings.deviceinfo.DeviceNamePreferenceController;

public class TopLevelAboutDevicePreferenceController extends BasePreferenceController {

    private static final String DEVICE_BRAND = "ro.product.brand";
    private static final String DEVICE_MODEL = "ro.product.model";

    public TopLevelAboutDevicePreferenceController(Context context, String preferenceKey) {
        super(context, preferenceKey);
    }

    @Override
    public int getAvailabilityStatus() {
        return AVAILABLE;
    }

    @Override
    public CharSequence getSummary() {
        final DeviceNamePreferenceController deviceNamePreferenceController =
                new DeviceNamePreferenceController(mContext, "unused_key");
         final String MyString = deviceNamePreferenceController.getSummary().toString();
         final String[] words = MyString.split(" ");
         
         StringBuilder remainingString = new StringBuilder();
         for (int i = 1; i < words.length; i++) {
            remainingString.append(words[i]).append(" ");
        }
        return words[0] + "\n" + remainingString.toString().trim();
    }
    
}
