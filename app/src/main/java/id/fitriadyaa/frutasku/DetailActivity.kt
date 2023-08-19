package id.fitriadyaa.frutasku

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setActionBarTitle("Detail Fruit")

        val tvPhoto: ImageView = findViewById(R.id.iv_article)
        val tvTitle: TextView = findViewById(R.id.tv_title)
        val tvDescription: TextView = findViewById(R.id.tv_description)
        val tvPrice: TextView = findViewById(R.id.tv_price)
        val tvStore: TextView = findViewById(R.id.tv_store)
        val tvStorePhoto : ImageView = findViewById(R.id.store_photo)

        val fruitData = intent.getParcelableExtra<FruitDatas>(EXTRA_FRUIT_DATA)

        fruitData?.let {
            tvTitle.text = it.name
            tvPhoto.setImageResource(it.photo)
            tvDescription.text = it.description
            tvPrice.text = it.price
            tvStore.text = it.store

            tvStorePhoto.setImageResource(it.storePhoto)
        }

        val btnShare: Button = findViewById(R.id.btn_set_share)
        btnShare.setOnClickListener {
            shareToWhatsApp()
        }

        val btnBuy: Button = findViewById(R.id.btn_set_favorite)
        btnBuy.setOnClickListener {
            fruitData?.let { fruit ->
                openOnlineStore(fruit.link)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed() // Navigate back to the previous activity (main activity)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setActionBarTitle(title: String) {
        title
    }

    companion object {
        const val EXTRA_FRUIT_DATA = "extra_fruit_data"
    }

    private fun shareToWhatsApp() {
        val title = findViewById<TextView>(R.id.tv_title).text.toString()
        val description = findViewById<TextView>(R.id.tv_description).text.toString()

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        val textToShare = "Check out this article:\n\n$title\n$description"
        shareIntent.putExtra(Intent.EXTRA_TEXT, textToShare)

        // Use the package name for WhatsApp to target it specifically
        shareIntent.setPackage("com.whatsapp")

        try {
            startActivity(shareIntent)
        } catch (e: Exception) {
            // Handle exceptions if WhatsApp is not installed or any other errors
            // For example, open a browser with a WhatsApp Web link
            val webUrl = "https://web.whatsapp.com/"
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(webUrl))
            startActivity(browserIntent)
        }
    }

    private fun openOnlineStore(onlineStoreUrl: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(onlineStoreUrl))
        startActivity(intent)
    }
}

