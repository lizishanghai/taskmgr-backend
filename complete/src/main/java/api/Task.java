package api;

import org.springframework.data.annotation.Id;

public class Task {
    @Id
    private String id;
    private String desc;
    private boolean completed;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}

//package hello;
//
//public class Greeting {
//
//    private final long id;
//    private final String content;
//
//    public Greeting(long id, String content) {
//        this.id = id;
//        this.content = content;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public String getContent() {
//        return content;
//    }
//}
