package es.iessaladillo.pedrojoya.baldogym.data

import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession

interface Repository {

    // Retorna una lista con todas las sesiones
    fun queryAllSessions(): List<TrainingSession>

    // Retorna una lista de cada día
    fun queryMonSessions(): List<TrainingSession>
    fun queryTueSessions(): List<TrainingSession>
    fun queryWedSessions(): List<TrainingSession>
    fun queryThuSessions(): List<TrainingSession>
    fun queryFriSessions(): List<TrainingSession>
    fun querySatSessions(): List<TrainingSession>
    fun querySunSessions(): List<TrainingSession>

}