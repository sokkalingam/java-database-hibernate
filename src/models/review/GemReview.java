package models.review;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import models.Model;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GemReview implements Model{
	
	public GemReview() {
	}
	
	@Id @GeneratedValue
	private Integer id;
	
	@JsonIgnore @OneToMany(cascade = CascadeType.ALL)
	private List<Review> reviews = new ArrayList<Review>();
	
	public AverageReview getAverageReview() {
		return AverageReview.getAverageReview(getReviews());
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
