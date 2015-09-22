package org.luke.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.Proxy.Type;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.luke.group.BatchChangGroup;
import org.luke.group.ChangGroup;
import org.luke.group.CreateGroup;
import org.luke.group.Group;
import org.luke.material.Article;
import org.luke.material.PicTextMessage;
import org.luke.pojo.AccessToken;
import org.luke.pojo.Button;
import org.luke.pojo.CommonButton;
import org.luke.pojo.ComplexButton;
import org.luke.pojo.Menu;
import org.luke.pojo.ViewButton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class WeixinUtil {
   private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);  
    
    /** 
     * ����https���󲢻�ȡ��� 
     *  
     * @param requestUrl �����ַ 
     * @param requestMethod ����ʽ��GET��POST�� 
     * @param outputStr �ύ������ 
     * @return JSONObject(ͨ��JSONObject.get(key)�ķ�ʽ��ȡjson���������ֵ) 
     */  
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {  
        JSONObject jsonObject = null;  
        StringBuffer buffer = new StringBuffer();  
        try {  
            // ����SSLContext���󣬲�ʹ������ָ�������ι�������ʼ��  
            TrustManager[] tm = { new MyX509TrustManager() };  
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");  
            sslContext.init(null, tm, new java.security.SecureRandom());  
            // ������SSLContext�����еõ�SSLSocketFactory����  
            SSLSocketFactory ssf = sslContext.getSocketFactory();  
  
            URL url = new URL(requestUrl);  
            Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress("proxy6.taikanglife.com", 8080));
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection(proxy);  
          httpUrlConn.setSSLSocketFactory(ssf);  
  
            httpUrlConn.setDoOutput(true);  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setUseCaches(false);  
            // ��������ʽ��GET/POST��  
            httpUrlConn.setRequestMethod(requestMethod);
  
            if ("GET".equalsIgnoreCase(requestMethod))  
            	
               httpUrlConn.connect();  
  
            // ����������Ҫ�ύʱ  
            if (null != outputStr) {  
                OutputStream outputStream = httpUrlConn.getOutputStream();  
                // ע������ʽ����ֹ��������  
                outputStream.write(outputStr.getBytes("UTF-8"));  
                outputStream.close();  
            }  
  
            // �����ص�������ת�����ַ���  
            InputStream inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
  
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            // �ͷ���Դ  
            inputStream.close();  
            inputStream = null;  
            httpUrlConn.disconnect();  
            jsonObject = JSONObject.fromObject(buffer.toString()); 
        } catch (ConnectException ce) {  
           log.error("Weixin server connection timed out.",ce);  
        	ce.printStackTrace();
        } catch (Exception e) {  
           log.error("https request error:{}", e);  
        	e.printStackTrace();
        }  
        System.out.println("11111s");
        return jsonObject;  
    } 
	

	
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";  
    
    /** 
     * ��ȡaccess_token 
     *  
     * @param appid ƾ֤ 
     * @param appsecret ��Կ 
     * @return 
     */  
    public static AccessToken getAccessToken(String appid, String appsecret) {  
        AccessToken accessToken = null;  
      
        String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);  
         JSONObject jsonObject = httpRequest(requestUrl, "GET", null);  
        // �������ɹ�  
        if (null != jsonObject) {  
            try {  
                accessToken = new AccessToken();  
                accessToken.setToken(jsonObject.getString("access_token"));  
                accessToken.setExpiresIn(jsonObject.getInt("expires_in"));  
            } catch (JSONException e) {  
                accessToken = null;  
                // ��ȡtokenʧ��  
                log.error("��ȡtokenʧ�� errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
            }  
        }  
        return accessToken;  
    }  

    public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";  
//    public static String pictextmessage_create_url = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN";																
    public static String pictextmessage_create_url = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";																
	public static String batchchange_group_url = "https://api.weixin.qq.com/cgi-bin/groups/members/batchupdate?access_token=ACCESS_TOKEN";
    public static String create_group_url = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";
    
    /** 
     * �����˵� 
     *  
     * @param menu �˵�ʵ�� 
     * @param accessToken ��Ч��access_token 
     * @return 0��ʾ�ɹ�������ֵ��ʾʧ�� 
     */  
    public static int createMenu(Menu menu, String accessToken) {  
        int result = 0;  
      
        // ƴװ�����˵���url  
        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);  
        // ���˵�����ת����json�ַ���  
        String jsonMenu = JSONObject.fromObject(menu).toString();  
        // ���ýӿڴ����˵�  
        JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);  
      
        if (null != jsonObject) {  
            if (0 != jsonObject.getInt("errcode")) {  
                result = jsonObject.getInt("errcode");  
              log.error("�����˵�ʧ�� errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
              System.out.println("ʧ��");
            }  
        }  
      
        return result;  
    }  
    
    public static int batchchangeGroup(BatchChangGroup cGroup, String accessToken) {  
        int result = 0;  
      
        // ƴװ�����˵���url  
        String url = batchchange_group_url.replace("ACCESS_TOKEN", accessToken);  
        // ���˵�����ת����json�ַ���  
        String jsonGroup = JSONObject.fromObject(cGroup).toString();  
        System.out.println(jsonGroup);
        
        JSONObject jsonObject = httpRequest(url, "POST", jsonGroup);  
      
        if (null != jsonObject) {  
        	System.out.println(jsonObject);
            if (0 != jsonObject.getInt("errcode")) {  
                result = jsonObject.getInt("errcode");  
              log.error("移动分组失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
            }  
        }  
      
        return result;  
    }  
    
    public static int  createGroup(CreateGroup group,String accessToken){
		int groupid = 0;
		
		String url = create_group_url.replace("ACCESS_TOKEN", accessToken);
		String jsonPTMessage = JSONObject.fromObject(group).toString();
		System.out.println(jsonPTMessage);
		JSONObject jsonObject = httpRequest(url, "POST", jsonPTMessage);
		
		
		if(null != jsonObject){
			System.out.println(jsonObject);
			if (null !=jsonObject.get("group")) {
				JSONObject jsonGroup = (JSONObject)jsonObject.get("group");
				System.out.println(jsonGroup);
				groupid = jsonGroup.getInt("id");
				System.out.println(groupid);
			}else{
				if (null !=jsonObject.get("errcode")) {
		              log.error("创建分组失败errcode:{} errmsg:{}", jsonObject.get("errcode"), jsonObject.getString("errmsg"));  
				}
		}
		}
		return groupid;
}
    
    public static String createPicTextMessage(PicTextMessage picTextMessage,String accessToken){
    		String media_id="";
    		
    		String url = pictextmessage_create_url.replace("ACCESS_TOKEN", accessToken);
    		String jsonPTMessage = JSONObject.fromObject(picTextMessage).toString();
    		
    		JSONObject jsonObject = httpRequest(url, "POST", jsonPTMessage);
    		
    		
    		if(null != jsonObject){
    			if (null !=jsonObject.get("media_id")) {
					media_id = jsonObject.getString("media_id");
				}else{
					if (0 !=jsonObject.getInt("errcode")) {
	    	              log.error("����ͼ��ʧ�� errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
					}
    		}
    		}
    		return media_id;
    }
    
 
    
    public static PicTextMessage getPicTextMessage(){
    	String QianNiuPicUrl = "http://mmbiz.qpic.cn/mmbiz/Edjt5cvbzfJbkVq3aF61DViaPPALuEXyt14cmqS3nTFAoXM8JgibeCOWDJ1zBQAeZqBIibyjrPCAWcCAebRz2UibKw/0";
    	Article article = new Article();
    	article.setAuthor("Luke");
    	article.setContent("Love your parents. We are too busy growing up yet we forget that they are already growing old���������һ���Լ��ĸ�ĸ�ɣ�������æ���Լ��ɳ���ȴ��������Ҳ�ڱ��ϡ� The moment you think about giving up,think of the reason why you held on so long����ÿ�����������ʱ����һ����ʲô֧������һ·��֡� I don't wanna be your 'number one' that implies there are a number two and maybe a number three. I want to be your only one�����Ҳ�������ġ���һ������Ϊ�Ǿ���ζ�Ż��еڶ�������������ֻ������ġ�Ψһ���� Total umbrella for someone else if he, you're just not for him in the rain.�����������Ϊ���˳�ɡ�����ֺο��Ϊ���������С� Hold my hand,you won't get lost even with eyes closed. ������ס�ҵ��֣���ʹ���]���۾�Ҳ������·�� We never really grow up. We only learn how to act in public. �������Ǵ�δ������������ֻ���ڱ�����ǰѧ���˼�װ�� Each trauma, is another kind of maturity. ����ÿһ�ִ��ˣ�������һ�ֳ��졣");
    	article.setDigest("ͼ����Ϣ��ժҪ�����е�ͼ����Ϣ����ժҪ����ͼ�Ĵ˴�Ϊ��");
    	article.setContent_source_url("");
    	article.setShow_cover_pic("1");
    	article.setThumb_media_id("FhG0yarsKCdoANOTwxsaQfrDhhgtvNAHOJCQf1KJuHRqYN_qs36B8xXO39wQhcmP");
    	article.setTitle("ͼ����Ϣ����");
    	
    	PicTextMessage picTextMessage = new PicTextMessage();
    	picTextMessage.setArticles(new Article[]{article});
    	return picTextMessage;
    }
    
    
	public static Menu getMenu(){
		CommonButton btn11 = new CommonButton();
		btn11.setKey("11");
		btn11.setName("����Ԥ��");
		btn11.setType("click");
		
	    ViewButton btn12 = new ViewButton();
		btn12.setUrl("xFpIcmE4ia23ggfKunqa4QXQCqjn52crhFZARW7OY4-9W46M43ng1049N0dzKL53");
		btn12.setName("������ѯ");
		btn12.setType("view");
		
		CommonButton btn13 = new CommonButton();
		btn13.setKey("13");
		btn13.setName("�ܱ�����");
		btn13.setType("click");
		
		CommonButton btn14 = new CommonButton();
		btn14.setKey("14");
		btn14.setName("��ʷ�ϵĽ���");
		btn14.setType("click");
		
		CommonButton btn21= new CommonButton();
		btn21.setKey("21");
		btn21.setName("�����㲥");
		btn21.setType("click");
		
		CommonButton btn22 = new CommonButton();
		btn22.setKey("22");
		btn22.setName("������Ϸ");
		btn22.setType("click");
		
		CommonButton btn23 = new CommonButton();
		btn23.setKey("23");
		btn23.setName("��Ů��̨");
		btn23.setType("click");
		
		CommonButton btn24 = new CommonButton();
		btn24.setKey("24");
		btn24.setName("����ʶ��");
		btn24.setType("click");
		
		CommonButton btn31 = new CommonButton();
		btn31.setKey("31");
		btn31.setName("Q��Ȧ");
		btn31.setType("click");
	
		CommonButton btn32 = new CommonButton();
		btn32.setKey("32");
		btn32.setName("��Ӱ���а�");
		btn32.setType("click");
		
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("��������");
		mainBtn1.setSub_button(new Button[]{btn11,btn12,btn13,btn14});
// 		mainBtn1.setSub_button(new CommonButton[]{btn11});
		
		
		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("������վ");
		mainBtn2.setSub_button(new CommonButton[]{btn21,btn22,btn23,btn24});
	//	mainBtn2.setSub_button(new CommonButton[]{btn21});
		
		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("��������");
	mainBtn3.setSub_button(new CommonButton[]{btn31,btn32});
		
  //			mainBtn3.setSub_button(new CommonButton[]{btn31});
		Menu menu = new Menu();
		menu.setButton(new Button[]{mainBtn1,mainBtn2,mainBtn3});
//		menu.setButtons(new Button[]{mainBtn1,mainBtn2});
		
		return menu;
	}
	
	
}
