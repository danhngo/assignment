package test.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "price")
public class Price {
	@XmlElement(name = "ticker")
	private String ticker;
	
	@XmlElement(name = "dateClose")
	private List<DatePrice> dateClose;

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public List<DatePrice> getDateClose() {
		return dateClose;
	}

	public void setDateClose(List<DatePrice> dateClose) {
		this.dateClose = dateClose;
	}	
	
	
}
