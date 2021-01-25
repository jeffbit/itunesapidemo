package com.weemusic.android.feature.albumlist.presentation.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.weemusic.android.R
import com.weemusic.android.databinding.AlbumListFragmentBinding
import com.weemusic.android.feature.albumlist.presentation.adapter.AlbumsAdapter
import com.weemusic.android.feature.albumlist.presentation.viewmodel.AlbumListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumListFragment : Fragment() {


    private val viewModel: AlbumListViewModel by viewModels()
    private lateinit var adapter: AlbumsAdapter

    private var _binding: AlbumListFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AlbumListFragmentBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        observeProgress()
        viewModel.retrieveAlbums()
        observeError()
        observeAlbums()

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_album -> {
                viewModel.retrieveAndSortAlbumByAlbum()
                true

            }
            R.id.action_price -> {
                viewModel.retrieveAndSortAlbumByPrice()
                true

            }
            R.id.action_artist -> {
                viewModel.retrieveAndSortAlbumByArtist()
                true

            }
            else -> super.onOptionsItemSelected(item)

        }

    }

    private fun observeAlbums() {
        viewModel.topAlbums.observe(viewLifecycleOwner, {
            adapter.updateList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun observeError() {
        viewModel.errorBoolean.observe(viewLifecycleOwner, { boolean ->
            viewModel.error.observe(viewLifecycleOwner, { error ->
                if (boolean != false) {
                    Toast.makeText(requireContext(), "Network Issue: $error", Toast.LENGTH_SHORT)
                        .show()
                }
            })
        })

    }

    private fun observeProgress() {
        viewModel.progress.observe(viewLifecycleOwner, { boolean ->
            if (boolean != true) {
                //hide progress
                binding.progressBar.visibility = View.GONE
                binding.rvFeed.visibility = View.VISIBLE

            } else {
                binding.progressBar.visibility = View.VISIBLE
                binding.rvFeed.visibility = View.GONE

            }
        })
    }


    private fun setupAdapter() {
        adapter = AlbumsAdapter(emptyList())
        binding.rvFeed.adapter = adapter
        binding.rvFeed.setHasFixedSize(true)
        binding.rvFeed.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}