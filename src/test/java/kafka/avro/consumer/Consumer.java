package kafka.avro.consumer;

import java.util.List;
import java.util.Properties;

import kafka.avro.converter.Reflection_GenericRecordConverter;
import kafka.avro.entity.SuperBase;

import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class Consumer extends BasicConsumeLoop {

	public Consumer(Properties config, List<String> topics) {
		super(config, topics);
	}

	@Override
	public void process(ConsumerRecord record) {
		System.out.println("consume" + record);
		SuperBase s;
		try {
			System.out.println(record.value().toString());
			s = (SuperBase) Reflection_GenericRecordConverter.INSTANCE.toObject((GenericRecord) record.value());
			System.out.println(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
