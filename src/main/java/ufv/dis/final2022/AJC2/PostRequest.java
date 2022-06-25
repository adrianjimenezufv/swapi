package ufv.dis.final2022.AJC2;

public class PostRequest {
    private String entity;
    private long id;

    public PostRequest() {
    }

    public PostRequest(String entity, long id) {
        this.entity = entity;
        this.id = id;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PostRequest{" +
                "entity='" + entity + '\'' +
                ", id=" + id +
                '}';
    }
}
