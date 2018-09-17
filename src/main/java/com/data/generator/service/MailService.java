package com.data.generator.service;

import com.data.generator.domain.Mail;
import com.data.generator.domain.User;
import com.data.generator.repository.MailRepository;
import com.data.generator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class MailService {

    private final UserRepository userRepository;
    private final MailRepository mailRepository;
    private final RandomDataService dataService;

    @Value("${com.data.generator.numberOfMails}")
    private int numberOfMails;

    @Value("${com.data.generator.numberOfMails.batch-size}")
    private int batchSize;

    @Autowired
    public MailService(UserRepository userRepository, MailRepository mailRepository, RandomDataService dataService) {
        this.userRepository = userRepository;
        this.mailRepository = mailRepository;
        this.dataService = dataService;
    }

    public void uploadMails() {
        List<Long> userIds = userRepository.fetchUserIds();

        for (int i = 0; i < numberOfMails; i += batchSize) {
            List<Mail> mails = getMailsForBatch(userIds, batchSize);
            mailRepository.uploadMails(mails);
            if (i % 100_000 == 0) {
                System.out.printf("%s mails processed...\n", i);
            }
        }
    }

    private List<Mail> getMailsForBatch(List<Long> userIds, int batchSize) {
        return IntStream.range(0, batchSize)
                .mapToObj(i -> Mail.from(
                        dataService.generateUserId(userIds),
                        dataService.generateUserId(userIds),
                        dataService.generateMailTopic(),
                        dataService.generateMailBody(),
                        dataService.generateDate(),
                        dataService.generateDate(),
                        dataService.generateSentStatus()
                ))
                .collect(Collectors.toList());
    }
}
