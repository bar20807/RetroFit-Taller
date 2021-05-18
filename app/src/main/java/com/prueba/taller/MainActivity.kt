package com.prueba.taller

import android.hardware.input.InputManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.prueba.taller.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import androidx.appcompat.widget.SearchView


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var binding:ActivityMainBinding
    private lateinit var adapter: ArticleAdapter
    private val articleList = mutableListOf<Articules>()

    private var place: String ="us"
    private var type: String = "business"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchNews.setOnQueryTextListener(this)

        intRecyclerView()
        searchNew(type,place)

        binding.btnUs.setOnClickListener{
            place = "us"
            searchNew(type,place)
            showMessage("USA News")
        }
        binding.btnCo.setOnClickListener{
            place = "co"
            searchNew(type,place)
            showMessage("Colombian News")
        }
        binding.btnRu.setOnClickListener {
            place = "ru"
            searchNew(type,place)
            showMessage("Rusian News")
        }
        binding.btnCa.setOnClickListener {
            place = "ca"
            searchNew(type,place)
            showMessage("Canadian News")
        }

        binding.btnBusiness.setOnClickListener{
            type = "business"
            searchNew(type,place)
            showMessage("Bussines")
        }
        binding.btnHealth.setOnClickListener{
            type = "health"
            searchNew(type,place)
            showMessage("Health")
        }
        binding.btnScience.setOnClickListener {
            type = "science"
            searchNew(type,place)
            showMessage("Science")
        }
        binding.btnSports.setOnClickListener {
            type = "sports"
            searchNew(type,place)
            showMessage("Sports")
        }

    }

    private fun intRecyclerView(){
        adapter = ArticleAdapter(articleList)
        binding.rvNews.layoutManager = LinearLayoutManager(this)
        binding.rvNews.adapter = adapter
    }

    private fun searchNew(category:String, country:String){
        val api = Retrofit2()

        CoroutineScope(Dispatchers.IO).launch {
            val call = api.getService()?.getNewsByCategory(country,category,"4cedbe9b9c064e38a5cc38cab2028d33")
            val news: NewResponse? = call?.body()

            runOnUiThread{
                if(call!!.isSuccessful){
                    if(news?.status.equals("ok")){
                        val article = news?.articles ?: emptyList()
                        articleList.clear()
                        articleList.addAll(article)
                        adapter.notifyDataSetChanged()
                    }else{
                        showMessage("Error en webservice")
                    }

                }else{
                    showMessage("Error en retrofit")
                }
            }
        }
    }
    private fun showMessage(message:String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            searchNew(query.toLowerCase(Locale.ROOT), query.toLowerCase(Locale.ROOT))
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }



}