package uz.kahero.rc4

import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main) {
    val rc4 = RC4()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        generate.setOnClickListener {
            if (isValid(text) && isValid(key))
                generate()
        }
    }

    private fun generate() {
        try {
            val result =
                rc4.generate(
                    key.text.toString(),
                    text.text.toString()
                )
            AlertDialog.Builder(requireContext())
                .setMessage(Html.fromHtml(result))
                .setTitle(getString(R.string.result))
                .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                    dialog.dismiss()
                }.setNegativeButton(getString(R.string.copy)) { dialog, _ ->
                    (requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).setPrimaryClip(
                        ClipData.newPlainText("RC4", result)
                    )
                    dialog.dismiss()
                }.show()
        } catch (e: Exception) {
            key.error = e.message
        }
    }

    private fun isValid(editText: EditText): Boolean {
        if (editText.text.toString().isEmpty()) editText.error = getString(R.string.required_field)
        return editText.text.toString().isNotEmpty()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about -> {
                (activity as MainActivity).startFragment(FragmentWebView.newInstance(), true)
            }
            R.id.code -> {
                (activity as MainActivity).startFragment(ShowCodeFragment.newInstance(), true)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}