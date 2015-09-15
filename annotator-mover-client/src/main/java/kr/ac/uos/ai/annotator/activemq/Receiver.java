package kr.ac.uos.ai.annotator.activemq;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import kr.ac.uos.ai.annotator.filemover.filemaker.FileMaker;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver implements Runnable {

	private String queueName;
	private ActiveMQConnectionFactory factory;
	private Connection connection;
	private Session session;
	private Queue queue;
	private MessageConsumer consumer;
	private Message message;
	private TextMessage tMsg;
	private FileMaker fM;

	public Receiver() {
		fM = new FileMaker();
	}

	private void consume() {
		try {
			consumer = session.createConsumer(queue);
			message = consumer.receive();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			while (true) {
				consume();

				tMsg = (TextMessage) message;

				if (tMsg.toString().substring(0, 4).equals("FILE")) {
					fM.makeFileFromByteArray("F:/jartest/clientTest/",
							Integer.parseInt(tMsg.toString().substring(5)));
				}

			}
		} catch (Exception e) {
			System.out.println("Receiver Run Error");
		}
	}

	public void init() {
		factory = new ActiveMQConnectionFactory(
				ActiveMQConnection.DEFAULT_BROKER_URL);

		try {
			connection = factory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			queue = session.createQueue(queueName);

		} catch (JMSException e) {

			e.printStackTrace();
		}
	}

	public TextMessage gettMsg() {
		return tMsg;
	}

	public void settMsg(TextMessage tMsg) {
		this.tMsg = tMsg;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
}