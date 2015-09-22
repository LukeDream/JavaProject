package org.luke.main;

import org.luke.group.BatchChangGroup;
import org.luke.group.ChangGroup;
import org.luke.group.CreateGroup;
import org.luke.group.Group;
import org.luke.pojo.AccessToken;
import org.luke.util.WeixinUtil;

public class GroupManager {
			
			//�û��������
			
			
	
	
			public static void main(String[] args) {
				
				String appId = "wx1daefe9c0da399a8";
				String appSecret = "136a5ca184a6cec33c24514210c8cd0d";
				
				AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
				if (null != at) {
					/*int id = WeixinUtil.createGroup(getGroup(), at.getToken());
					System.out.println("test 分组的id为："+id);*/
	//				WeixinUtil.batchchangeGroup(getbatchchangeGroup(id), at.getToken());
					WeixinUtil.batchchangeGroup(getbatchchangeGroup(105), at.getToken());
				}
				
			}
			
			public static BatchChangGroup getbatchchangeGroup(int id){
				
				BatchChangGroup cGroup = new BatchChangGroup();
				String[] openids = {"oIB7lwQr_-DKz9n1n_7YG-sS4974","oIB7lwVg4rragk3Hay0E42tQvnX8","oIB7lwQqTj89DN9is0smsQQiDYGQ"};
				
				cGroup.setOpenid_list(openids);
				cGroup.setTo_groupid(id);
				
				
				return cGroup;
			}
			
			public static CreateGroup getGroup(){
				CreateGroup createGroup = new CreateGroup();
				
				Group group = new Group();
				group.setName("asd");
				
				createGroup.setGroup(group);
				
				return createGroup;
			}
			
}
