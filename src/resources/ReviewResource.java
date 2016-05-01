package resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import models.review.GemReview;
import models.review.Review;
import services.ReviewService;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReviewResource {
	
	private ReviewService reviewService = new ReviewService();
	
	@GET
	public GemReview getReviews(@PathParam("gemId") Integer gemId) {
		return reviewService.getGemReview(gemId);
	}
	
	@GET
	@Path("/{reviewId}")
	public Review getReview(@PathParam("gemId") Integer gemId, @PathParam("reviewId") Integer reviewId) {
		return reviewService.getReview(gemId, reviewId);
	}
	
	@POST
	public void addReview(@PathParam("gemId") Integer gemId, Review review){
		reviewService.addReview(gemId, review);
	}
	
//	@PUT
//	@Path("/{reviewId}")
//	public Review updateReview(@PathParam("gemId") Integer gemId, @PathParam("reviewId") Integer reviewId, Review review) {
//		return reviewService.updateReview(gemId, reviewId, review);
//	}
	
	@DELETE
	@Path("/{reviewId}")
	public void deleteReview(@PathParam("gemId") Integer gemId, @PathParam("reviewId") Integer reviewId) {
		reviewService.deleteReview(gemId, reviewId);
	}
}
