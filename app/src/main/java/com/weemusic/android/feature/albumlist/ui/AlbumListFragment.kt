package com.weemusic.android.feature.albumlist.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.weemusic.android.R
import com.weemusic.android.databinding.AlbumListFragmentBinding
import com.weemusic.android.feature.albumlist.adapter.AlbumsAdapter
import com.weemusic.android.feature.albumlist.viewmodel.AlbumListViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

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
        viewModel.retrieveAlbums()
        viewModel.topAlbums.observe(viewLifecycleOwner, {
            adapter.updateList(it)
        })
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_album -> {
                Toast.makeText(requireContext(), "Album", Toast.LENGTH_SHORT).show();
                true

            }
            R.id.action_price -> {
                Toast.makeText(requireContext(), "Price", Toast.LENGTH_SHORT).show();
                true

            }
            R.id.action_title -> {
                Toast.makeText(requireContext(), "Title", Toast.LENGTH_SHORT).show()
                true

            }
            else -> super.onOptionsItemSelected(item)

        }

    }


    private fun setupAdapter() {
        adapter = AlbumsAdapter(emptyList())
        binding.rvFeed.adapter = adapter
        binding.rvFeed.setHasFixedSize(true)
        binding.rvFeed.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}