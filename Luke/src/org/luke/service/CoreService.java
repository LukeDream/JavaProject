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
//	����΢�ŷ���������
	
	public static String processRequest(HttpServletRequest request){
		String respMessage = null;
		
		String QianNiuPicUrl = "http://mmbiz.qpic.cn/mmbiz/Edjt5cvbzfJbkVq3aF61DViaPPALuEXyt14cmqS3nTFAoXM8JgibeCOWDJ1zBQAeZqBIibyjrPCAWcCAebRz2UibKw/0";
	//	String respMessage = "testtesttest";
		try{
			String respContent = "�������쳣�����Ժ�����";
			
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
					article.setTitle("΢�ſ���Java�̳�");
					article.setDescription("���������ʵ�õ�΢��java�̳̾�������");
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
				respContent = "�����͵���ͼƬ��Ϣ";
			}
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)){
				respContent = "�����͵��ǵ���λ����Ϣ";
			}
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)){
				respContent = "�����͵���������Ϣ";
			}
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)){
				respContent = "�����͵�����Ƶ��Ϣ";
			}
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)){
				String eventType = requestMap.get("Event");
				String eventKey = requestMap.get("EventKey");
				
				if(eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){
					respContent = "лл���Ĺ�ע��";
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
						article1.setTitle("΢�ſ���PHP�̳�");
						article1.setDescription("��õ�΢��PHP�̳�");
						article1.setPicUrl("");
						article1.setUrl("");
						
						Article article2 = new Article();
						article2.setTitle("΢�ſ���Java�̳�");
						article2.setDescription("���������ʵ�õ�΢��java�̳̾�������");
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
