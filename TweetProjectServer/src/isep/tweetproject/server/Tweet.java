package isep.tweetproject.server;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Tweet {
	private long id;
	private long authorId;
	private String message;
	private Date date;

	public Tweet() {
	}

	public Tweet(long id, long authorId, String message, Date date) {
		this.id = id;
		this.authorId = authorId;
		this.message = message;
		this.date = date;
	}

	public String toString() {
		return "Tweet (" + id + ") Author(" + authorId + ")" + " Date : "
				+ date + " : " + message;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(long authorId) {
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
