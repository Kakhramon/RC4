package uz.kahero.rc4

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_web_view.*

class FragmentWebView : Fragment(R.layout.fragment_web_view) {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onViewCreated(view, savedInstanceState)
        web.apply {
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progressBar.isGone = true
                }
            }
            settings.setSupportZoom(true)
            settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
            settings.javaScriptEnabled = true
            loadUrl("https://docs.google.com/gview?embedded=true&url=https://sandilands.info/sgordon/teaching/css322y07s2/protected/CSS322Y07S2H03-RC4-Example.pdf")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_web, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.download) {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://sandilands.info/sgordon/teaching/css322y07s2/protected/CSS322Y07S2H03-RC4-Example.pdf")
            }
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun newInstance() = FragmentWebView()
    }
}