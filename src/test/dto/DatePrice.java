package test.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "price")
public class DatePrice {
	@XmlElement(name = "dateClose")
	private String dateClose;
	@XmlElement(name = "dateClose")
	private Double price;
	
	public String getDateClose() {
		return dateClose;
	}
	public void setDateClose(String dateClose) {
		this.dateClose = dateClose;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}	
}
