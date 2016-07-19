package jiaop.message.threadpoolexecuto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 */
public class MainActivity extends AppCompatActivity {

    //控制线程的变量
    private boolean controler_first = true;
    //线程池
    ExecutorService executorService;

    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //立即终止线程池，并尝试打断正在执行的任务，并且清空任务缓存队列，返回尚未执行的任务
        executorService.shutdownNow();
        //不会立即终止线程池，而是要等所有任务缓存队列中的任务都执行完后才终止，但再也不会接受新的任务
        executorService.shutdown();

    }

    /**
     * newCachedThreadPool
     * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
     * 创建一个可根据需要创建新线程的线程池，但是在以前构造的线程可用时将重用它们。对于执行很多短期异步任务的程序而言，这些线程池通常可提高程序性能。
     * 调用 execute 将重用以前构造的线程（如果线程可用）。如果现有线程没有可用的，则创建一个新线程并添加到池中。终止并从缓存中移除那些已有 60 秒钟未被使用的线程。
     * 因此，长时间保持空闲的线程池不会使用任何资源。注意，可以使用 ThreadPoolExecutor 构造方法创建具有类似属性但细节不同（例如超时参数）的线程池。
     */
    private void CachedThreadPool() {
        executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        while (controler_first) {
                            Log.d("TAG", "index:" + index++);
                            if (index == 1000) {
                                controler_first = false;
                            }
                        }
                    }
                }.start();
            }
        });
    }

    /**
     * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
     * 创建一个可重用固定线程数的线程池，以共享的无界队列方式来运行这些线程。在任意点，在大多数 nThreads 线程会处于处理任务的活动状态。
     * 如果在所有线程处于活动状态时提交附加任务，则在有可用线程之前，附加任务将在队列中等待。如果在关闭前的执行期间由于失败而导致任何线程终止，那么一个新线程将代替它执行后续的任务（如果需要）。
     * 在某个线程被显式地关闭之前，池中的线程将一直存在。
     * @param number 线程数量
     */
    private void FixedThreadPool(int number) {
        executorService = Executors.newFixedThreadPool(number);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        while (controler_first) {
                            Log.d("TAG", "index:" + index++);
                            if (index == 1000) {
                                controler_first = false;
                            }
                        }
                    }
                }.start();
            }
        });
    }

    /**
     *  创建一个定长线程池，支持定时及周期性任务执行。
     * @param number 线程数量
     */
    private void ScheduledThreadPool(int number) {
        /**
         * 延迟执行
         */
        ScheduledExecutorService executorService = Executors
                .newScheduledThreadPool(number);
        executorService.schedule(new Runnable() {

            @Override
            public void run() {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        while (controler_first) {
                            Log.d("TAG", "index:" + index++);
                            if (index == 1000) {
                                controler_first = false;
                            }
                        }
                    }
                }.start();
            }
        }, 3, TimeUnit.SECONDS);//设置在3秒后开始执行

        /**
         * 定期执行，可以用来做定时器
         */
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        while (controler_first) {
                            Log.d("TAG", "index:" + index++);
                            if (index == 1000) {
                                controler_first = false;
                            }
                        }
                    }
                }.start();
            }
        }, 1, 3, TimeUnit.SECONDS);
    }

    /**
     * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
     * 创建一个使用单个 worker 线程的 Executor，以无界队列方式来运行该线程。（注意，如果因为在关闭前的执行期间出现失败而终止了此单个线程，那么如果需要，一个新线程将代替它执行后续的任务）。
     * 可保证顺序地执行各个任务，并且在任意给定的时间不会有多个线程是活动的。与其他等效的 newFixedThreadPool(1) 不同，可保证无需重新配置此方法所返回的执行程序即可使用其他的线程。
     */
    private void SingleThreadExecutor(){
        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        while (controler_first) {
                            Log.d("TAG", "index:" + index++);
                            if (index == 1000) {
                                controler_first = false;
                            }
                        }
                    }
                }.start();
            }
        });
    }

}
