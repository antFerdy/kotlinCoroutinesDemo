import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

fun main () = runBlocking {

//    justSuspendWithoutResume()

//    justSuspendWithoutResume()

//    suspendAndResumeImmediatelyOnAnotherThread()

//    suspendAndResumeOnAnotherThreadWithScheduler()

    suspendAndReturnToContinue()
}

suspend fun justSuspendWithoutResume() {
    println("Before")

    suspendCoroutine<Unit> {
        println("BeforeSuspend")
    }

    println("After")
}


suspend fun suspendAndResumeImmediately() {

    println("Before")

    suspendCoroutine<Unit> { continuation ->
        continuation.resume(Unit)
    }

    println("After")
}

suspend fun suspendAndResumeImmediatelyOnAnotherThread() {

    println("Before")

    suspendCoroutine<Unit> { continuation ->
        continueOnAnotherThread(continuation) //creates a thread and removes it after execution
    }

    println("After")
}

private fun continueOnAnotherThread(continuation: Continuation<Unit>) {
    thread {
        Thread.sleep(3000)
        println("Launch after sleep")
        continuation.resume(Unit)
    }
}


suspend fun suspendAndResumeOnAnotherThreadWithScheduler() {
    println("Before")

    suspendCoroutine<Unit> { continuation ->
        continueOnThreadScheduler(continuation) //creates a thread and removes it after execution
    }

    println("After")
}

private val executor = Executors.newSingleThreadScheduledExecutor {
    Thread(it, "scheduler").apply { isDaemon = true }
}

fun continueOnThreadScheduler(continuation: Continuation<Unit>) {
    executor.schedule({
        println("Resuming in 3 sec")
        continuation.resume(Unit)
    }, 3000, TimeUnit.MILLISECONDS)
}


suspend fun suspendAndReturnToContinue() {

    println("Before")

    val returnedString = suspendCoroutine<String> {
        it.resume("Returning string")
    }

    println("After. Returned val: $returnedString")

    val returnedInt = suspendCoroutine<Int> {
        it.resume(42)
    }

    println("After. Returned val: $returnedInt")
}