/*
 * SPDX-FileCopyrightText: 2019 The Android Open Source Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.droidx.settings.gestures;

import static android.provider.Settings.System.THREE_FINGER_GESTURE;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.text.TextUtils;

import com.android.settings.gestures.GesturePreferenceController;

public class SwipeToScreenshotPreferenceController extends GesturePreferenceController {

    private final int ON = 1;
    private final int OFF = 0;

    private static final String PREF_KEY_VIDEO = "swipe_to_screenshot_video";

    public SwipeToScreenshotPreferenceController(Context context, String key) {
        super(context, key);
    }

    @Override
    public int getAvailabilityStatus() {
        return AVAILABLE;
    }

    @Override
    public boolean isSliceable() {
        return TextUtils.equals(getPreferenceKey(), "swipe_to_screenshot");
    }

    @Override
    protected String getVideoPrefKey() {
        return PREF_KEY_VIDEO;
    }

    @Override
    public boolean setChecked(boolean isChecked) {
        return Settings.System.putInt(mContext.getContentResolver(), THREE_FINGER_GESTURE,
                isChecked ? ON : OFF);
    }

    @Override
    public boolean isChecked() {
        return Settings.System.getInt(mContext.getContentResolver(), THREE_FINGER_GESTURE, 0) != 0;
    }
}
