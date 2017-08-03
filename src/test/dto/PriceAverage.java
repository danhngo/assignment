package test.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "200dma")
public class PriceAverage {
	
	public PriceAverage(String ticker,Double avg) {
		this.ticker = ticker;
		this.avg = avg;
		if (avg == null) {
			this.note = "Symbol is not found";
		}
	}
	@XmlElement(name = "ticker")
	private String ticker;
	
	@XmlElement(name = "avg")
	private Double avg;
	
	@XmlElement(name = "note")
	private String note;

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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
}
