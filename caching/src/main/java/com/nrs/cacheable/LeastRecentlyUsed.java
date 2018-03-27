/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nrs.cacheable;

import java.util.HashMap;

/**
 *
 * @author root
 */
public class LeastRecentlyUsed implements CacheStrategy{
    private final int capacity;
    HashMap<MethodInfo, Node> results = new HashMap<>();
    
    private Node head;
    private Node tail;
    
    
    public LeastRecentlyUsed(final int capacity) {
        this.capacity = capacity;
    }
    
    
    
    @Override
    public Object getValue(final MethodInfo key) {
        
        if(results.containsKey(key)){
            Node result = results.get(key);
            remove(result);
            setHead(result);
            return result.value;
        }
        return null;
    }
    
    private void remove(Node n){
        if(n.previous != null){
            n.previous.next = n.next;
        }else{
            head = n.next;
        }
        if(n.next != null){
            n.next.previous = n.previous;
        }else{
            tail = n.previous;
        }
    }
    
    @Override
    public void putValue(MethodInfo key, Object value){
        if(results.containsKey(key)){
            Node r = results.get(key);
            r.value = value;
            remove(r);
            setHead(r);
        }else{
            Node n = new Node(key, value);
            if(results.size() >= capacity){
                results.remove(tail.key);
                setHead(head);
            }else{
                setHead(n);
            }
            results.put(key,n);
        }
        
    }
    
    private void setHead(Node nod) {
        nod.next = head;
        nod.previous = null;

        if (head != null) {
            head.previous = nod;
        } else {
            tail = nod.previous;
        }
    }
    
    class Node{
    MethodInfo key;
    Object value;
    Node next;
    Node previous;
    
    public Node(MethodInfo key, Object value){
        this.key = key;
        this.value = value;
    }
    
    
    
}
    
}
