package mc.open.model;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author macheng
 * @date 2021/8/25 9:18
 */
public class SaveResultBO<T> {

    private List<T> addList;
    private List<T> saveList;
    private ReadWriteLock readWriteLock;
    private final AtomicInteger atomicInteger;
    @Getter
    private volatile Boolean complete;
    private CountDownLatch countDownLatch;
    private Integer timeout;
    private Integer latch;

    public SaveResultBO(int threads , int latch , int timeout) {
        this.addList = new CopyOnWriteArrayList<>();
        this.saveList = new CopyOnWriteArrayList<>();
        this.complete = false;
        this.readWriteLock = new ReentrantReadWriteLock();
        this.latch=latch;
        this.timeout=timeout;
        this.countDownLatch = new CountDownLatch(latch);
        atomicInteger = new AtomicInteger(threads);

    }

    public SaveResultBO(int threads) {
        this.addList = new CopyOnWriteArrayList<>();
        this.saveList = new CopyOnWriteArrayList<>();
        this.complete = false;
        this.latch=100;
        this.timeout=5;
        this.readWriteLock = new ReentrantReadWriteLock();
        this.countDownLatch = new CountDownLatch(this.latch);
        atomicInteger = new AtomicInteger(threads);

    }

    public void add(T e) {
        readWriteLock.readLock().lock();
        addList.add(e);
        countDownLatch.countDown();
        readWriteLock.readLock().unlock();
    }

    public void add(List<T> e) {
        readWriteLock.readLock().lock();
        addList.addAll(e);
        countDownLatch.countDown();
        readWriteLock.readLock().unlock();
    }

    public List<T> getToSave() throws InterruptedException {
        if (!countDownLatch.await(timeout, TimeUnit.SECONDS)) {
            System.out.println("WAIT TIMEOUT");
        }
        readWriteLock.writeLock().lock();
        saveList = addList;
        addList = new CopyOnWriteArrayList<>();
        countDownLatch = new CountDownLatch(latch);
        readWriteLock.writeLock().unlock();
        return saveList;
    }

    public List<T> lastGetToSave() {
        readWriteLock.writeLock().lock();
        saveList = addList;
        addList = new CopyOnWriteArrayList<>();
        countDownLatch = new CountDownLatch(latch);
        readWriteLock.writeLock().unlock();
        return saveList;
    }

    public void complete() {
        if (atomicInteger.decrementAndGet() == 0) {
            this.complete = true;
            while (countDownLatch.getCount() > 0) {
                countDownLatch.countDown();
            }
        }
    }
}
