package com.lasteinsa.noteappmu.ui.main

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.lasteinsa.noteappmu.R
import com.lasteinsa.noteappmu.core.domain.model.Note
import com.lasteinsa.noteappmu.core.utils.CardHelper
import com.lasteinsa.noteappmu.core.utils.SnackbarHelper
import com.lasteinsa.noteappmu.databinding.ActivityMainBinding
import com.lasteinsa.noteappmu.ui.note.NoteActivity
import com.lasteinsa.noteappmu.core.ui.NoteAdapter
import com.lasteinsa.noteappmu.ui.setting.SettingActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private var noteAdapter: NoteAdapter = NoteAdapter()
    private var noteDataSelected = ArrayList<Note>()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFirstLaunch()
        initSetup()
        initAction()

    }

    private fun initFirstLaunch() {
        if(mainViewModel.getFirstLaunch()) {
            when(resources.configuration.uiMode and  Configuration.UI_MODE_NIGHT_MASK) {
                Configuration.UI_MODE_NIGHT_YES -> {
                    mainViewModel.setTheme(true)
                }
                else -> {
                    mainViewModel.setTheme(false)
                }
            }
            mainViewModel.setFirstLaunched()
        }

        if(mainViewModel.getTheme()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun initSetup() {
        val stub = binding.mainViewStub
        stub.layoutResource = R.layout.layout_no_item
        stub.inflate()

        with(binding.rvNotes) {
            setHasFixedSize(true)
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            adapter = noteAdapter
        }

        mainViewModel.note.observe(this) { note ->
            if (note != null) {
                noteAdapter.setData(note)

            }
            if(noteAdapter.itemCount == 0) {
                stub.visibility = View.VISIBLE
            } else {
                stub.visibility = View.INVISIBLE
            }
        }

    }

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == 212) {
            SnackbarHelper().infoSnackbar(this, binding.root, binding.mainBottomAppbar, "Empty Note Discarded")
        } else if(result.resultCode == 121) {
            SnackbarHelper().dangerSnackbar(this, binding.root, binding.mainBottomAppbar, "Deleted")
        }
    }

    private fun initAction() {
        noteAdapter.onItemClick = {
            val moveIntent = Intent(this@MainActivity, NoteActivity::class.java)
            moveIntent.putExtra("noteData",it)
            resultLauncher.launch(moveIntent)
        }

        noteAdapter.onItemLongClick = { position, noteData, card ->
            if (position in noteAdapter.selectedPositions) {
                noteAdapter.selectedPositions.remove(position)
                noteDataSelected.remove(noteData)
                CardHelper().setCardUnSelect(this@MainActivity,card)
            } else {
                noteAdapter.selectedPositions.add(position)
                noteDataSelected.add(noteData)
                CardHelper().setCardOnSelect(this@MainActivity,card)
            }
        }

        binding.btnAdd.setOnClickListener {
            val moveToAdd = Intent(this@MainActivity, NoteActivity::class.java)
            resultLauncher.launch(moveToAdd)
        }

        binding.mainToolbar.setOnClickListener {
            binding.searchNote.onActionViewExpanded()
        }

        binding.mainBottomAppbar.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId) {
                R.id.action_main_delete -> {
                    if(noteDataSelected.isNotEmpty()) {
                        noteDataSelected.forEach {
                            mainViewModel.deleteNote(it)
                        }
                        noteAdapter.selectedPositions.clear()
                        noteDataSelected.clear()
                        SnackbarHelper().dangerSnackbar(this, binding.root, binding.mainBottomAppbar, "Deleted")
                    } else {
                        SnackbarHelper().infoSnackbar(this, binding.root, binding.mainBottomAppbar, "Nothing to delete")
                    }
                    true
                }
                R.id.action_settings -> {
                    startActivity(Intent(this@MainActivity, SettingActivity::class.java))
                    true
                }
                else -> false
            }
        }

        binding.searchNote.setOnQueryTextListener(object: androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (p0 != null) {
                    searchNote(p0)
                    binding.searchNote.clearFocus()
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if(p0 != null) {
                    searchNote(p0)
                }
                return false
            }
        })
    }

    private fun searchNote(text: String) {
        val query = "%$text%"
        mainViewModel.searchNote(query).observe(this) {
            it.let { noteData ->
                noteAdapter.setData(noteData)
            }
        }
    }
}