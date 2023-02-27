package com.timosolo.chatgpt

import CustomTabs
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient


class MyWebViewClient : WebViewClient() {


    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        val uri = request.url
        println("host = " + uri.host)
        if (uri != null && uri.host == "accounts.google.com" || uri.host == "chat.openai.com" || uri.host == "auth0.openai.com") {
            return false
        }
        CustomTabs.openUrl(view.context, uri.toString())

        return true
    }

}