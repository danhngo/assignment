package test.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tickerDate")
public class TickerDate {
	@XmlElement(name = "tickers")
	private List<String> tickers;
	
	@XmlElement(name = "startDate")
	private String startDate;

	public List<String> getTickers() {
		return tickers;
	}
	public void setTickers(List<String> tickers) {
		this.tickers = tickers;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	
}
