package org.luke.main;

import org.luke.pojo.AccessToken;
import org.luke.pojo.Button;
import org.luke.pojo.CommonButton;
import org.luke.pojo.ComplexButton;
import org.luke.pojo.Menu;
import org.luke.pojo.ViewButton;
import org.luke.util.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class MenuManager {
	
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);
	
	public static void main(String[] args){
		String appId = "wx1daefe9c0da399a8";
		String appSecret = "136a5ca184a6cec33c24514210c8cd0d";
		
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
		System.out.println(at.getToken());

		
		
/*		if (null != at) {
			String pictextMess1 = WeixinUtil.createPicTextMessage(WeixinUtil.getPicTextMessage(),at.getToken() );
			System.out.println(pictextMess1);
		}*/
		
		
		
		if(null != at){
			int result = WeixinUtil.createMenu(getMenu(), at.getToken());
			
			if(0 == result)
				log.info("�˵������ɹ�!");
			else
				log.info("�˵�����ʧ�ܣ�������:"+result);
		}
		
	}
	
	public  static Menu getMenu(){
		CommonButton btn11 = new CommonButton();
		btn11.setKey("11");
		btn11.setName("����Ԥ��");
		btn11.setType("click");
		
	    ViewButton btn12 = new ViewButton();
		btn12.setUrl("http://www.baidu.com/");
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
