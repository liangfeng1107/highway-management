
package com.highway.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getUser", namespace = "http://service.highway.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getUser", namespace = "http://service.highway.com/")
public class GetUser {

    @XmlElement(name = "username", namespace = "")
    private String username;

    /**
     * 
     * @return
     *     returns String
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * 
     * @param username
     *     the value for the username property
     */
    public void setUsername(String username) {
        this.username = username;
    }

}
