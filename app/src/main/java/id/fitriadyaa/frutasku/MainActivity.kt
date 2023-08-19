package id.fitriadyaa.frutasku

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : ComponentActivity() {
    private lateinit var rvFruits: RecyclerView
    private val list = ArrayList<FruitDatas>()
    private val title = "Frutasku"

    private fun setActionBarTitle(title: String) {
        title
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setActionBarTitle(title)

        rvFruits = findViewById(R.id.rv_heroes)
        rvFruits.setHasFixedSize(true)

        list.addAll(getListFruits())
        showRecyclerCardView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        when (item.itemId) {
            R.id.action_list -> {
                rvFruits.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvFruits.layoutManager = GridLayoutManager(this, 2)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListFruits(): ArrayList<FruitDatas> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataPrice = resources.getStringArray(R.array.data_price)
        val dataStore = resources.getStringArray(R.array.data_store)
        val dataStorePhoto = resources.obtainTypedArray(R.array.data_store_photo)
        val dataLink = resources.getStringArray(R.array.data_link)
        val listFruit = ArrayList<FruitDatas>()
        for (i in dataName.indices) {
            val fruit = FruitDatas(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1), dataPrice[i], dataStore[i], dataStorePhoto.getResourceId(i, -1), dataLink[i])
            listFruit.add(fruit)
        }
        return listFruit
    }


    private fun showRecyclerCardView() {
        rvFruits.layoutManager = LinearLayoutManager(this)
        val cardViewArticleAdapter = ListFruitAdapter(list)
        rvFruits.adapter = cardViewArticleAdapter

        cardViewArticleAdapter.setOnItemClickCallback(object : ListFruitAdapter.OnItemClickCallback {
            override fun onItemClicked(data: FruitDatas) {
                val intentToDetail = Intent(this@MainActivity, DetailActivity::class.java)
                intentToDetail.putExtra(DetailActivity.EXTRA_FRUIT_DATA, data)
                startActivity(intentToDetail)
            }
        })
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {

            R.id.about_page -> {
                val aboutIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(aboutIntent)
            }
        }
    }

    private fun showSelectedFruit(fruit: FruitDatas) {
        Toast.makeText(this, "Kamu memilih " + fruit.name, Toast.LENGTH_SHORT).show()
    }
}
