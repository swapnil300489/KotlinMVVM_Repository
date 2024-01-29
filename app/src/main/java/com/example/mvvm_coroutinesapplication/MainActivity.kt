package com.example.mvvm_coroutinesapplication

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_coroutinesapplication.Model.Post
import com.example.mvvm_coroutinesapplication.Repository.PostRepository
import com.example.mvvm_coroutinesapplication.ViewModel.PostViewModel
import com.example.mvvm_coroutinesapplication.ViewModel.PostViewModelFactory
import com.example.mvvm_coroutinesapplication.adapter.ListAdapter

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var postAdapter: ListAdapter
    lateinit var postViewModel: PostViewModel
    var pd : ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        val postRepository = PostRepository()
        val viewModelFactory = PostViewModelFactory(postRepository)
        postViewModel = ViewModelProvider(this, viewModelFactory)[PostViewModel::class.java]
        postViewModel.getPost()
        pd = ProgressDialog(this@MainActivity)
        pd?.setTitle("Please wait...")
        pd?.setCancelable(false)
        pd?.show()
        postViewModel.postMutableLiveData.observe(this, Observer {
            pd?.dismiss()
            postAdapter.setData(it as ArrayList<Post>)
        })
    }

    private fun initView() {
        recyclerView = findViewById(R.id.id_list_rc_view)
        postAdapter = ListAdapter(this, ArrayList())

        recyclerView.apply {
            this.setHasFixedSize(true)
            this.layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter = postAdapter
        }
    }
}