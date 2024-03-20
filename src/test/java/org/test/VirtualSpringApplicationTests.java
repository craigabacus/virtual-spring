package org.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VirtualSpringApplicationTests {

    @Test
    public void pinned() throws InterruptedException {

        final Thread thread0 = Thread.startVirtualThread(() -> testForPin(0));
        final Thread thread1 = Thread.startVirtualThread(() -> testForPin(1));
        final Thread thread2 = Thread.startVirtualThread(() -> testForPin(2));
        System.out.println("THREADS STARTED\n");

        thread0.join();
        thread1.join();
        thread2.join();

    }

    synchronized public void testForPin(final int i) {

        System.out.println("Thread: " + Thread.currentThread().toString() + ", virtual=" + Thread.currentThread().isVirtual() + " i=" + i);
        try {
            Thread.sleep(1000);
            System.out.println("Thread Finished: " + Thread.currentThread().toString() + ", virtual=" + Thread.currentThread().isVirtual() + " i=" + i);
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
