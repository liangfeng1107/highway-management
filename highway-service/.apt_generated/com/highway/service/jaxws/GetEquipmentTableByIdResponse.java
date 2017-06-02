
package com.highway.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getEquipmentTableByIdResponse", namespace = "http://service.highway.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getEquipmentTableByIdResponse", namespace = "http://service.highway.com/")
public class GetEquipmentTableByIdResponse {

    @XmlElement(name = "return", namespace = "")
    private com.highway.model.EquipmentTable _return;

    /**
     * 
     * @return
     *     returns EquipmentTable
     */
    public com.highway.model.EquipmentTable getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(com.highway.model.EquipmentTable _return) {
        this._return = _return;
    }

}
