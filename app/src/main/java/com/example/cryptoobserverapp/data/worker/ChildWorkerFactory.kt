package com.example.cryptoobserverapp.data.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters

interface ChildWorkerFactory {

    fun create(context: Context, workerParameters: WorkerParameters) : ListenableWorker
}