package com.gt.service;

import java.util.ArrayList;

import com.gt.domain.Users;
import com.gt.utils.SqlHelper;

//这是专门处理业务逻辑的类
//处理和users表相关的业务逻辑
public class UsersService {
	//验证用户是否合法的方法，合法则返回该用户的其他信息
	//不仅判断用户合不合法，还要把用户本身作为一个数据拿来用
	public boolean checkUser(Users user){
		//到数据库去验证
		String sql="select * from users where users_id=? and passwd=?";
		String paras[]={user.getId()+"",user.getPwd()};
		ArrayList al=new SqlHelper().executeQuery(sql, paras);
		if(al.size()==0){
			return false;
		}else{
			Object[] objects=(Object[])al.get(0);
			//把对象数组封装到Users对象			
			user.setName((String)objects[1]);
			user.setEmail((String)objects[3]);
			user.setGrade(Integer.parseInt(objects[5].toString()));
			//在用户登陆后同时还需要取出该用户的其他信息,故封装到一个对象
			return true;
		}	
	}
}
