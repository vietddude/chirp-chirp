package viet.io.chirpchirp.config;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Configuration class for setting up Flyway database migrations.
 */
@Slf4j
@Configuration
public class FlywayConfig {

    @Value("${spring.flyway.locations}")
    private String[] flywayLocation;

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.username}")
    private String dataSourceUsername;

    @Value("${spring.datasource.password}")
    private String dataSourcePassword;

    /**
     * Configures and returns a Flyway instance for database migrations.
     *
     * @return a configured Flyway instance
     */
    @Bean
    public Flyway flyway() {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource())
                .locations(flywayLocation)
                .baselineOnMigrate(true)
                .baselineVersion("0")
                .load();

        // Migrate the database
        flyway.migrate();
        log.debug("Flyway migration completed");
        return flyway;
    }

    /**
     * Configures and returns a DataSource instance.
     *
     * @return a configured DataSource instance
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(dataSourceUrl);
        dataSource.setUsername(dataSourceUsername);
        dataSource.setPassword(dataSourcePassword);
        return dataSource;
    }
}