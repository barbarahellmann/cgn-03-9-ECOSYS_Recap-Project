import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();


 public Optional<Order> addOrder(List<String> productIds) throws NoProductException {

        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            try {
                Optional<Product> productToOrder = productRepo.getProductById(productId);
                productToOrder.ifPresent(products::add);
            } catch (Exception NoProductException) {
                System.out.println("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
                return null;
            }
        }
        Order newOrder = new Order(UUID.randomUUID().toString(), products, OrderStatus.PROCESSING);

        return Optional.ofNullable(orderRepo.addOrder(newOrder));
    }
    public List<Order>listOfProcessingOrders (List<Order> orderStatusList){

        List<Order> orderStatus = new ArrayList<>();

        orderStatus = orderStatusList.stream()
                .filter(f -> orderStatusList.contains(OrderStatus.PROCESSING))
                .toList();
        return orderStatus;
         }

}
