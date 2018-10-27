package at.jku.cp.ai.search.algorithms;

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
		// create stack for visited nodes and fringe for queue
		StablePriorityQueue<Double, Node> fringe = new StablePriorityQueue<>();
		StackWithFastContains<Node> alreadyVisited = new StackWithFastContains<>();
		// create pair<f,s> for queue from start node + add to fringe and alreadyVisited
		Pair<Double,Node> pair = new Pair<>(cost.apply(start),start);
		fringe.add(pair);
		alreadyVisited.push(start);
		while (!fringe.isEmpty()) {
			pair = fringe.poll();

			// goal check
			if (endPredicate.test(pair.s)) {
				//System.out.println("found solution!");
				return pair.s;
			}
			// expand child nodes
			for(Node node: pair.s.adjacent()) {
				// if not alreadyVisited -> add to visited and add to fringe
				if(!alreadyVisited.contains(node)) {
					alreadyVisited.push(node);
					// sum up costs in new pair for correct calculation of costs
					Pair<Double,Node> p = new Pair<>(cost.apply(node)+pair.f,node);
					fringe.add(p);
				}
			}
		}
		//System.out.println("failed!");
		return null;
	}
}
