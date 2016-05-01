package models;

import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import models.review.GemReview;
import testdata.GemData;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "name", "price", "description", "quantity", "canPurchase", "soldOut", "specifications", "gemReview" })
public class Gem implements Model {

	@Id @GeneratedValue
	@JsonProperty("id")
	private Integer id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("price")
	private Double price;
	@Lob
	@JsonProperty("description")
	private String description;
	@JsonProperty("quantity")
	private Integer quantity;
	@JsonProperty("canPurchase")
	private Boolean canPurchase;
	@JsonProperty("soldOut")
	private Boolean soldOut;
	@JsonProperty("inCart")
	private Boolean inCart;
	@Lob
	@JsonProperty("specifications")
	private String specifications;
	@Lob
	@JsonProperty("image")
	private String image;
	@OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private GemReview gemReview = new GemReview();
	
	@JsonProperty("gemReview")
	public GemReview getGemReview() {
		return gemReview;
	}
	@JsonIgnore
	public void setGemReview(GemReview gemReview) {
		this.gemReview = gemReview;
	}

	/**
	 * 
	 * @return The name
	 */
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 *            The name
	 */
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return The price
	 */
	@JsonProperty("price")
	public Double getPrice() {
		return price;
	}

	/**
	 * 
	 * @param price
	 *            The price
	 */
	@JsonProperty("price")
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * 
	 * @return The description
	 */
	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description
	 *            The description
	 */
	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return The quantity
	 */
	@JsonProperty("quantity")
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * 
	 * @param quantity
	 *            The quantity
	 */
	@JsonProperty("quantity")
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * 
	 * @return The canPurchase
	 */
	@JsonProperty("canPurchase")
	public Boolean getCanPurchase() {
		return canPurchase;
	}

	/**
	 * 
	 * @param canPurchase
	 *            The canPurchase
	 */
	@JsonProperty("canPurchase")
	public void setCanPurchase(Boolean canPurchase) {
		this.canPurchase = canPurchase;
	}

	/**
	 * 
	 * @return The soldOut
	 */
	@JsonProperty("soldOut")
	public Boolean getSoldOut() {
		return quantity != null ? quantity <= 0 : false;
	}

	/**
	 * 
	 * @return The specifications
	 */
	@JsonProperty("specifications")
	public String getSpecifications() {
		return specifications;
	}

	/**
	 * 
	 * @param specifications
	 *            The specifications
	 */
	@JsonProperty("specifications")
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	/**
	 * 
	 * @return image
	 */
	@JsonProperty("image")
	public String getImage() {
		return image;
	}

	/**
	 * 
	 * @param image
	 *            The specifications
	 */
	@JsonProperty("image")
	public void setImage(String image) {
		this.image = image;
	}
	
	@JsonProperty("inCart")
	public Boolean isInCart() {
		return inCart;
	}
	@JsonProperty("inCart")
	public void setInCart(Boolean inCart) {
		this.inCart = inCart;
	}


	public static Gem generateModel() {
		Gem gem = new Gem();
		gem.setName(GemData.getName());
		gem.setSpecifications("Item ID:	399349, Content: 10 gems, Weight: 2.18ct (total), Calibrated: 3.5 mm, "
				+ "ExactSize:	3.50 mm x 2.06 mm (avg.), Shape:	Round Facet, Clarity:	VS-SI, "
				+ "Treatment:	Heated, Origin:	Myanmar");
		gem.setDescription("From the Radiant Reflections® collection, this spectacular solitaire ring for her features"
				+ " a 1/2-carat princess-cut diamond set in 10K white gold. The ring is meticulously crafted to create a"
				+ " larger, more dramatic look. Diamond Total Carat Weight may range from .45 - .57 carats.");
		gem.setQuantity(new Random().nextInt(4) + 1);
		gem.setCanPurchase(true);
		gem.setInCart(false);
		gem.setImage(GemData.getImage());
		gem.setPrice((double) (new Random().nextInt(30000) + 2000));
		return gem;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Gem [name="
				+ name
				+ ", price="
				+ price
				+ ", description="
				+ description
				+ ", quantity="
				+ quantity
				+ ", canPurchase="
				+ canPurchase
				+ ", soldOut="
				+ soldOut
				+ ", specifications="
				+ specifications
				+ ", image="
				+ image
				+ ", gemReview="
				+ gemReview
				+ "]";
	}
}