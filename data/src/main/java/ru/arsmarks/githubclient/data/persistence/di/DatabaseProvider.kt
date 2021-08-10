package ru.arsmarks.githubclient.data.persistence.di

import android.content.Context
import androidx.room.Room
import ru.arsmarks.githubclient.data.R
import ru.arsmarks.githubclient.data.persistence.AppDatabase
import toothpick.InjectConstructor
import javax.inject.Provider

@InjectConstructor
class DatabaseProvider(
    private val context: Context
) : Provider<AppDatabase> {
    override fun get(): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            context.getString(R.string.github_database)
        ).build()
    }
}