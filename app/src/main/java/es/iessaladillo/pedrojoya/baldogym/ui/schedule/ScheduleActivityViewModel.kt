package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.baldogym.data.Repository
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.data.entity.WeekDay
import es.iessaladillo.pedrojoya.baldogym.data.entity.getCurrentWeekDay

class ScheduleActivityViewModel (private val repository: Repository,
                                 private val application: Application) : ViewModel() {

    var listSessionTraining: MutableList<TrainingSession> = querySessions(getCurrentWeekDay()).toMutableList()
    private val _listSession: MutableLiveData<List<TrainingSession>> = MutableLiveData()
    val listSession: LiveData<List<TrainingSession>>
        get() = _listSession

    init {
        _listSession.value = listSessionTraining
    }

    // Filtra sesiones por los días:
    private fun querySessions(filter: WeekDay): List<TrainingSession> {
        var list: List<TrainingSession> = emptyList()
        if(filter == WeekDay.MONDAY) {
            list = repository.queryMonSessions()
        } else if(filter == WeekDay.TUESDAY) {
            list = repository.queryTueSessions()
        } else if(filter == WeekDay.WEDNESDAY) {
            list = repository.queryWedSessions()
        } else if(filter == WeekDay.THURSDAY) {
            list = repository.queryThuSessions()
        } else if(filter == WeekDay.FRIDAY) {
            list = repository.queryFriSessions()
        } else if(filter == WeekDay.SATURDAY) {
            list = repository.querySatSessions()
        } else if(filter == WeekDay.SUNDAY) {
            list = repository.querySunSessions()
        }

        return list
    }

}