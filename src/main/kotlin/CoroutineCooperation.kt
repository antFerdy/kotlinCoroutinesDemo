import kotlinx.coroutines.*

fun main() {

//    failedCancelDemo()

    cancellableCoroutine()
}

private fun failedCancelDemo() = runBlocking {
    println("Launching coroutine on thread ${Thread.currentThread().name}")

    val job = launch {
        for (i in 0..500) {
            print(i)
            Thread.sleep(50)
        }
    }

    delay(500)
    job.cancel() //coroutine is not cooperative
    job.join()

    println("Main thread ends ${Thread.currentThread().name}")
}

private fun cancellableCoroutine() = runBlocking {
    println("Launching coroutine on thread ${Thread.currentThread().name}")

    val job = launch {
        for (i in 0..500) {
            print(i)
            delay(50)
        }
    }

    delay(500)
    job.cancelAndJoin()

    println("Main thread ends ${Thread.currentThread().name}")
}