package top.alexmmd.rabbit.tut4;

/**
 * @author 汪永晖
 */

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class Tut4Receiver {

    @RabbitListener(queues = "autoDeleteQueue1")
    public void receive1(String in) throws InterruptedException {
        receive(in, 1);
    }

    @RabbitListener(queues = "autoDeleteQueue2")
    public void receive2(String in) throws InterruptedException {
        receive(in, 2);
    }

    @RabbitListener(queues = "autoDeleteQueue3")
    public long fibonacci(long n) {
        System.out.println(" [x] Received request for " + n);
        long result = fib(n);
        System.out.println(" [.] Returned " + result);
        return result;
    }

    public long fib(long n) {
        return n == 0 ? 0 : n == 1 ? 1 : (fib(n - 1) + fib(n - 2));
    }

    public void receive(String in, int receiver) throws InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start();
        System.out.println("instance " + receiver + " [x] Received '" + in + "'");
        doWork(in);
        watch.stop();
        System.out.println("instance " + receiver + " [x] Done in " + watch.getTotalTimeSeconds() + "s");
    }

    private void doWork(String in) throws InterruptedException {
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(1000);
            }
        }
    }

}
