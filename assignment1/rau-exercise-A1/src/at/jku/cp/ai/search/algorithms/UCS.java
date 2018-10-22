package at.jku.cp.ai.search.algorithms;

import java.util.function.Function;
import java.util.function.Predicate;

import at.jku.cp.ai.search.Node;
import at.jku.cp.ai.search.Search;
import at.jku.cp.ai.search.datastructures.StablePriorityQueue;

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

		// insert in fringe

		// loop

		// check frist node in fringe

		// goal test

		return null;
	}
}
