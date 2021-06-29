package com.example.contactlist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactlist.R
import com.example.contactlist.databinding.FragmentContactListBinding
import com.example.contactlist.model.Contact
import com.example.contactlist.viewmodel.ContactViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [ContactListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ContactListFragment : Fragment(),
    View.OnClickListener, ContactAdapter.OnItemClickListener{
    private var contactViewModel: ContactViewModel? = null
    private var binding: FragmentContactListBinding? = null
    private var isSearch = false
    var adapter: ContactAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        contactViewModel = ContactViewModel(requireContext())
        binding = FragmentContactListBinding.inflate(inflater, container, false)
        val view: View = binding!!.root
        initRecyclerView()
        binding!!.searchView.setOnClickListener(this)
        binding!!.etSearch.setOnClickListener(this)
        return view
    }

    private fun initRecyclerView() {
        binding!!.contactRecyclerView.layoutManager =
            LinearLayoutManager(binding!!.contactRecyclerView.context)
        binding!!.contactRecyclerView.addItemDecoration(
            DividerItemDecoration(
                binding!!.contactRecyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )
        adapter = ContactAdapter(binding!!.contactRecyclerView.context)
        adapter!!.setContacts(contactViewModel!!.getContacts())
        binding!!.contactRecyclerView.adapter = adapter
        adapter!!.setOnItemClickListener(this)
    }

    /**
     * Handle  clicks  on the registered views
     *
     * @param view pressed view
     */
    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.et_search) {
            isSearch = true
            binding!!.searchLayout.visibility = View.INVISIBLE
            binding!!.searchView.visibility = View.VISIBLE
            setUpSearch()
        }
    }

    /**
     * Close Search
     */
    private fun closeSearch() {
        if (isSearch) {
            isSearch = false
            binding!!.searchLayout.visibility = View.VISIBLE
            binding!!.searchView.visibility = View.INVISIBLE
        }
    }

    /**
     * Set up search.
     */
    private fun setUpSearch() {
        binding!!.searchView.isIconified = false
        binding!!.searchView.queryHint = getString(R.string.enter_name)
        binding!!.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                adapter!!.getFilter()!!.filter(s)
                return true
            }
        })
        binding!!.searchView.setOnCloseListener {
            closeSearch()
            true
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ContactListFragment.
         */
        fun newInstance(): ContactListFragment {
            return ContactListFragment()
        }
    }

    override fun onItemClick(view: View?, obj: Contact?, position: Int) {
        Toast.makeText(
            context,
            """
                Contact Selected
                ${obj!!.getName()}${obj.getPhoneNumber()}
                """.trimIndent(),
            Toast.LENGTH_LONG
        ).show()
    }
}