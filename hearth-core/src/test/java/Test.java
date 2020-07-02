import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            List<Integer> yzList = 求因子(i);
            if (yzList.stream().mapToInt(Integer::intValue).sum() == i){
                System.out.println(i);
            }
        }
    }

    public static List<Integer> 求因子(int a) {
        List<Integer> yz = new ArrayList<>();
        for (int i = 1; i < a; i++) {
            if (a % i == 0) {
                yz.add(i);
            }
        }
        return yz;
    }
}
