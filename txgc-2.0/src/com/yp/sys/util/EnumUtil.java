package com.yp.sys.util;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yp.sys.common.BaseEnum;


/**
 * 枚举工具类
 * 
 * @author shijian
 */
@SuppressWarnings("all")
public class EnumUtil {

    /**
     * 将BaseEnum.getId()作为Key,BaseEnum.getName()作为value,存放在Map中并返回
     * @param <T>
     * @param values
     * @return 
     * @return 
     * @return 
     * @return
     */
    public static List<? extends BaseEnum> toList(Class<? extends BaseEnum> enumClass) {
    	return Arrays.asList(enumClass.getEnumConstants());         
    }

    /**
     * 将BaseEnum.getId()作为Key,BaseEnum.getName()作为value,存放在Map中并返回
     * @param <T>
     * @param values
     * @return
     */
    public static <T extends BaseEnum> LinkedHashMap toMap(Class<? extends BaseEnum> enumClass) {
        return toMap(enumClass.getEnumConstants());
    }
    
    /**
     * 将BaseEnum.getId()作为Key,BaseEnum.getName()作为value,存放在Map中并返回
     * @param <T>
     * @param values
     * @return
     */
    public static  <T extends BaseEnum> LinkedHashMap toMap(T[] values) {
        LinkedHashMap map = new LinkedHashMap();
        for(BaseEnum item : values) {
            map.put(item.getId(), item.getName());
        }
        return map;
    }
    
    public static <T extends BaseEnum<K>,K> K getId(T enumValue) {
        if(enumValue == null) return null;
        return enumValue.getId();
    }
    
    public static <T extends BaseEnum> String getName(T enumValue) {
        if(enumValue == null) return null;
        return enumValue.getName();
    }
   
//   public static <T extends BaseEnum> String getName(T enumValue) {
//       if(enumValue == null) return null;
//       return enumValue.getName();
//   }
//
//   public static <T extends Enum> String getName(T enumValue) {
//       if(enumValue == null) return null;
//       return enumValue.name();
//   }

   /**
    * 根据id查找得到Enum
    * @param id
    * @param values
    * @return
    */
   public static <T extends BaseEnum> T getById(Object id,Class<? extends BaseEnum> enumClass) {
       return (T)getById(id, enumClass.getEnumConstants());
   }
   

   public static String getNameById(Object id,Class<? extends BaseEnum> enumClass) {
       return getById(id, enumClass.getEnumConstants()).getName();
   }
   
   /**
    * 根据id查找得到Enum
    * @param id
    * @param values
    * @return
    */
   public static <T extends BaseEnum> T getById(Object id,T[] values) {
       if(id == null) return null;
       if(id instanceof String && StringUtils.isBlank((String)id)) return null;
       
       for (T item : values) {
            if (item.getId().equals(id)) {
                return item;
            }
       }
       return null;
   }

   /**
    * 根据id得到Enum,找不到则抛异常.如果id为null或者是空字符串,则返回null
    * @throws IllegalArgumentException 根据id得到Enum,找不到则抛异常
    */
   public static <T extends BaseEnum> T getRequiredById(Object id,Class<? extends BaseEnum> enumClass) {
       return (T)getRequiredById(id, enumClass.getEnumConstants());
   }
   
   /**
    * 根据id得到Enum,找不到则抛异常.如果id为null或者是空字符串,则返回null
    * @param <T>
    * @param id
    * @param values
    * @return
    * @throws IllegalArgumentException 根据id得到Enum,找不到则抛异常
    */
   public static <T extends BaseEnum> T getRequiredById(Object id,T[] values) throws IllegalArgumentException {
       if(id == null) return null;
       if(id instanceof String && StringUtils.isBlank((String)id)) return null;
       
       BaseEnum v = getById(id,values);
       if(v == null) {
           if(values.length > 0) {
               String className = values[0].getClass().getName();
               throw new IllegalArgumentException("not found Enum:"+className+" value by id:"+id);
           }else {
               throw new IllegalArgumentException("not found Enum by id:"+id);
           }
       }
       return (T)v;
   }
   
}

