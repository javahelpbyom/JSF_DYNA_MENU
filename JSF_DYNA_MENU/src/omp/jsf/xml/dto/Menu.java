package omp.jsf.xml.dto;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@XmlRootElement(namespace = "omp.jhp.menu")
public class Menu {

    private ArrayList<MenuItem> childList;
    private String version;

    public Menu() {
    }

    public Menu(Menu srcMenu) {

        this.version = srcMenu.getVersion();
        List<MenuItem> list = srcMenu.getChildList();
        if (null == list) {
            return;
        }

        Iterator<MenuItem> menuIterator = list.iterator();
        childList = new ArrayList<MenuItem>();
        while (menuIterator.hasNext()) {
            childList.add(new MenuItem(menuIterator.next()));
        }

    }

    @XmlElementWrapper(name = "menuList")
    @XmlElement(name = "menuItem")
    public ArrayList<MenuItem> getChildList() {
        return childList;
    }

    public void setChildList(ArrayList<MenuItem> childList) {
        this.childList = childList;
    }

    @XmlAttribute
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}