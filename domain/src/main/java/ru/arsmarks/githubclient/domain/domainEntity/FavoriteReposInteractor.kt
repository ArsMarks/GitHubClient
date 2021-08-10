package ru.arsmarks.githubclient.domain.domainEntity

import ru.arsmarks.githubclient.domain.GetFavoriteReposUseCase
import javax.inject.Inject

class FavoriteReposInteractor @Inject constructor(
    private val getFavoriteReposUseCase: GetFavoriteReposUseCase
) {


}