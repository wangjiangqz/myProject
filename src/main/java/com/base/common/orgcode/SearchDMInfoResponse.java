package com.base.common.orgcode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for searchDMInfoResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="searchDMInfoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://wsserver.codeck.cncait.com/}queryResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "searchDMInfoResponse", propOrder = { "_return" })
public class SearchDMInfoResponse {

	@XmlElement(name = "return")
	protected QueryResult _return;

	/**
	 * Gets the value of the return property.
	 * 
	 * @return possible object is {@link QueryResult }
	 * 
	 */
	public QueryResult getReturn() {
		return _return;
	}

	/**
	 * Sets the value of the return property.
	 * 
	 * @param value
	 *            allowed object is {@link QueryResult }
	 * 
	 */
	public void setReturn(QueryResult value) {
		this._return = value;
	}

}
