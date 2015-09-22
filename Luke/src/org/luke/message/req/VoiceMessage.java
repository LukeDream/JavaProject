package org.luke.message.req;

public class VoiceMessage extends BaseMessage {
	private String MediaId;
	
	private String Format;
	
	public String getMediaId(){
		return MediaId;
	}
	
	public void setMediaId(String mediaId){
		MediaId = mediaId;
	}
	
	public String getFormat(){
		return Format;
	}
	public void  setFromat(String format){
		Format = format;
	}
}
