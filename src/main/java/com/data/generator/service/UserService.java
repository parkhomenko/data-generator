package com.data.generator.service;

import com.data.generator.domain.Mail;
import com.data.generator.domain.User;
import com.data.generator.repository.UserRepository;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RandomDataService dataService;

    @Value("${com.data.generator.numberOfUsers}")
    private int numberOfUsers;

    @Value("${com.data.generator.numberOfUsers.batch-size}")
    private int batchSize;

    @Autowired
    public UserService(UserRepository userRepository, RandomDataService dataService) {
        this.userRepository = userRepository;
        this.dataService = dataService;
    }

    public void uploadUsers() {
        for (int i = 0; i < numberOfUsers; i += batchSize) {
            List<User> users = getUsersForBatch(batchSize);
            userRepository.uploadUsers(users);
            if (i % 100_000 == 0) {
                System.out.printf("%s users processed...\n", i);
            }
        }
    }

    private List<User> getUsersForBatch(int batchSize) {
        return IntStream.range(0, batchSize)
                .mapToObj(i -> User.from(
                        dataService.generateName(),
                        dataService.generateAge(),
                        dataService.generateAddress()
                ))
                .collect(Collectors.toList());
    }
}
