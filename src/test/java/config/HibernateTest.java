package config;

import static org.junit.Assert.assertEquals;

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

@RunWith(SpringJUnit4ClassRunner.class)	
@ContextConfiguration(classes = {HibernateConfig.class, LoginInfoDAO.class})
public class HibernateTest {
	
	@Autowired
	private Session session;
	
	@Autowired
	private LoginInfoDAO dao;
	
	private UserLoginDO user1;
	
	@Before
	public void setup() {
		this.user1 = new UserLoginDO();
		user1.setEnabled(false);
		String hash = new BCryptPasswordEncoder().encode("foo");
		user1.setHashedPassword(hash);
		user1.setUsername("foo");
	}
	
	@Test
	public void hibernateTest1() {
		try{
			dao.update(user1);
			UserLoginDO temp = dao.getUserByUsername("foo");
			assertEquals("foo", temp.getUsername());
			dao.delete(user1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
