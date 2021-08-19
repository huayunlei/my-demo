package com.my.demo.test;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.google.common.collect.Lists;
import com.my.demo.controller.MyTestController;
import com.my.demo.domain.User;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author hyl
 * @create 2020-09-01
 * @version: branch_member_20200601_v3_8
 */
@Slf4j
public class HutoolUtilTest {

    public static void testConvert() {
        //转换为字符串
        int a = 1;
        String aStr = Convert.toStr(a);
//转换为指定类型数组
        String[] b = {"1", "2", "3", "4"};
        Integer[] bArr = Convert.toIntArray(b);
        System.out.println(bArr);
//转换为日期对象
        String dateStr = "2017-05-06";
        Date date = Convert.toDate(dateStr);
        System.out.println(date);
//转换为列表
        String[] strArr = {"a", "b", "c", "d"};
        List<String> strList = Convert.toList(String.class, strArr);
        System.out.println(strList);
    }

    public static void testDateUtil() {
//Date、long、Calendar之间的相互转换
//当前时间
        Date date = DateUtil.date();
//Calendar转Date
        date = DateUtil.date(Calendar.getInstance());
//时间戳转Date
        date = DateUtil.date(System.currentTimeMillis());
//自动识别格式转换
        String dateStr = "2017-03-01";
        date = DateUtil.parse(dateStr);
//自定义格式化转换
        date = DateUtil.parse(dateStr, "yyyy-MM-dd");
//格式化输出日期
        String format = DateUtil.format(date, "yyyy-MM-dd");
//获得年的部分
        int year = DateUtil.year(date);
//获得月份，从0开始计数
        int month = DateUtil.month(date);
//获取某天的开始、结束时间
        Date beginOfDay = DateUtil.beginOfDay(date);
        Date endOfDay = DateUtil.endOfDay(date);
//计算偏移后的日期时间
        Date newDate = DateUtil.offset(date, DateField.DAY_OF_MONTH, 2);
//计算日期时间之间的偏移量
        long betweenDay = DateUtil.between(date, newDate, DateUnit.DAY);

    }

    public static void testStrUtil() {
//判断是否为空字符串
        String str = "test";
        StrUtil.isEmpty(str);
        StrUtil.isNotEmpty(str);
//去除字符串的前后缀
        StrUtil.removeSuffix("a.jpg", ".jpg");
        StrUtil.removePrefix("a.jpg", "a.");
//格式化字符串
        String template = "这只是个占位符:{}";
        String str2 = StrUtil.format(template, "我是占位符");

    }

    public static void testClassPathResource() throws IOException {
//获取定义在src/main/resources文件夹中的配置文件
        ClassPathResource resource = new ClassPathResource("generator.properties");
        Properties properties = new Properties();
        properties.load(resource.getStream());

    }

    public static void testReflectUtil() {
//获取某个类的所有方法
        Method[] methods = ReflectUtil.getMethods(User.class);
//获取某个类的指定方法
        Method method = ReflectUtil.getMethod(User.class, "getId");
//使用反射来创建对象
        User user = ReflectUtil.newInstance(User.class);
//反射执行对象的方法
        ReflectUtil.invoke(user, "setId", 1);

    }

    public static void testNumberUtil() {
        double n1 = 1.234;
        double n2 = 1.234;
        double result;
//对float、double、BigDecimal做加减乘除操作
        result = NumberUtil.add(n1, n2);
        result = NumberUtil.sub(n1, n2);
        result = NumberUtil.mul(n1, n2);
        result = NumberUtil.div(n1, n2);
//保留两位小数
        BigDecimal roundNum = NumberUtil.round(n1, 2);
        String n3 = "1.234";
//判断是否为数字、整数、浮点数
        NumberUtil.isNumber(n3);
        NumberUtil.isInteger(n3);
        NumberUtil.isDouble(n3);

    }

    public static void testBeanUtil() {
        User brand = new User();
        brand.setId(1);
        brand.setName("小米");
        brand.setAge(20);
//Bean转Map
        Map<String, Object> map = BeanUtil.beanToMap(brand);
        log.info("beanUtil bean to map:{}", map);
//Map转Bean
        User mapBrand = BeanUtil.mapToBean(map, User.class, false);
        log.info("beanUtil map to bean:{}", mapBrand);
//Bean属性拷贝
        User copyBrand = new User();
        BeanUtil.copyProperties(brand, copyBrand);
        log.info("beanUtil bean to bean:{}", copyBrand);
    }

    public static void testCollUtil() {
//数组转换为列表
        String[] array = new String[]{"a", "b", "c", "d", "e"};
//        List<String> a = Arrays.asList(array);
//        System.out.println(a);
//        List<String> b = Lists.newArrayList(array);
//        System.out.println(b);
        List<String> list = CollUtil.newArrayList(array);
        System.out.println(list);
//join：数组转字符串时添加连接符号
        String joinStr = CollUtil.join(list, ",");
        log.info("collUtil join:{}", joinStr);
//将以连接符号分隔的字符串再转换为列表
        List<String> splitList = StrUtil.split(joinStr, ',');
        log.info("collUtil split:{}", splitList);
//创建新的Map、Set、List
        HashMap<Object, Object> newMap = CollUtil.newHashMap();
        HashSet<Object> newHashSet = CollUtil.newHashSet();
        ArrayList<Object> newList = CollUtil.newArrayList();
//判断列表是否为空
        CollUtil.isEmpty(list);

        //将多个键值对加入到Map中
        Map<Object, Object> map = MapUtil.of(new String[][]{
                {"key1", "value1"},
                {"key2", "value2"},
                {"key3", "value3"}
        });
//判断Map是否为空
        MapUtil.isEmpty(map);
        MapUtil.isNotEmpty(map);

    }

    public static void testAnnotationUtil() {
//获取指定类、方法、字段、构造器上的注解列表
        Annotation[] annotationList = AnnotationUtil.getAnnotations(MyTestController.class, false);
        log.info("annotationUtil annotations:{}", annotationList);
//获取指定类型注解
        Api api = AnnotationUtil.getAnnotation(MyTestController.class, Api.class);
        log.info("annotationUtil api value:{}", api.value());
//获取指定类型注解的值
        Object annotationValue = AnnotationUtil.getAnnotationValue(MyTestController.class, RequestMapping.class);
        log.info("annotationUtil api annotationValue:{}", annotationValue);
    }

    public static void testSecureUtil() {
//MD5加密
        String str = "123456";
        String md5Str = SecureUtil.md5(str);
        log.info("secureUtil md5:{}", md5Str);

    }

    public static void testCaptchaUtil(HttpServletRequest request, HttpServletResponse response) {
//生成验证码图片
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        try {
            request.getSession().setAttribute("CAPTCHA_KEY", lineCaptcha.getCode());
            response.setContentType("image/png");//告诉浏览器输出内容为图片
            response.setHeader("Pragma", "No-cache");//禁止浏览器缓存
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            lineCaptcha.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        testCollUtil();
    }

}
