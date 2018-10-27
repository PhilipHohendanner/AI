package at.jku.cp.ai.search.algorithms;

import java.util.function.Predicate;

import at.jku.cp.ai.search.Node;
import at.jku.cp.ai.search.Search;
import at.jku.cp.ai.search.datastructures.Pair;
import at.jku.cp.ai.search.datastructures.StablePriorityQueue;
import at.jku.cp.ai.search.datastructures.StackWithFastContains;

// Depth-Limited Depth-First Search
public class DLDFS implements Search {
	// we need an O(1) datastructure for path-avoidance.
	// 'contains' is O(N) in a stack, where N
	// is the current depth, so we use a stack and a set in parallel
	@SuppressWarnings("unused")
	private StackWithFastContains<Node> path;
	@SuppressWarnings("unused")
	private int limit;

	public DLDFS(int limit) {
		this.limit = limit;
	}

	@Override
	public Node search(Node start, Predicate<Node> endPredicate) {

/*
		StablePriorityQueue<Double, Node> fringe = new StablePriorityQueue<>();
		path.push(start);
		for (Node n:start.adjacent()){
			//fringe.add(Function)
		}






		if (limit == 0) {
			// goal check and return because of limit
			if (endPredicate.test(start)) return start; return null;
		} else {
			// goal check
			if (endPredicate.test(start)) return start;
			//StablePriorityQueue<Double, Node> fringe = new StablePriorityQueue<>();
			//fringe.addAll(start.adjacent());
			while (! fringe.isEmpty()){
				Pair p = fringe.poll();
				if (!path.contains(p)){
					//path.push();
				}
			}
		}



*/
		return null;
	}
}