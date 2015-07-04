package omp.jsf.bean;

import java.io.InputStreamReader;
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
	
	private void loadXML() throws JAXBException {
		System.out.println("load xml called");
		ExternalContext externalContext =  FacesContext.getCurrentInstance().getExternalContext();
		JAXBContext jaxbContext = JAXBContext.newInstance(Menu.class);
		jaxbContext.createMarshaller();
		
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Menu menu = (Menu)unmarshaller.unmarshal(new InputStreamReader(externalContext.getResourceAsStream("/WEB-INF/resources/menu.xml")));
		
		this.menuList = menu.getChildList();
		System.out.println("Menu list is "+this.menuList.toString());
	}
	
}
