package org.example.lecturecleanarchitecture.helper

import java.util.concurrent.CompletableFuture

object ConcurrentTestHelper {
    fun <T> executeAsyncTasks(
        taskCount: Int,
        task: () -> T,
    ): List<Boolean> {
        val futureList =
            (1..taskCount).map {
                CompletableFuture.supplyAsync {
                    try {
                        task()
                        true
                    } catch (e: Exception) {
                        false
                    }
                }
            }
        CompletableFuture.allOf(*futureList.toTypedArray()).join()
        return futureList.map { it.get() }
    }

    fun <T> executeAsyncTasksWithId(
        taskCount: Int,
        ids: List<Long>,
        task: (Long) -> T,
    ): List<Boolean> {
        val futureList =
            (1..taskCount).map { i ->
                CompletableFuture.supplyAsync {
                    try {
                        task(ids[i])
                        true
                    } catch (e: Exception) {
                        false
                    }
                }
            }
        CompletableFuture.allOf(*futureList.toTypedArray()).join()
        return futureList.map { it.get() }
    }
}
