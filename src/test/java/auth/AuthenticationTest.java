package auth;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import auth.DAO.LoginInfoDAO;
import auth.DO.UserLoginDO;
import config.HibernateConfig;

@RunWith(SpringJUnit4ClassRunner.class)	
@ContextConfiguration(classes = {HibernateConfig.class, LoginInfoDAO.class})
public class AuthenticationTest {
	
	@Autowired
	private Session session;
	
	@Autowired
	private LoginInfoDAO dao;
	
	private UserLoginDO user1;
	
	private UUID uuid = UUID.randomUUID();
	
	private String hash = new BCryptPasswordEncoder().encode(uuid.toString());
	
	@Before
	public void setup() {
		this.user1 = new UserLoginDO();
		user1.setEnabled(false);
		user1.setHashedPassword(hash);
		user1.setUsername("foo");
	}
	
	@Test
	public void authenticationTest1() {
		try{
			dao.update(user1);
			String actual = dao.getPasswordByUsername("foo");
			assertEquals(hash, actual);
			dao.delete(user1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
