package com.codepath.articlesearch
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.articlesearch.databinding.ActivityMainBinding
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.serialization.json.Json
import okhttp3.Headers
import org.json.JSONException


fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

private const val TAG = "MainActivity/"



class MainActivity : AppCompatActivity() {





    val fragmentManager = supportFragmentManager
    private lateinit var binding: ActivityMainBinding
    private fun replaceFragment(articleListFragment: ArticleListFragment) {

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.article_frame_layout, articleListFragment)
        fragmentTransaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // define your fragments here
        val fragment1: Fragment = BestSellerBooksFragment()
        val fragment2: Fragment = ArticleListFragment()
        val bottomNavigationView: BottomNavigationView =  findViewById(R.id.bottom_navigation)




        replaceFragment(ArticleListFragment())

        // handle navigation selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.booksitem -> fragment = fragment1
                R.id.articlesitem -> fragment = fragment2
            }
            fragmentManager.beginTransaction().replace(R.id.article_frame_layout, fragment).commit()
            true
        }

        // Set default selection
        bottomNavigationView.selectedItemId = R.id.booksitem
    }
}

