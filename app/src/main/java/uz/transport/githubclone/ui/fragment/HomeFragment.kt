package uz.transport.githubclone.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import uz.transport.githubclone.R
import uz.transport.githubclone.databinding.FragmentHomeBinding
import uz.transport.githubclone.ui.activity.RepositoryActivity
import uz.transport.githubclone.ui.activity.SearchActivity


class HomeFragment : Fragment() {
    private lateinit var repositories:TextView
    private lateinit var ivSearch:ImageView
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        toSearchFragment()
    }

    private fun initViews(view: View) {
        repositories=view.findViewById(R.id.repositories)
        ivSearch=view.findViewById(R.id.icSearch)
        repositories.setOnClickListener {
            val intent= Intent(requireContext(), RepositoryActivity::class.java)
            startActivity(intent)

        }





    }
    private fun toSearchFragment() {
        ivSearch.setOnClickListener {
            val intent =Intent(requireContext(),SearchActivity::class.java)
            startActivity(intent)
        }
    }

    fun showKeyboard(editText: EditText) {
        editText.requestFocus()
        val content =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        content.showSoftInput(editText, 0)
        content.toggleSoftInput(
            InputMethodManager.SHOW_FORCED,
            InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    fun hideKeyboard() {
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }


}