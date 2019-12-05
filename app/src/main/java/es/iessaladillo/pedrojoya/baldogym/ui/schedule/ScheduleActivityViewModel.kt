package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.baldogym.data.Repository
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession

class ScheduleActivityViewModel (private val repository: Repository,
                                 private val application: Application) : ViewModel() {

    var listSessionTraining: MutableList<TrainingSession> = repository.queryAllSessions().toMutableList()
    private val _listSession: MutableLiveData<List<TrainingSession>> = MutableLiveData()
    val listSession: LiveData<List<TrainingSession>>
        get() = _listSession

    init {
        _listSession.value = listSessionTraining
    }

    // Filtra sesiones por los días:
    private fun queryTasks(filter: ScheduleActivityFilter?) {
        if(filter == ScheduleActivityFilter.MON) {
            _listSession.value = repository.queryAllSessions()
        }
    }


}