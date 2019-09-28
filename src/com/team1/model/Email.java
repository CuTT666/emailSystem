package com.team1.model;

public class Email {
	private String email_id;   //邮件id
	private String username;    //用户名
	private String user_id;     //用户id，暂时不用它，用保存在session中的account
	private String account;     //用户账号
	private String title;       //邮件名称
	private String content;     //邮件内容
	private String draft;       //是否在草稿箱  1为true，0为false
	private String create_date;  //邮件创建日期
	private String mailbox;       //是否在垃圾箱 1为true，0为false
	private String sender_user_id; //发件人id
	private String receiver_user_id; //收件人id
	public Email() {
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDraft() {
		return draft;
	}
	public void setDraft(String draft) {
		this.draft = draft;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getMailbox() {
		return mailbox;
	}
	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}
	public String getSender_user_id() {
		return sender_user_id;
	}
	public void setSender_user_id(String sender_user_id) {
		this.sender_user_id = sender_user_id;
	}
	public String getReceiver_user_id() {
		return receiver_user_id;
	}
	public void setReceiver_user_id(String receiver_user_id) {
		this.receiver_user_id = receiver_user_id;
	}
	
	@Override
	public String toString() {
		return "Email [email_id=" + email_id + ", username=" + username + ", user_id=" + user_id + ", account="
				+ account + ", title=" + title + ", content=" + content + ", draft=" + draft + ", create_date="
				+ create_date + ", mailbox=" + mailbox + ", sender_user_id=" + sender_user_id + ", receiver_user_id="
				+ receiver_user_id + "]";
	}
	
	public Email(String email_id, String username, String user_id, String account, String title, String content,
			String draft, String create_date, String mailbox, String sender_user_id, String receiver_user_id) {
		super();
		this.email_id = email_id;
		this.username = username;
		this.user_id = user_id;
		this.account = account;
		this.title = title;
		this.content = content;
		this.draft = draft;
		this.create_date = create_date;
		this.mailbox = mailbox;
		this.sender_user_id = sender_user_id;
		this.receiver_user_id = receiver_user_id;
	}
	
	
	
}
