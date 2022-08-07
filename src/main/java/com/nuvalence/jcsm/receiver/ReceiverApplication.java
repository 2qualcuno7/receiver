package com.nuvalence.jcsm.receiver;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

@SpringBootApplication
@RestController
public class ReceiverApplication {
	private static final Logger log = LoggerFactory.getLogger(ReceiverApplication.class);
	//@Value("${kafka.bootstrap-servers:kafka.local:9092}")
	String bootstrapAddress = "kafka-poc.kafka.svc.cluster.local:9092";


	@GetMapping("/")
	public String home() {
		String bootstrapServers = bootstrapAddress;

		log.info("Here, server config: " + bootstrapServers);

		Properties properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

		ProducerRecord<String, String> producerRecord =
				new ProducerRecord<>("first_topic", "hello world");

		producer.send(producerRecord);

		producer.flush();

		producer.close();

		return "Result: " + "jgyfytvb";
	}


	public static void main(String[] args) {
		log.info("Application started");

		SpringApplication.run(ReceiverApplication.class, args);
	}

}
