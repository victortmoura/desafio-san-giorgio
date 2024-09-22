package com.victor.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class AwsSqsConfig {

    @Value("${aws.region}")
    private String region;

    @Value("${aws.accessKeyId}")
    private String ACCESS_KEY_ID;

    @Value("${aws.secretKey}")
    private String SECRET_ACCESS_KEY;

    @Bean
    public SqsClient sqsClient() {

        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(ACCESS_KEY_ID, SECRET_ACCESS_KEY);

        return SqsClient.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();
    }
}
