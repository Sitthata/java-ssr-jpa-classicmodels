package sit.int202.classicmodels.entities.cart;

import lombok.Getter;
import lombok.Setter;
import sit.int202.classicmodels.entities.Product;

@Setter
@Getter
public class ClassicModelLineItem implements CartItem {

    private Product product;
    private int quantity;
    private double percentDiscount;

    public ClassicModelLineItem(Product product, int quantity, double percentDiscount) {
        this.product = product;
        this.quantity = quantity;
        this.percentDiscount = percentDiscount;
    }

    public ClassicModelLineItem(Product product) {
        this(product, 1, 0.0);
    }

    public ClassicModelLineItem(Product product, int quantity) {
        this(product, quantity, 0.0);
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public double getUnitPrice() {
        return product.getPrice().doubleValue();
    }

    @Override
    public double getTotal() {
        return getUnitPrice() * getQuantity() - (getUnitPrice() * getQuantity() * percentDiscount);
    }

    @Override
    public double getPercentDiscount() {
        return this.percentDiscount;
    }

    public void setPercentDiscount(double percentDiscount) {
        this.percentDiscount = percentDiscount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ClassicModelLineItem{");
        sb.append("product=").append(product);
        sb.append(", quantity=").append(quantity);
        sb.append(", percentDiscount=").append(percentDiscount);
        sb.append('}');
        return sb.toString();
    }
}
