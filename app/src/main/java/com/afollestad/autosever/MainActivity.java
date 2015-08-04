package com.afollestad.autosever;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Aidan Follestad (afollestad)
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.statusImage)
    ImageView statusImage;
    @Bind(R.id.statusText)
    TextView statusText;

    private WifiManager mManager;
    private Toast mToast;

    private void showToast(@StringRes int text) {
        if (mToast != null) mToast.cancel();
        mToast = Toast.makeText(this, text, Toast.LENGTH_LONG);
        mToast.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Util.defaultState(this, mManager.isWifiEnabled());
        invalidate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.frame)
    public void onToggle() {
        Util.isEnabled(this, !Util.isEnabled(this));
        if (!mManager.isWifiEnabled()) {
            mManager.setWifiEnabled(true);
            showToast(R.string.enabling_wifi);
        }
        invalidate();
    }

    private void invalidate() {
        if (mManager.isWifiEnabled()) {
            final boolean enabled = Util.isEnabled(this);
            statusImage.setImageResource(enabled ?
                    R.drawable.ic_enabled : R.drawable.ic_disabled);
            statusText.setText(enabled ?
                    R.string.status_enabled : R.string.status_disabled);
        } else {
            statusImage.setImageResource(R.drawable.ic_disabled);
            statusText.setText(R.string.status_off);
        }
    }
}