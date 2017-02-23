package kafka.avro.producer;

import java.util.Properties;

import kafka.avro.converter.Reflection_GenericRecordConverter;

import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.SerializationException;

/**
 * @author anamika.gupta created_on : 14-Feb-2017
 * @param <K>
 * @param <T>
 **/
public class Publisher {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void publish(String topicName, Object object) {
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.252.4.121:9092");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, io.confluent.kafka.serializers.KafkaAvroSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				io.confluent.kafka.serializers.KafkaAvroSerializer.class);
		props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "gzip");
		props.put("schema.registry.url", "http://10.252.3.210:8081");
		KafkaProducer producer = new KafkaProducer(props);

		GenericRecord genericRecord = Reflection_GenericRecordConverter.INSTANCE.toGenericRecord(object);

		ProducerRecord<String, GenericRecord> record = new ProducerRecord(topicName, "Key", genericRecord);
		try {
			producer.send(record);
		} catch (SerializationException e) {
			System.out.println("Error");
			e.printStackTrace();
			// may need to do something with it
		}
		producer.close();
	}

}
