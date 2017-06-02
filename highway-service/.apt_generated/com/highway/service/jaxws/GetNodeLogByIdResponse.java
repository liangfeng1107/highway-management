
package com.highway.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getNodeLogByIdResponse", namespace = "http://service.highway.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getNodeLogByIdResponse", namespace = "http://service.highway.com/")
public class GetNodeLogByIdResponse {

    @XmlElement(name = "return", namespace = "")
    private com.highway.model.NodeLog _return;

    /**
     * 
     * @return
     *     returns NodeLog
     */
    public com.highway.model.NodeLog getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(com.highway.model.NodeLog _return) {
        this._return = _return;
    }

}
