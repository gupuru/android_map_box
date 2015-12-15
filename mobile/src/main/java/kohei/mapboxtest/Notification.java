package kohei.mapboxtest;


import com.squareup.otto.Produce;

public class Notification {

    private String data;

    public Notification(String data) {
        this.data = data;
    }

    @Produce
    public Notification getNotification() {
        return new Notification(data);
    }

    public String getData() {
        return data;
    }

}