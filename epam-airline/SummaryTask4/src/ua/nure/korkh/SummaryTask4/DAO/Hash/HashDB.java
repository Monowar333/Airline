package ua.nure.korkh.SummaryTask4.DAO.Hash;

import java.util.Hashtable;
import java.util.List;

import ua.nure.korkh.SummaryTask4.entity.Entity;

/**
 * Class for hash query
 * 
 * @author Korkh
 * 
 */
public class HashDB {
	private static Hashtable<String, List<? extends Entity>> hashDB;

	private HashDB() {

	}

	public static void init() {
		if (hashDB == null) {
			hashDB = new Hashtable<>();
		}
	}

	public static List<? extends Entity> getData(String query) {
		init();
		List<? extends Entity> result = null;
		if (hashDB.get(query) != null) {
			result = hashDB.get(query);
		}
		return result;
	}

	public static void setData(String query, List<? extends Entity> data) {
		hashDB.put(query, data);
	}

	public static void deleteData(String query) {
		if (hashDB.containsKey(query)) {
			hashDB.remove(query);
		}
	}
}
