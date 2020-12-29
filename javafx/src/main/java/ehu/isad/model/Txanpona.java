package ehu.isad.model;




public class Txanpona {
    private Integer trade_id;
    private Float price;
    private Float volume;

    public Txanpona(Integer trade_id, Float price, Float volume) {
        this.trade_id = trade_id;
        this.price = price;
        this.volume = volume;
    }

    public Integer getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(Integer trade_id) {
        this.trade_id = trade_id;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }
}
        