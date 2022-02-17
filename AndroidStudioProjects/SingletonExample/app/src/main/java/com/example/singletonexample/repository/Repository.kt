package com.example.singletonexample.repository

import androidx.lifecycle.LiveData
import com.example.singletonexample.api.RetrofitBuilder
import com.example.singletonexample.models.User
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object Repository {

    var job: CompletableJob? = null

    fun getUser(userId: String): LiveData<User> {
        job = Job()
        return object: LiveData<User>() {
            override fun onActive() {
                super.onActive()
                job?.let { executeJob ->
                    CoroutineScope(IO + executeJob).launch {
                        val user = RetrofitBuilder.apiService.getUser(userId)
                        withContext(Main) {
                            value = user
                            executeJob.complete()
                        }

                    }
                }
            }
        }
    }

    fun cancelJobs() {
        job?.cancel()
    }
}