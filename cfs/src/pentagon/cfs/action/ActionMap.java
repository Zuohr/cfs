/**
 * Team Pentagon
 * Task 7 - Web application development
 * Carnegie Financial Services
 * Jan 2014
 */

package pentagon.cfs.action;

import java.util.HashMap;

public class ActionMap {
	private HashMap<String, Action> map;

	public ActionMap() {
		map = new HashMap<String, Action>();
	}

	public synchronized boolean addAction(Action a) {
		if (a == null) {
			return false;
		}
		String name = a.getName();
		if (map.get(name) != null) {
			return false;
		} else {
			map.put(name, a);
			return true;
		}
	}

	public synchronized Action getAction(String name) {
		return map.get(name);
	}
}
