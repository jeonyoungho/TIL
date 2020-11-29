
import java.util.HashMap;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.jms.HornetQJMSClient;
import org.hornetq.core.config.impl.FileConfiguration;
import org.hornetq.core.server.HornetQServer;
import org.hornetq.core.server.HornetQServers;
import org.hornetq.integration.transports.netty.NettyConnectorFactory;
import org.hornetq.integration.transports.netty.TransportConstants;
import org.hornetq.jms.server.JMSServerManager;
import org.hornetq.jms.server.impl.JMSServerManagerImpl;


/**
 * Reference 
 * https://howtodoinjava.com/hornetq/basic-jms-messaging-example-using-hornetq-stand-alone-server/
 * 
 * */
public class HornetQMessageDemo {
	
	private static FileConfiguration configuration;
	private static HornetQServer server;
	private static JMSServerManager jmsServerManager;
	private static Session session;
	
	static void startServer(FileConfiguration configuration) {
		try {
			server = HornetQServers.newHornetQServer(configuration);
			jmsServerManager = new JMSServerManagerImpl(server, "hornetq-jms.xml");
			// if you want to use JNDI, simple inject a context here or don't call this
			// method and make sure the JNDI parameters are set.
			jmsServerManager.setContext(null);
			jmsServerManager.start();
			
			System.out.println("Server started !!");
		} catch (Throwable e) {
			System.out.println("Damn it !!");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		
		configuration = new FileConfiguration();
		configuration.setConfigurationUrl("hornetq-configuration.xml");
		configuration.start();
		
		// Start the server
		startServer(configuration);

		Connection connection = null;
		try {
			// Step 1. Directly instantiate the JMS Queue object.
			Queue queue = HornetQJMSClient.createQueue("exampleQueue");

			// Step 2. Instantiate the TransportConfiguration object which
			// contains the knowledge of what transport to use,
			// The server port etc.

			Map<String, Object> connectionParams = new HashMap<String, Object>();
			connectionParams.put(TransportConstants.PORT_PROP_NAME, 5445);

			TransportConfiguration transportConfiguration = new TransportConfiguration(
					NettyConnectorFactory.class.getName(), connectionParams);

			// Step 3 Directly instantiate the JMS ConnectionFactory object
			// using that TransportConfiguration
			ConnectionFactory cf = HornetQJMSClient.createConnectionFactory(transportConfiguration);

			// Step 4.Create a JMS Connection
			connection = cf.createConnection();
			
			// Step 5. Create a JMS Session
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Step 6. Create a JMS Message Producer
			MessageProducer producer = session.createProducer(queue);

			// Step 7. Create a Text Message
			TextMessage textMessage = session.createTextMessage("text message");
			CustomMessage customMessage = new CustomMessage();
			customMessage.setTitle("custom message title");
			customMessage.setContent("custom message content");
			System.out.println("[customMessage] title: " + customMessage.getTitle() + 
					", content: " + customMessage.getContent());
			Message message = session.createObjectMessage(customMessage);
			
			// Step 8. Send the Message
			System.out.println("Sent message: " + textMessage.getText());
			producer.send(textMessage);
			System.out.println("Sent message: " + message.toString());
			producer.send(message);
			

			// Step 9. Create a JMS Message Consumer
			MessageConsumer messageConsumer = session.createConsumer(queue);
			
			// Step 10. Start the Connection
			connection.start();
			System.out.println("=======================================================");

			// Step 11. Receive the message
			TextMessage receivedTextMessage = (TextMessage) messageConsumer.receive(5000);
			System.out.println("Received message: " + receivedTextMessage.getText());
			
			ObjectMessage receivedObjectMessage = (ObjectMessage) messageConsumer.receive(5000);
			System.out.println("Received message: " + receivedObjectMessage.toString());
			CustomMessage receivedCutsomMessage = (CustomMessage) receivedObjectMessage.getObject();
			System.out.println("[receivedCustomMessage] title: " + receivedCutsomMessage.getTitle() + 
					", content: " + receivedCutsomMessage.getContent());
		} finally {
			if (connection != null) {
				connection.close();
				connection = null;
			}
			
			if(session != null) {
				session.close();
				session = null;
			}
			
			if(jmsServerManager.isStarted()) {
				jmsServerManager.stop();
				jmsServerManager = null;
			}
		}
	}
}
