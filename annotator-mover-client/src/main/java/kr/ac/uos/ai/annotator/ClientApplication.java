package kr.ac.uos.ai.annotator;

import org.junit.Test;

import kr.ac.uos.ai.annotator.activemq.ActiveMQManager;

/*
 * This class is written by Chan Yeon, Cho
 * AI-Laboratory, Seoul, Korea
 * 2015. 9. 15. 오후 3:04:46
 */

public class ClientApplication {
	
	public ClientApplication() {
		init();
	}
	
	
	public void init() {
		ActiveMQManager amqM = new ActiveMQManager();
		amqM.init("FileTest");
	}
	
	public static void main(String[] args) {
		new ClientApplication();
	}
	
	
}
