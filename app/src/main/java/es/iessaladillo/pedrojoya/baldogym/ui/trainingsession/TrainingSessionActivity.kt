package es.iessaladillo.pedrojoya.baldogym.ui.trainingsession

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import es.iessaladillo.pedrojoya.baldogym.R
import es.iessaladillo.pedrojoya.baldogym.ui.schedule.ScheduleActivity

class TrainingSessionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.training_session_activity)
    }

    companion object {
        const val RC_LIST = "RC_LIST"
        fun newIntent(context: Context, schedule: Bundle): Intent
                = Intent(context, TrainingSessionActivity::class.java)
            .putExtras(bundleOf(RC_LIST to schedule))
    }
}
