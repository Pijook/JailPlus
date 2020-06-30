package pl.Pijok.JailPlus.jail;

public class Prisoner {

    private String prison_name;
    private long throw_into_time;
    private long ban_time;

    public Prisoner(String prison_name, long throw_into_time, long ban_time){
        this.prison_name = prison_name;
        this.throw_into_time = throw_into_time;
        this.ban_time = ban_time;
    }

    public String getPrison_name() {
        return prison_name;
    }

    public void setPrison_name(String prison_name) {
        this.prison_name = prison_name;
    }

    public long getThrow_into_time() {
        return throw_into_time;
    }

    public void setThrow_into_time(long throw_into_time) {
        this.throw_into_time = throw_into_time;
    }

    public long getBan_time() {
        return ban_time;
    }

    public void setBan_time(long ban_time) {
        this.ban_time = ban_time;
    }
}
