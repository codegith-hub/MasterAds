package com.adsmedia.mastermodul;

import android.app.Activity;
import android.view.View;
import android.widget.RelativeLayout;

import com.startapp.sdk.ads.banner.Banner;
import com.startapp.sdk.ads.banner.BannerListener;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MasterAdsHelper {
    public static void initializeAds(Activity activity, int keyPos) {
      Config.loadKey(activity,keyPos);
    }

    public static void debugMode(Boolean debug) {
        StartAppSDK.setTestAdsEnabled(debug);
    }

    public static void showBanner(Activity activity, RelativeLayout layout) {
        Banner startAppBanner = new Banner(activity, new BannerListener() {
            @Override
            public void onReceiveAd(View view) {
            }

            @Override
            public void onFailedToReceiveAd(View view) {
                layout.setVisibility(View.GONE);
            }

            @Override
            public void onImpression(View view) {
            }

            @Override
            public void onClick(View view) {
            }
        });
        RelativeLayout.LayoutParams bannerParameters =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        bannerParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layout.addView(startAppBanner, bannerParameters);
    }


    public static void showInterstitial(Activity activity) {
        StartAppAd.showAd(activity);
    }
}
