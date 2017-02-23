package kafka.avro.consumer;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Properties;

public class ConsumerTest {

	public static void main(String[] args) throws UnknownHostException {
		Properties config = new Properties();
		config.put("client.id", InetAddress.getLocalHost().getHostName());
		config.put("group.id", "foo");
		config.put("bootstrap.servers", "10.252.4.121:9092");
		config.put("key.deserializer", io.confluent.kafka.serializers.KafkaAvroDeserializer.class);
		config.put("value.deserializer", io.confluent.kafka.serializers.KafkaAvroDeserializer.class);
		config.put("schema.registry.url", "http://10.252.3.210:8081");
		Consumer child = new Consumer(config, Arrays.asList("22Feb_Test"));
		child.run();

	}
}
