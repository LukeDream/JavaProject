package org.luke.message.resp;

public class MusicMessage extends BaseMessage {
	private Music Music;
	
	private Music getMusic(){
		return Music;
	}
	
	private void setMusic(Music music){
		Music = music;
	}
}
