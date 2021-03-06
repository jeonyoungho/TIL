import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class MyKafkaConsumer {
	public static void main(String[] args) {
		Properties configs = new Properties();
		// 환경 변수 설정
		configs.put("bootstrap.servers", "52.78.38.62:9092"); // kafka server host 및 port
		configs.put("session.timeout.ms", "10000"); // session 설정
		configs.put("group.id", "topic1"); // topic 설정
		configs.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); // key deserializer
		configs.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); // value deserializer
		configs.put("enable.auto.commit","true");
		configs.put("auto.commit.interval.ms","1000");
		configs.put("session.timeout.ms","30000");
        
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(configs); // consumer 생성
		consumer.subscribe(Arrays.asList("topic1")); // topic 설정
		while (true) { // 계속 loop를 돌면서 producer의 message를 띄운다.
			ConsumerRecords<String, String> records = consumer.poll(500);
			for (ConsumerRecord<String, String> record : records) {
				String s = record.topic();
				if ("topic1".equals(s)) {
					System.out.println(record.value());
				} else {
					throw new IllegalStateException("get message on topic " + record.topic());
				}
			}
		}
	}
}
