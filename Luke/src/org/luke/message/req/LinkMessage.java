package org.luke.message.req;

public class LinkMessage extends BaseMessage {
	private String Title;
	
	private String Description;
	
	private String Url;
	
	public String getTitle(){
		return Title;
	}
	
	public void setTitle(String title){
		Title = title;
	}
	
	public String getDescription(){
		return Description;
	}
	
	public void setDescription(String descripton){
		Description = descripton;
	}
	
	public String getUrl(){
		return Url;
	}
	
	public void setUrl(String url){
		Url = url;
	}
}
