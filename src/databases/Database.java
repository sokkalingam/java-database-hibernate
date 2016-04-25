package databases;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import models.Gem;

public class Database {
	
	private static Map<Integer, Gem> gems = new ConcurrentHashMap<Integer, Gem>();
	private static List<Gem> gemsInCart = new ArrayList<Gem>();

	public static Map<Integer, Gem> getGems() {
		return gems;
	}

	public static List<Gem> getGemsInCart() {
		return gemsInCart;
	}
}
