/*
 * Copyright (C) 2019 The Android Open Source Project
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
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.preference.Preference;

import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;

public class RomVersionDetailPreferenceController extends BasePreferenceController {

    private static final String TAG = "romDialogCtrl";
    private static final String KEY_ROM_VERSION_PROP = "ro.droidx.build.version";
    private static final String KEY_ROM_VERSION_CODENAME = "ro.droidx.version";
    private static final String KEY_ROM_RELEASETYPE_PROP = "ro.droidx.releasetype";
    private static final String KEY_ROM_VARIANT_PROP = "ro.droidx.releasevariant";

    private final PackageManager mPackageManager;

    public RomVersionDetailPreferenceController(Context context, String key) {
        super(context, key);
        mPackageManager = mContext.getPackageManager();
    }

    @Override
    public int getAvailabilityStatus() {
        return AVAILABLE;
    }

    @Override
    public CharSequence getSummary() {
        String romVersion = SystemProperties.get(KEY_ROM_VERSION_PROP,
                this.mContext.getString(R.string.device_info_default));
        String romCodeName = SystemProperties.get(KEY_ROM_VERSION_CODENAME,
                this.mContext.getString(R.string.device_info_default));
        String romReleasetype = SystemProperties.get(KEY_ROM_RELEASETYPE_PROP,
                this.mContext.getString(R.string.device_info_default));
        String romVariant = SystemProperties.get(KEY_ROM_VARIANT_PROP,
                this.mContext.getString(R.string.device_info_default));
        if (!romVersion.isEmpty() && !romReleasetype.isEmpty())
            return romVersion + " | " + romReleasetype + " | " + romVariant;
        else
            return mContext.getString(R.string.rom_version_default);
    }
}
