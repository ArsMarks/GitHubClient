package ru.arsmarks.githubclient.data

interface BaseMapper<T, R> {
    fun transform(data: T): R
}