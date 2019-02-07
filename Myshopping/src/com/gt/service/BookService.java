package com.gt.service;

import java.util.ArrayList;

import com.gt.domain.Book;
import com.gt.utils.SqlHelper;

//这是一个业务逻辑类，用于处理与book表相关的业务 各司其职 
public class BookService {
	//根据书的编号 返回一个Book
	public Book getBookById(String id) {
		Book book=new Book();
		String sql="select * from goods where book_id=?";
		String paras[] = {id};
		ArrayList al=new SqlHelper().executeQuery(sql,paras); 
		System.out.println("al的长度："+al.size());
		if (al.size()==1) {
			Object[] obj=(Object[])al.get(0);
			book.setId(obj[0].toString());
			book.setName(obj[1].toString());
			book.setAuthor(obj[2].toString());
			book.setPublishHouse(obj[3].toString());
			book.setBookprice(Float.parseFloat(obj[4].toString()));
			book.setReserve(Integer.parseInt(obj[5].toString()));
		}
		return book;
	}
	
	
	//得到所有的书籍信息（分页！！）
	public ArrayList getAllBook(){
		String sql="select * from goods where 1=?";
		String paras[] = {"1"};
		ArrayList al=new SqlHelper().executeQuery(sql,paras); 
		ArrayList<Book> newAl=new ArrayList<Book>();
		//二次业务封装
		for (int i = 0; i < al.size(); i++) {
			Object obj[]=(Object[])al.get(i);
			Book book=new Book();
//			book.setId(Integer.parseInt(obj[0].toString()));
			book.setId(obj[0].toString());
			book.setName(obj[1].toString());
			book.setAuthor(obj[2].toString());
			book.setPublishHouse(obj[3].toString());
			book.setBookprice(Float.parseFloat(obj[4].toString()));
			book.setReserve(Integer.parseInt(obj[5].toString()));
			newAl.add(book);
			
		}
		return newAl;
	}

}
