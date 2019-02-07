package com.gt.javamail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
	//默认设置
	private String smtp = "smtp.qq.com";		//SMTP服务器  
	private String from = "xxxx@qq.com"; 		//发信人 
	private String password="xxxx";				//密匙   密码为QQ邮箱开通的stmp服务后得到的客户端授权码	
	private String to = "xxxx@qq.com";			//收信人  
	private String copyto = "xxxx@qq.com";		//抄送人  
	private String subject = "订单通知";		//邮件主题  
	private String content = "请尽快查收订单内的书籍！";	//邮件内容   
	private String username="亲爱的顾客";				//用户名  
	private String filename = "";					//附件地址路径，如：F:\\笔记<a>\\struts2</a>与mvc.txt 未实现
	
	
	public void SendMail(String To,String Subject,String Content,String Username,String Filename)  
			throws AddressException,MessagingException {
		this.to=To;
		this.subject = Subject;
		this.content = Content;
		this.username= Username;
		this.filename= Filename;
		
		
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");// 连接协议
        properties.put("mail.smtp.host", smtp);// 主机名
        properties.put("mail.smtp.port", 465);// 端口号
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
        properties.put("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息
        // 得到回话对象
        Session session = Session.getInstance(properties);
        // 获取邮件对象
        Message message = new MimeMessage(session);
        // 设置发件人邮箱地址
        message.setFrom(new InternetAddress(from));
        // 设置收件人邮箱地址 
        //一个收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        //抄送
//      message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{
//      		new InternetAddress("xxxxx@qq.com"),
//      		new InternetAddress("xxxxx@qq.com"),
//      		new InternetAddress("xxxxx@qq.com")
//      		});
        
        // 设置邮件标题
        message.setSubject(subject);
        // 设置邮件内容
        message.setText(content);
        // 得到邮差对象
        Transport transport = session.getTransport();
        // 连接自己的邮箱账户
        // 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
        transport.connect(from, password);
        // 发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        System.out.println("邮件发送完毕！");
    }
}

