package at.jku.cp.ai.search.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import at.jku.cp.ai.search.Node;
import at.jku.cp.ai.search.Search;
import at.jku.cp.ai.search.datastructures.StackWithFastContains;

// Iterative Deepening Search
public class IDS implements Search
{
	@SuppressWarnings("unused")
	private int limit;

	public IDS(int limit)
	{
		this.limit = limit;
	}

	@Override
	public Node search(Node start, Predicate<Node> endPredicate)
	{
		List<Node> queue = new ArrayList<>();
		StackWithFastContains<Node> alreadyVisited = new StackWithFastContains<>();
		Node currentNode;
		queue.add(start);
		alreadyVisited.push(start);
		int depth = 0;
		while (!queue.isEmpty()) {
			currentNode = queue.remove(0);
			// safety measurement against null nodes
			if (currentNode == null) {
				return null;
			}
			//checks if current node is goal node
			if (endPredicate.test(currentNode)) {
				return currentNode;
			}
			depth = 0;
			for(Node node: currentNode.adjacent()) {
				if(depth <= limit) {
					depth++;
					if(!alreadyVisited.contains(node)) {
						alreadyVisited.push(node);
						queue.add(node);
					}
				}
			}
		}
		//limit reached, start next iteration
		if(depth > limit) {
			IDS nextIteration = new IDS(limit+1);
			return nextIteration.search(start, endPredicate);
		}
		return null;
	}

}

