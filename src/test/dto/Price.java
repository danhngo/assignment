package test.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "price")
public class Price {
	@XmlElement(name = "ticker")
	private String ticker;
	
	@XmlElement(name = "dateCloses")
	private List<DatePrice> dateCloses;

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public List<DatePrice> getDateCloses() {
		return dateCloses;
	}

	public void setDateCloses(List<DatePrice> dateCloses) {
		this.dateCloses = dateCloses;
	}	
	
	
}
