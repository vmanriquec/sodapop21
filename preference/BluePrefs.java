package com.food.sistemas.sodapopapp.preference;

import android.content.Context;

public class BluePrefs {

    private SingletonPrefs prefs;

    private static final String KEY_CONNECTED = "blue_connected";

    private static final String KEY_PAIRED_MACCADDRESS = "paired_mac_address";
    private static final String KEY_OPTION = "option";
    private static final String KEY_SUBOPTION = "suboption";

    public BluePrefs(Context context) {
        prefs = SingletonPrefs.getInstance(context);
    }

    public void reset() {
        setKeyBlueConnected(false);
        setKeyPairedMaccAddress("");
        setKeyOption("");
        setKeySuboption("");
    }

    public void setKeyOption(String value) {
        prefs.put(KEY_OPTION, value);
    }

    public String getKeyOption() {
        return prefs.getString(KEY_OPTION);
    }

    public void setKeySuboption(String value) {
        prefs.put(KEY_SUBOPTION, value);
    }

    public String getKeySuboption() {
        return prefs.getString(KEY_SUBOPTION);
    }

    public void setKeyPairedMaccAddress(String value) {
        prefs.put(KEY_PAIRED_MACCADDRESS, value);
    }

    public String getKeyPairedMaccAddress() {
        return prefs.getString(KEY_PAIRED_MACCADDRESS);
    }

    public void setKeyBlueConnected(Boolean val) {
        prefs.put(KEY_CONNECTED, val);
    }

    public Boolean getKeyBlueConnected() {
        return prefs.getBoolean(KEY_CONNECTED);
    }

}
