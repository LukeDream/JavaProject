package org.luke.main;

import org.luke.message.push.Filter;
import org.luke.message.push.Message;
import org.luke.message.push.Mpnews;
import org.luke.message.push.NewsMessage;
import org.luke.message.push.Text;
import org.luke.message.push.TextMessage;
import org.luke.pojo.AccessToken;
import org.luke.pojo.Menu;
import org.luke.util.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

public class MaterialPush {
	
	 private static Logger log = LoggerFactory.getLogger(MaterialPush.class);  
				
			public static void main(String[] args) {
				
				
				String appId = "wx1daefe9c0da399a8";
				String appSecret = "136a5ca184a6cec33c24514210c8cd0d";
				
				AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
				if (0 == pushTitle(getTextMessage(),at.getToken() )) {
					
					System.out.println("���ͳɹ�");
				}
			
				
			}
			   
			   public static String  push_url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
			   public static int pushTitle(Message message, String accessToken) {  
			        int result = 0;  
			      
			        // ƴװ�����˵���url  
			        String url =push_url.replace("ACCESS_TOKEN", accessToken);  
			        // ���˵�����ת����json�ַ���  
			        String jsonMessage = JSONObject.fromObject(message).toString();  
			        // ���ýӿڴ����˵�  
			        
			        System.out.println(jsonMessage);
			        JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", jsonMessage);  
			      
			        if (null != jsonObject) {  
			            if (0 != jsonObject.getInt("errcode")) {  
			                result = jsonObject.getInt("errcode");  
			              log.error("����ͼ��ʧ�� errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
			              System.out.println("ʧ��");
			            }  
			        }  
			      
			        return result;  
			    }  
			
			   	public static NewsMessage getNewsMessage(){
				
				Filter filter = new Filter();
				filter.setIs_to_all(false);
				filter.setGroup_id("105");
				
				Mpnews mpnews = new Mpnews();
				mpnews.setMedia_id("hNsi109YW7d_XQRt7ozU2Ee52RZmmCntMj7vnZJsauFf5okHZhzWll5IXgvuzHEx");
				
				NewsMessage newsMessage = new NewsMessage();
				newsMessage.setFilter(filter);
				newsMessage.setMpnews(mpnews);
				newsMessage.setMsgtype("mpnews");
				
				return newsMessage;
			}
			   	
				public static TextMessage getTextMessage(){
					
					Filter filter = new Filter();
					filter.setIs_to_all(false);
					filter.setGroup_id("105");
					
					Text text = new Text();
					text.setContent("不要回复，接受信息的代码还没写 ");
					
					TextMessage textMessage = new TextMessage();
					textMessage.setFilter(filter);
					textMessage.setMsgtype("text");
					textMessage.setText(text);
					
				
					
					return textMessage;
				}
}
