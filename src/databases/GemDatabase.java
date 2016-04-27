package databases;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;

import models.Gem;

public class GemDatabase extends Database {
	
	public void addGem(Gem gem) {
		sessionStart();
		
		session.save(gem);
		
		sessionEnd();
	}
	
	public void deleteGem(Gem gem) {
		sessionStart();
		
		session.delete(gem);
		
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

}
