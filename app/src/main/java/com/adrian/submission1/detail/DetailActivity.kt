package com.adrian.submission1.detail

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.adrian.core.domain.model.Restoran
import com.adrian.submission1.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val detailViewModel: DetailViewModel by viewModel()

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val detailRestoran = intent.getParcelableExtra<Restoran>(EXTRA_DATA)
        showDetail(detailRestoran)

        back_button.setOnClickListener {
            onBackPressed()
        }

    }

    private fun showDetail(detailRestoran: Restoran?) {
        detailRestoran?.let {
            name_detail.text = detailRestoran.name
            desc_detail.text = detailRestoran.description
            Glide.with(this)
                .load(detailRestoran.pictureLink)
                .into(image_detail)

            var statusFavorite = detailRestoran.isFavorite
            favoriteStatus(statusFavorite)
            favorite_button.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteRestoran(detailRestoran, statusFavorite)
                favoriteStatus(statusFavorite)
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun favoriteStatus(statusFavorite: Boolean) {
        if (statusFavorite) {
            favorite_button.text = getString(R.string.unFavorite)
        } else {
            favorite_button.text = getString(R.string.favorite)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}