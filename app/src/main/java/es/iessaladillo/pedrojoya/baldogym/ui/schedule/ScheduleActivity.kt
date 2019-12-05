package es.iessaladillo.pedrojoya.baldogym.ui.schedule

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
import es.iessaladillo.pedrojoya.baldogym.ui.trainingsession.TrainingSessionActivity
import kotlinx.android.synthetic.main.schedule_activity.*

class ScheduleActivity : AppCompatActivity() {

    private val viewModel: ScheduleActivityViewModel by viewModels {
        ScheduleActivityViewModelFactory(LocalRepository, application)
    }
    private val listAdapter : ScheduleActivityAdapter = ScheduleActivityAdapter().apply {
        onCheckListener = { observeTasks() }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule_activity)
        currentDay(getCurrentWeekDay())
        setupViews()
    }

    private fun setupViews() {
        setupRecyclerView()
        onClickDays()
        observeTasks()
        clickItem()
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

    private fun currentDay(filter: WeekDay) {
        currentDay.text = getCurrentWeekDay().name
        viewModel.submitList(filter)
        // Monday
        if(filter == WeekDay.MONDAY) {
            changeColor(sltSat)
            // Tuesday
        } else if (filter == WeekDay.TUESDAY) {
            changeColor(sltTue)
            // Wednesday
        } else if (filter == WeekDay.WEDNESDAY) {
            changeColor(sltWed)
            // Thursday
        } else if (filter == WeekDay.THURSDAY) {
            changeColor(sltThu)
            // Friday
        } else if (filter == WeekDay.FRIDAY) {
            changeColor(sltFri)
            // Saturday
        } else if (filter == WeekDay.SATURDAY) {
            changeColor(sltSat)
            // Sunday
        } else if (filter == WeekDay.SUNDAY) {
            changeColor(sltSun)
        }

    }

    private fun onClickDays() {
        sltMon.setOnClickListener { changeDay(sltMon, WeekDay.MONDAY) }
        sltTue.setOnClickListener { changeDay(sltTue, WeekDay.TUESDAY) }
        sltWed.setOnClickListener { changeDay(sltWed, WeekDay.WEDNESDAY) }
        sltThu.setOnClickListener { changeDay(sltThu, WeekDay.THURSDAY) }
        sltFri.setOnClickListener { changeDay(sltFri, WeekDay.FRIDAY) }
        sltSat.setOnClickListener { changeDay(sltSat, WeekDay.SATURDAY) }
        sltSun.setOnClickListener { changeDay(sltSun, WeekDay.SUNDAY) }
    }

    private fun changeDay(textView: TextView, filter: WeekDay) {
        viewModel.submitList(filter)
        changeColor(textView)
    }

    private fun changeColor(textView: TextView) {
        // Monday
        if(textView.text == getString(R.string.schedule_mon)) {
            textView.setTextColor(ResourcesCompat.getColor(resources, R.color.white, null))
            sltTue.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltWed.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltThu.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltFri.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltSat.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltSun.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))

        // Tuesday
        } else if (textView.text == getString(R.string.schedule_tue)) {
            textView.setTextColor(ResourcesCompat.getColor(resources, R.color.white, null))
            sltMon.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltWed.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltThu.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltFri.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltSat.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltSun.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))

        // Wednesday
        } else if (textView.text == getString(R.string.schedule_wed)) {
            textView.setTextColor(ResourcesCompat.getColor(resources, R.color.white, null))
            sltMon.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltTue.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltThu.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltFri.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltSat.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltSun.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))

        // Thursday
        } else if (textView.text == getString(R.string.schedule_thu)) {
            textView.setTextColor(ResourcesCompat.getColor(resources, R.color.white, null))
            sltMon.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltTue.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltWed.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltFri.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltSat.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltSun.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))

        // Friday
        } else if (textView.text == getString(R.string.schedule_fri)) {
            textView.setTextColor(ResourcesCompat.getColor(resources, R.color.white, null))
            sltMon.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltTue.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltWed.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltThu.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltSat.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltSun.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))

        // Saturday
        } else if (textView.text == getString(R.string.schedule_sat)) {
            textView.setTextColor(ResourcesCompat.getColor(resources, R.color.white, null))
            sltMon.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltTue.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltWed.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltThu.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltFri.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltSun.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))

        // Sunday
        } else if (textView.text == getString(R.string.schedule_sun)) {
            textView.setTextColor(ResourcesCompat.getColor(resources, R.color.white, null))
            sltMon.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltWed.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltThu.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltFri.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltSat.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
            sltTue.setTextColor(ResourcesCompat.getColor(resources, R.color.white_semi, null))
        }
    }

    private fun clickItem() {
        currentDay.setOnClickListener{ navigateToSessionActivity() }
    }

    private fun navigateToSessionActivity() {
        val intent = TrainingSessionActivity.newIntent(applicationContext, viewModel.obtainBundle())
        startActivityForResult(intent, 1)
    }

}
