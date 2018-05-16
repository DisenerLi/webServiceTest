package com.bnq.common.zookeeper;

import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;

/**
 * Created by liqiang on 2018/5/9.
 */
public class MyZkSerializer implements ZkSerializer {
    @Override
    public byte[] serialize(Object data) throws ZkMarshallingError {
        return data.toString().getBytes();
    }

    @Override
    public Object deserialize(byte[] bytes) throws ZkMarshallingError {
        return new String(bytes);
    }
}
