package databases;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import models.Gem;

public class GemDatabase extends Database {
	
	public Integer addGem(Gem gem) {
		Integer id = null;
		sessionStart();
		id = (Integer) session.save(gem);
		sessionEnd();
		return id;
	}
	
	public void deleteGem(Integer gemId) {
		sessionStart();
		
		session.delete(gemId);
		
		sessionEnd();
	}
	
	public void updateGem(Gem gem) {
		sessionStart();
		
		session.update(gem);
		
		sessionEnd();
	}
	
	public Gem getGem(Integer id) {
		Gem gem = null;
		
		sessionStart();
		gem = session.get(Gem.class, id);
		sessionEnd();
		
		return gem;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Gem> readGems() {
		List<Gem> gems = new ArrayList<Gem>();
		
		sessionStart();
		
		gems = session.createCriteria(Gem.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		
		sessionEnd();
		
		return gems;
	}
	
	@SuppressWarnings("unchecked")
	public List<Gem> readGemsInCart() {
		List<Gem> gemsInCart = new ArrayList<Gem>();
		
		sessionStart();
		
		gemsInCart = session.createCriteria(Gem.class).add(Restrictions.eq("inCart", true)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		
		sessionEnd();
		
		return gemsInCart;
	}

}
