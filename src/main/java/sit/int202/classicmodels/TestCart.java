package sit.int202.classicmodels;

import sit.int202.classicmodels.entities.Product;
import sit.int202.classicmodels.entities.cart.Cart;
import sit.int202.classicmodels.entities.cart.ClassicModelLineItem;
import sit.int202.classicmodels.repository.ProductRepository;

public class TestCart {
    public static void main(String[] args) {
        Cart<String, ClassicModelLineItem> cart = new Cart<>();
        ProductRepository productRepository = new ProductRepository();
        Product product = productRepository.findById("S10_1678");
        cart.addItem(product.getProductCode(), new ClassicModelLineItem(product,5));
        product = productRepository.findById("S12_3380");
        cart.addItem(product.getProductCode(), new ClassicModelLineItem(product));
        System.out.println(cart.getTotalPrice());
        System.out.println(cart.getAllItem());
        cart.removeItem("S10_1678");
    }
}
