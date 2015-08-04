package com.afollestad.autosever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.widget.Toast;

/**
 * @author Aidan Follestad (afollestad)
 */
public class StateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
            final NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            final boolean state = info.getDetailedState() == NetworkInfo.DetailedState.CONNECTED;
            if (Util.lastState(context) != state) {
                Util.lastState(context, state);
                if (!state && Util.isEnabled(context)) {
                    ((WifiManager) context.getSystemService(Context.WIFI_SERVICE)).setWifiEnabled(false);
                    Toast.makeText(context, R.string.disabling_wifi, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
