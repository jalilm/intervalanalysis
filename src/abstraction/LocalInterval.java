package abstraction;

import java.util.Iterator;
import java.util.List;

import soot.jimple.toolkits.scalar.ToppedSet;
import soot.toolkits.scalar.FlowSet;


public class LocalInterval extends ToppedSet {
	
	HashMap<Local,Interval> 
	public LocalInterval(FlowSet under) {
		super(under);
	}

	@Override
	public void add(Object arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(Object arg0, FlowSet arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FlowSet clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void copy(FlowSet arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void difference(FlowSet arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void difference(FlowSet arg0, FlowSet arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object emptySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void intersection(FlowSet arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void intersection(FlowSet arg0, FlowSet arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Object arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Object arg0, FlowSet arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List toList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void union(FlowSet arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void union(FlowSet arg0, FlowSet arg1) {
		// TODO Auto-generated method stub
		
	}

}
