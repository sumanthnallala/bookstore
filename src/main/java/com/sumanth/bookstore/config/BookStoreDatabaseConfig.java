package com.sumanth.bookstore.config;

import jakarta.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.sumanth.bookstore.repository",
    entityManagerFactoryRef = "bookstoreEntityManagerFactory",
    transactionManagerRef = "bookstoreTransactionManager"
)
public class BookStoreDatabaseConfig {

  @Primary
  @Bean(name = "bookstoreDataSource")
  @ConfigurationProperties(prefix = "spring.datasource.bookstore")
  public DataSource bookstoreDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = "bookstoreEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean bookstoreEntityManagerFactory(
      EntityManagerFactoryBuilder builder,
      @Qualifier("bookstoreDataSource") DataSource dataSource) {
    Map<String, String> jpaProperties = new HashMap<>();
    jpaProperties.put("hibernate.hbm2ddl.auto", "update");
    jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
    jpaProperties.put("hibernate.show_sql", "true");
    return builder
        .dataSource(dataSource)
        .packages("com.sumanth.bookstore.entity")
        .persistenceUnit("bookstore")
        .properties(jpaProperties)
        .build();
  }

  @Primary
  @Bean(name = "bookstoreTransactionManager")
  public PlatformTransactionManager bookstoreTransactionManager(
      @Qualifier("bookstoreEntityManagerFactory") EntityManagerFactory emf) {
    return new JpaTransactionManager(emf);
  }
}
