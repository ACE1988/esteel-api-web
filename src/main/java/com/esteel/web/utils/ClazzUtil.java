package com.esteel.web.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClazzUtil {
    public static List<Field> getClassFieldList(Class<?> clazz) {
        List<Field> list = new ArrayList<>();
        do {
            Collections.addAll(list, clazz.getDeclaredFields());
            clazz = clazz.getSuperclass();
        } while (clazz != Object.class && clazz != null);
        return list;
    }

    public static Object getField(Object bean, Field field) {
        try {
            PropertyDescriptor pd = new PropertyDescriptor(field.getName(), bean.getClass());
            Method method = pd.getReadMethod();
            return method.invoke(bean);
        } catch (IllegalAccessException | IntrospectionException | InvocationTargetException e) {
            return null;
        }
    }
}