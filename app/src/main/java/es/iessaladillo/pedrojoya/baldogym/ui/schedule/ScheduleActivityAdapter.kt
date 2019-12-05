package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.baldogym.R
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession

class ScheduleActivityAdapter () : RecyclerView.Adapter<ScheduleActivityAdapter.ViewHolder>() {

    private var data: List<TrainingSession> = emptyList()
    var onCheckListener: ((Int) -> Unit)? = null


    fun currentList(position: Int): TrainingSession {
        return data[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.schedule_activity_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = data[position]
        holder.bind(task)
    }

    fun submitList(newList: List<TrainingSession>) {
        data = newList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val btnJoin: Button = itemView.findViewById(R.id.btnJoin)

        init {
            btnJoin.setOnClickListener {
                onCheckListener?.invoke(adapterPosition)
            }
        }

        fun bind(trainingSession: TrainingSession) {
        }

    }
}