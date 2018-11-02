package auth.DO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="login")
public class UserLoginDO {
	
	@Id
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String hashedPassword;
	
	@Column(name="enabled")
	private boolean enabled;
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getHashedPassword() {
		return this.hashedPassword;
	}
	
	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
	
	public boolean getEnabled() {
		return this.enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
