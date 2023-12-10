package com.example.roomwordsample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.roomwordsample.R
import com.example.roomwordsample.adapters.WordListAdapter
import com.example.roomwordsample.data.Word
import com.example.roomwordsample.databinding.FragmentStartBinding
import com.google.android.material.snackbar.Snackbar

class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding
    private lateinit var recyclerView: RecyclerView
    private val viewModel: WordViewModel by activityViewModels()
    private lateinit var wordListAdapter: WordListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerView

        wordListAdapter = WordListAdapter()
        recyclerView.adapter = wordListAdapter

        binding.addButton.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_addNewWordFragment)
        }

        binding.deleteButton.setOnClickListener {
            viewModel.delete()
        }

        viewModel.allWords.observe(viewLifecycleOwner) {
            wordListAdapter.differ.submitList(it)
        }

        swipeToDelete()

    }

    private fun swipeToDelete() {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val item = wordListAdapter.differ.currentList[position]
                viewModel.deleteWord(item)
                Toast.makeText(
                    requireContext(),
                    "item ${item.word} deleted",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }).attachToRecyclerView(binding.recyclerView)
    }

}