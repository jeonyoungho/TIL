import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class MyKafkaProducer {
	public static void main(String[] args) throws IOException {
		System.out.println("--start--");
		Properties configs = new Properties();
		configs.put("bootstrap.servers", "localhost:9092"); // kafka host 및 server 설정
		configs.put("acks", "all"); // 자신이 보낸 메시지에 대해 카프카로부터 확인을 기다리지 않습니다.
		configs.put("block.on.buffer.full", "true"); // 서버로 보낼 레코드를 버퍼링 할 때 사용할 수 있는 전체 메모리의 바이트수
		configs.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer"); // serialize 설정
		configs.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer"); // serialize 설정
		configs.put("retries",1);
		configs.put("batch.size",20000);
		configs.put("linger.ms",1);
		configs.put("buffer.memory",24568545);
		// producer 생성
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(configs);

		// message 전달
		for (int i = 0; i < 5; i++) {
			String v = "cc" + i;
			producer.send(new ProducerRecord<String, String>("topic1", v));
		}
		// 종료
		producer.flush();
		producer.close();
		System.out.println("--end--");
	}
}
