package com.adrian.submission1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrian.core.data.Resource
import com.adrian.core.ui.RestoranAdapter
import com.adrian.submission1.detail.DetailActivity
import com.adrian.submission1.home.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {

            val restoranAdapter = RestoranAdapter()

            restoranAdapter.onItemClick = { selectedData ->
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.restoran.observe(this, { restoran ->
                if (restoran != null) {
                    when (restoran) {
                        is Resource.Loading -> progress_bar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            restoranAdapter.setData(restoran.data)
                            progress_bar.visibility = View.GONE
                            error.visibility = View.GONE
                        }
                        is Resource.Error -> {
                            progress_bar.visibility = View.GONE
                            error.visibility = View.VISIBLE
                        }
                    }
                }
            })

            with(home_list) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = restoranAdapter
            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favorite -> {
                val intent = Uri.parse("restoranapp://fav")
                startActivity(Intent(Intent.ACTION_VIEW, intent))
                true
            }
            else -> true
        }
    }

}