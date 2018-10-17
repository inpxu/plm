import com.zhiyun.internal.plm.MattersStoreDto;
import com.zhiyun.internal.plm.PlmService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author 邓艺
 * @version v1.0
 * @date 2018-10-17 13:49
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PlmServiceImplTest {
    @Autowired
    private PlmService plmService;

    @Test
    public void demo() {
        List<MattersStoreDto> mattersStoreDtos = plmService.queryAllMatters(234411L);
        System.out.println(mattersStoreDtos);
    }

}