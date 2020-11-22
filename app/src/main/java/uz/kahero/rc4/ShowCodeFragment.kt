package uz.kahero.rc4

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment

/**
 * A simple [Fragment] subclass.
 * Use the [ShowCodeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShowCodeFragment : Fragment(R.layout.fragment_web_view) {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<WebView>(R.id.web).apply {
            webViewClient = WebViewClient()
            settings.setSupportZoom(true)
            settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
            settings.javaScriptEnabled = true
            loadUrl("https://docs.google.com/gview?embedded=true&url=http://library.ziyonet.uz/ru/book/download/75166")
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment ShowCodeFragment.
         */
        @JvmStatic
        fun newInstance() =
            ShowCodeFragment()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_web, menu)
        menu.findItem(R.id.download).setIcon(R.drawable.github)
        super.onCreateOptionsMenu(menu, inflater)
    }
}