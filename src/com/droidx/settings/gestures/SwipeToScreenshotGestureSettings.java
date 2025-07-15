/*
 * SPDX-FileCopyrightText: 2019 The Android Open Source Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.droidx.settings.gestures;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.SearchIndexableResource;

import com.android.settings.R;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.dashboard.suggestions.SuggestionFeatureProvider;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settingslib.search.SearchIndexable;

import java.util.Arrays;
import java.util.List;

@SearchIndexable
public class SwipeToScreenshotGestureSettings extends DashboardFragment {

     private static final String TAG = "SwipeToScreenshotGestureSettings";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public int getMetricsCategory() {
        return -1;
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected int getPreferenceScreenResId() {
        return R.xml.swipe_to_screenshot_gesture_settings;
    }

    public static final SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER =
            new BaseSearchIndexProvider() {
                @Override
                public List<SearchIndexableResource> getXmlResourcesToIndex(
                        Context context, boolean enabled) {
                    final SearchIndexableResource sir = new SearchIndexableResource(context);
                    sir.xmlResId = R.xml.swipe_to_screenshot_gesture_settings;
                    return Arrays.asList(sir);
                }
            };
}
