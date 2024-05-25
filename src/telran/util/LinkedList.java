package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedList<T> implements List<T> {
    Node<T> head;
    Node<T> tail;
    int size;
    
    private static class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;
        Node(T data) {
            this.data = data;
        }
    }

    @Override
    
    public boolean add(T obj) {		//O(1)
        Node<T> node = new Node<>(obj);
        addNode(size, node);	
        return true;
    }

    @Override
  
    public boolean remove(T pattern) {//O(N)
        int index = indexOf(pattern);
        boolean result = index > -1;
        if (result) {
            remove(index);
        }
        return result;
    }

    @Override
  
    public boolean contains(T pattern) { //O(N)
        return indexOf(pattern) > -1;
    }

    @Override
  
    public int size() {//O(1)
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }
    
    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current = head;

        @Override
       
        public boolean hasNext() {  //O(1)
            return current != null;
        }

        @Override
       
        public T next() {  //O(1)
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            return data;
        }
    }

    @Override
   
    public T get(int index) { //O(N)
        List.checkIndex(index, size, true);
        return getNode(index).data;
    }

    @Override
  
    public void add(int index, T obj) {  //O(N)
        List.checkIndex(index, size, false);
        Node<T> node = new Node<>(obj);
        addNode(index, node);
    }

    @Override
    
    public T remove(int index) { //O(N)
        List.checkIndex(index, size, true);
        Node<T> node = getNode(index);
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
        size--;
        return node.data;
    }

    @Override
  
    public int indexOf(T pattern) { //O(N)
        int index = 0;
        Node<T> current = head;
        int result = -1;
        
        while (current != null && result == -1) {
            if (Objects.equals(current.data, pattern)) {
                result = index;
            }
            current = current.next;
            index++;
        }
        
        return result;
    }
    @Override
    
    public int lastIndexOf(T pattern) {//O(N)
        int index = size - 1;
        Node<T> current = tail;
        int result = -1;

        while (current != null && result == -1) {
            if (Objects.equals(current.data, pattern)) {
                result = index;
            }
            current = current.prev;
            index--;
        }

        return result;
    }

    private Node<T> getNode(int index) { //O(N)
        return index < size / 2 ? getNodeFromHead(index) : getNodeFromTail(index);
    }

    private Node<T> getNodeFromTail(int index) { //O(N)
        Node<T> current = tail;
        for (int i = size - 1; i > index; i--) {
            current = current.prev;
        }
        return current;
    }

    private Node<T> getNodeFromHead(int index) {//O(N)
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private void addNode(int index, Node<T> node) {//O(N)
        if (index == 0) {
            addHead(node);
        } else if (index == size) {
            addTail(node);
        } else {
            addMiddle(node, index);
        }
        size++;
    }

    private void addMiddle(Node<T> node, int index) {//O(N)
        Node<T> nodeNext = getNode(index);
        Node<T> nodePrev = nodeNext.prev;
        nodeNext.prev = node;
        nodePrev.next = node;
        node.prev = nodePrev;
        node.next = nodeNext;
    }

    private void addTail(Node<T> node) { //O(1)
        // head cannot be null
        tail.next = node;
        node.prev = tail;
        tail = node;
    }

    private void addHead(Node<T> node) {//O(1)
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
    }
}
