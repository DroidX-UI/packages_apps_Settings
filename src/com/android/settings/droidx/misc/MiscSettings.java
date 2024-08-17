package com.android.settings.droidx.misc;

import android.os.Bundle;

import com.android.internal.logging.nano.MetricsProto;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

public class MiscSettings extends SettingsPreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.misc_settings);
    }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.CUSTOMIZE;
    }
}
