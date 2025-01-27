package cn.garymb.ygomobile.ui.mycard;
import cn.garymb.ygomobile.lite.BuildConfig;

import java.text.MessageFormat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class X5WebView extends WebView {
    private WebViewClient client = new WebViewClient() {
        /**
         * 防止加载网页时调起系统浏览器
         */
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    };

    @SuppressLint("SetJavaScriptEnabled")
    public X5WebView(Context arg0, AttributeSet arg1) {
        super(arg0, arg1);
        this.setWebViewClient(client);
        // this.setWebChromeClient(chromeClient);
        // WebStorage webStorage = WebStorage.getInstance();
        initWebViewSettings();
        this.getView().setClickable(true);
    }

    public X5WebView(Context arg0) {
        super(arg0);
        setBackgroundColor(85621);
    }

    private void initWebViewSettings() {
        WebSettings webSetting = this.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        //webSetting.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        //禁止多窗口
        webSetting.setSupportMultipleWindows(false);
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);

        // this.getSettingsExtension().setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);//extension
        // settings 的设计
        webSetting.setSaveFormData(true);
        webSetting.setDatabaseEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        setHorizontalScrollBarEnabled(false);
        webSetting.setAllowContentAccess(true);
        webSetting.setMixedContentMode(WebSettings.LOAD_NORMAL);

        webSetting.setUserAgentString(webSetting.getUserAgentString() + MessageFormat.format(
                " YGOMobile/{0} ({1} {2,number,#})",
                BuildConfig.VERSION_NAME,
                BuildConfig.APPLICATION_ID,
                BuildConfig.VERSION_CODE
        ));
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
		/*boolean ret = super.drawChild(canvas, child, drawingTime);
		canvas.save();
		Paint paint = new Paint();
		paint.setColor(0x7fff0000);
		paint.setTextSize(24.f);
		paint.setAntiAlias(true);
		if (getX5WebViewExtension() != null) {
			canvas.drawText(this.getContext().getPackageName() + "-pid:"
							+ android.os.Process.myPid(), 10, 50, paint);
			canvas.drawText(
				"X5  Core:" + QbSdk.getTbsVersion(this.getContext()), 10,
				100, paint);
		} else {
			canvas.drawText(this.getContext().getPackageName() + "-pid:"
							+ android.os.Process.myPid(), 10, 50, paint);
			canvas.drawText("Sys Core", 10, 100, paint);
		}
		canvas.drawText(Build.MANUFACTURER, 10, 150, paint);
		canvas.drawText(Build.MODEL, 10, 200, paint);
		canvas.restore();
		return ret;*/
        return super.drawChild(canvas, child, drawingTime);
    }

    //清除所有cookie
    public void removeAllCookie(String urlpath) {
        CookieSyncManager cookieSyncManager = CookieSyncManager.createInstance(getContext());
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();
        String testcookie1 = cookieManager.getCookie(urlpath);
        cookieManager.removeAllCookie();
        cookieSyncManager.sync();
        String testcookie2 = cookieManager.getCookie(urlpath);
    }

    public void removeAllCookie() {
        removeAllCookie(getUrl());
    }

}

