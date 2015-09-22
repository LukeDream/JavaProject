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
				log.info("菜单创建成功!");
			else
				log.info("菜单创建失败，错误码:"+result);
		}
		
	}
	
	public  static Menu getMenu(){
		CommonButton btn11 = new CommonButton();
		btn11.setKey("11");
		btn11.setName("天气预报");
		btn11.setType("click");
		
	    ViewButton btn12 = new ViewButton();
		btn12.setUrl("http://www.baidu.com/");
		btn12.setName("公交查询");
		btn12.setType("view");
	
		CommonButton btn13 = new CommonButton();
		btn13.setKey("13");
		btn13.setName("周边搜索");
		btn13.setType("click");
		
		CommonButton btn14 = new CommonButton();
		btn14.setKey("14");
		btn14.setName("历史上的今天");
		btn14.setType("click");
		
		CommonButton btn21= new CommonButton();
		btn21.setKey("21");
		btn21.setName("歌曲点播");
		btn21.setType("click");
		
		CommonButton btn22 = new CommonButton();
		btn22.setKey("22");
		btn22.setName("经典游戏");
		btn22.setType("click");
		
		CommonButton btn23 = new CommonButton();
		btn23.setKey("23");
		btn23.setName("美女电台");
		btn23.setType("click");
		
		CommonButton btn24 = new CommonButton();
		btn24.setKey("24");
		btn24.setName("人脸识别");
		btn24.setType("click");
		
		CommonButton btn31 = new CommonButton();
		btn31.setKey("31");
		btn31.setName("Q友圈");
		btn31.setType("click");
	
		CommonButton btn32 = new CommonButton();
		btn32.setKey("32");
		btn32.setName("电影排行榜");
		btn32.setType("click");
		
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("生活助手");
		mainBtn1.setSub_button(new Button[]{btn11,btn12,btn13,btn14});
// 		mainBtn1.setSub_button(new CommonButton[]{btn11});
		
		
		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("休闲驿站");
		mainBtn2.setSub_button(new CommonButton[]{btn21,btn22,btn23,btn24});
	//	mainBtn2.setSub_button(new CommonButton[]{btn21});
		
		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("更多体验");
	mainBtn3.setSub_button(new CommonButton[]{btn31,btn32});
		
  //			mainBtn3.setSub_button(new CommonButton[]{btn31});
		Menu menu = new Menu();
		menu.setButton(new Button[]{mainBtn1,mainBtn2,mainBtn3});
//		menu.setButtons(new Button[]{mainBtn1,mainBtn2});
		
		return menu;
	}
	
	
}
