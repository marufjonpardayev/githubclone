package uz.transport.githubclone.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import uz.transport.githubclone.R
import uz.transport.githubclone.adapter.UserAdapter
import uz.transport.githubclone.databinding.ActivityMainBinding
import uz.transport.githubclone.databinding.ActivitySearchBinding
import uz.transport.githubclone.viewModel.SearchViewModel

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var adapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = UserAdapter()
        adapter.notifyDataSetChanged()
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(SearchViewModel::class.java)
        binding.apply {
            rvUser.layoutManager=LinearLayoutManager(this@SearchActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter=adapter
            searchPeople.setOnClickListener {

            }
            editText.setOnKeyListener { view, i, keyEvent ->
                if (keyEvent.action==KeyEvent.ACTION_DOWN && i==KeyEvent.KEYCODE_ENTER){
                    searchUsers()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }




        }
        viewModel.getSearchUsers().observe(this,){
            if (it!=null){
                adapter.setList(it)
            }
        }



        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val keyWord = s.toString().trim()
                showSelection(keyWord)
            }

            override fun afterTextChanged(s: Editable?) {}

        })


    }

    private fun showSelection(keyWord: String) {
        if (keyWord == null) {
            binding.searchPage.visibility = View.GONE
        }
        binding.searchRepository.text = "Repositories with--$keyWord"
        binding.searchPeople.text = "Peoples with--$keyWord"
        binding.searchPage.visibility = View.VISIBLE


    }
    private fun searchUsers(){
        binding.apply {
            val query=editText.text.toString()
            if (query.isEmpty())return
            viewModel.setSearchUser(query)
        }
    }




}