package com.example.comics.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.comics.databinding.ActivityMainBinding
import com.example.comics.interactor.Interactor
import com.example.comics.presenter.Presenter
import com.example.comics.repository.ResultModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), IView {

    val interactor: Interactor = Interactor(Presenter(this))

    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        refrash()

        swipeList()
    }

    fun swipeList() = binding?.swipeRefresh?.setOnRefreshListener {
        refrash()
    }

    override fun refrash() = binding.run {
        this?.swipeRefresh?.isRefreshing = true
        lifecycle.coroutineScope.launch {
            interactor.getMovies()
        }
        Unit
    }

    override fun viewList(list: List<ResultModel>) = binding.run {
        this?.errorTV?.visibility = View.GONE
        this?.listItem?.visibility = View.VISIBLE
        this?.listItem?.adapter = Adapter(list)
        this?.listItem?.layoutManager = LinearLayoutManager(this@MainActivity)
        this?.swipeRefresh?.isRefreshing = false
    }

    override fun error() = binding.run {
        this?.listItem?.visibility = View.GONE
        this?.errorTV?.visibility = View.VISIBLE
        this?.swipeRefresh?.isRefreshing = false
    }

}