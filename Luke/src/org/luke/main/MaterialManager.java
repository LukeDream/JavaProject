package org.luke.main;

import org.luke.material.Article;
import org.luke.material.PicTextMessage;
import org.luke.pojo.AccessToken;
import org.luke.util.WeixinUtil;

public class MaterialManager {

	public static void main(String[] args) {
		
		String appId = "wx1daefe9c0da399a8";
		String appSecret = "136a5ca184a6cec33c24514210c8cd0d";
		
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
		if (null != at) {
			String pictextMess1 = WeixinUtil.createPicTextMessage(getPicTextMessage(),at.getToken() );
			System.out.println(pictextMess1);
		}
	}
	
	 public static PicTextMessage getPicTextMessage(){
	    	String QianNiuPicUrl = "http://mmbiz.qpic.cn/mmbiz/Edjt5cvbzfJbkVq3aF61DViaPPALuEXyt14cmqS3nTFAoXM8JgibeCOWDJ1zBQAeZqBIibyjrPCAWcCAebRz2UibKw/0";
	    	Article article = new Article();
	    	article.setAuthor("Luke");
	    	article.setContent("Love your parents. We are too busy growing up yet we forget that they are already growing old���������һ���Լ��ĸ�ĸ�ɣ�������æ���Լ��ɳ���ȴ��������Ҳ�ڱ��ϡ� The moment you think about giving up,think of the reason why you held on so long����ÿ�����������ʱ����һ����ʲô֧������һ·��֡� I don't wanna be your 'number one' that implies there are a number two and maybe a number three. I want to be your only one�����Ҳ�������ġ���һ������Ϊ�Ǿ���ζ�Ż��еڶ�������������ֻ������ġ�Ψһ���� Total umbrella for someone else if he, you're just not for him in the rain.�����������Ϊ���˳�ɡ�����ֺο��Ϊ���������С� Hold my hand,you won't get lost even with eyes closed. ������ס�ҵ��֣���ʹ���]���۾�Ҳ������·�� We never really grow up. We only learn how to act in public. �������Ǵ�δ������������ֻ���ڱ�����ǰѧ���˼�װ�� Each trauma, is another kind of maturity. ����ÿһ�ִ��ˣ�������һ�ֳ��졣");
	    	article.setDigest("最美丽的景色在我们的心中");
	    	article.setContent_source_url("");
	    	article.setShow_cover_pic("1");
	    	article.setThumb_media_id("FhG0yarsKCdoANOTwxsaQfrDhhgtvNAHOJCQf1KJuHRqYN_qs36B8xXO39wQhcmP");
	    	article.setTitle("美丽的一天");
	    	
	    	PicTextMessage picTextMessage = new PicTextMessage();
	    	picTextMessage.setArticles(new Article[]{article});
	    	return picTextMessage;
	    }
}
