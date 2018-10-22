package at.jku.cp.ai.search.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import at.jku.cp.ai.search.Node;
import at.jku.cp.ai.search.Search;


// Breadth-First search
public class BFS implements Search
{
	@Override
	public Node search(Node start, Predicate<Node> endPredicate)
	{
		List<Node> queue = new ArrayList<>();
		Node currentNode;
		queue.add(start);
		while(!queue.isEmpty()) {
			currentNode = queue.get(0);
			// safety measurement against null nodes
			if(currentNode == null) {
				return null;
			}
			//checks if current node is goal node
			if(endPredicate.test(currentNode)) {
				return currentNode;
			}
			queue.remove(0);
			queue.addAll(currentNode.adjacent());
		}
		return null;
	}
}