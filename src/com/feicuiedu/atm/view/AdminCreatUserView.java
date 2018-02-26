package com.feicuiedu.atm.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.feicuiedu.atm.anno.Column;
import com.feicuiedu.atm.core.AbstractView;
import com.feicuiedu.atm.entity.User;
import com.feicuiedu.atm.service.UserService;
import com.feicuiedu.atm.util.CommonUtils;

public class AdminCreatUserView  extends AbstractView{
	
		//账户id
		@Column("id")
		private int id;
		
		//用户账号
		@Column("account")
		private String account;
		
		//姓名
		@Column("user_name")
		private String name;
		
		//余额
		@Column("amount")
		private double amount;
		
		//性别 1:男2：女
		@Column("gender")
		private int gender;
		
		//身份证号
		@Column("card")
		private String card;
		
		//账户状态 0：管理员 1：正常用户 2：销户 3：锁定
		@Column("userType")
		private String userType;
		
		//密码
		@Column("passwd")
		private String passwd;
		
		//出生日期
		@Column("birthday")
		Date birthday  = new Date();
		String birthdayStr;

		//地址
		@Column("address")
		private String address;
		
		//备注
		@Column("remark")
		private String remark;
		
		@Override
		public AbstractView view() {
			
			
			UserService us = new UserService();
			
			//身份证号
			
			while(true) {
				
				CommonUtils.printFromProperties("C004");
				card = scr.next();
				if(CommonUtils.checkCardNum(card) && !us.hasCard(card)) {
					break;
				}
				CommonUtils.printFromProperties("M106");
			}
			
			//名字
			while(true) {
				
				CommonUtils.printFromProperties("C005");
				name = scr.next();
				break;
			}
			
			//性别
			while(true) {
				
				CommonUtils.printFromProperties("C006");
				gender = Integer.valueOf(scr.next());
				
				if(1==gender||2==gender) {
					
					break;
				}
				CommonUtils.printFromProperties("M106");
			}
			
			
			//生日 格式 yyyy-MM-dd
			while(true) {  

				CommonUtils.printFromProperties("C007");
				String str=scr.next();
				
				if(!CommonUtils.checkBirthday(str)){
					
					CommonUtils.printFromProperties("M106");
					continue;
				}	
					// 接收
					try{
						
					birthday = new SimpleDateFormat("yyyy-MM-dd").parse(str) ;
					String[] strArray = str.split("-");
					StringBuilder sb = new StringBuilder();
					for(String s:strArray) {
						sb.append(s);
					}
					birthdayStr = sb.toString();
					}catch(Exception e){
						
					e.printStackTrace();
					}
					
	
				//System.out.println(birthdayStr) ;
				//System.out.println(birthday) ;
				
				break;
			}
			
			//地址
			while(true) {
				
				CommonUtils.printFromProperties("C008");
				address = scr.next();
				break;
			}
			
			//备注
			while(true) {
				
				CommonUtils.printFromProperties("C009");
				remark = scr.next();
				break;
			}
			
			//账户状态 0：管理员 1：正常用户 2：销户 3：锁定
			userType = "1";
		
			Random random = new Random(9);
			String radomNum=String.valueOf(1000 + random.nextInt(8999));
			account = "BC18" + "0" + gender  + birthdayStr +radomNum ;
			CommonUtils.printFromProperties("M109");
			System.out.println(account);
			
			//密码
			while(true) {
				
				CommonUtils.printFromProperties("C002");
				passwd = scr.next();
				if(CommonUtils.checkPasswd(passwd)) {
					break;
				}
				CommonUtils.printFromProperties("M106");
			}
			
			//重新输入密码
			while(true) {
				CommonUtils.printFromProperties("C010");
				String passwd = scr.next();
				if(this.passwd.equals(passwd)){
					break;
				}
				CommonUtils.printFromProperties("M106");
			}
			
			id = us.findMaxId()+1;
			
			User user = new User(id,account,name,amount,gender,card,userType,passwd,birthday,address,remark);
			
			us.create(user);
			CommonUtils.printFromProperties("M101");
			
			return CommonUtils.setDispatherPathReturnAbstractView("AdminMenuView");
		}
		
}
