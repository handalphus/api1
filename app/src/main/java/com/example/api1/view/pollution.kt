package com.example.api1.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.api1.R
import com.example.api1.viewmodel.PollutionValuesAdapter
import com.example.api1.viewmodel.PollutionViewModel
import kotlinx.android.synthetic.main.fragment_pollution.*
import kotlinx.coroutines.delay

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [pollution.newInstance] factory method to
 * create an instance of this fragment.
 */
class pollution : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var pollutionViewModel: PollutionViewModel
    lateinit var adapter1: PollutionValuesAdapter
    lateinit var viewManager1: RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pollutionViewModel = ViewModelProvider(requireActivity()).get(PollutionViewModel::class.java)
        viewManager1 = LinearLayoutManager(requireContext())

        adapter1 = PollutionValuesAdapter(pollutionViewModel.pollutionsLive)
        pollutionViewModel.pollutionsLive.observe(viewLifecycleOwner,{adapter1.notifyDataSetChanged()})
        pollutionViewModel.nameOfPolution.observe(viewLifecycleOwner,{textViewPollutionName.text})
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pollution, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.apply {
            adapter=adapter1
            layoutManager=viewManager1
        }

        button.setOnClickListener {
            pollutionViewModel.updatePollutions(92)
        }


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment pollution.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            pollution().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}