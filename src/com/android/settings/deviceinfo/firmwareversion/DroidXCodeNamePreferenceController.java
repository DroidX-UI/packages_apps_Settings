/*
 * Copyright (C) 2021 PixelPlusUI
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

package com.android.settings.deviceinfo.firmwareversion;

import android.content.Context;
import android.os.SystemProperties;
import android.text.TextUtils;

import androidx.preference.Preference;

import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;

public class DroidXCodeNamePreferenceController extends BasePreferenceController {

    private static final String TAG = "DroidXCodeNamePreferenceController";
    private static final String DX_CODENAME = "ro.droidx.codename";

    public DroidXCodeNamePreferenceController(Context context, String key) {
        super(context, key);
    }

    public int getAvailabilityStatus() {
        return AVAILABLE;
    }

    public CharSequence getSummary() {
        String dxcodename = SystemProperties.get(DX_CODENAME,
                this.mContext.getString(R.string.device_info_default));
        return dxcodename;
    }
}
