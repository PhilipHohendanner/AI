package at.jku.cp.ai.search.algorithms;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Predicate;

import at.jku.cp.ai.search.Node;
import at.jku.cp.ai.search.Search;
import at.jku.cp.ai.search.datastructures.*;

// Uniform Cost Search
public class UCS implements Search
{
	@SuppressWarnings("unused")
	private Function<Node, Double> cost;

	public UCS(Function<Node, Double> cost) {
		this.cost = cost;
	}

	@Override
	public Node search(Node start, Predicate<Node> endPredicate)
	{

		if(endPredicate.test((start))) return start;

		// create variables
		StablePriorityQueue fringe = new StablePriorityQueue<>(50);
		ArrayList<Node> memory = new ArrayList<>();

		// insert in fringe and memory
		fringe.addAll(start.adjacent());
		//memory.add(start);
		// loop
		while(!fringe.isEmpty()) {
			Pair p = fringe.peek();

			// goal test
			if(p.equals(endPredicate)) {
				return null;
			}
		}
		return null;
	}
}
