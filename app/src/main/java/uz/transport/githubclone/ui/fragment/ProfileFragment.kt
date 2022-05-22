package uz.transport.githubclone.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import dagger.hilt.android.AndroidEntryPoint
import uz.transport.githubclone.R
import uz.transport.githubclone.adapter.PopularAdapter
import uz.transport.githubclone.model.Popular
import uz.transport.githubclone.model.User
import uz.transport.githubclone.ui.activity.RepositoryActivity
import uz.transport.githubclone.utils.Constants
import uz.transport.githubclone.utils.SharedPref
import uz.transport.githubclone.viewModel.RepositoryViewModel

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private val viewModel by viewModels<RepositoryViewModel>()
    private lateinit var bio: TextView
    private lateinit var name: TextView
    private lateinit var username: TextView
    private lateinit var location: TextView
    private lateinit var following: TextView
    private lateinit var followers: TextView
    private lateinit var repositories: TextView
    private lateinit var organization: TextView
    private lateinit var tv_repositoryes: TextView
    private lateinit var accessToken: String
    private lateinit var ivProfile: ShapeableImageView
    private lateinit var sharedPref: SharedPref

    lateinit var rvPop: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPopularRep(view)
        initViews(view)


        sharedPref = SharedPref(requireContext())



        //val accessToken = intent?.getStringExtra(EXTRA_ACCESS_TOKEN)
         accessToken=sharedPref.getTokenList(Constants.EXTRA_ACCESS_TOKEN)
        accessToken.let {
            viewModel.getUserData(it)
        }

        viewModel.userData.observe(requireActivity()) {
            Glide.with(requireContext()).load(it.avatar_url).into(ivProfile)

            Log.d("@@@",it.bio)
            bio.text=it.bio
            username.text=it.username
            name.text=it.name
            followers.text=it.followers.toString()
            following.text=it.following.toString()
            Toast.makeText(requireContext(), "Welcome ${it.name}", Toast.LENGTH_SHORT).show()
        }





    }

    private fun initViews(view: View) {
        bio=view.findViewById(R.id.bio)
        name=view.findViewById(R.id.name)
        username=view.findViewById(R.id.username)
        location=view.findViewById(R.id.location)
        following=view.findViewById(R.id.following)
        followers=view.findViewById(R.id.followers)
        repositories=view.findViewById(R.id.repositories)
        organization=view.findViewById(R.id.organization)
        ivProfile=view.findViewById(R.id.ivProfile)
        tv_repositoryes=view.findViewById(R.id.tv_repositoryes)
        tv_repositoryes.setOnClickListener {
             RepositoryActivity.startActivity(requireContext(),accessToken)
        }





    }

    private fun initPopularRep(view: View) {
        rvPop = view.findViewById(R.id.rvPopular)
        rvPop.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val adapter = PopularAdapter(getAllPopulars())
        rvPop.adapter = adapter
    }

    private fun getAllPopulars(): ArrayList<Popular> {
        val items = ArrayList<Popular>()
        items.add(Popular("Currency"))
        items.add(Popular("Currency"))
        items.add(Popular("Currency"))
        items.add(Popular("Currency"))
        items.add(Popular("Currency"))
        items.add(Popular("Currency"))
        return items
    }






}
