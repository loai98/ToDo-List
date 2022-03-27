package com.example.todolist.Model;

public class ToDoItem {

    String id,name,createdAt,priority,key;
    public ToDoItem(String createdAt ,String name, String priority){
        this.name= name;
        this.priority=priority;
        this.createdAt =createdAt;
    }
    public ToDoItem(){};

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPriority() {
        return priority;
    }

    public String getDate() {
        return createdAt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setPriority(String  priority) {
        this.priority = priority;
    }
}
