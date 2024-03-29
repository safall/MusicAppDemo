package com.example.musicapp.ui.albums.albumdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.musicapp.R
import com.example.musicapp.data.model.Album
import com.example.musicapp.data.network.DataRepo
import com.example.musicapp.utils.ImageUtils
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.album_detail_fragment.*
import kotlinx.android.synthetic.main.artist_list_fragment.*
import kotlinx.android.synthetic.main.artist_list_fragment.recycler_view
import java.util.*
import javax.inject.Inject

class AlbumDetailFragment : DaggerFragment() {
    @Inject
    lateinit var dataSource: DataRepo
    private lateinit var viewModel: AlbumDetailViewModel
    private lateinit var binding: ViewDataBinding
    private var trackListAdapter = TrackListAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val album = requireActivity().intent.getParcelableExtra<Album>(AlbumDetailActivity.ARG_ALBUM)
        val artistName = requireActivity().intent.getStringExtra(AlbumDetailActivity.ARG_ARTIST_NAME)
        viewModel = ViewModelProviders.of(this).get(AlbumDetailViewModel::class.java)
        viewModel.dataSource = dataSource
        viewModel.album = album
        viewModel.artistName = artistName
        observeData()
        viewModel.loadAlbumTracks()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.album_detail_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ic_arrow_back.setOnClickListener { requireActivity().finish() }
        ImageUtils.loadImage(viewModel.album.cover_xl, album_cover_image_view)
        album_title_text_view.text = viewModel.album.title
        album_artist_text_view.text = viewModel.artistName
        recycler_view.adapter = trackListAdapter
    }

    private fun observeData() {
        viewModel.trackList_.observe(this, Observer {
            val elements: ArrayList<Any> = ArrayList()
            val groupedTransactions = it.groupBy { it.disk_number }.toSortedMap()
            elements.clear()
            groupedTransactions.let { sortedMap ->
                for (group in sortedMap) {
                    elements.add(group.key)
                    elements.addAll(group.value.sortedBy { it.disk_number })
                }
            }
            trackListAdapter.submitList(elements)
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
