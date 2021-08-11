package ru.arsmarks.githubclient.data.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import io.reactivex.Maybe
import ru.arsmarks.githubclient.data.persistence.entity.RepoEntity
import ru.arsmarks.githubclient.data.persistence.entity.RepoEntity.Companion.REPO_ID
import ru.arsmarks.githubclient.data.persistence.entity.RepoEntity.Companion.REPO_IS_FAVORITE
import ru.arsmarks.githubclient.data.persistence.entity.RepoEntity.Companion.REPO_TABLE

@Dao
abstract class RepoDao {

    @Query("SELECT * FROM $REPO_TABLE WHERE $REPO_IS_FAVORITE = 1")
    abstract fun getAll(): Flowable<List<RepoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(entity: RepoEntity): Maybe<Long>

    @Query("DELETE FROM $REPO_TABLE WHERE $REPO_ID = :id")
    abstract fun delete(id: Int)

    @Query("SELECT EXISTS(SELECT * FROM $REPO_TABLE WHERE $REPO_ID = :id)")
    abstract fun isRowIsExist(id: Int): Boolean
}