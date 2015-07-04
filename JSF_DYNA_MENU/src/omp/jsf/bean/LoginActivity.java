package omp.jsf.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

@ManagedBean(name="login")
@SessionScoped
public class LoginActivity {

	private String userId;
	private String password;
	
	@ManagedProperty(value="#{menuBean}")
	private MenuBean menuBean;
	
	public LoginActivity() {
		System.out.println("Constructor Invoked");
	}
	
	public void setMenuBean(MenuBean menuBean) {
		this.menuBean = menuBean;
	}

	public String doLogin() {
		menuBean.getMenuList();
		return "welcome";
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
