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
//	�������ͣ��ı�
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";
	
//	�������ͣ�����
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
//	�������ͣ�ͼ��
	public static final String RESP_MESSAGE_TYPE_NEWS ="news";
//	�������ͣ��ı�
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";
//	�������ͣ�ͼƬ
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
//	�������ͣ�����
	public static final String REQ_MESSAGE_TYPE_LINK = "link";
//	�������ͣ�����λ��
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
//	�������ͣ���Ƶ
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
//	�������ͣ�����
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";
//	�������ͣ�����
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
//  �¼����ͣ�unsubscribe��ȡ�����ģ�
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
//	�¼����ͣ�CLICK(�Զ���˵�����¼���
	public static final String EVENT_TYPE_CLICK = "CLICK";
	
//	����΢�ŷ���������
	
	
	public static String getMainMenu() {  
	    StringBuffer buffer = new StringBuffer();  
	    buffer.append("���ã���ӭ����LukeDream����ظ�����ѡ�����").append("\n\n");  
	    buffer.append("1  ����Ԥ��").append("\n");  
	    buffer.append("2  ������ѯ").append("\n");  
	    buffer.append("3  �ܱ�����").append("\n");  
	    buffer.append("4  �����㲥").append("\n");  
	    buffer.append("5  ������Ϸ").append("\n");  
	    buffer.append("6  ��Ů��̨").append("\n");  
	    buffer.append("7  ����ʶ��").append("\n");  
	    buffer.append("8  �������").append("\n\n");  
	    buffer.append("<a href=\"http://www.baidu.com\">�ٶ�</a>").append("\n");
	    buffer.append("�ظ���?����ʾ�˰����˵�");  
	    
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
		                // ������xml�ڵ��ת��������CDATA���  
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