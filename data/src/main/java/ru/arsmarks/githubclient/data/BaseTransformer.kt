package ru.arsmarks.githubclient.data

interface BaseTransformer<T, R> {
    fun transform(data: T): R
}