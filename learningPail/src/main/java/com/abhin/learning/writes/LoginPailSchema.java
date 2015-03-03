package com.abhin.learning.writes;

import com.abhin.learning.model.Login;
import com.backtype.hadoop.pail.PailStructure;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by abhin on 3/2/15.
 */
public class LoginPailSchema implements PailStructure<Login> {

    @Override
    public boolean isValidTarget(String... var1){
        return true;
    }

    @Override
    public List<String> getTarget(Login object){
        return Collections.EMPTY_LIST;
    }

    @Override
    public Class getType(){
        return Login.class;
    }

    @Override
    public byte[] serialize(Login login){
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        DataOutputStream dataOut = new DataOutputStream(byteOut);

        byte[] userBytes = login.getUserName().getBytes();

        try {
            dataOut.writeInt(userBytes.length);
            dataOut.write(userBytes);
            dataOut.writeLong(login.getUnixTime());
            dataOut.close();
        }catch (IOException e){
            throw new RuntimeException();
        }

        return byteOut.toByteArray();
    }

    @Override
    public Login deserialize(byte[] serialized){
        DataInputStream dataIn = new DataInputStream(new ByteArrayInputStream(serialized));

        try {
            byte[] userBytes = new byte[dataIn.readInt()];
            dataIn.read(userBytes);
            return new Login(new String(userBytes), dataIn.readLong());
        }catch (IOException e){
            throw new RuntimeException();
        }
    }

}
