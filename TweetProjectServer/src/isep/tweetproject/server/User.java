package isep.tweetproject.server;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	private int id;
	private String name;
	private String twitterNickname;
	private Date joinedDate;
	
	public User(){
		
	}
	public User(int id, String name, String nickname, Date d)
	{
		this.id = id;
		this.name = name;
		this.twitterNickname = nickname;
		this.joinedDate = d;
	}
	
	public String toString(){
		return "User " + twitterNickname 
				+ " (id : " + id + ") named " 
				+ name + " joined : " + joinedDate;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTwitterNickname() {
		return twitterNickname;
	}
	public void setTwitterNickname(String twitterNickname) {
		this.twitterNickname = twitterNickname;
	}
	public Date getJoinedDate() {
		return joinedDate;
	}
	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}
	
	
}
