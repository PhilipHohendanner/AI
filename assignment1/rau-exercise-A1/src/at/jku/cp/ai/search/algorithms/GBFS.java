package at.jku.cp.ai.search.algorithms;

import java.util.function.Function;
import java.util.function.Predicate;

import at.jku.cp.ai.search.Node;
import at.jku.cp.ai.search.Search;
import at.jku.cp.ai.search.datastructures.Pair;
import at.jku.cp.ai.search.datastructures.StablePriorityQueue;
import at.jku.cp.ai.search.datastructures.StackWithFastContains;

// Greedy Best-First Search
public class GBFS implements Search
{
	@SuppressWarnings("unused")
	private Function<Node, Double> heuristic;

	public GBFS(Function<Node, Double> heuristic) {
		this.heuristic = heuristic;
	}
	
	@Override
	public Node search(Node start, Predicate<Node> endPredicate)
	{
		// create stack for visited nodes and fringe for queue
		StablePriorityQueue<Double, Node> fringe = new StablePriorityQueue<>();
		StackWithFastContains<Node> alreadyVisited = new StackWithFastContains<>();
		// create pair<f,s> for queue from start node + add to fringe and alreadyVisited
		Pair<Double,Node> pair = new Pair<>(null,start);
		fringe.add(pair);
		alreadyVisited.push(start);
		Node checknode;
		do {
			checknode = fringe.poll().s;
			// goal check
			if (endPredicate.test(checknode)) {
				return checknode;
			}
			// expand child nodes
			for(Node node: checknode.adjacent()) {
				// if not alreadyVisited -> add to visited and add to fringe
				if(!alreadyVisited.contains(node)) {
					alreadyVisited.push(node);
					// sum up costs in new pair for correct calculation of costs
					fringe.add(new Pair<>(heuristic.apply(node),node));
				}
			}
		} while (!fringe.isEmpty());
		return null;
	}
}
