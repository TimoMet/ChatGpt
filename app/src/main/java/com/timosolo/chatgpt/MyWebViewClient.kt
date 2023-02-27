package com.timosolo.chatgpt

import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient


class MyWebViewClient : WebViewClient() {
    var hasFirstLoaded = false
    var isError = false

    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        val uri = request.url
        println("host = " + uri.host)
        if (uri != null && (uri.host == "accounts.google.com" || uri.host == "chat.openai.com" || uri.host == "auth0.openai.com")) {
            return false
        }
        CustomTabs.openUrl(view.context, uri.toString())

        return true
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        isError = false
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        //view?.visibility = if (isError) View.GONE else View.VISIBLE
        if (!isError && !hasFirstLoaded) {
            hasFirstLoaded = true
            view?.visibility = View.VISIBLE
        }
    }

    override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError) {
        Handler(Looper.getMainLooper()).postDelayed({
            view?.loadUrl(view.url!!)
        }, 1000)
        isError = true
        view?.stopLoading()
    }
}