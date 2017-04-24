package cn.itcast.zt;

import cn.itcast.zt.consul.CheckTtl;
import cn.itcast.zt.consul.Semaphore;
import com.ecwid.consul.v1.ConsulClient;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Logger;

/**
 * http://blog.didispace.com/spring-cloud-consul-lock-and-semphore/
 * http://blog.didispace.com/consul-deregister/
 * Created by zhangtian on 2017/4/24.
 */
public class TestSemaphore {
    @Test
    public void testSemaphore() throws Exception {
        ConsulClient consulClient = new ConsulClient();
        new Thread(new SemaphoreRunner(1, new CheckTtl("semaphore-1", consulClient))).start();
        new Thread(new SemaphoreRunner(2, new CheckTtl("semaphore-2", consulClient))).start();
        new Thread(new SemaphoreRunner(3, new CheckTtl("semaphore-3", consulClient))).start();
        new Thread(new SemaphoreRunner(4, new CheckTtl("semaphore-4", consulClient))).start();
        new Thread(new SemaphoreRunner(5, new CheckTtl("semaphore-5", consulClient))).start();
        new Thread(new SemaphoreRunner(6, new CheckTtl("semaphore-6", consulClient))).start();
        new Thread(new SemaphoreRunner(7, new CheckTtl("semaphore-7", consulClient))).start();
        new Thread(new SemaphoreRunner(8, new CheckTtl("semaphore-8", consulClient))).start();
        new Thread(new SemaphoreRunner(9, new CheckTtl("semaphore-9", consulClient))).start();
        new Thread(new SemaphoreRunner(10, new CheckTtl("semaphore-10", consulClient))).start();
        Thread.sleep(50000L);
    }
}

class SemaphoreRunner implements Runnable {
    private Logger logger = Logger.getLogger(SemaphoreRunner.class.getName()) ;
    private int flag;
    private CheckTtl checkTtl;

    public SemaphoreRunner(int flag, CheckTtl checkTtl) {
        this.flag = flag;
        this.checkTtl = checkTtl;
    }

    @Override
    public void run() {
        Semaphore semaphore = new Semaphore(new ConsulClient(), 3, "mg-init", checkTtl);
        try {
            if (semaphore.acquired(true)) {
                // 获取到信号量，执行业务逻辑
                logger.info("Thread {} start!"+ flag);
                Thread.sleep(new Random().nextInt(10000));
                logger.info("Thread {} end!"+ flag);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 信号量释放
                // Session锁释放
                // Session删除
                semaphore.release();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
