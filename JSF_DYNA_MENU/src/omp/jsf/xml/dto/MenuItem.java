package omp.jsf.xml.dto;

import javax.inject.Inject;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;


@XmlRootElement(name = "menuItem")
public class MenuItem {

    private String type;
    private String name;
    private String label;
    private String path;
    private String target;
    private String title;
    private Boolean external;
    private ArrayList<MenuItem> childList;
    private MenuItem parent = null;

    public MenuItem() {
    }

    public MenuItem(MenuItem item) {
        super();
        this.type = item.getType();
        this.name = item.getName();
        this.label = item.getLabel();
        this.path = item.getPath();
        this.target = item.getTarget();
        this.title = item.getTitle();
        this.external = item.getExternal();

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

    public boolean hasChildren() {
        boolean childFlag = false;
        if (!(this.childList.isEmpty())) {
            childFlag = true;
        }
        return childFlag;
    }

    @XmlAttribute
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @XmlAttribute
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlAttribute
    public Boolean getExternal() {
        return external;
    }

    public void setExternal(Boolean external) {
        this.external = external;
    }

    // TODO make MenuItem Comparable
    public MenuItem getChildByName(String name) {

        MenuItem result = null;
        for (MenuItem menu : this.getChildList()) {
            if (menu.getName().equalsIgnoreCase(name)) {
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
        this.type = null;
        this.name = null;
        this.label = null;
        this.path = null;
        this.target = null;
        this.title = null;
        this.external = null;

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((childList == null) ? 0 : childList.hashCode());
        result = prime * result + ((external == null) ? 0 : external.hashCode());
        result = prime * result + ((label == null) ? 0 : label.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((parent == null) ? 0 : parent.hashCode());
        result = prime * result + ((path == null) ? 0 : path.hashCode());
        result = prime * result + ((target == null) ? 0 : target.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "MenuItem [type=" + type + ", name=" + name + ", title=" + title + ", parent=" + parent + "]";
    }

 
}
