//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.12.15 at 10:53:24 AM CET 
//


package de.sernet.sync.data;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for syncObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="syncObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="syncAttribute" type="{http://www.sernet.de/sync/data}syncAttribute" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="extId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="extObjectType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="icon" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="children" type="{http://www.sernet.de/sync/data}syncObject" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="file" type="{http://www.sernet.de/sync/data}syncFile" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "syncObject", propOrder = {
    "syncAttribute",
    "extId",
    "extObjectType",
    "icon",
    "children",
    "file"
})
public class SyncObject {

    protected List<SyncAttribute> syncAttribute;
    @XmlElement(required = true)
    protected String extId;
    @XmlElement(required = true)
    protected String extObjectType;
    protected String icon;
    protected List<SyncObject> children;
    protected List<SyncFile> file;

    /**
     * Gets the value of the syncAttribute property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the syncAttribute property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSyncAttribute().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SyncAttribute }
     * 
     * 
     */
    public List<SyncAttribute> getSyncAttribute() {
        if (syncAttribute == null) {
            syncAttribute = new ArrayList<SyncAttribute>();
        }
        return this.syncAttribute;
    }

    /**
     * Gets the value of the extId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtId() {
        return extId;
    }

    /**
     * Sets the value of the extId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtId(String value) {
        this.extId = value;
    }

    /**
     * Gets the value of the extObjectType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtObjectType() {
        return extObjectType;
    }

    /**
     * Sets the value of the extObjectType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtObjectType(String value) {
        this.extObjectType = value;
    }

    /**
     * Gets the value of the icon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Sets the value of the icon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIcon(String value) {
        this.icon = value;
    }

    /**
     * Gets the value of the children property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the children property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChildren().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SyncObject }
     * 
     * 
     */
    public List<SyncObject> getChildren() {
        if (children == null) {
            children = new ArrayList<SyncObject>();
        }
        return this.children;
    }

    /**
     * Gets the value of the file property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the file property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFile().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SyncFile }
     * 
     * 
     */
    public List<SyncFile> getFile() {
        if (file == null) {
            file = new ArrayList<SyncFile>();
        }
        return this.file;
    }

}
