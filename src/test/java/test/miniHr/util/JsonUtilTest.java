package test.miniHr.util;

import com.miniHr.entity.User;
import com.miniHr.util.JsonUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DELL on 2017/6/2.
 */
public class JsonUtilTest {

    @Test
    public void fromJson() throws Exception {
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("retCode","00");
        User user = new User();
        user.setId(1);
        user.setAge(3);
        map.put("retData",user);

        System.out.println(JsonUtil.toJson(map));

    }

    @Test
    public void fromJson1() throws Exception {

    }

    @Test
    public void toJson() throws Exception {

    }

    @Test
    public void toJson1() throws Exception {

    }
}