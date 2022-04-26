package arpa.home.springpoll.persistence.dataSource;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;


public class PGDataSourceFactoryImpLocalhost implements DataSourceFactory {
	
	
	public PGDataSourceFactoryImpLocalhost() {}
	
	
	@Override
	public PGSimpleDataSource makeSource() {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return buildLocalhostPGDataSource();
	}

	private PGSimpleDataSource buildLocalhostPGDataSource() {
		PGSimpleDataSource pgSource = new PGSimpleDataSource();
		pgSource.setUser("postgres");
		pgSource.setPassword("123Spill+");
		
		return pgSource;
	}



}
