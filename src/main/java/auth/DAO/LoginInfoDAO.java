package auth.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import auth.DO.UserLoginDO;

@Component
public class LoginInfoDAO {
	
	@Autowired
	private Session session;
	
	public void update(UserLoginDO user) {
		session.getTransaction().begin();
		session.saveOrUpdate(user);
		session.getTransaction().commit();
	}
	
	public void delete(UserLoginDO user) {
		session.getTransaction().begin();
		session.delete(user);
		session.getTransaction().commit();
	}
	
	private UserLoginDO getUserByUsername(String username) {
		String hql = "from UserLoginDO u WHERE u.username = '" + username + "'";
		Query<UserLoginDO> query = session.createQuery(hql);
		List<UserLoginDO> result = query.list();
		return result.get(0);
	}
	
	public String getPasswordByUsername(String username) {
		UserLoginDO user = getUserByUsername(username);
		return user.getHashedPassword();
	}
	
	public boolean isUserEnabled(String username) {
		UserLoginDO user = getUserByUsername(username);
		return user.getEnabled();
	}
}
