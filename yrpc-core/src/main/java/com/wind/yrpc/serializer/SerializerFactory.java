package com.wind.yrpc.serializer;

import com.wind.yrpc.spi.SpiLoader;

import java.util.Map;

/**
 * 序列化器工厂（用于获取序列化器对象）
 *
 * @author wind
 */
public class SerializerFactory {

    static {
        Map<String, Class<?>> load = SpiLoader.load(Serializer.class);
//        SpiLoader.loadAll();
    }

//    /**
//     * 序列化映射（用于实现单例）
//     */
//    public static final Map<String,Serializer> KEY_SERIALIZER_MAP = new HashMap<String,Serializer>(){{
//        put(SerializerKeys.JDK,new JdkSerializer());
//        put(SerializerKeys.JSON,new JsonSerializer());
//        put(SerializerKeys.HESSIAN,new HessianSerializer());
//        put(SerializerKeys.KRYO,new KryoSerializer());
//    }};

    /**
     * 默认序列化器
     */
    private static final Serializer DEFAULT_SERIALIZER = new JdkSerializer();

    /**
     * 获取实例
     *
     * @param key
     * @return
     */
    public static Serializer getInstance(String key){

//        return KEY_SERIALIZER_MAP.getOrDefault(key,DEFAULT_SERIALIZER);
        return SpiLoader.getInstance(Serializer.class,key);
    }

}
