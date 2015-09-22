package org.luke.util;

import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.luke.message.resp.TextMessage;
import org.luke.message.resp.Article;
import org.luke.message.resp.MusicMessage;
import org.luke.message.resp.NewsMessage;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;





public class MessageUtil {
//	返回类型：文本
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";
	
//	返回类型：音乐
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
//	返回类型：图文
	public static final String RESP_MESSAGE_TYPE_NEWS ="news";
//	请求类型：文本
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";
//	请求类型：图片
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
//	请求类型：连接
	public static final String REQ_MESSAGE_TYPE_LINK = "link";
//	请求类型：地理位置
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
//	请求类型：音频
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
//	请求类型：推送
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";
//	请求类型：订阅
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
//  事件类型：unsubscribe（取消订阅）
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
//	事件类型：CLICK(自定义菜单点击事件）
	public static final String EVENT_TYPE_CLICK = "CLICK";
	
//	解析微信发来的请求
	
	
	public static String getMainMenu() {  
	    StringBuffer buffer = new StringBuffer();  
	    buffer.append("您好，欢迎来到LukeDream，请回复数字选择服务：").append("\n\n");  
	    buffer.append("1  天气预报").append("\n");  
	    buffer.append("2  公交查询").append("\n");  
	    buffer.append("3  周边搜索").append("\n");  
	    buffer.append("4  歌曲点播").append("\n");  
	    buffer.append("5  经典游戏").append("\n");  
	    buffer.append("6  美女电台").append("\n");  
	    buffer.append("7  人脸识别").append("\n");  
	    buffer.append("8  聊天唠嗑").append("\n\n");  
	    buffer.append("<a href=\"http://www.baidu.com\">百度</a>").append("\n");
	    buffer.append("回复“?”显示此帮助菜单");  
	    
	    return buffer.toString();  
	}  
	
	@SuppressWarnings("unchecked")
			public static Map<String, String>  parseXml(HttpServletRequest request) throws  Exception{
				Map<String,String> map = new HashMap<String,String>();
				
				InputStream inputStream = request.getInputStream();
				
				SAXReader reader = new SAXReader();
				
				Document document = reader.read(inputStream);
				
				Element root = document.getRootElement();
				
				List<Element> elementList = root.elements();
				
				for(Element e : elementList)
					map.put(e.getName(),e.getText());
				
				inputStream.close();
				inputStream = null;
				
				return map;
			}
	
	public static String textMessageToXml(TextMessage textMessage){
		
		xstream.alias("xml",textMessage.getClass());
		return xstream.toXML(textMessage);
	}
	
	public static String musicMessageToXml(MusicMessage musicMessage){
		
		xstream.alias("xml",musicMessage.getClass());
		return xstream.toXML(musicMessage);
	}
	
	public static String newsMessageToXml(NewsMessage newsMessage){
		
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(newsMessage);
	}
	
	
		    private static XStream xstream = new XStream(new XppDriver() {  
		        public HierarchicalStreamWriter createWriter(Writer out) {  
		            return new PrettyPrintWriter(out) {  
		                // 对所有xml节点的转换都增加CDATA标记  
		                boolean cdata = true;  
		  
		                @SuppressWarnings("unchecked")  
		                public void startNode(String name, Class clazz) {  
		                    super.startNode(name, clazz);  
		                }  
		  
		                protected void writeText(QuickWriter writer, String text) {  
		                    if (cdata) {  
		                        writer.write("<![CDATA[");  
		                        writer.write(text);  
		                        writer.write("]]>");  
		                    } else {  
		                        writer.write(text);  
		                    }  
		                }  
		            };  
		        }  
		    });  
		}  