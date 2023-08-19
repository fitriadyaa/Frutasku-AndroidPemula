package id.fitriadyaa.frutasku

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class AboutActivity : ComponentActivity() {
    private fun setActionBarTitle(title: String) {
        title
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val btnVisitWebsite: Button = findViewById(R.id.btn_visit_website)
        btnVisitWebsite.setOnClickListener {
            openWebsite()
        }
    }

    private fun openWebsite() {
        val url = "https://fitriadyaa.github.io"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}