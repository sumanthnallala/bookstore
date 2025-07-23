package com.sumanth.bookstore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.sumanth.bookstore.repository",
    entityManagerFactoryRef = "bookStoreEntityManagerFactory",
    transactionManagerRef = "bookStoreTransactionManager"
)
public class BookStoreDatabaseConfig {

}
