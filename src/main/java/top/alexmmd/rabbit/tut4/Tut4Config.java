package top.alexmmd.rabbit.tut4;

/**
 * @author 汪永晖
 */

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Tut4Config {

    @Bean
    public DirectExchange direct() {
        return new DirectExchange("tut.direct");
    }

    @Bean
    public Queue autoDeleteQueue1() {
        return new Queue("autoDeleteQueue1");
    }

    @Bean
    public Queue autoDeleteQueue2() {
        return new Queue("autoDeleteQueue2");
    }

    @Bean
    public Queue autoDeleteQueue3() {
        return new Queue("autoDeleteQueue3");
    }

    @Bean
    public Binding binding1a(DirectExchange direct, Queue autoDeleteQueue1) {
        return BindingBuilder.bind(autoDeleteQueue1).to(direct).with("orange");
    }

    @Bean
    public Binding binding1b(DirectExchange direct, Queue autoDeleteQueue1) {
        return BindingBuilder.bind(autoDeleteQueue1).to(direct).with("black");
    }

    @Bean
    public Binding binding2a(DirectExchange direct, Queue autoDeleteQueue2) {
        return BindingBuilder.bind(autoDeleteQueue2).to(direct).with("green");
    }

    @Bean
    public Binding binding2b(DirectExchange direct, Queue autoDeleteQueue2) {
        return BindingBuilder.bind(autoDeleteQueue2).to(direct).with("black");
    }

    @Bean
    public Binding binding3a(DirectExchange direct, Queue autoDeleteQueue3) {
        return BindingBuilder.bind(autoDeleteQueue3).to(direct).with("fib");
    }

}
