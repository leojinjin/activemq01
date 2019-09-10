package com.atguigu.activemq;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


public class JMSProduce {

    public static final String MQ_URL = "tcp://192.168.61.88:61616";
    public static final String MyQUEUE = "queueLeo01";


    public static void main(String[] args) throws JMSException, JMSException {

        //1. 通过ConnectionFactory 工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(MQ_URL);

        //2. 获得connection 对象 并启动
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        //3. 通过connection对象 获得session对象
        // 第一个参数叫mq的事务， 第二个参数叫消息的签收   可忽略用默认
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        //4. 通过session 获得目的地
        Queue queue = session.createQueue(MyQUEUE);

        //5.通过session 获得消息的生产者
        MessageProducer messageProducer = session.createProducer(queue);
        //messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        //messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);


        //6. 开始生产3条消息 并发送到Activemqq上
        for (int i = 1; i <= 9; i++) {
            TextMessage textMessage = session.createTextMessage("msg=====" + i);
            //7.用messageProducer 发送消息到mq
            messageProducer.send(textMessage);
        }

        //8. 释放资源
        messageProducer.close();
        //session.commit();
        session.close();
        connection.close();

        //打印测试
        System.out.println("<<<发送成功>>>");

    }

}
















































