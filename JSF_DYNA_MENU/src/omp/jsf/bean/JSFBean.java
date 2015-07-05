package omp.jsf.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="jsfBean")
@SessionScoped
public class JSFBean {

	
	public String gotoPage() {
		return "/pages/jsfPage";
	}
}
