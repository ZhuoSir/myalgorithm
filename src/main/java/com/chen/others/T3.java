package com.chen.others;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**实现死锁 */
public class T3 {

    static class Th implements Callable {

        Object obj1;
        Object obj2;

        public Th(Object obj1, Object obj2) {
            this.obj1 = obj1;
            this.obj2 = obj2;
        }

        @Override
        public Object call() throws Exception {
            while (true) {
                synchronized (obj1) {
                    System.out.println(Thread.currentThread().getName() + "获取到了第一个资源!");
                    synchronized (obj2) {
                        System.out.println(Thread.currentThread().getName() + "成功获取全部资源！");
                        break;
                    }
                }
            }
            return null;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Object res1 = new Object();
        Object res2 = new Object();

        Th th1 = new Th(res1, res2);
        Th th2 = new Th(res2, res1);

        Collection<Callable<Integer>> threads = new ArrayList<>();
        threads.add(th1);
        threads.add(th2);

        ExecutorService service = Executors.newCachedThreadPool();
        service.invokeAll(threads);
    }
}
