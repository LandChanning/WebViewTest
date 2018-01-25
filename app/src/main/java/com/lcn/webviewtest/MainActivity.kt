package com.lcn.webviewtest

import android.net.http.SslError
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        val settings = webView.settings
        webView.addJavascriptInterface(JavaScriptInterface(), "App")
        webView.webViewClient = MyWebViewClient()
        webView.webChromeClient = MyWebChromeClient()
        settings.javaScriptEnabled = true
        settings.setSupportZoom(true)
        settings.builtInZoomControls = true
        settings.displayZoomControls = false
        settings.useWideViewPort = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true)
        }
        // 有时 JS 代码加载时 WebView 并没有 attach 到屏幕上，导致获取的宽度为 0
        if (webView.width > 0) {
            webView.loadUrl("file:///android_asset/viewport/web1.html")
        } else {
            webView.post {
                webView.loadUrl("file:///android_asset/viewport/web1.html")
            }
        }
    }

    inner class JavaScriptInterface {

        @JavascriptInterface
        fun getWindowWidth(): Int {
            val webViewWidth = webView.width
            val scale = resources.displayMetrics.density
            return (webViewWidth / scale + 0.5f).toInt()
        }
    }

    inner class MyWebViewClient : WebViewClient() {
        override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
            handler?.proceed()
        }
    }

    inner class MyWebChromeClient : WebChromeClient() {

        override fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
            return true
        }

        override fun onGeolocationPermissionsShowPrompt(origin: String?, callback: GeolocationPermissions.Callback?) {
            callback?.invoke(origin, true, false)
            super.onGeolocationPermissionsShowPrompt(origin, callback)
        }
    }
}
