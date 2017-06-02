
package com.highway.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getAreaByIdResponse", namespace = "http://service.highway.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAreaByIdResponse", namespace = "http://service.highway.com/")
public class GetAreaByIdResponse {

    @XmlElement(name = "return", namespace = "")
    private com.highway.model.Area _return;

    /**
     * 
     * @return
     *     returns Area
     */
    public com.highway.model.Area getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(com.highway.model.Area _return) {
        this._return = _return;
    }

}
