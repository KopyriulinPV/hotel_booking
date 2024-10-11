package com.example.hotel_booking.service.statistics;

import com.example.hotel_booking.DTO.statistics.CreateUserEvent;
import com.example.hotel_booking.mapper.statistics.StatisticsMapper;
import com.example.hotel_booking.repository.statistics.StatisticsRepositoryCrUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class StatisticsServiceListener2 {

    private final StatisticsRepositoryCrUser statisticsRepositoryCrUser;

    private final StatisticsMapper statisticsMapper;


    @KafkaListener(topics = "${app.kafka.kafkaMessageTopic2}",
            groupId = "${app.kafka.kafkaMessageGroupId2}",
            containerFactory = "kafkaMessageConcurrentKafkaListenerContainerFactory2")
    public void listen2(@Payload CreateUserEvent createUserEvent,
                        @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                        @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp) {
        log.info("Received message: {}", createUserEvent);
        log.info("Key: {}; Partition: {}; Topic: {}; Timestamp: {}", key, partition, topic, timestamp);

        statisticsRepositoryCrUser.save(statisticsMapper.createUserEventToStatisticsCreateUser(createUserEvent));
    }
}
