package example;

import com.bnq.ao.ProductAO;
import org.springframework.core.ResolvableType;

/**
 * Created by liqiang on 2018/4/9.
 */
public class ResolvableTypeTest {

    public static void main(String[] args) {
        ResolvableType resolvableType = ResolvableType.forClass(ProductAO.class);
        ResolvableType componentType = resolvableType.getComponentType();
        System.out.println(componentType.getSource());
    }
}
