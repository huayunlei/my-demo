package com.my.demo.bean.copy.orika;

import com.google.common.collect.Maps;
import com.my.demo.bean.copy.demo.UserDO;
import com.my.demo.bean.copy.demo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @version:
 */
@Slf4j
public class OrikaBeanMapperTest {

    @Test
    public void objMap () {
        UserDO userDO = new UserDO(1L, "Van", 18, 1);
        UserVO userVO = OrikaBeanMapper.INSTANCE.map(UserVO.class, userDO);
        log.info("userVO:{}",userVO);
    }

    @Test
    public void objMapConfig () {
        UserDO userDO = new UserDO(1L, "Van", 18, 1);

        Map<String, String> map = Maps.newHashMap();
        map.put("userName", "name");

        UserVO userVO = OrikaBeanMapper.INSTANCE.map(UserVO.class, userDO, map);
        log.info("userVO:{}",userVO);
    }

    @Test
    public void listMap () {
        List<UserDO> userDOList = new ArrayList();
        userDOList.add(new UserDO(1L, "Van", 18, 1));
        userDOList.add(new UserDO(2L, "VanVan", 20, 2));

        List<UserVO> userVOList = OrikaBeanMapper.INSTANCE.mapAsList(UserVO.class, userDOList);
        log.info("userVOList:{}",userVOList);
    }

    @Test
    public void listMapConfig () {
        List<UserDO> userDOList = new ArrayList();
        userDOList.add(new UserDO(1L, "Van", 18, 1));
        userDOList.add(new UserDO(2L, "VanVan", 20, 2));

        Map<String, String> map = Maps.newHashMap();
        map.put("userName", "name");

        List<UserVO> userVOList = OrikaBeanMapper.INSTANCE.mapAsList(UserVO.class, userDOList, map);
        log.info("userVOList:{}",userVOList);
    }


}
