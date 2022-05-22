package uz.transport.githubclone.ui.fragment


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import dev.davlatov.githubproject.data.model.search.SearchRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.transport.githubclone.R
import uz.transport.githubclone.adapter.SearchAdapter
import uz.transport.githubclone.network.RetrofitHttp
import uz.transport.githubclone.utils.SharedPref

class SearchFragment : Fragment(R.layout.fragment_search) {
    private lateinit var sharedPref: SharedPref
    private lateinit var recyclerView: RecyclerView
    private lateinit var editText: EditText
    private val searchAdapter by lazy { SearchAdapter() }
    var userNameList = ArrayList<SearchRepository>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = SharedPref(requireContext())
        initViews(view)
        showKeyboard(editText)
        editTextManage()
        refreshAdapter()



    }

    private fun refreshAdapter() {
        recyclerView.adapter = searchAdapter

    }

    private fun editTextManage() {
        editText.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->

            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                loadRepository(editText.text.toString())
                hideKeyboard()
                return@OnEditorActionListener true
            }
            false
        })

    }

    private fun loadRepository(repository: String) {
        RetrofitHttp.posterService.getRepositoryName(repository)
            .enqueue(object : Callback<SearchRepository> {
                @SuppressLint("SetTextI18n")
                override fun onResponse(
                    call: Call<SearchRepository>,
                    response: Response<SearchRepository>,
                ) {
                    if (response.body() != null) {
                        userNameList.addAll(listOf(response.body()!!))
                        Log.d("@@@",userNameList.toString())
                        searchAdapter.submitList(userNameList)
                    }
                }

                override fun onFailure(call: Call<SearchRepository>, t: Throwable) {
                    Log.d("@@@", "error: ${t.localizedMessage}+${t.message}")
                }
            })

    }

    private fun initViews(view: View){
        recyclerView=view.findViewById(R.id.recyclerView)
        editText=view.findViewById(R.id.editText)

    }

    private fun showKeyboard(editText: EditText) {
        editText.requestFocus()
        val content =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        content.showSoftInput(editText, 0)
        content.toggleSoftInput(InputMethodManager.SHOW_FORCED,
            InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    private fun hideKeyboard() {
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }


}