package com.gt.service;

import java.util.*;

import com.gt.domain.Book;

//这个表示我的购物车
public class MyCart {
	//键-值表
	HashMap<String,Book> hm = new HashMap<String,Book>();
	
	//添加书
	public void addBook(String id,Book book)
	{
//		System.out.println("id:"+id);
//		System.out.println("Book书名："+book.getName());
		if (hm.containsKey(id)) {
			book=hm.get(id);
			//如果这本书已经购买过，shoppingNum数量加一
			int shoppingNum=book.getShoppingNum();
			shoppingNum++;
			book.setShoppingNum(shoppingNum);
		}else {
			hm.put(id,book);
		}	
	}
	
	//添加书的第二个方法
	public void addBook2(String id) {
		if (hm.containsKey(id)) {
			//hm已经由这本书了
			Book book = hm.get(id);
			int shoppingNum=book.getShoppingNum();
			book.setShoppingNum(shoppingNum+1); 
		}else {
			hm.put(id,new BookService().getBookById(id));
		}
		
	}
	
	//删除书
	public void delBook(String id) {
		hm.remove(id);
	}
	//更新书(对于购物车的更新)
	public void updateBook(String id,int nums) {
		//取出id对应的Book
		Book book=hm.get(id);
		book.setShoppingNum(nums);
	}
	//显示该购物车中的所有商品信息
	public ArrayList showMyCart() {
		ArrayList<Book> al=new ArrayList<Book>();
		//遍历HashMap
		Iterator iterator = hm.keySet().iterator();	
		while (iterator.hasNext()) {
			String id= (String) iterator.next();
			//取出Book
			Book book=hm.get(id);
			System.out.println("showMyCart中的Book书名："+book.getName());
			al.add(book);
		}
		return al;
	}
	
	//清空购物车
	public void clearBook() {
		hm.clear();
	}
	
	//返回该购物车的总价
	public float getTotalPrice() {
		//得到总价格
		float totalPrice = 0.0f;
		ArrayList<Book> al=new ArrayList<Book>(); 
		Iterator iterator = hm.keySet().iterator();
		while (iterator.hasNext()) {
			//取出书号
			String bookId =(String) iterator.next();
			Book book = hm.get(bookId);
			totalPrice +=book.getBookprice()*book.getShoppingNum();	
		}
		return totalPrice;
	}
	//返回该购物车是否为空
	public Boolean IsEmpty() {
		System.out.println("判断购物车为空？");
		if (hm.isEmpty()){
			System.out.println("购物车为空");
			return true;
		}else {
			System.out.println("购物车为不为空");
			return false;
		}
		
		
	}
	
}
