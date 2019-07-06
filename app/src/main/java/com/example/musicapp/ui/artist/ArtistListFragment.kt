package com.example.musicapp.ui.artist

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.example.musicapp.R
import com.example.musicapp.data.model.Artist
import com.example.musicapp.data.network.DataRepo
import com.example.musicapp.ui.albums.albumslist.AlbumListActivity
import com.example.musicapp.utils.AdapterCallback
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.artist_list_fragment.*
import javax.inject.Inject

class ArtistListFragment : DaggerFragment() {
    @Inject
    lateinit var dataSource: DataRepo

    private lateinit var viewModel: ArtistListViewModel
    private lateinit var binding: ViewDataBinding
    private lateinit var artistAdapter: ArtistListAdapter
    private var arrayList = ArrayList<Artist>()
    var queryValue: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ArtistListViewModel::class.java)
        viewModel.dataSource = dataSource
        artistAdapter = ArtistListAdapter(object : AdapterCallback {
            override fun <T> onItemClicked(item: T) {
                val artist = item as Artist
                val bundle = AlbumListActivity.createBundle(artist.id, artist.name)
                NavHostFragment.findNavController(host_fragment)
                    .navigate(R.id.search_fragment_to_albums_activity, bundle)
            }
        })
        observeData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.artist_list_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.adapter = artistAdapter
        setupSearchView()
    }

    //sets up the search view
    private fun setupSearchView() {
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        search_view.apply {
            setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
            queryHint = getString(R.string.search_artist)
            inputType = InputType.TYPE_TEXT_FLAG_CAP_WORDS
            imeOptions = imeOptions or EditorInfo.IME_ACTION_SEARCH or
                    EditorInfo.IME_FLAG_NO_EXTRACT_UI or EditorInfo.IME_FLAG_NO_FULLSCREEN
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    viewModel.subject.onComplete()
                    return false
                }

                override fun onQueryTextChange(query: String): Boolean {
                    viewModel.subject.onNext(query)
                    artistAdapter.submitList(null)
                    arrayList.clear()
                    queryValue = query
                    return false
                }
            })
        }
    }

    private fun observeData() {
        viewModel.artistList.observe(this, Observer<List<Artist>> {
            if (!queryValue.isNullOrEmpty()) {
                arrayList.addAll(it)
                artistAdapter.submitList(arrayList)
                recycler_view.visibility = View.VISIBLE
            }
        })

        viewModel.isEmpty.observe(this, Observer {
            search_error_text_view.text = getString(R.string.empty_result)
            search_error_text_view.visibility = View.VISIBLE
            recycler_view.visibility = View.GONE
        })

        viewModel.showError.observe(this, Observer {
            search_error_text_view.text = it
            search_error_text_view.visibility = View.VISIBLE
            recycler_view.visibility = View.GONE
        })
    }

}