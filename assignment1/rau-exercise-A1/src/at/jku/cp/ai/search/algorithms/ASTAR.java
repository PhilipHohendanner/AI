package at.jku.cp.ai.search.algorithms;

import java.util.function.Function;
import java.util.function.Predicate;
import at.jku.cp.ai.search.Node;
import at.jku.cp.ai.search.Search;
import at.jku.cp.ai.search.datastructures.Pair;
import at.jku.cp.ai.search.datastructures.StablePriorityQueue;
import at.jku.cp.ai.search.datastructures.StackWithFastContains;

// A* Search
public class ASTAR implements Search
{
	@SuppressWarnings("unused")
	private Function<Node, Double> heuristic;
	@SuppressWarnings("unused")
	private Function<Node, Double> cost;

	// class to save costs and heuristic costs
	// need seperate class that is comparable for stablePriorityQueue
	// cost contain costs of node + costs from parent node without heuristic costs
	private final class Costs implements Comparable<Costs> {
		public final double cost;
		public final double heuristic;

		public Costs(double cost, double heuristic){
			this.cost = cost;
			this.heuristic = heuristic;
		}

		@Override
		public int compareTo(Costs otherCosts) {
			return Double.compare(cost + heuristic, otherCosts.cost + otherCosts.heuristic);
		}
	}

	public ASTAR(Function<Node, Double> heuristic, Function<Node, Double> cost) {
		this.heuristic = heuristic;
		this.cost = cost;
	}

	@Override
	public Node search(Node start, Predicate<Node> endPredicate) {

		// create stack for alreadyVisited nodes and fringe as StablePriorityQueue with costs saved in own class
		StackWithFastContains<Node> alreadyVisited = new StackWithFastContains<>();
		StablePriorityQueue<Costs, Node> fringe = new StablePriorityQueue<>();
		fringe.add(new Pair(new Costs(heuristic.apply(start), cost.apply(start)), start));

		Pair<Costs, Node> p;
		while (! fringe.isEmpty()) {
			p = fringe.poll();
			Node n = p.s;

			// goal check
			if (endPredicate.test(n))
				return n;

			// check if node has already been visited
			if (!alreadyVisited.contains(n)) {
				alreadyVisited.push(n);
				// expand node and add costs from parent node to costs of node + save heuristic costs seperately -> add to fringe
				for (Node node : n.adjacent()) {
					Costs costs = new Costs(p.f.cost + cost.apply(node),heuristic.apply(node));
					fringe.add(new Pair(costs, node));
				}
			}
		}
		return null;
	}
}