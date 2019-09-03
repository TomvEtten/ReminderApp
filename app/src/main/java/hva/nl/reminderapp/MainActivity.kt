package hva.nl.reminderapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val reminders = arrayListOf<Reminder>()
    private val reminderAdapter = ReminderAdapter(reminders)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        fab.setOnClickListener {
            val reminder = etReminder.text.toString()
            addReminder(reminder)
            initViews()
        }
    }

    private fun initViews() {

        // Initialize the recycler view with a linear layout manager, adapter
        rvReminder.layoutManager =
            LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvReminder.adapter = reminderAdapter
        rvReminder.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun addReminder(reminder: String) {
        if (!reminder.isNotBlank()) {
            Snackbar.make(etReminder, "You must fill in the input field!", Snackbar.LENGTH_SHORT)
                .show()
            return
        }
        reminders.add(Reminder(reminder))
        reminderAdapter.notifyDataSetChanged()
        etReminder.text?.clear()
    }


}