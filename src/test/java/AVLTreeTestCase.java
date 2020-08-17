import com.chen.MyAVLTree;
import org.junit.Test;

import java.util.Random;

public class AVLTreeTestCase {

    @Test
    public void test1() {

        MyAVLTree myAVLTree = new MyAVLTree();
        Random random = new Random(3255);
        for (int i = 0; i < 7; i++) {

            int r = random.nextInt(1000);
            myAVLTree.insert(r);
            System.out.println(i);
        }

        myAVLTree.print();
    }

}
