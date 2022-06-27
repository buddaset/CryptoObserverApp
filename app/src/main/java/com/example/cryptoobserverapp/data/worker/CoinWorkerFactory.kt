package com.example.cryptoobserverapp.data.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import javax.inject.Provider

class CoinWorkerFactory(
    private val workerProviders : Map<Class< out ListenableWorker>, Provider<ChildWorkerFactory>>
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? =
        when(workerClassName) {
            RefreshDataWorker::class.qualifiedName -> {
                val childWorkerFactory = workerProviders[RefreshDataWorker::class.java]?.get()
                childWorkerFactory?.create(appContext, workerParameters)

            }
            else -> null
        }
}