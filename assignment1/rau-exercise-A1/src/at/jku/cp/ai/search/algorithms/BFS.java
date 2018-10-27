package at.jku.cp.ai.search.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import at.jku.cp.ai.search.Node;
import at.jku.cp.ai.search.Search;
import at.jku.cp.ai.search.datastructures.StackWithFastContains;


// Breadth-First search
public class BFS implements Search
{

	public Node search(Node start, Predicate<Node> endPredicate) {
		// create fringe for nodes to check and stack for alreadyVisited nodes
		List<Node> fringe = new ArrayList<>();
		StackWithFastContains<Node> alreadyVisited = new StackWithFastContains<>();
		// create pointer and set up starting position by adding to fringe and alreadyVisited
		Node currentNode;
		fringe.add(start);
		alreadyVisited.push(start);
		while (!fringe.isEmpty()) {
			currentNode = fringe.remove(0);
			// safety measurement against null nodes
			if (currentNode == null) {
				return null;
			}
			//checks if current node is goal node
			if (endPredicate.test(currentNode)) {
				return currentNode;
			}
			// expand child nodes from current
			for(Node node: currentNode.adjacent()) {
				// if not already visited -> add to fringe and to alreadyVisited to prevent loops
				if(!alreadyVisited.contains(node)) {
					alreadyVisited.push(node);
					fringe.add(node);
				}
			}
		}
		return null;
	}
}