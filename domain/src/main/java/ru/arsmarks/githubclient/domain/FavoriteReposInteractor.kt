package ru.arsmarks.githubclient.domain

import ru.arsmarks.githubclient.domain.usecases.FavoriteUseCase
import ru.arsmarks.githubclient.domain.usecases.GetFavoriteReposUseCase

class FavoriteReposInteractor constructor(
    private val getFavoriteReposUseCase: GetFavoriteReposUseCase,
    private val favoriteUseCase: FavoriteUseCase
) {
}