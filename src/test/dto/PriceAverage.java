package test.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "200dma")
public class PriceAverage {
	@XmlElement(name = "ticker")
	private String ticker;
	
	@XmlElement(name = "avg")
	private Double avg;

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public Double getAvg() {
		return avg;
	}

	public void setAvg(Double avg) {
		this.avg = avg;
	}
	
	
}
