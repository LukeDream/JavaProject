package org.luke.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.luke.message.resp.Article;
import org.luke.message.resp.NewsMessage;
import org.luke.message.resp.TextMessage;
import org.luke.util.MessageUtil;

public class CoreService {
//	处理微信发来的请求
	
	public static String processRequest(HttpServletRequest request){
		String respMessage = null;
		
		String QianNiuPicUrl = "http://mmbiz.qpic.cn/mmbiz/Edjt5cvbzfJbkVq3aF61DViaPPALuEXyt14cmqS3nTFAoXM8JgibeCOWDJ1zBQAeZqBIibyjrPCAWcCAebRz2UibKw/0";
	//	String respMessage = "testtesttest";
		try{
			String respContent = "请求处理异常，请稍后重试";
			
			Map<String, String> requestMap =MessageUtil.parseXml(request);
			
			String fromUserName = requestMap.get("FromUserName");
			
			String toUserName = requestMap.get("ToUserName");
			
			String msgType = requestMap.get("MsgType");
			
			/*String fromUserName = request.getParameter("FromUserName");
			
			String toUserName = request.getParameter("ToUserName");
			
			String msgType = request.getParameter("MsgType");*/
			
			
			
			
			TextMessage textMessage = new TextMessage();
			
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			
			if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)){
				String content = requestMap.get("Content");
				
				
				NewsMessage newsMessage = new NewsMessage();
				newsMessage.setToUserName(fromUserName);
				newsMessage.setFromUserName(toUserName);
				newsMessage.setCreateTime(new Date().getTime());
				newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
				newsMessage.setFuncFlag(0);
				
				List<Article> articleList = new ArrayList<Article>();
				String test = "1";
				
				
				if (test.equals(content)) {
					Article article = new Article();
					article.setTitle("微信开发Java教程");
					article.setDescription("最新最好最实用的微信java教程就在这里");
					article.setPicUrl("");
					article.setUrl("");
					articleList.add(article);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				} else {
					respContent = MessageUtil.getMainMenu();
					textMessage.setContent(respContent);
					respMessage = MessageUtil.textMessageToXml(textMessage);
				}
				
				
			}
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)){
				respContent = "您发送的是图片信息";
			}
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)){
				respContent = "您发送的是地理位置信息";
			}
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)){
				respContent = "您发送的是链接信息";
			}
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)){
				respContent = "您发送的是音频信息";
			}
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)){
				String eventType = requestMap.get("Event");
				String eventKey = requestMap.get("EventKey");
				
				if(eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){
					respContent = "谢谢您的关注！";
				}
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)){
					
				}
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)){
					if(eventKey.equals("11")){
						
						List<Article> articleList = new ArrayList<Article>();
						
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						newsMessage.setFuncFlag(0);
						
						Article article1 = new Article();
						article1.setTitle("微信开发PHP教程");
						article1.setDescription("最好的微信PHP教程");
						article1.setPicUrl("");
						article1.setUrl("");
						
						Article article2 = new Article();
						article2.setTitle("微信开发Java教程");
						article2.setDescription("最新最好最实用的微信java教程就在这里");
						article2.setPicUrl(QianNiuPicUrl);
						article2.setUrl("");
						
						articleList.add(article2);
						articleList.add(article1);
						
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respMessage = MessageUtil.newsMessageToXml(newsMessage);
					}
				}
			}
			
/*			textMessage.setContent(respContent);
			respMessage = MessageUtil.textMessageToXml(textMessage);*/
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return respMessage;
	}

}
