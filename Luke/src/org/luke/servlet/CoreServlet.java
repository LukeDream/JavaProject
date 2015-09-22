package org.luke.servlet;

import java.io.IOException;
import java.io.PrintWriter;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.luke.pojo.AccessToken;
import org.luke.pojo.Button;
import org.luke.pojo.CommonButton;
import org.luke.pojo.ComplexButton;
import org.luke.pojo.Menu;
import org.luke.service.CoreService;
import org.luke.util.SignUtil;
import org.luke.util.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class CoreServlet extends HttpServlet {
	private static final long serialVersionUID = 4440739483644821986L;
	
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		String signature = request.getParameter("signature");
		
		String timestamp = request.getParameter("timestamp");
		
		String nonce =  request.getParameter("nonce");
		
		String echostr = request.getParameter("echostr");
		
		PrintWriter out = response.getWriter();
		
	
		if(SignUtil.checkSignature(signature,timestamp,nonce)){
			out.print(echostr);
		}
		
		    out.close();  
	        out = null;  
	        
	    		
	    	}
	    	
	    
		
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		Logger log = LoggerFactory.getLogger(CoreServlet.class);
/*
		String appId = "wx1daefe9c0da399a8";
		String appSecret = "136a5ca184a6cec33c24514210c8cd0d";
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
		
		
		if(null != at){
			int result = WeixinUtil.createMenu(WeixinUtil.getMenu(), at.getToken());
			
			if(0 == result)
				log.info("菜单创建成功!");
				
			else
				log.info("菜单创建失败，错误码:"+result);
		}*/
		
	
	
		
		System.out.println("1");
		
		String respMessage = CoreService.processRequest(request);
		
		PrintWriter out = response.getWriter();
		out.println(respMessage);
		out.close();
		
		
    }  
}
