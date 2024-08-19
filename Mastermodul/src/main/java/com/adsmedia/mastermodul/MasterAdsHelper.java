package com.adsmedia.mastermodul;

import static com.adsmedia.mastermodul.RestApi.getJSON;

import android.app.Activity;
import android.view.View;
import android.widget.RelativeLayout;

import com.startapp.sdk.ads.banner.Banner;
import com.startapp.sdk.ads.banner.BannerListener;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.adlisteners.VideoListener;


public class MasterAdsHelper {
    private static StartAppAd startAppAd;
    public static boolean unlockreward = false;
    public static void initializeAds(Activity activity, String packName) {
        getJSON(activity, packName);
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

    public static void loadInterstitial(Activity activity) {
        startAppAd = new StartAppAd(activity);
        startAppAd.loadAd();
    }

    public static void showInterstitial(Activity activity) {
        startAppAd.showAd();
    }

    public static StartAppAd rewardedVideo;

    public static void loadReward(Activity activity) {
        rewardedVideo = new StartAppAd(activity);
        rewardedVideo.setVideoListener(new VideoListener() {
            @Override
            public void onVideoCompleted() {
                unlockreward = true;
            }
        });
        rewardedVideo.loadAd(StartAppAd.AdMode.REWARDED_VIDEO, new AdEventListener() {
            @Override
            public void onReceiveAd(com.startapp.sdk.adsbase.Ad ad) {

            }

            @Override
            public void onFailedToReceiveAd(com.startapp.sdk.adsbase.Ad ad) {

            }
        });
    }

    public static void showReward(Activity activity) {
        if (rewardedVideo.isReady()) {
            rewardedVideo.showAd();
        }
    }
}
