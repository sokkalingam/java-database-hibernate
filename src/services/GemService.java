package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import databases.Database;
import models.Gem;
import services.helpers.ParseHelper;

public class GemService {
	
	private Map<Integer, Gem> gems = Database.getGems();
	
	private static Integer gemCounter = 0;
	
	public GemService() {
		
	}
	
	public List<Gem> getAllGems() {
		return new ArrayList<Gem>(this.gems.values());
	}
	
	/**Search Gems based on query params
	 * Search by name contains and Price range within
	 * query params: name, minPrice, maxPrice
	 * @param uriInfo
	 * @return
	 */
	public List<Gem> searchGems(UriInfo uriInfo) {
		
		List<Gem> allGems = getAllGems();
		List<Gem> result = new ArrayList<Gem>();
		
		MultivaluedMap<String, String> queryParameters = uriInfo.getQueryParameters();
		String searchName = queryParameters.getFirst("name");
		String minPrice = queryParameters.getFirst("minPrice");
		String maxPrice = queryParameters.getFirst("maxPrice");
		String rating = queryParameters.getFirst("rating");
		for (Gem gem : allGems)
			if (_isNameContains(gem, searchName)
					&& _isWithinPriceRange(gem, minPrice, maxPrice)
					&& _isAverageReviewAbove(gem, rating))
				result.add(gem);
		
		return result;
	}
	
	public Gem getGem(Integer id) {
		if (id == null)
			return null;
		return this.gems.get(id);
	}
	
	public Gem addGem(Gem gem) {
		if (gem == null)
			return null;
		gem.setId(gemCounter++);
		this.gems.put(gem.getId(), gem);
		return gem;
	}
	
	/**Update Gem
	 * Sets Id and preserves reviews
	 * @param id
	 * @param gem
	 * @return
	 */
	public Gem updateGem(Integer id, Gem gem) {
		if (id == null || gem == null)
			return null;
		Gem existingGem = this.gems.get(id);
		if (existingGem == null)
			return null;
		gem.setId(id);
		gem.setGemReview(existingGem.getGemReview());
		this.gems.put(id, gem);
		return gem;
	}
	
	public Gem checkout(Integer id) {
		if (id == null)
			return null;
		Gem gem = null;
		if (isGemPresent(id)) {
			gem = gems.get(id);
			
			if (gem.getQuantity() > 0)
				gem.setQuantity(gem.getQuantity() - 1);
			
			return gem;
		}
		
		return null;
	}
	
	public Gem deleteGem(Integer id) {
		if (id == null)
			return null;
		return this.gems.remove(id);
	}
	
	public List<Gem> getGemsInCart() {
		List<Gem> gemsInCart = new ArrayList<Gem>();
		for (Gem gem : this.gems.values())
			if (BooleanUtils.isTrue(gem.isInCart()))
				gemsInCart.add(gem);
		return gemsInCart;
	}
	
	public Gem addGemToCart(Integer id) {
		if (isGemPresent(id)) {
			Gem gem = gems.get(id);
			if (!BooleanUtils.isTrue(gem.isInCart())) {
				gem.setInCart(true);
				return gem;
			}
		}
		return null;
	}
	
	public Gem removeGemFromCart(Integer id) {
		if (isGemPresent(id)) {
			Gem gem = gems.get(id);
			if (gem.isInCart()) {
				gem.setInCart(false);
				return gem;
			}
		}
		return null;
	}
	
	public boolean isGemPresent(Integer gemId) {
		if (gemId == null)
			return false;
		return gems.containsKey(gemId);
	}
	
	private boolean _isNameContains(Gem gem, String searchName) {
		if (StringUtils.isBlank(searchName))
			return true;
		return StringUtils.containsIgnoreCase(gem.getName(), searchName);
	}
	
	private boolean _isWithinPriceRange(Gem gem, String minPrice, String maxPrice) {
		if (StringUtils.isBlank(minPrice) && StringUtils.isBlank(maxPrice))
			return true;
		
		Double minPriceVal = ParseHelper.getDouble(minPrice);
		Double maxPriceVal = ParseHelper.getDouble(maxPrice);
		
		if (minPriceVal != null && maxPriceVal != null)
			return gem.getPrice() >= minPriceVal && gem.getPrice() <= maxPriceVal;

		if (minPriceVal != null)
			return gem.getPrice() >= minPriceVal;
			
		if (maxPriceVal != null)
			return gem.getPrice() <= maxPriceVal;
		
		return false;
	}
	
	private boolean _isAverageReviewAbove(Gem gem, String rating) {
		if (StringUtils.isBlank(rating))
			return true;
		
		Double val = ParseHelper.getDouble(rating);
		
		return val != null && gem.getGemReview().getAverageReview().getAverageReview() >= val;
		
	}

}
