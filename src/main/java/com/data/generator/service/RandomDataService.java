package com.data.generator.service;

import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RandomDataService {

    private static final String PROCESSING = "PROCESSING";
    private static final String FAILED = "FAILED";
    private static final String SENT = "SENT";

    private final DataFactory dataFactory;

    public RandomDataService() {
        this.dataFactory = new DataFactory();
    }

    public String generateName() {
        return dataFactory.getName();
    }

    public String generateAddress() {
        return String.join(", ", dataFactory.getAddress(), dataFactory.getCity(), dataFactory.getNumberText(5));
    }

    public int generateAge() {
        return dataFactory.getNumberBetween(16, 100);
    }

    public LocalDate generateDate() {
        return LocalDate.of(
                dataFactory.getNumberBetween(2017, 2018),
                dataFactory.getNumberBetween(1, 12),
                dataFactory.getNumberBetween(1, 28)
        );
    }

    public Long generateUserId(List<Long> userIds) {
        int index = dataFactory.getNumberBetween(0, userIds.size());
        return userIds.get(index);
    }

    public String generateMailTopic() {
        return dataFactory.getRandomWord();
    }

    public String generateMailBody() {
        return dataFactory.getRandomText(256);
    }

    public String generateSentStatus() {
        boolean status = dataFactory.chance(10);
        if (status) {
            return PROCESSING;
        }

        status = dataFactory.chance(5);
        if (status) {
            return FAILED;
        }

        return SENT;
    }
}
