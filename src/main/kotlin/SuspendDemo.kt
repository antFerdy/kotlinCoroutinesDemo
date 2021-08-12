import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread


fun main() {
    runBlocking { launchRoutine() } //u can call suspend function either inside coroutine or in another suspend function
}


fun threadLaunch() {
    println(Thread.currentThread().name)

    //the process waits until this thread finished
    thread {

        println("Fake work started: " + Thread.currentThread().name)
        Thread.sleep(5000)
        println("Fake work done")
    }


    println(Thread.currentThread().name)
}

suspend fun launchRoutine() {
    println(Thread.currentThread().name)

    GlobalScope.launch { //creating background coroutine that works on bg threads (DefaultDispatcher workers)
        println("Fake work started: " + Thread.currentThread().name)
        delay(5000)
        println("Fake work ended: " + Thread.currentThread().name)
    }

    delay(7000)
    println(Thread.currentThread().name)
}

