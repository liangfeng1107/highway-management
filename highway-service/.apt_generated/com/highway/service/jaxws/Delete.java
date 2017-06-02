
package com.highway.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "delete", namespace = "http://service.highway.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "delete", namespace = "http://service.highway.com/")
public class Delete {

    @XmlElement(name = "user", namespace = "")
    private com.highway.model.User user;

    /**
     * 
     * @return
     *     returns User
     */
    public com.highway.model.User getUser() {
        return this.user;
    }

    /**
     * 
     * @param user
     *     the value for the user property
     */
    public void setUser(com.highway.model.User user) {
        this.user = user;
    }

}
