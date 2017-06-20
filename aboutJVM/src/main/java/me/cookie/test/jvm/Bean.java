package me.cookie.test.jvm;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;

/**
 * Created by cookie on 2017/6/2.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bean {
    //不被序列化的字段
    public transient String name;
    public Integer value;

    public void writeObject(ObjectOutputStream out) throws IOException {
        System.out.println("writeObject");
        out.defaultWriteObject();
        out.writeUTF(name);
    }

    private Object readResolve() throws ObjectStreamException {
        // instead of the object we're on,
        // return the class variable INSTANCE
        System.out.println("readResolve");
        return new Bean();
    }
}
