package com.kafka.cus;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author lianghaiyang 2018/11/22 10:37
 * 注解方式监听
 */
//@Slf4j
//@Component
public class KafkaAnnotationConsumer {
    /**
     * 消费消息统一处理
     */
//    @KafkaListener(topicPattern = "app_log")
//    public void listen(ConsumerRecord<?, ?> record) {
//        System.out.println("kafka监听接收消息================================="+record.value());
////        IO写入磁盘，加一个读取本地磁盘的解析项目
////        本项目不改动
//
//    }
}