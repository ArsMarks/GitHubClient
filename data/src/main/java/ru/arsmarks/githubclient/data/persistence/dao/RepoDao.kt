package ru.arsmarks.githubclient.data.persistence.dao

import androidx.room.*
import io.reactivex.Flowable
import ru.arsmarks.githubclient.data.persistence.entity.RepoEntity
import ru.arsmarks.githubclient.data.persistence.entity.RepoEntity.Companion.REPO_IS_FAVORITE
import ru.arsmarks.githubclient.data.persistence.entity.RepoEntity.Companion.REPO_TABLE

@Dao
abstract class RepoDao {

    @Query("SELECT * FROM $REPO_TABLE WHERE $REPO_IS_FAVORITE = 1")
    abstract fun getAll(): Flowable<List<RepoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(entity: RepoEntity): Long

    /**
     * Delete an entity
     * @return A number of entity deleted. This should always be `1`
     */
    @Delete
    abstract fun delete(entity: RepoEntity): Int
}