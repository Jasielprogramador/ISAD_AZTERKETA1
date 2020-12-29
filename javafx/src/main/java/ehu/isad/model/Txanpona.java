package ehu.isad.model;




public class Txanpona {
    private Integer trade_id;
    private Float price;
    private Float volume;

    private String mota;
    private String data;

    public Txanpona(Integer trade_id, Float price, Float volume, String mota, String data) {
        this.trade_id = trade_id;
        this.price = price;
        this.volume = volume;
        this.mota = mota;
        this.data = data;
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

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
        