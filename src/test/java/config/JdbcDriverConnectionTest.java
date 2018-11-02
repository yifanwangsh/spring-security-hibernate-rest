package config;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class JdbcDriverConnectionTest {
	
	private DriverManagerDataSource ds;
	
	@Before
	public void setup() {
		this.ds = new DriverManagerDataSource();
		ds.setDriverClassName("org.postgresql.Driver");
		ds.setUrl("jdbc:postgresql://localhost:5432/proj");
		ds.setSchema("auth");
		ds.setUsername("postgres");
		ds.setPassword("admin");
	}
	
	@Test
	public void JdbcConnectionTest() {
		try {
			assertNotNull(ds.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void JdbcSQLQueryTest() {
		try {
			Connection conn = ds.getConnection();
			
			Statement stmt = conn.createStatement();
			String query = "select * from auth.login";
			ResultSet rs = stmt.executeQuery(query);
			
			assertNotNull(rs);
			
			while(rs.next()){
		        String userId = rs.getString("username");
		        String password = rs.getString("password");

		        System.out.print("ID: " + userId);
		        System.out.print(", Password: " + password);
		    }
			
			rs.close();
			stmt.close();
			conn.close();
			System.out.println(new BCryptPasswordEncoder().encode("admin"));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
