import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun main() {
//    runBlocking { anotherAsyncRoutine()  }

//    runBlockingDemo()
//    dramMachineDemo()

    dramMachineDemoV2()
}


fun anotherAsyncRoutine() {
    GlobalScope.launch {

        for (i in (0..100)) {
            println("Hey hey")
        }
        delay(1000L)
    }
    println("I am over")
}

fun runBlockingDemo() {
    println("MAIN THREAD: Started blocking coroutine")
    runBlocking {
        println("COROUTINE: Before timeout")
        delay(5000L)
        println("COROUTINE: After timeout")
    }

    println("MAIN THREAD: Blocking coroutine has ended")
}

fun dramMachineDemo() {
    runBlocking {
        launch { playBeats() }
        playDrums()
    }
}


fun playBeats() {
    println("ID of current thread ${Thread.currentThread().id}")
    println("Bam")
    println("Bam")
    println("Bam")
    println("Bam")
    println("Bam")
    println("Bam")
}


fun playDrums() {
    println("ID of current thread ${Thread.currentThread().id}")
    println("Tss")
    println("Tss")
}


suspend fun dramMachineDemoV2() {
    runBlocking {
        launch { playBeatsV2() }
        playDrumsV2()
    }
}


suspend fun playDrumsV2() {
    println("${Thread.currentThread().id}: Tss")
    delay(1000L)
    println("${Thread.currentThread().id}: Tss")
}


suspend fun playBeatsV2() {
    println("${Thread.currentThread().id}: Bam")
    println("${Thread.currentThread().id}: Bam")
    delay(1000L)
    println("${Thread.currentThread().id}: Bam")
    println("${Thread.currentThread().id}: Bam")
    delay(1000L)
    println("${Thread.currentThread().id}: Bam")
    println("${Thread.currentThread().id}: Bam")
}