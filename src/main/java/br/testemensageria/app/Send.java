package br.testemensageria.app;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

public class Send {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
    	//criando conexão com o server
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        //tentando a conexão
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
        	//declaração da queue
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            //setando uma mensagem a ser publicada
            String message = "Hello World1!";
            //aderindo a mensagem ao queue
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            //mostrando que a mensagem foi enviada
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
