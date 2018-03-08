package org.cpm.zwl.config;

import java.io.IOException;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.cpm.zwl.commons.constrants.CategoryName;
import org.cpm.zwl.commons.entities.Account;
import org.cpm.zwl.commons.entities.RacDatasourceInfo;
import org.cpm.zwl.commons.log.factories.ZwlLogFactory;
import org.cpm.zwl.commons.utils.CompactPasswordUtil;
import org.cpm.zwl.commons.utils.RacDatasourceUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @PropertySource("classpath:application.properties")
@Configuration
public class DbConfig {
  private final Logger logger = ZwlLogFactory.getLogger(DbConfig.class);

  @Value("${compact.password}")
  private boolean compactPassword;

  @Value("${spring.datasource.url}")
  private String url;

  @Value("${spring.datasource.username}")
  private String username;

  @Value("${spring.datasource.password}")
  private String password;

  @Value("${spring.datasource.ons.configuration}")
  private String onsConfiguration;

  @Bean
  public DataSource dataSource() throws IOException, SQLException {
    String dbUsername;
    String dbPassword;

    logger.info("compactPassword:" + compactPassword);

    if (compactPassword) {
      Account account = CompactPasswordUtil.getUsernameAndPassword(CategoryName.ZWF);
      dbUsername = account.getUsername();
      dbPassword = account.getPassword();
    } else {
      dbUsername = username;
      dbPassword = password;
    }

    RacDatasourceInfo info =
        RacDatasourceInfo.getDefault(onsConfiguration, url, dbUsername, dbPassword);
    return RacDatasourceUtil.getRacDatasource(info);

  }
}
