package com.xiyoukeji.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wangqiyun on 16/8/13.
 */

/**
 * @deprecated
 */
public class Core {
    public static <T> T assignSrc(T dest, Object src) {
        Class srcClass = src.getClass(), destClass = dest.getClass();
        Annotation srcClassDeclaredAnnotation = srcClass.getDeclaredAnnotation(AssignType.class);
        for (Field field : srcClass.getDeclaredFields()) {
            Assign assign = field.getDeclaredAnnotation(Assign.class);
            NotAssign notAssign = field.getDeclaredAnnotation(NotAssign.class);
            AssignIF assignIF = checkAssign(srcClassDeclaredAnnotation, assign, notAssign);
            EmptyAssign emptyAssign = field.getDeclaredAnnotation(EmptyAssign.class);
            if (assignIF != null) {
                try {
                    PropertyDescriptor pd = new PropertyDescriptor(field.getName(), srcClass);
                    Method readMethod = pd.getReadMethod();
                    Object srcValue = readMethod.invoke(src);
                    pd = getDestFieldProperty(assignIF, field, destClass);
                    if (srcValue instanceof Collection) {
                        Object destValue = pd.getReadMethod().invoke(dest);
                        if (destValue instanceof Collection) {
                            if (emptyAssign != null)
                                ((Collection) destValue).clear();
                            ((Collection) srcValue).removeAll(NULL);
                            ((Collection) destValue).addAll((Collection) srcValue);
                        }
                    } else if (assignIF.isNull() || srcValue != null) {
                        pd.getWriteMethod().invoke(dest, srcValue);
                    }
                } catch (Exception e) {
                }
            }
        }
        return dest;
    }

    public static <T> T assignDest(T dest, Object src, Set<String> remove, String base) {
        Class srcClass = src.getClass(), destClass = dest.getClass();
        Annotation destClassDeclaredAnnotation = destClass.getDeclaredAnnotation(AssignType.class);
        for (Field field : destClass.getDeclaredFields()) {
            Assign assign = field.getDeclaredAnnotation(Assign.class);
            NotAssign notAssign = field.getDeclaredAnnotation(NotAssign.class);
            AssignIF assignIF = checkAssign(destClassDeclaredAnnotation, assign, notAssign);
            if (assignIF != null && !remove.contains(base + field.getName())) {
                try {
                    PropertyDescriptor pd = getDestFieldProperty(assignIF, field, srcClass);
                    Method readMethod = pd.getReadMethod();
                    Object srcValue = readMethod.invoke(src);
                    pd = new PropertyDescriptor(field.getName(), destClass);
                    if (srcValue instanceof Collection) {
                        Object destValue = pd.getReadMethod().invoke(dest);
                        if (destValue instanceof Collection) {
                            Class destFieldClass = null;
                            Type genericType = field.getGenericType();
                            if (genericType != null && genericType instanceof ParameterizedType) {
                                destFieldClass = (Class) (((ParameterizedType) genericType).getActualTypeArguments()[0]);
                            }
                            AssignClass assignClass = field.getAnnotation(AssignClass.class);
                            if (assignClass != null)
                                destFieldClass = assignClass.value();
                            for (Object srcTmp : (Collection) srcValue) {
                                if (srcTmp != null) {
                                    if (destFieldClass == null || destFieldClass.equals(srcTmp.getClass()) || !checkUserClass(srcTmp.getClass()))
                                        ((Collection) destValue).add(srcTmp);
                                    else {
                                        Object destTmp = destFieldClass.newInstance();
                                        assignDest(destTmp, srcTmp, remove, base + field.getName() + ".");
                                        ((Collection) destValue).add(destTmp);
                                    }
                                }
                            }
                        }
                    } else {
                        if (srcValue != null) {
                            Class destFieldClass = field.getType();
                            AssignClass assignClass = field.getAnnotation(AssignClass.class);
                            if (assignClass != null)
                                destFieldClass = assignClass.value();
                            if (!checkUserClass(srcValue.getClass()) || srcValue.getClass().equals(destFieldClass))
                                pd.getWriteMethod().invoke(dest, srcValue);
                            else {
                                Object destValue = field.getType().newInstance();
                                assignDest(destValue, srcValue, remove, base + field.getName() + ".");
                                pd.getWriteMethod().invoke(dest, destValue);
                            }
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
        return dest;
    }

    public static <T> T assignDest(T dest, Object src, Collection<String> remove) {
        Set<String> t_remove = new HashSet<String>();
        t_remove.addAll(remove);
        return assignDest(dest, src, t_remove, "");
    }

    public static <T> T assignDest(T dest, Object src) {
        return assignDest(dest, src, new HashSet<String>());
    }

    private final static Set<String> types = new HashSet<String>();

    static {
        types.add("java.lang.Integer");
        types.add("java.lang.Double");
        types.add("java.lang.Float");
        types.add("java.lang.Long");
        types.add("java.lang.Short");
        types.add("java.lang.Byte");
        types.add("java.lang.Boolean");
        types.add("java.lang.Character");
        types.add("java.lang.String");
    }

    public static boolean checkUserClass(Class c) {
        return !(c.isEnum() || c.isPrimitive() || types.contains(c.getName()));
    }

    private static AssignIF checkAssign(Annotation annotation, Assign assign, NotAssign notAssign) {
        AssignIF assignIF = null;
        if (notAssign == null) {
            if (assign != null) {
                assignIF = new AssignIF(assign.value(), assign.isNull());
            } else if (annotation != null) {
                assignIF = new AssignIF(null, false);
            }
        }
        return assignIF;
    }

    private static PropertyDescriptor getDestFieldProperty(AssignIF assign, Field field, Class destClass) throws IntrospectionException {
        String destFieldName;
        if (assign.value() == null)
            destFieldName = field.getName();
        else
            destFieldName = assign.value();
        return new PropertyDescriptor(destFieldName, destClass);
    }

    private static Collection NULL = Collections.singleton(null);
}
