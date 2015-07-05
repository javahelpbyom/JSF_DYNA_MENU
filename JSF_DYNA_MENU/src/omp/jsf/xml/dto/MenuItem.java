package omp.jsf.xml.dto;

import javax.inject.Inject;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;


@XmlRootElement(name = "menuItem")
public class MenuItem {

    private String id;
    private String label;
    private String path;
    private String target;
    private String actionMethod;
    private String beanName;
    private ArrayList<MenuItem> childList;
    private MenuItem parent = null;
    private boolean isHasChildren;

	public MenuItem() {
    }

    public MenuItem(MenuItem item) {
        super();
        this.label = item.getLabel();
        this.path = item.getPath();
        this.target = item.getTarget();

        if (null != item.getChildList()) {
            this.childList = new ArrayList<MenuItem>();
            for (MenuItem source : item.getChildList()) {
                this.childList.add(new MenuItem(source));
            }
        }
        if (null != item.getParent()) {
            this.parent = new MenuItem(parent);
        }

    }

    @XmlElementWrapper(name = "menuList")
    @XmlElement(name = "menuItem")
    public ArrayList<MenuItem> getChildList() {
        if (null == childList) {
            childList = new ArrayList<MenuItem>();
        }
        return childList;
    }

    public void setChildList(ArrayList<MenuItem> childList) {
        this.childList = childList;
    }

    public boolean getIsHasChildren() {
    	return isHasChildren();
    }
    
    public boolean isHasChildren() {
        isHasChildren = false;
        if (!(this.childList.isEmpty())) {
        	isHasChildren = true;
        }
        return isHasChildren;
    }

    @XmlAttribute
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @XmlAttribute
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @XmlAttribute
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }


    // TODO make MenuItem Comparable
    public MenuItem getChildByName(String id) {

        MenuItem result = null;
        for (MenuItem menu : this.getChildList()) {
            if (menu.getId().equalsIgnoreCase(id)) {
                result = menu;
            }
        }

        return result;

    }

    public MenuItem getParent() {
        return parent;
    }

    public void setParent(MenuItem parent) {
        this.parent = parent;
    }

    public void empty() {
        this.label = null;
        this.path = null;
        this.target = null;

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((childList == null) ? 0 : childList.hashCode());
        result = prime * result + ((label == null) ? 0 : label.hashCode());
        result = prime * result + ((parent == null) ? 0 : parent.hashCode());
        result = prime * result + ((path == null) ? 0 : path.hashCode());
        result = prime * result + ((target == null) ? 0 : target.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "MenuItem [actionMethod=" + actionMethod + ", id=" + id + ", label=" + label + ", parent=" + parent + "]";
    }
    @XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@XmlAttribute
	public String getActionMethod() {
		return actionMethod;
	}

	public void setActionMethod(String actionMethod) {
		this.actionMethod = actionMethod;
	}

	public void setHasChildren(boolean isHasChildren) {
		this.isHasChildren = isHasChildren;
	}
	@XmlAttribute
	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

 
}
