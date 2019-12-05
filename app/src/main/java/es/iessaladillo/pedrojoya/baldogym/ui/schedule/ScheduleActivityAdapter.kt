package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
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
        private val lblTitleSession: TextView = itemView.findViewById(R.id.lblTitleSession)
        private val lblTrainer: TextView = itemView.findViewById(R.id.lblTrainer)
        private val lblRoom: TextView = itemView.findViewById(R.id.lblRoom)
        private val lblParticipants: TextView = itemView.findViewById(R.id.lblParticipants)
        private val imgSession: ImageView = itemView.findViewById(R.id.imgSession)

        init {
            btnJoin.setOnClickListener {
                onCheckListener?.invoke(adapterPosition)
            }
        }

        fun bind(trainingSession: TrainingSession) {
            trainingSession.run {
                imgSession.setImageResource(photoResId)
                lblTitleSession.text = name
                lblTrainer.text = trainer
                lblRoom.text = room

                // No funciona getString por lo que está de esta manera y no con plurals:
                if(participants <= 1) {
                    lblParticipants.text = String.format("%d participant", participants)
                } else {
                    lblParticipants.text = String.format("%d participants", participants)
                }
                //lblParticipants.text = getString(R.plurals.schedule_item_participants, participants)
            }
        }

    }
}