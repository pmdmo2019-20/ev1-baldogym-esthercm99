package es.iessaladillo.pedrojoya.baldogym.data

import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession

interface Repository {

    // Retorna una lista con todas las sesiones
    fun queryAllSessions(): List<TrainingSession>

}