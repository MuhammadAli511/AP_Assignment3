package i190417_A_A3;

public class BST {
	class Node
	{
		String key;
		Node left_Child;
		Node right_Child;
		public Node()
		{
			key = "";
			left_Child = null;
			right_Child = null;
		}
	}
	Node root_Node;
	BST()
	{
		root_Node = null;
	}
	void insert(String k)
	{
		if (root_Node == null)
		{
			Node adding_Node = new Node();
			adding_Node.key = k;
			adding_Node.left_Child = null;
			adding_Node.right_Child = null;
			root_Node = adding_Node;
		}
		else
		{
			Node forward_iteration_node = root_Node;
			Node previous_iteration_Node = null;
			while (forward_iteration_node != null)
			{
				if (k.compareTo(forward_iteration_node.key) < 0)
				{
					previous_iteration_Node = forward_iteration_node;
					forward_iteration_node = forward_iteration_node.left_Child;
				}
				else if (k.compareTo(forward_iteration_node.key) > 0)
				{
					previous_iteration_Node = forward_iteration_node;
					forward_iteration_node = forward_iteration_node.right_Child;
				}
			}
			Node adding_Node = new Node();
			adding_Node.key = k;
			adding_Node.right_Child = null;
			adding_Node.left_Child = null;
			if (adding_Node.key.compareTo(previous_iteration_Node.key) < 0)
			{
				previous_iteration_Node.left_Child = adding_Node;
				return;
			}
			else if (adding_Node.key.compareTo(previous_iteration_Node.key) > 0)
			{
				previous_iteration_Node.right_Child = adding_Node;
				return;
			}
		}
	}
	boolean Search(String data)
	{
		if (root_Node.key.compareTo(data) == 0)
		{
			return true;
		}
		else
		{
			Node forward_iteration_node = root_Node;
			Node previous_iteration_Node = null;
			while (forward_iteration_node != null)
			{
				if (forward_iteration_node.key.compareTo(data) == 0)
				{
					return true;
				}
				else if (data.compareTo(forward_iteration_node.key) < 0)
				{
					previous_iteration_Node = forward_iteration_node;
					forward_iteration_node = forward_iteration_node.left_Child;
				}
				else if (data.compareTo(forward_iteration_node.key) > 0)
				{
					previous_iteration_Node = forward_iteration_node;
					forward_iteration_node = forward_iteration_node.right_Child;
				}
			}
			return false;
		}
	}
	void Traverse(Node r)
	{
		if (r != null)
		{
			System.out.println(r.key);
			Traverse(r.left_Child);
			Traverse(r.right_Child);
		}
	}
	void Display()
	{
		Node iteration_Node = root_Node;
		Traverse(iteration_Node);
	}
}
