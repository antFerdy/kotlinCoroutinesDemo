import kotlinx.coroutines.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

fun main() {
//    launchBuilderWithoutGlobalScope()

//    launchJobJoinDemo()

//    asyncDemo()

    runBlockingBuilderDemo()
}


fun launchBuilderWithoutGlobalScope() = runBlocking {

    println(Thread.currentThread().name)
    launch {
        println(Thread.currentThread().name)
        delay(3000)
        println(Thread.currentThread().name)
    }
    println(Thread.currentThread().name)
}

fun launchJobJoinDemo() = runBlocking{

    println("Started demo")

    val job = launch {
        println(Thread.currentThread().name)
        delay(3000)
        println(Thread.currentThread().name)
    }

    println("Thread ${java.lang.Thread.currentThread().name} waits coroutine")

    job.join()
}

fun asyncDemo() = runBlocking {

    println("Started")
    println(Thread.currentThread().name)

    val deferredResult: Deferred<String> = GlobalScope.async {
        println(Thread.currentThread().name)
        delay(5000)
        "Result"
    }

    //deferredResult.join() - u can use that function, but it doesn't return value

    val resultName = deferredResult.await() //blocks coroutine execution until async returns value

    println("Ended with $resultName")
}

fun runBlockingBuilderDemo() {

    thread {

        println("Thread started, name of thread: ${Thread.currentThread().name}")
        
        runBlocking {
            println("Coroutine executes in ${Thread.currentThread().name}")
            println("Thread was blocked by coroutine")
        }
        
        println("Thread ended")
    }

}