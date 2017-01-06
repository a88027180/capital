package com.xiyoukeji.tools;

import com.xiyoukeji.beans.UserBean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by dasiy on 16/12/21.
 */
public class Utils {
    public Object copy(Object object) throws Exception {
        Class<?> classType = object.getClass();


        /* 生成新的对象的讨论
        // 获得Constructor对象,此处获取第一个无参数的构造方法的
        Constructor cons = classType.getConstructor(new Class[] {});//不带参数，所以传入一个为空的数组
        // 通过构造方法来生成一个对象
        Object obj = cons.newInstance(new Object[] {});

        // 以上两行代码等价于：
        Object obj11 = classType.newInstance();  // 这行代码无法处理构造函数有参数的情况

        //用第二个带参数的构造方法生成对象
        Constructor cons2 = classType.getConstructor(new Class[] {String.class, int.class});
        Object obj2 = cons2.newInstance(new Object[] {"ZhangSan",20});

        */

//        Object objectCopy = classType.getConstructor(new Class[]{}).newInstance(new Object[]{});

        //获得对象的所有成员变量
        UserBean user = new UserBean();
        Field[] fields = classType.getDeclaredFields();
        for (Field field : fields) {
            //获取成员变量的名字
            String name = field.getName();    //获取成员变量的名字，此处为id，name,age
            //System.out.println(name);

            //获取get和set方法的名字
            String firstLetter = name.substring(0, 1).toUpperCase();    //将属性的首字母转换为大写
            String getMethodName = "get" + firstLetter + name.substring(1);
            String setMethodName = "set" + firstLetter + name.substring(1);
            //System.out.println(getMethodName + "," + setMethodName);

            //获取方法对象
            Method getMethod = classType.getMethod(getMethodName, new Class[]{});
            Method setMethod = classType.getMethod(setMethodName, new Class[]{field.getType()});//注意set方法需要传入参数类型

            //调用get方法获取旧的对象的值
            Object value = getMethod.invoke(object, new Object[]{});
            //调用set方法将这个值复制到新的对象中去
            setMethod.invoke(user, new Object[]{value});

        }

        return user;

    }

    public static void reflectionAttr(Object class1, Object class2) throws Exception {
        Class clazz1 = Class.forName(class1.getClass().getName());
        Class clazz2 = Class.forName(class2.getClass().getName());
//      获取两个实体类的所有属性
        Field[] fields1 = clazz1.getDeclaredFields();
        Field[] fields2 = clazz2.getDeclaredFields();
        Utils cr = new Utils();
//      遍历class1Bean，获取逐个属性值，然后遍历class2Bean查找是否有相同的属性，如有相同则赋值
        for (Field f1 : fields1) {
//            if (f1.getName().equals("id"))
//                continue;
            Object value = cr.invokeGetMethod(class1, f1.getName(), null);
            for (Field f2 : fields2) {
                if (f1.getName().equals(f2.getName())) {
                    Object[] obj = new Object[1];
                    obj[0] = value;
                    cr.invokeSetMethod(class2, f2.getName(), obj);
                }
            }
        }

    }

    private Object invokeGetMethod(Object clazz, String fieldName, Object[] args) {
        String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Method method = null;
        try {
            method = Class.forName(clazz.getClass().getName()).getDeclaredMethod("get" + methodName);
            return method.invoke(clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private Object invokeSetMethod(Object clazz, String fieldName, Object[] args) {
        String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Method method = null;
        try {
            Class[] parameterTypes = new Class[1];
            Class c = Class.forName(clazz.getClass().getName());
            Field field = c.getDeclaredField(fieldName);
            parameterTypes[0] = field.getType();
            method = c.getDeclaredMethod("set" + methodName, parameterTypes);
            return method.invoke(clazz, args);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getQuarter() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        String v = year + "-" + (month / 3 + 1);
//        String v = year + "-" + (month / 3);
        return v;
    }

    public static String getTime() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        return time;
    }

    public static String getDate() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(date);
        return time;
    }

    public static String getCode(String time) {
        Date date = new Date();
        String result = "";
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date1 = format1.parse(time);
            DateFormat format = new SimpleDateFormat("yyyyMMdd");
            result = format.format(date1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
