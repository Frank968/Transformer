package aeqb.com.beans;

public class TransformerResponse {
	String result;
	String text;
	
	TransformerResponse(String result, String text){
		this.result = result;
		this.text = text;
	}
	
	public String getResult() {
		return result;
	}
	public void setStatus(String result) {
		this.result = result;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}		
	
}
