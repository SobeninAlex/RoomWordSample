package com.example.roomwordsample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.roomwordsample.R
import com.example.roomwordsample.data.Word
import com.example.roomwordsample.databinding.FragmentAddNewWordBinding

class AddNewWordFragment : Fragment() {

    private lateinit var binding: FragmentAddNewWordBinding
    private val viewModel: WordViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAddNewWordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveButton.setOnClickListener {
            saveWord()
        }

    }

    private fun saveWord() {
        val text = binding.textInputEditText.text.toString()
        if (text.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill field", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.insert(Word(text))
            findNavController().navigate(R.id.action_addNewWordFragment_to_startFragment)
        }

    }

}