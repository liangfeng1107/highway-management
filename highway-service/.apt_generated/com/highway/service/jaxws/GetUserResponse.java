
package com.highway.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getUserResponse", namespace = "http://service.highway.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getUserResponse", namespace = "http://service.highway.com/")
public class GetUserResponse {

    @XmlElement(name = "return", namespace = "")
    private com.highway.model.User _return;

    /**
     * 
     * @return
     *     returns User
     */
    public com.highway.model.User getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(com.highway.model.User _return) {
        this._return = _return;
    }

}
