package top.alexmmd.rabbit.tut4;

/**
 * @author 汪永晖
 */

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Tut4Sender {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange direct;

    AtomicInteger index = new AtomicInteger(0);

    AtomicInteger count = new AtomicInteger(0);

    private final String[] keys = {"orange", "black", "green"};

//    @Scheduled(fixedDelay = 1000, initialDelay = 500)
//    public void send() {
//        StringBuilder builder = new StringBuilder("Hello to ");
//        if (this.index.incrementAndGet() == 3) {
//            this.index.set(0);
//        }
//        String key = keys[this.index.get()];
//        builder.append(key).append(' ');
//        builder.append(this.count.incrementAndGet());
//        String message = builder.toString();
//        template.convertAndSend(direct.getName(), key, message);
//        System.out.println(" [x] Sent '" + message + "'");
//    }

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        Integer message = index.incrementAndGet();
        System.out.println(" [x] Requesting fib(" + message + ")");
        Long response = (Long) template.convertSendAndReceive(direct.getName(), "fib", message);
        System.out.println(" [.] Got '" + response + "'");
    }

}
