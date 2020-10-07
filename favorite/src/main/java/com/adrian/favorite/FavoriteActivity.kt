package com.adrian.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrian.core.ui.RestoranAdapter
import com.adrian.submission1.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_favorite.*
import org.koin.core.context.loadKoinModules
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        loadKoinModules(favoriteModule)

        val restoranAdapter = RestoranAdapter()

        restoranAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favoriteRestoran.observe(this, { restoran ->
            restoranAdapter.setData(restoran)
            if (restoran.isEmpty()) {
                empty.visibility = View.VISIBLE
            } else {
                empty.visibility = View.GONE
            }
        })

        with(favorite_list) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = restoranAdapter
        }
    }
}