
package com.highway.service.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getNodeLogResponse", namespace = "http://service.highway.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getNodeLogResponse", namespace = "http://service.highway.com/")
public class GetNodeLogResponse {

    @XmlElement(name = "return", namespace = "")
    private List<com.highway.model.NodeLog> _return;

    /**
     * 
     * @return
     *     returns List<NodeLog>
     */
    public List<com.highway.model.NodeLog> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<com.highway.model.NodeLog> _return) {
        this._return = _return;
    }

}
