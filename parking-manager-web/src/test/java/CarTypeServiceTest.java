import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import parking.manager.Application;
import parking.manager.service.ICarTypeService;

/**
 * Author: huang
 * Date: created in 2020-04-19 20:56
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CarTypeServiceTest {

    @Autowired
    private ICarTypeService carTypeService;

    @Test
    public void testFindCarTypeWithCars() {
        carTypeService.findCarTypeWithCars().stream().forEach(System.out::println);
    }
}
