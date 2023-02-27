package com.timosolo.chatgpt

import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private val web: WebView by lazy { findViewById(R.id.webView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        web.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        web.settings.javaScriptEnabled = true
        web.settings.domStorageEnabled = true
        web.settings.allowFileAccess = true

        web.webViewClient = MyWebViewClient()

        // Set User Agent
        val userAgent = System.getProperty("http.agent")
        web.settings.userAgentString = userAgent!! + "ChatGpt";

        web.loadUrl("https://chat.openai.com")

    }

}