package com.atguigu.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSProduceTopic {

    public static final String MQ_URL = "tcp://192.168.61.88:61616";
    public static final String MyTOPIC = "topicLeo01";

    public static void main(String[] args) throws JMSException {

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(MQ_URL);

        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic(MyTOPIC);

        MessageProducer messageProducer = session.createProducer(topic);

        for (int i = 1; i <=3; i++)
        {
            TextMessage textMessage = session.createTextMessage("topicMsg--" + i);
            messageProducer.send(textMessage);
        }
        messageProducer.close();
        session.close();
        connection.close();

        System.out.println("<<<主题消息发送成功>>>");


    }
}
