package models.review;

import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import models.Model;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"stars", "body", "author" })
public class Review implements Model {
	
	@Override
	public String toString() {
		return "Review [stars=" + stars + ", body=" + body + ", author=" + author + "]";
	}

	public Review() {
	}

	public Review(Integer stars, String body, String author) {
		super();
		this.stars = stars;
		this.body = body;
		this.author = author;
	}

	public Review(Integer reviewId) {
		this.id = reviewId;
	}

	@Id @GeneratedValue
	@JsonProperty("id")
	private Integer id;
	@JsonProperty("stars")
	private Integer stars;
	@JsonProperty("body")
	private String body;
	@JsonProperty("author")
	private String author;

	/**
	 * 
	 * @return The stars
	 */
	@JsonProperty("stars")
	public Integer getStars() {
		return stars;
	}

	/**
	 * 
	 * @param stars
	 *            The stars
	 */
	@JsonProperty("stars")
	public void setStars(Integer stars) {
		this.stars = stars;
	}

	/**
	 * 
	 * @return The body
	 */
	@JsonProperty("body")
	public String getBody() {
		return body;
	}

	/**
	 * 
	 * @param body
	 *            The body
	 */
	@JsonProperty("body")
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * 
	 * @return The author
	 */
	@JsonProperty("author")
	public String getAuthor() {
		return author;
	}

	/**
	 * 
	 * @param author
	 *            The author
	 */
	@JsonProperty("author")
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public static Review generateRandomModel() {
		Review review  = new Review();
		Random random = new Random();
		
		Integer stars = random.nextInt(5) + 1;
		review.setStars(stars);
		
		if (stars > 3)
			review.setBody("It looks fantastic! I loved it");
		if (stars == 3)
			review.setBody("It looks ok, you get what you pay for.");
		if (stars < 3)
			review.setBody("Don't waste your money on this, not good at all");
		
		review.setAuthor("user"+random.nextInt(9999)+1);
		
		return review;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((stars == null) ? 0 : stars.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
//		if (author == null) {
//			if (other.author != null)
//				return false;
//		} else if (!author.equals(other.author))
//			return false;
//		if (body == null) {
//			if (other.body != null)
//				return false;
//		} else if (!body.equals(other.body))
//			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
//		if (stars == null) {
//			if (other.stars != null)
//				return false;
//		} else if (!stars.equals(other.stars))
//			return false;
		return true;
	}
}