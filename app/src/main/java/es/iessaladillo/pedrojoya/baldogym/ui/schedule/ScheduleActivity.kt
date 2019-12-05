package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.baldogym.R
import es.iessaladillo.pedrojoya.baldogym.data.LocalRepository
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import kotlinx.android.synthetic.main.schedule_activity.*

class ScheduleActivity : AppCompatActivity() {

    private val viewModel: ScheduleActivityViewModel by viewModels {
        ScheduleActivityViewModelFactory(LocalRepository, application)
    }
    private val listAdapter : ScheduleActivityAdapter = ScheduleActivityAdapter().also {
        it.onCheckListener = { observeTasks() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule_activity)
        setupViews()
    }

    private fun setupViews() {
        setupRecyclerView()
        observeTasks()
    }
    private fun setupRecyclerView() {
        lstSession.run {
            setHasFixedSize(true)
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            itemAnimator = DefaultItemAnimator()
        }
    }
    private fun observeTasks() {
        viewModel.listSession.observe(this) {
            updateList(it)
        }
    }
    private fun updateList(newList: List<TrainingSession>) {
        lstSession.post {
            listAdapter.submitList(newList)
            lblEmptyView.visibility = if (newList.isEmpty()) View.VISIBLE else View.INVISIBLE
        }
    }

}
