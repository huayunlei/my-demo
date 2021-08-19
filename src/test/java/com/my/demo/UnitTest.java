package com.my.demo;


import cn.hutool.core.util.StrUtil;
import com.my.demo.bean.copy.BeanUtilCopy;
import com.my.demo.bean.copy.demo.SexEnum;
import com.my.demo.bean.copy.demo.UserDO;
import com.my.demo.bean.copy.demo.UserVO;
import com.my.demo.util.AESUtil;
import com.my.demo.util.LocalDateUtil;
import com.my.demo.util.RSAEncrypt;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.time.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author hyl
 * @create 2020-05-15
 * @version:
 */
@Slf4j
public class UnitTest {

    @Test
    public void beanCopy() {
        List<UserDO> userDOList = new ArrayList();
        userDOList.add(new UserDO(1L, "Van", 18, 1));
        userDOList.add(new UserDO(2L, "VanVan", 20, 2));

        List<UserVO> userVOList = BeanUtilCopy.copyListProperties(userDOList, UserVO::new);
        log.info("userVOList:{}",userVOList);

        List<UserVO> UserVOOList = BeanUtilCopy.copyListProperties(userDOList, UserVO::new, (userDo, userVo) -> {
            // 这里可以定义特定的转换规则
            userVo.setSex(SexEnum.getDescByCode(userDo.getSex()).getDesc());
        });

        log.info("UserVOOList:{}",UserVOOList);

    }

    @Test
    public void streamTest() {
        int size = 1000000;
        List<Integer> ints = new ArrayList<>(size);
        for (int i=0;i<size;i++) {
            ints.add(i);
        }

        AtomicInteger a = new AtomicInteger();
        AtomicInteger b = new AtomicInteger();

        long begin2 = System.currentTimeMillis();
        Stream<Integer> evenIntegers = ints.stream()
                .filter(i -> i.intValue() % 2 == 0);
        Stream<Integer> oddIntegers = ints.stream()
                .filter(i -> i.intValue() % 2 != 0);
        evenIntegers.forEach(i -> b.set(i * 2));
        oddIntegers.forEach(i -> b.set(i * 2));
        System.out.println("bbbbbbbbb " + (System.currentTimeMillis()-begin2));

        long begin1 = System.currentTimeMillis();
        ints.stream()
                .forEach(i -> {
                    if (i.intValue() % 2 == 0) {
                        a.set(i * 2);
                    } else {
                        a.set(i * 2);;
                    }
                });
        System.out.println("aaaaaaaaaa " + (System.currentTimeMillis()-begin1));
    }

    @Test
    public void teststr() {
        String s = " Request URI:/homepage/getPrivZoneInfo.do, Request Method:HomePageNewController.getPrivZoneInfo(..), params:{\"homeId\":1,\"pageNo\":3,\"zoneId\":82}, response:{\"data\":{\"zoneBenefitList\":[{\"benefitShopSkus\":[{\"availableAmount\":100,\"couponAmount\":10,\"couponId\":1908,\"couponType\":\"1\",\"expire\":false,\"iconUrl\":\"http://memberwsdl-test.vivo.com.cn/memberwsdl.vivo.com.cn/icon/2020/06/2020062316204391785856.jpg\",\"id\":866,\"name\":\"商城优惠券\",\"requiredLevel\":2,\"skuList\":[{\"iconUrl\":\"http://memberwsdl-test.vivo.com.cn/memberwsdl.vivo.com.cn/icon/2020/07/2020072315085225216594.jpg\"},{\"iconUrl\":\"http://memberwsdl-test.vivo.com.cn/memberwsdl.vivo.com.cn/icon/2020/07/2020072316253964824490.gif\"},{\"iconUrl\":\"http://memberwsdl-test.vivo.com.cn/memberwsdl.vivo.com.cn/icon/2020/07/2020072315092989492339.jpg\"}],\"targetUrl\":\"vivomarket://details?id=ctrip.android.view\",\"userBenefitDto\":{\"amount\":\"10.00\",\"available\":true,\"benefit\":{\"amount\":\"10.00\",\"available\":true,\"brief\":\"减20-XXX专用\",\"description\":\"减20-XXX专用\",\"endTime\":1645948256000,\"expireOffset\":30,\"expireType\":1,\"giftType\":5,\"iconUrl\":\"http://membermng.test.vivo.xyz/img/upload/icon/2019/05/2019052715512153060195.png\",\"iconUrlNew\":\"http://memberwsdl-test.vivo.com.cn/memberwsdl.vivo.com.cn/icon/2020/06/2020062316204391785856.jpg\",\"id\":866,\"launchApp\":0,\"multiRecv\":0,\"name\":\"商城优惠券\",\"relatedAppName\":\"ctrip.android.view\",\"requiredLevel\":2,\"showOrder\":12,\"startTime\":1558943454000,\"stickFlag\":0,\"stockDisplay\":1,\"subBenefit\":false,\"targetUrl\":\"\",\"type\":0},\"benefitId\":866,\"brief\":\"减20-XXX专用\",\"description\":\"减20-XXX专用\",\"expire\":false,\"expireOffset\":30,\"expireType\":1,\"giftType\":5,\"iconUrl\":\"http://membermng.test.vivo.xyz/img/upload/icon/2019/05/2019052715512153060195.png\",\"iconUrlNew\":\"http://memberwsdl-test.vivo.com.cn/memberwsdl.vivo.com.cn/icon/2020/06/2020062316204391785856.jpg\",\"id\":866,\"launchApp\":0,\"multiRecv\":0,\"name\":\"商城优惠券\",\"relatedAppName\":\"ctrip.android.view\",\"requiredLevel\":2,\"showOrder\":12,\"stickFlag\":0,\"stockAvailable\":\"956\",\"stockDisplay\":1,\"stockPercent\":0.956,\"stockUsed\":\"44\",\"subBenefit\":false,\"targetUrl\":\"vivomarket://details?id=ctrip.android.view\",\"userStatus\":3,\"userStatusOrder\":3},\"userStatus\":3},{\"availableAmount\":10000,\"couponAmount\":3000,\"couponId\":1970,\"couponType\":\"1\",\"expire\":false,\"iconUrl\":\"http://memberwsdl-test.vivo.com.cn/memberwsdl.vivo.com.cn/icon/2020/06/2020062316200699367718.jpg\",\"id\":1140,\"name\":\"100代金券\",\"requiredLevel\":2,\"skuList\":[{\"iconUrl\":\"http://memberwsdl-test.vivo.com.cn/memberwsdl.vivo.com.cn/icon/2020/07/2020072315030647985017.jpg\"},{\"iconUrl\":\"http://memberwsdl-test.vivo.com.cn/memberwsdl.vivo.com.cn/icon/2020/07/2020072316002854344174.jpg\"},{\"iconUrl\":\"http://memberwsdl-test.vivo.com.cn/memberwsdl.vivo.com.cn/icon/2020/07/2020072316002861362971.jpg\"}],\"targetUrl\":\"ireader://com.chaozh.iReaderFree/openurl?url=https%3a%2f%2fah2.zhangyue.com%2fzyam%2fapp%2fapp.php%3fca%3dActivity_Welfare.Index%26actId%3d6410%26a0%3dpush_a_Y_vivo100%26backToAd%3dtrue\",\"userBenefitDto\":{\"amount\":\"3000.00\",\"available\":true,\"benefit\":{\"amount\":\"3000.00\",\"available\":true,\"brief\":\"电子书100代金券\",\"description\":\"【福利内容】电子书100代金券；\\r\\n【使用说明】领取后点击立即使用，在阅读书籍时付费抵扣；\\r\\n【有效期】领取后7日内使用有效\",\"endTime\":1608134399000,\"expireOffset\":99,\"expireType\":1,\"giftType\":5,\"iconUrl\":\"http://memberwsdl-test.vivo.com.cn/memberwsdl.vivo.com.cn/icon/2019/11/2019111809330628034619.png\",\"iconUrlNew\":\"http://memberwsdl-test.vivo.com.cn/memberwsdl.vivo.com.cn/icon/2020/06/2020062316200699367718.jpg\",\"id\":1140,\"launchApp\":0,\"multiRecv\":0,\"name\":\"100代金券\",\"relatedAppName\":\"\",\"requiredLevel\":2,\"showOrder\":104,\"startTime\":1574040751000,\"stickFlag\":0,\"stockDisplay\":1,\"subBenefit\":false,\"targetUrl\":\"ireader://com.chaozh.iReaderFree/openurl?url=https%3a%2f%2fah2.zhangyue.com%2fzyam%2fapp%2fapp.php%3fca%3dActivity_Welfare.Index%26actId%3d6410%26a0%3dpush_a_Y_vivo100%26backToAd%3dtrue\",\"type\":0},\"benefitId\":1140,\"brief\":\"电子书100代金券\",\"description\":\"【福利内容】电子书100代金券；\\r\\n【使用说明】领取后点击立即使用，在阅读书籍时付费抵扣；\\r\\n【有效期】领取后7日内使用有效\",\"expire\":false,\"expireOffset\":99,\"expireType\":1,\"giftType\":5,\"iconUrl\":\"http://memberwsdl-test.vivo.com.cn/memberwsdl.vivo.com.cn/icon/2019/11/2019111809330628034619.png\",\"iconUrlNew\":\"http://memberwsdl-test.vivo.com.cn/memberwsdl.vivo.com.cn/icon/2020/06/2020062316200699367718.jpg\",\"id\":1140,\"launchApp\":0,\"multiRecv\":0,\"name\":\"100代金券\",\"relatedAppName\":\"\",\"requiredLevel\":2,\"showOrder\":104,\"stickFlag\":0,\"stockAvailable\":\"1965\",\"stockDisplay\":1,\"stockPercent\":0.9825,\"stockUsed\":\"35\",\"subBenefit\":false,\"targetUrl\":\"ireader://com.chaozh.iReaderFree/openurl?url=https%3a%2f%2fah2.zhangyue.com%2fzyam%2fapp%2fapp.php%3fca%3dActivity_Welfare.Index%26actId%3d6410%26a0%3dpush_a_Y_vivo100%26backToAd%3dtrue\",\"userStatus\":3,\"userStatusOrder\":3},\"userStatus\":3},{\"availableAmount\":100,\"couponAmount\":99,\"couponId\":198,\"couponType\":\"1\",\"expire\":false,\"iconUrl\":\"http://memberwsdl-test.vivo.com.cn/memberwsdl.vivo.com.cn/icon/2020/06/2020062316195102142754.jpg\",\"id\":1116,\"name\":\"商城手机优惠券支持领\",\"requiredLevel\":2,\"skuList\":[{\"iconUrl\":\"http://memberwsdl-test.vivo.com.cn/memberwsdl.vivo.com.cn/icon/2020/07/2020072316132962457789.jpg\"},{\"iconUrl\":\"http://memberwsdl-test.vivo.com.cn/memberwsdl.vivo.com.cn/icon/2020/07/2020072316020220253053.jpg\"},{\"iconUrl\":\"http://memberwsdl-test.vivo.com.cn/memberwsdl.vivo.com.cn/icon/2020/07/2020072316020225665502.jpg\"}],\"targetUrl\":\"http://shop.vivo.com.cn/my/coupon?forbidVivoSpace=true\",\"userBenefitDto\":{\"amount\":\"99.99\",\"available\":true,\"benefit\":{\"amount\":\"99.99\",\"available\":true,\"brief\":\"满980减50元\",\"description\":\"【使用条件】在官方商城购物单品满980元即可使用；适用范围：Z3、 Z3X、iQOO 12+256、S1 4+128、S1 Pro 8+128；\\r\\n 【使用方法】领取后在商城提交订单时可使用；\\r\\n 【有效期】领取后7天内使用有效；\\r\\n 【备注】每用户可领取1次，秒杀商品不支持；优惠券不支持叠加。\",\"endTime\":1606406399000,\"expireOffset\":99,\"expireType\":1,\"giftType\":5,\"iconUrl\":\"http://membermng.test.vivo.xyz/img/upload/icon/2019/11/2019111417321870820752.png\",\"iconUrlNew\":\"http://memberwsdl-test.vivo.com.cn/memberwsdl.vivo.com.cn/icon/2020/06/2020062316195102142754.jpg\",\"id\":1116,\"launchApp\":0,\"multiRecv\":0,\"name\":\"商城手机优惠券支持领\",\"relatedAppName\":\"\",\"requiredLevel\":2,\"showOrder\":109,\"startTime\":1594102608000,\"stickFlag\":0,\"stockDisplay\":1,\"subBenefit\":false,\"targetUrl\":\"http://shop.vivo.com.cn/my/coupon?forbidVivoSpace=true\",\"type\":0},\"benefitId\":1116,\"brief\":\"满980减50元\",\"description\":\"【使用条件】在官方商城购物单品满980元即可使用；适用范围：Z3、 Z3X、iQOO 12+256、S1 4+128、S1 Pro 8+128；\\r\\n 【使用方法】领取后在商城提交订单时可使用；\\r\\n 【有效期】领取后7天内使用有效；\\r\\n 【备注】每用户可领取1次，秒杀商品不支持；优惠券不支持叠加。\",\"expire\":false,\"expireOffset\":99,\"expireType\":1,\"giftType\":5,\"iconUrl\":\"http://membermng.test.vivo.xyz/img/upload/icon/2019/11/2019111417321870820752.png\",\"iconUrlNew\":\"http://memberwsdl-test.vivo.com.cn/memberwsdl.vivo.com.cn/icon/2020/06/2020062316195102142754.jpg\",\"id\":1116,\"launchApp\":0,\"multiRecv\":0,\"name\":\"商城手机优惠券支持领\",\"relatedAppName\":\"\",\"requiredLevel\":2,\"showOrder\":109,\"stickFlag\":0,\"stockAvailable\":\"9936\",\"stockDisplay\":1,\"stockPercent\":0.9936,\"stockUsed\":\"64\",\"subBenefit\":false,\"targetUrl\":\"http://shop.vivo.com.cn/my/coupon?forbidVivoSpace=true\",\"userStatus\":3,\"userStatusOrder\":3},\"userStatus\":3},{\"couponAmountDoc\":\"为什么不展示我\",\"couponDiscount\":\"7.1\",\"couponId\":2013,\"couponType\":\"2\",\"expire\":false,\"iconUrl\":\"http://memberwsdl-test.vivo.com.cn/memberwsdl.vivo.com.cn/icon/2020/07/2020070916150140516242.jpg\",\"id\":1714,\"name\":\"371优惠券测试\",\"requiredLevel\":2,\"skuList\":[{\"iconUrl\":\"http://memberwsdl-test.vivo.com.cn/memberwsdl.vivo.com.cn/icon/2020/08/2020080317284749916230.png\"},{\"iconUrl\":\"http://memberwsdl-test.vivo.com.cn/memberwsdl.vivo.com.cn/icon/2020/08/2020080317284754966717.png\"},{\"iconUrl\":\"http://memberwsdl-test.vivo.com.cn/memberwsdl.vivo.com.cn/icon/2020/08/2020080317284760271891.png\"}],\"targetUrl\":\"http://shop.vivo.com.cn/my/coupon?forbidVivoSpace=true\",\"userBenefitDto\":{\"amount\":\"999.00\",\"available\":true,\"benefit\":{\"amount\":\"999.00\",\"available\":true,\"brief\":\"asfasf\",\"description\":\"safcsadvas\",\"endTime\":1614936120000,\"expireOffset\":30,\"expireType\":1,\"giftType\":5,\"iconUrl\":\"http://membermng.test.vivo.xyz/img/upload/icon/2019/03/2019030517222926830853.png\",\"iconUrlNew\":\"http://memberwsdl-test.vivo.com.cn/memberwsdl.vivo.co";

        System.out.println(s.length());

    }

    @Test
    public void testOptional() throws Exception {
        String str = null;
        String p = Optional.ofNullable(str).orElse("is nuul");
        System.out.println(p);

        Optional.ofNullable(str).orElseThrow(() -> new Exception(" is null null"));
    }

    @Test
    public void testLocaldate() {
//        LocalDate date = LocalDate.now();
//        System.out.println(date);
//        System.out.println(date.toString());
//
//        Date now = new Date();
//        Instant instant = now.toInstant();
//        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
//        LocalDate localDate = localDateTime.toLocalDate();
//        System.out.println(localDate);
//
//        LocalTime time = LocalTime.now();
//        System.out.println(time);

        System.out.println(LocalDateUtil.date2LocalDate(new Date()));

        System.out.println(LocalDateUtil.localDate2Date(LocalDate.now()));
    }


    @Test
    public void nullTest() {
        String longitude = null;
        String latitude = "ds";
        if (null != longitude && null == latitude || null == longitude && null != latitude) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
        long systime = System.currentTimeMillis();
        System.out.println(systime);
    }

    @Test
    public void bitTest() {
        int a = 50,b = 10;

        // 乘以 2n: s << n
        System.out.println(b << 2);
        // 除以 2n: s >> n
        System.out.println(b >> 2);
        // 交集: s & t
        System.out.println(a & b);
        // 并集: s | t
        System.out.println(a | b);


        // 取出最小非 0 位（Extract lowest set bit）: s & (-s)
        System.out.println(b & (-b));

    }

    private String resolverFirstPhone(String phone) {
        // 判断电话字段中6个字符之后是否有除了阿拉伯数字的特殊字符。若有，则调起电话键盘时，只输入出现首个特殊字符前的字段。
        // 例：“1234-12343244，110110110/222211111”，则点击调起电话时，电话中输入“1234-12343244”
        if (StrUtil.isNotEmpty(phone) && StrUtil.length(phone) > 6) {
            String result = phone.replaceAll("[^0-9]", "#");
            String sub = result.substring(6);
            if (StrUtil.contains(sub, '#')) {
                return StrUtil.sub(phone, 0, StrUtil.indexOf(sub, '#') + 6);
            }
        }
        return phone;
    }

    @Test
    public void strTest() {
        System.out.println(StrUtil.length(String.valueOf(System.currentTimeMillis())));
//        for (int i=0;i<100;i++) {
//            String no = getFlowNo("member_flowno");
//            System.out.println(no);
//            System.out.println(no.length());
//        }
    }

    public static String getFlowNo(String prefix) {
        //业务系统生成，必须保证唯一，建议“前缀+时间戳+随机生成的6位数字”
        return prefix + System.currentTimeMillis() + ThreadLocalRandom.current().nextInt(100000,1000000);
    }

    @Test
    public void aesDsaTest() {
        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh6XBD5LDVTlSu5CeUOFmz352URQUBVmLob0W92D4vuDfjrGWaGd/bc/UvXC9/7zyKXRg3G9F1mHisP2EGbZEHoK/v2g0FD+6C0VYBWwLfTF9RseWKX/fcWZXJ3/J3L8TzrGTPKLMbMZsWpb/8pF+fEf4siRq32M233ewVuATiumxuCne94qtDIcaM0smSE0/zrC7HB9JzoA8dzxCuGk3ac8ID9nPBnWaRTkPKs0QER2IdyHCEPFHjFKlHx9oMUmzDFabKsLhcoadfL0YMqmTo8CN9ssRhANmmytRIphOpkbAqGy1W7eKk5h1B3tiUXENa8F3O7R1zPgLon9jq0i0NQIDAQAB";
        String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCHpcEPksNVOVK7kJ5Q4WbPfnZRFBQFWYuhvRb3YPi+4N+OsZZoZ39tz9S9cL3/vPIpdGDcb0XWYeKw/YQZtkQegr+/aDQUP7oLRVgFbAt9MX1Gx5Ypf99xZlcnf8ncvxPOsZM8osxsxmxalv/ykX58R/iyJGrfYzbfd7BW4BOK6bG4Kd73iq0MhxozSyZITT/OsLscH0nOgDx3PEK4aTdpzwgP2c8GdZpFOQ8qzRARHYh3IcIQ8UeMUqUfH2gxSbMMVpsqwuFyhp18vRgyqZOjwI32yxGEA2abK1EimE6mRsCobLVbt4qTmHUHe2JRcQ1rwXc7tHXM+Auif2OrSLQ1AgMBAAECggEALllDm0eheKhKjY4/mhE6aOGkGdYLdTyAinYqt8IpnEcH/CDP7MKYdvQRk91G/nAYKnP6eLeInFT7+U/WaFav9eDlFcjMKrMPpbQ+/DNLxa44SQv/UhTAS2YVl4iBd+zOHszsK0bRJMWz1kxMDh0Ko/qPjocFAWlMIZUq6rRL2SW6ouGdZC3N5b6mCC86t7dvT9tgPpLcis6vlMFDTm1NzJLYrHcAEIEPkT/Qm7pr1EOM4gGLnVr7fTMJUBOxTADGf8b5NRkSjpL0GX1whdt52olmtpJPl4wRN+Ut/AHoyNS7jOOkNtSKr54k6XepYYu89c/FNWHPxFo30SQ3XdcngQKBgQDPTCa8Pj6JS0g3LLSdRzlyTbY87D+zlzMg5lHRk/vvKe1iF95cYDe5ivKcWwsL6g6Xa8ZDtnKkofwT2I4+A3Gf63p2vcU9SdBE4GOYNp7AQtQDXRjvag0JX8ucIQdU3adOELB/Y0cZduTVL4YiifNfnE62PynFEg97XpPipY7tYQKBgQCnhDvtawfdtC8UJs0CrVi6ENsHzsdq+8KOnTfzga7vN6eDVlAETPGc+ipCKVzYyFmjNT1ok46ezDhKEEOpd9IUGu7D8vX8U+iVLFTQB23AWAy7QYIMOM9n95aPUtmeMTuD0FhQyO4WLdQ2HLRQ+IiRaBfkFNik+dqgNYPat+PDVQKBgB6vUgiJWi5ug1kPeAjT8y/1SuWHvwIxfnL/RNs2AZPsa+QaTmL0JyuC4ti8LU1tLw3A+U8qoZu3Ep5JbvzE+7vFWpJbEug4leisInH0m9kvyzxYOomt9BIQKQ6KYRBx4wMOO2IP6wVc8RCRgVR5gRoFBC27+c3fF4WWgWOKSDbhAoGAUgjByCyn93xrqbSupOb7EnN6r8IlilCsIGcBpXAWmPNqRzkXimOb1sHzu97Bpy/GJdTGs9W+/CkKT8rTN8SDznC6CJ+MsvrsZVEGX7Gjn5P++BzGcPHJMCLhSmLKS1vUx2JaPYWIgrgCulUoQJbVWk/dk0ZaKcBpvhc28P8ovNkCgYADtlsxrK9cwoI2aXctDBvSOXwkFhRET1CyHtmSmmrE8fOhD1NW7UOtSe7rnx28lJhsR0Sw8sdtWHUIEOyLgFPWj8ceasNZ/+aArR/Z+jimGLKJpI7eGQdGOqL7jEy5mSiUITd/V+oHnlBwjGI4HSd7hxQTtippUzLjyNih55nM4A==";
        String aesKey = AESUtil.generateAesKey(privateKey);
        System.out.println("aesKey:"+aesKey);
        String encryptAes = RSAEncrypt.getEncryptKey(aesKey, publicKey);
        System.out.println("encryptAes:"+encryptAes);

        String content = "{\"homeId\":1}";
        // 加密
        String encryptData = AESUtil.encryptAesNew(content, aesKey);
        System.out.println("encryptData:"+encryptData);
        // 解密
        String origionAes = RSAEncrypt.getAesKey(encryptAes, privateKey);
        System.out.println("origionAes:"+origionAes);
        String origionData = AESUtil.decryptAesNew(encryptData, origionAes);
        System.out.println("origionData:"+origionData);

    }

    @Test
    public void leftRight() {
        int i = 3;
        int left = ( i + 5  - 1 ) % 5;
        int right = ( i + 1 ) % 5;
        System.out.println(String.format("left:%s, right:%s", left, right));
    }

    @Test
    public void byteTest() {
        System.out.println(1<<4);// 1* 2^4
        System.out.println(2<<4);// 2* 2^4

        System.out.println(1<<1);// 1* 2^1
        System.out.println(2<<1);// 2 *2^1
        System.out.println(3<<1);
        new HashMap(6);
    }

    @Test
    public void test7() throws Exception {
        Map<Integer, Integer> m = new HashMap(4);
        //获取HashMap整个类
        Class<?> mapType = m.getClass();
        //获取指定属性，也可以调用getDeclaredFields()方法获取属性数组
        Field threshold =  mapType.getDeclaredField("threshold");
        //将目标属性设置为可以访问
        threshold.setAccessible(true);
        //获取指定方法，因为HashMap没有容量这个属性，但是capacity方法会返回容量值
        Method capacity = mapType.getDeclaredMethod("capacity");
        //设置目标方法为可访问
        capacity.setAccessible(true);
        //打印刚初始化的HashMap的容量、阈值和元素数量
        System.out.println("容量："+capacity.invoke(m)+"    阈值："+threshold.get(m)+"    元素数量："+m.size());
    }

    @Test
    public void test8 () {
        Integer a = IntStream.rangeClosed(1, 10).sum();
        System.out.println(a);
    }

    public static void main(String[] args) {
        System.out.println("黄浦区".contains("黄浦sf"));
    }


}
