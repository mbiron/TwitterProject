package isep.tweetproject.server;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Tweet {
	private int id;
	private int authorId;
	private String message;
	private Date date;

	public Tweet() {
	}

	public Tweet(int id, int authorId, String message, Date date) {
		this.id = id;
		this.authorId = authorId;
		this.message = message;
		this.date = date;
	}

	public String toString() {
		return "Tweet (" + id + ") Author(" + authorId + ")" + " Date : "
				+ date + " : " + message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
