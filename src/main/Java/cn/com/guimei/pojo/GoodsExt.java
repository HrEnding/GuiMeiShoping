package cn.com.guimei.pojo;

public class GoodsExt extends Goods {
    private Discount discount;
    private Seller seller;
    private Smallclass smallclass;

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Smallclass getSmallclass() {
        return smallclass;
    }

    public void setSmallclass(Smallclass smallclass) {
        this.smallclass = smallclass;
    }
}
