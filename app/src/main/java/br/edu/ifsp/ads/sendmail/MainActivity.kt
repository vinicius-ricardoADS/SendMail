package br.edu.ifsp.ads.sendmail

import android.content.Intent
import android.content.Intent.*
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import br.edu.ifsp.ads.sendmail.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        with (amb) {
            cleanBt.setOnClickListener {
                listOf(toEt, ccEt, bccEt, subjectEt, messageEt).clearText()
            }

            sendBt.setOnClickListener {
                startActivity(Intent(ACTION_CHOOSER).putExtra(EXTRA_INTENT, createEmailIntent()))
            }
        }
    }

    private fun List<EditText>.clearText() = forEach { it.text.clear() }

    private fun createEmailIntent(): Intent {
        val sendEmail = Intent(ACTION_SENDTO)
        with (amb) {
            with (sendEmail) {
                putExtra(EXTRA_EMAIL, arrayOf(toEt.text.toString()))
                putExtra(EXTRA_CC, arrayOf(ccEt.text.toString()))
                putExtra(EXTRA_BCC, arrayOf(bccEt.text.toString()))
                putExtra(EXTRA_SUBJECT, arrayOf(subjectEt.text.toString()))
                putExtra(EXTRA_TEXT, arrayOf(messageEt.text.toString()))
                type = "message/rfc822"
                data = Uri.parse("mailto:")
            }
        }
        return sendEmail
    }
}