package com.gt.service;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

import com.gt.utils.*;
import com.gt.domain.Book;
import com.gt.domain.Users;
import com.gt.utils.DBUtil;


//处理与订单相关的业务逻辑
public class OrderService {
	
	private Connection ct=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	Serializable slt = null;
	

	//下订单涉及到两张表，而两张表有关系
	//
	public void submitOrder(MyCart mycart,Users user) {
		String sql="insert into orderform(users_id,ord_totalPrice) values(?,?)";
		
		//因为添加订单复杂！所以这种操作很特别，于是我们不使用SqlHelper,而是
		//专门针对下订单写对数据库的操作
		try {
			ct=DBUtil.getConnection();
			//为了保证我们的订单号，是稳定的，所以将其事务隔离级别升级（可串行）
			ct.setAutoCommit(false);//事务不再自动提交
			ct.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			
			ps=ct.prepareStatement(sql);
			ps.setInt(1,user.getId());
			ps.setFloat(2,mycart.getTotalPrice());
			ps.executeUpdate();
			//如何得到刚刚插入的订单记录的订单号呢？
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				//取出刚刚生成的订单号
				slt = (Serializable) rs.getObject(1);
			}
//			sql="select"+slt+"from orderform";
//			ps=ct.prepareStatement(sql);
//			rs=ps.executeQuery();
//			if(rs.next()){}
			//把订单细节表也生成【批量提交！！！】
			ArrayList al=mycart.showMyCart();
			for (int i = 0; i < al.size(); i++) {
				Book book =(Book)al.get(i);
				sql = "insert into orderdetail(ord_id,goods_id,goods_num) values(?,?,?)";
				ps=ct.prepareStatement(sql);
				ps.setInt(1,Integer.parseInt(String.valueOf(slt)));
				ps.setString(2,book.getId());
				ps.setInt(3,book.getShoppingNum());
				ps.executeUpdate();
				
			}
			//整体提交
			ct.commit();
			
			
			
		}catch (Exception e) {
			try {
				ct.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println("插入数据失败");
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally
		{
			DBUtil.close(rs,ps,ct);
		}
		
	}	
}
