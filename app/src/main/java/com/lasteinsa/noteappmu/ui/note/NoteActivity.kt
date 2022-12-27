package com.lasteinsa.noteappmu.ui.note

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.lasteinsa.noteappmu.R
import com.lasteinsa.noteappmu.core.domain.model.Note
import com.lasteinsa.noteappmu.core.utils.DateHelper
import com.lasteinsa.noteappmu.databinding.ActivityNoteBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class NoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteBinding
    private val noteViewModel: NoteViewModel by viewModels()
    private var noteId: Int? = null
    private var noteData: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSetup()
        initAction()
        initMain()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_edit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete -> {
                noteViewModel.deleteNote(
                    Note(
                        noteId = noteId,
                        title = binding.inputTitle.text.toString(),
                        body = binding.inputBody.text.toString(),
                        createdAt = Calendar.getInstance().toString()
                    )
                )
                setResult(121,intent)
                finish()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initSetup() {
        setSupportActionBar(binding.noteToolbar)

        noteData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("noteData", Note::class.java)
        } else {
            intent.getParcelableExtra<Note>("noteData")
        }

        noteData.let {
            binding.inputTitle.setText(it?.title)
            binding.inputBody.setText(it?.body)
            binding.noteCreatedAt.text = it?.createdAt
            if(it?.noteId != null) {
                noteId = it.noteId
                supportActionBar?.title = "Edit Note"
            } else {
                supportActionBar?.title = "Add Note"
            }
        }

        if(binding.noteCreatedAt.text.isNullOrBlank()) {
            binding.noteCreatedAt.text = DateHelper().getCurrentDate()
        }
    }

    private fun initAction() {
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if(binding.inputBody.text.isNullOrBlank() && binding.inputTitle.text.isNullOrBlank()) {
                    setResult(212,intent)
                } else {
                    setResult(Activity.RESULT_OK,intent)
                    noteViewModel.save(
                        Note(
                            noteId  = noteId,
                            title   = binding.inputTitle.text.toString(),
                            body    = binding.inputBody.text.toString(),
                            createdAt = binding.noteCreatedAt.text.toString()
                        )
                    )
                }
                finish()
            }
        })
    }

    private fun initMain() {
        binding.inputBody.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                binding.noteCreatedAt.text = DateHelper().getCurrentDate()
            }

        })
    }
}