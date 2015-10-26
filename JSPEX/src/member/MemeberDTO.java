package member;
/*CREATE TABLE MEMBER
(ID VARCHAR2(255),
PASSWD VARCHAR2(255),
NAME VARCHAR2(255),
JUMIN1 VARCHAR2(255),
JUMIN2 VARCHAR2(255),
EMAIL VARCHAR2(255),
BLOG VARCHAR2(255),
REG_DATE TIMESTAMP)*/

import java.sql.Timestamp;

public class MemeberDTO {

	private String id;
	private String passwd;
	private String name;
	private String jumin1;
	private String jumin2;
	private String email;
	private String blog;
	private Timestamp regDate;
	
	
	
	public String getId(){
		return id;
	}
	public String getPasswd() {
		return passwd;
	}
	public String getname() {
		return name;
	}
	public String getJumin1() {
		return jumin1;
	}
	public String getJumin2() {
		return jumin2;
	}
	public String getEmail() {
		return email;
	}
	public String getBlog() {
		return blog;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	
	
	
	public void setId(String id) {
		this.id = id;
	}
	public void setPasswd(String passwd){
		this.passwd = passwd;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setJumin1(String jumin1) {
		this.jumin1 = jumin1;
	}
	public void setJumin2(String jumin2) {
		this.jumin2 = jumin2;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setBlog(String blog) {
		this.blog = blog;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
}
