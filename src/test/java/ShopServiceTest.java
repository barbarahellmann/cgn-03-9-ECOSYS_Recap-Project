import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void addOrderTest() throws NoProductException{
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");


        //WHEN
        Optional<Order> actualOptional = Optional.ofNullable(shopService.addOrder(productsIds));

        //THEN
        assertTrue(actualOptional.isPresent());
        Order expected = new Order("-1", List.of(new Product("1", "Apfel")), OrderStatus.IN_DELIVERY);
        assertEquals(expected.products(), actualOptional.get().products());
        assertNotNull(expected.id());
    }

    @Test
    void addOrderTest_whenInvalidProductId_expectException() throws NoProductException {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1", "2");

        //WHEN
        Optional<Order> actualOptional = Optional.ofNullable(shopService.addOrder(productsIds));

        //THEN

        assertThrows(NoProductException.class, () -> shopService.addOrder(productsIds));
    }


}
