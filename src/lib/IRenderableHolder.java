package lib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import lib.IRenderableHolder;
import lib.IRenderableObject;

public class IRenderableHolder {
	
	private static final IRenderableHolder instance = new IRenderableHolder();
	private List<IRenderableObject> entities;
	private Comparator<IRenderableObject> comparator;
	
	public IRenderableHolder(){
		entities = new ArrayList<>();
		comparator = (IRenderableObject o1, IRenderableObject o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}
	
	public static IRenderableHolder getInstance() {
		return instance;
	}
	
	public void addAndSort(IRenderableObject entity) {
		add(entity);
		sort();
	}
	
	public void add(IRenderableObject entity) {
		entities.add(entity);
		sort();
	}
	
	public void sort(){
		Collections.sort(entities, comparator);
	}
	
	
	public List<IRenderableObject> getEntities() {
		return entities;
	}
}
