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

import android.app.settings.SettingsEnums;
import android.os.Bundle;

import com.android.settings.R;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settingslib.search.SearchIndexable;

import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;

@SearchIndexable
public class FirmwareVersionSettings extends DashboardFragment {

    @Override
    protected int getPreferenceScreenResId() {
        return R.xml.firmware_version;
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        final PreferenceScreen screen = getPreferenceScreen();
        Preference pref = screen.getPreference(0);
        pref.setLayoutResource(R.layout.codenameLayout);
        pref = screen.getPreference(1);
        pref.setLayoutResource(R.layout.androidDxuiVlayout);
        pref = screen.getPreference(2);
        pref.setLayoutResource(R.layout.deviceinfoLayout);
        for (int i = 3; i < screen.getPreferenceCount(); i++) {
            pref = screen.getPreference(i);
            boolean isValid = pref.isEnabled() && pref.isVisible() && pref.getTitle() != null;
            if (isValid && pref.getLayoutResource() != R.layout.codenameLayout &&
                pref.getLayoutResource() != R.layout.androidDxuiVlayout) {
                if(i == 3){ pref.setLayoutResource(R.layout.firmwareremainlayoutTop); }
                else { pref.setLayoutResource(R.layout.firmwareremainlayout); }
            }
        }
    }

    @Override
    protected String getLogTag() {
        return "FirmwareVersionSettings";
    }

    @Override
    public int getMetricsCategory() {
        return SettingsEnums.DIALOG_FIRMWARE_VERSION;
    }

    public static final BaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER =
            new BaseSearchIndexProvider(R.xml.firmware_version);
}
