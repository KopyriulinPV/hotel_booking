package com.example.hotel_booking.service.statistics;

import com.example.hotel_booking.DTO.statistics.RoomBookingEvent;
import com.example.hotel_booking.mapper.statistics.StatisticsMapper;
import com.example.hotel_booking.repository.statistics.StatisticsRepositoryBooking;
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
public class StatisticsServiceListener {

    private final StatisticsRepositoryBooking statisticsRepositoryBooking;

    private final StatisticsMapper statisticsMapper;

        @KafkaListener(topics = "${app.kafka.kafkaMessageTopic1}",
                groupId = "${app.kafka.kafkaMessageGroupId1}",
                containerFactory = "kafkaMessageConcurrentKafkaListenerContainerFactory")
        public void listen(@Payload RoomBookingEvent roomBookingEvent,
                           @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
                           @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                           @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                           @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp) {
            log.info("Received message: {}", roomBookingEvent);
            log.info("Key: {}; Partition: {}; Topic: {}; Timestamp: {}", key, partition, topic, timestamp);

            statisticsRepositoryBooking.save(statisticsMapper.roomBookingEventToStatistics(roomBookingEvent));
        }
}
