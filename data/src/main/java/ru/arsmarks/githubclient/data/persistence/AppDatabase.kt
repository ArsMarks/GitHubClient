package ru.arsmarks.githubclient.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.arsmarks.githubclient.data.persistence.dao.RepoDao
import ru.arsmarks.githubclient.data.persistence.entity.RepoEntity

@Database(entities = [(RepoEntity::class)], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun repoDao(): RepoDao
}
