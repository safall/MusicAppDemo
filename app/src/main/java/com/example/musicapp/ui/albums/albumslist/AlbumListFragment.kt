package com.example.musicapp.ui.albums.albumslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.example.musicapp.R
import com.example.musicapp.data.model.Album
import com.example.musicapp.data.network.DataRepo
import com.example.musicapp.databinding.AlbumListFragmentBinding
import com.example.musicapp.ui.albums.albumdetail.AlbumDetailActivity
import com.example.musicapp.utils.AdapterCallback
import com.example.musicapp.utils.GridItemDecoration
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.artist_list_fragment.*
import javax.inject.Inject

class AlbumListFragment : DaggerFragment() {
    @Inject
    lateinit var dataSource: DataRepo
    private lateinit var viewModel: AlbumListViewModel
    private lateinit var binding: AlbumListFragmentBinding
    private lateinit var albumListAdapter: AlbumListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this).get(AlbumListViewModel::class.java)
        viewModel.dataSource = dataSource
        val artistId = requireActivity().intent.getStringExtra(AlbumListActivity.ARG_ARTIST_ID)
        val artistName = requireActivity().intent.getStringExtra(AlbumListActivity.ARG_ARTIST_NAME)
        viewModel.artistId = artistId
        viewModel.artistName = artistName

        albumListAdapter = AlbumListAdapter(object : AdapterCallback {
            override fun <T> onItemClicked(item: T) {
                val album = item as Album
                val bundle = AlbumDetailActivity.createBundle(album, viewModel.artistName)
                NavHostFragment.findNavController(host_fragment)
                    .navigate(R.id.search_fragment_to_albums_activity, bundle)
            }
        }, viewModel.artistName)

        observeData()
        viewModel.loadAlbums()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.album_list_fragment, container, false)
        binding.lifecycleOwner = this
        val gridSpacing = resources.getDimensionPixelSize(R.dimen.albums_item_spacing)
        this.binding.recyclerView.addItemDecoration(GridItemDecoration(gridSpacing, 2))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.adapter = albumListAdapter
    }

    private fun observeData() {
        viewModel.albumList_.observe(this, Observer {
            albumListAdapter.submitList(it)
            recycler_view.visibility = View.VISIBLE
        })

        viewModel.isEmpty.observe(this, Observer {
            search_error_text_view.text = getString(R.string.empty_album_result)
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