package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.baldogym.R
import es.iessaladillo.pedrojoya.baldogym.data.LocalRepository
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.data.entity.WeekDay
import es.iessaladillo.pedrojoya.baldogym.data.entity.getCurrentWeekDay
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
        currentDay.text = getCurrentWeekDay().name
        setupRecyclerView()
        onClickDays()
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

    private fun onClickDays() {
        sltMon.setOnClickListener { onClickDay(sltMon, WeekDay.MONDAY) }
        sltTue.setOnClickListener { onClickDay(sltTue, WeekDay.TUESDAY) }
        sltWed.setOnClickListener { onClickDay(sltWed, WeekDay.WEDNESDAY) }
        sltThu.setOnClickListener { onClickDay(sltThu, WeekDay.THURSDAY) }
        sltFri.setOnClickListener { onClickDay(sltFri, WeekDay.FRIDAY) }
        sltSat.setOnClickListener { onClickDay(sltSat, WeekDay.SATURDAY) }
        sltSun.setOnClickListener { onClickDay(sltSun, WeekDay.SUNDAY) }
    }

    private fun onClickDay(textView: TextView, filter: WeekDay) {
        viewModel.submitList(filter)
        textView.setTextColor(ResourcesCompat.getColor(resources, R.color.white, null))
    }

}
