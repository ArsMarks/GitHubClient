package ru.arsmarks.githubclient.data.mappers

interface BaseMapper<T, R> {
    fun transform(data: T): R
}