package cn.itcast.zt;

import cn.itcast.zt.consul.CheckTtl;
import cn.itcast.zt.consul.Lock;
import com.ecwid.consul.v1.ConsulClient;
import org.junit.Test;

import java.util.Random;
import java.util.logging.Logger;

/**
 * http://blog.didispace.com/spring-cloud-consul-lock-and-semphore/
 * http://blog.didispace.com/consul-deregister/
 * Created by zhangtian on 2017/4/24.
 */
public class TestLock {
    @Test
    public void testLock() throws Exception  {
        ConsulClient consulClient = new ConsulClient();
        CheckTtl checkTtl = new CheckTtl("lock-1", consulClient);
        new Thread(new LockRunner(1, new CheckTtl("lock-1", consulClient))).start();
        new Thread(new LockRunner(2, new CheckTtl("lock-2", consulClient))).start();
        new Thread(new LockRunner(3, new CheckTtl("lock-3", consulClient))).start();
        new Thread(new LockRunner(4, new CheckTtl("lock-4", consulClient))).start();
        new Thread(new LockRunner(5, new CheckTtl("lock-5", consulClient))).start();
        Thread.sleep(30000L);
    }
}

class LockRunner implements Runnable {
    Logger logger = Logger.getLogger(LockRunner.class.getName()) ;
    private int flag;
    private CheckTtl checkTtl;

    public LockRunner(int flag, CheckTtl checkTtl) {
        this.flag = flag;
        this.checkTtl = checkTtl;
    }

    @Override
    public void run() {
        Lock lock = new Lock(new ConsulClient(), "lock-key", checkTtl);
        try {
            // 获取分布式互斥锁（参数含义：阻塞模式、每次尝试获取锁的间隔500ms、尝试n次）
            if (lock.lock(true, 500L, null)) {
                logger.info("Thread {} start!"+ flag);
                // 处理业务逻辑
                Thread.sleep(new Random().nextInt(5000));
                logger.info("Thread {} end!"+ flag);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
