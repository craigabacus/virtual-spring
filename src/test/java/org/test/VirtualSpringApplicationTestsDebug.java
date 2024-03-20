package org.test;

import java.util.Arrays;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled("Use VirtualSpringApplicationTests")
class VirtualSpringApplicationTestsDebug {

    @Test
    public void pinned() throws InterruptedException {

        final Thread thread0 = Thread.startVirtualThread(() -> testForPin(0));
        final Thread thread1 = Thread.startVirtualThread(() -> testForPin(1));
        final Thread thread2 = Thread.startVirtualThread(() -> testForPin(2));
//        final Thread thread0 = new Thread(() -> testForPin(0));
//        thread0.start();
//        final Thread thread1 = new Thread(() -> testForPin(1));
//        thread1.start();
//        final Thread thread2 = new Thread(() -> testForPin(2));
//        thread2.start();

//        printThreadState(thread0);
//        printThreadState(thread1);
//        printThreadState(thread2);

        System.out.println("THREADS STARTED\n");

//        final Set<Thread> threads = Thread.getAllStackTraces().keySet();
//        for (final Thread thread : threads) {
//            printThreadState(thread);
//        }
//
//        final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
//        System.out.println(forkJoinPool);
//        System.out.println(Thread.currentThread().toString() + " Number of threads " + Thread.activeCount() + " out of " + threads.size());

        thread0.join();
        thread1.join();
        thread2.join();

    }

    protected void printThreadState(final Thread thread) {
        System.out.println("Thread name: " + thread.getName());
        System.out.println("Thread state: " + thread.getState());
        System.out.println("Thread ID: " + thread.threadId());
        System.out.println("Thread is virtual: " + thread.isVirtual());

        if (thread.getState() == Thread.State.BLOCKED) {
            final StackTraceElement[] stack = thread.getStackTrace();
            System.out.println("\nThread :" + thread.getName() + " is blocked on ");
            Arrays.stream(stack).forEach(s -> System.out.println(s));
        }
        System.out.println("------------------------");
    }

    synchronized public void testForPin(final int i) {

        System.out.println("Thread: " + Thread.currentThread().toString() + ", virtual=" + Thread.currentThread().isVirtual() + " i=" + i);
        try {
            Thread.sleep(1000);
            System.out.println("Thread Finished: " + Thread.currentThread().toString() + ", virtual=" + Thread.currentThread().isVirtual() + " i=" + i);
//            notifyAll();
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
