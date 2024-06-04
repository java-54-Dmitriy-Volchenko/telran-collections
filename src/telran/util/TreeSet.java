package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeSet<T> implements SortedSet<T> {
private static class Node<T> {
	T data;
	Node<T> parent;
	Node<T> left;
	Node<T> right;
	Node(T data) {
		this.data = data;
	}
	
}
private class TreeSetIterator implements Iterator<T> {
	Node<T> current = getLeastFrom(root);
	@Override
	public boolean hasNext() {
		
		return current != null;
	}

	@Override
	public T next() {
		if(!hasNext()) {
			throw new NoSuchElementException();
		}
		T res = current.data;
		current = getCurrent(current);
		return res;
	}
	
}
	Node<T> root;
	int size;
	private Comparator<T> comp;
	public TreeSet(Comparator<T> comp) {
		this.comp = comp;
	}
	@SuppressWarnings("unchecked")
	public TreeSet() {
		this((Comparator<T>)Comparator.naturalOrder());
	}
	@Override
	public T get(T pattern) {
		Node<T> node = getNode(pattern);
		
		return node == null ? null : node.data;
	}

	private Node<T> getNode(T pattern) {
		Node<T> res = null;
		Node<T> node = getParentOrNode(pattern);
		if (node != null && comp.compare(node.data, pattern) == 0) {
			res = node;
		}
		return res;
	}

	private Node<T> getParentOrNode(T pattern) {
		Node<T> current = root;
		Node<T> parent = null;
		int compRes = 0;
		while(current != null && (compRes = comp.compare(pattern, current.data)) != 0) {
			parent = current;
			current = compRes > 0 ? current.right : current.left;
		}
		return current == null ? parent : current;
	}

	public Node<T> getCurrent(Node<T> current) {
		
		//Algorithm see on the board
		return current.right != null ? getLeastFrom(current.right) :
			getFirstGreaterParent(current);
	}
//done
	private Node<T> getFirstGreaterParent(Node<T> current) {
		while (current.parent != null && current.parent.left != current) {
			current = current.parent;
		}
		return current.parent;
	}
	private Node<T> getLeastFrom(Node<T> node) {
		if (node != null) {
			
			while(node.left != null) {
				node = node.left;
			}
		}
		return node;
	}

	@Override
	public boolean add(T obj) {
		boolean res = false;
		if(obj == null) {
			throw new NullPointerException();
		}
		if(!contains(obj)) {
			res = true;
			Node<T> node = new Node<>(obj);
			if(root == null) {
				addRoot(node);
			} else {
				addWithParent(node);
			}
			size++;
		}
		return res;
	}

	private void addWithParent(Node<T> node) {
		Node<T> parent = getParent(node);
		if(comp.compare(node.data,parent.data) > 0) {
			parent.right = node;
		} else {
			parent.left = node;
		}
		node.parent = parent;
		
	}

	private Node<T> getParent(Node<T> node) {
		Node<T> parent = getParentOrNode(node.data);
		return parent;
	}

	private void addRoot(Node<T> node) {
		root = node;
		
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		Node<T> node = getNode(pattern);
		if (node != null) {
			removeNode(node);
			res = true;
		}
		return res;
	}
//done
	private void removeNode(Node<T> node) {
		if (isFull(node)) {
			deleteFullNode(node);
		} else {
			deleteNotFullNode(node);
		}
		size--;
		
	}

	private void deleteNotFullNode(Node<T> node) {
		Node<T> parent = node.parent;
		Node<T> son = oneSon(node);
		if (parent != null) {
			if (node == parent.right) {
				parent.right = son;
			} else {
				parent.left = son;
			}
		} else {
			root = son;
		}
		if (son != null) {
			son.parent = parent;
		}
		
	}
	private Node<T> oneSon(Node<T> node) {
		return node.right != null ? node.right : node.left;
	}
	
	private void deleteFullNode(Node<T> node) {
		Node<T> toDelete = getLeastFrom(node.right);
		node.data = toDelete.data;
		deleteNotFullNode(toDelete);
		
	}
	private boolean isFull(Node<T> node) {
		return node.left != null && node.right != null;
	}
	
	
	@Override
	public boolean contains(T pattern) {
		
		return getNode(pattern) != null;
	}

	@Override
	public int size() {
		
		return size;
	}

	@Override
	public Iterator<T> iterator() {
		
		return new TreeSetIterator();
	}

	@Override
	public T first() {
		
		return root == null ? null : getLeastFrom(root).data;
	}

	@Override
	public T last() {
		
		return root == null ? null : getGreatesFrom(root).data;
	}

	private Node<T> getGreatesFrom(Node<T> node) {
		if(node != null) {
			while(node.right != null) {
				node = node.right;
			}
		}
		return node;
	}

}