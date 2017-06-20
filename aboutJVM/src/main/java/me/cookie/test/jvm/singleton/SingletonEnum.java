package me.cookie.test.jvm.singleton;

/**
 * Created by cookie on 2017/6/1.
 */
public enum SingletonEnum {

    Singleton("name","value");


    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    SingletonEnum(String name,String value){
        System.out.println("load");
    }
}
