package omp.jsf.bean;

import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;

import omp.jsf.xml.dto.Menu;
import omp.jsf.xml.dto.MenuItem;

@ManagedBean(name="menuBean")
@SessionScoped
public class MenuBean {

	private List<MenuItem> menuList;
	private String beanMethod;
	private String beanAction;
	
	public List<MenuItem> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<MenuItem> menuList) {
		this.menuList = menuList;
	}

	public MenuBean() {
		System.out.println("MENU BEAN CALLED");
	}
	
	@PostConstruct
	public void init() {
		System.out.println("MENU BEAN CALLED");
		try {
			loadXML();
		} catch(Exception ex) {
			ex.setStackTrace(null);
		}
	}
	private String callMethod(String methodName, String beanName) {
		Object obj = getBeanObject(beanName);
		String returnS = "";
		try {
			if(obj!=null) {
				Method method = obj.getClass().getDeclaredMethod(methodName,null);
				returnS  = (String)method.invoke(obj);
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnS;
	}
	
	private Object getBeanObject(String beanName) {
		Object obj = findBean(beanName);
		return obj;
	}
	
	public static <T> T findBean(String beanName) {
	    FacesContext context = FacesContext.getCurrentInstance();
	    return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
	}
	public String triggerAction() {
		System.out.println("Trigger action called");
		String forwardName = callMethod(this.getBeanMethod(), this.getBeanAction());
		//filterMenuList(this.getSubMenuParentId());
		System.out.println("Forward is "+forwardName);
		return forwardName;
	}
	private void loadXML() throws JAXBException {
		System.out.println("load xml called");
		ExternalContext externalContext =  FacesContext.getCurrentInstance().getExternalContext();
		JAXBContext jaxbContext = JAXBContext.newInstance(Menu.class);
		jaxbContext.createMarshaller();
		
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Menu menu = (Menu)unmarshaller.unmarshal(new InputStreamReader(externalContext.getResourceAsStream("/WEB-INF/resources/menu.xml")));
		
		this.menuList = menu.getChildList();
		System.out.println("Menu list is "+this.menuList.toString());
		for(MenuItem menuItem:menuList) {
			System.out.println(menuItem.getLabel()+""+menuItem.getId());
			if(menuItem.getChildList()!=null) {
				for(MenuItem childMenuItem:menuItem.getChildList()) {
					System.out.println("Chile Menu Item is "+childMenuItem.getLabel()+""+childMenuItem.getId());
				}
			}
		}
	}
	
	public String callAction() {
		return "";
	}

	public String getBeanMethod() {
		return beanMethod;
	}

	public void setBeanMethod(String beanMethod) {
		this.beanMethod = beanMethod;
	}

	public String getBeanAction() {
		return beanAction;
	}

	public void setBeanAction(String beanAction) {
		this.beanAction = beanAction;
	}
	
}
