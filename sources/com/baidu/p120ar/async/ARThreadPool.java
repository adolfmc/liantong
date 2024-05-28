package com.baidu.p120ar.async;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.async.ARThreadPool */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARThreadPool {
    private int keepAliveTime = 1500;
    private final ReentrantLock mainLock = new ReentrantLock();
    private Map<String, Worker> mWorkers = new HashMap();

    public boolean execute(ARTask aRTask) {
        if (aRTask == null) {
            return false;
        }
        boolean z = true;
        ReentrantLock reentrantLock = this.mainLock;
        reentrantLock.lock();
        try {
            String tag = aRTask.getTag();
            if (tag == null) {
                tag = "";
            }
            Worker worker = this.mWorkers.get(tag);
            if (worker == null) {
                Worker worker2 = new Worker(tag, aRTask);
                this.mWorkers.put(tag, worker2);
                worker2.thread.start();
            } else {
                z = worker.addTask(aRTask);
            }
            return z;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean isTaskRunning(String str) {
        Worker worker;
        return (TextUtils.isEmpty(str) || (worker = this.mWorkers.get(str)) == null || worker.mTasks.size() == 0) ? false : true;
    }

    public int removeTasks(String str) {
        Worker worker;
        if (TextUtils.isEmpty(str) || (worker = this.mWorkers.get(str)) == null) {
            return 0;
        }
        BlockingQueue<ARTask> blockingQueue = worker.mTasks;
        int size = blockingQueue.size();
        blockingQueue.clear();
        return size;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.async.ARThreadPool$Worker */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class Worker implements Runnable {
        ARTask firstTask;
        BlockingQueue<ARTask> mTasks;
        String tag;
        final Thread thread;

        Worker(String str, ARTask aRTask) {
            this.firstTask = aRTask;
            this.thread = new Thread(this);
            this.mTasks = new ArrayBlockingQueue(10);
            this.tag = str;
        }

        Worker(String str, BlockingQueue<ARTask> blockingQueue) {
            this.thread = new Thread(this);
            this.mTasks = blockingQueue;
            this.tag = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                runWorker();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        final void runWorker() throws InterruptedException {
            ARTask aRTask = this.firstTask;
            this.firstTask = null;
            while (true) {
                if (aRTask == null) {
                    try {
                        aRTask = this.mTasks.poll(ARThreadPool.this.keepAliveTime, TimeUnit.MILLISECONDS);
                        if (aRTask == null) {
                            return;
                        }
                    } finally {
                        processWorkerExit();
                    }
                }
                aRTask.run();
                aRTask = null;
            }
        }

        private void processWorkerExit() {
            ARThreadPool.this.mainLock.lock();
            try {
                ARThreadPool.this.mWorkers.remove(this.tag);
                if (this.mTasks.size() > 0) {
                    Worker worker = new Worker(this.tag, this.mTasks);
                    ARThreadPool.this.mWorkers.put(this.tag, worker);
                    worker.thread.start();
                }
            } finally {
                ARThreadPool.this.mainLock.unlock();
            }
        }

        public boolean addTask(ARTask aRTask) {
            return this.mTasks.offer(aRTask);
        }
    }
}
