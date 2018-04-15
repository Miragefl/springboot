package com.bhz.mail.config.database;

public class DataBaseContextHolder {

    public enum DataBaseType {
        MASTER, SLAVER
    }

    private final static ThreadLocal<DataBaseType> contextHolder = new ThreadLocal<DataBaseType>();

    public static void setDataBaseType(DataBaseType dataBaseType) {
        if (null == dataBaseType) {
            throw new NullPointerException();
        }
        contextHolder.set(dataBaseType);
    }

    public static DataBaseType getDataBaseType() {
        return contextHolder.get() == null ? DataBaseType.MASTER : contextHolder.get();
    }

    public static void clearDataBaseType() {
        contextHolder.remove();
    }
}
