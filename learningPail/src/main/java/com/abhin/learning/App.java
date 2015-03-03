package com.abhin.learning;

import com.abhin.learning.model.Login;
import com.abhin.learning.writes.LoginPailSchema;
import com.backtype.hadoop.pail.Pail;

import java.io.IOException;

/**
 * Hello world!
 *
 * This is a simple program to use pail API's to write and read the schema.
 *
 * Create a jar
 *
 * mvn clean compile assembly:single
 *
 * Transfer the jar to the grid gateway and then run
 *
 * hadoop jar *.jar com.abhin.learning.App
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException{
        writeLogin();
        readLogin();
    }


    public static void writeLogin() throws IOException{
        Pail<Login> loginPail = Pail.create("/user/abhin/nathan/login", new LoginPailSchema());
        Pail.TypedRecordOutputStream out = loginPail.openWrite();

        out.writeObject(new Login("abhinav", 123456789));
        out.writeObject(new Login("supriya", 987654321));

        out.close();
    }

    public static void readLogin() throws IOException{
        Pail<Login> loginPail = new Pail<Login>("/user/abhin/nathan/login");
        for(Login l : loginPail)
            System.out.println(l.toString());
    }

    public static void simpleIO() throws IOException{
        Pail pail = Pail.create("/user/abhin/nathan/mypail");
        Pail.TypedRecordOutputStream os = pail.openWrite();

        os.writeObject(new byte[]{1,2,3});
        os.writeObject(new byte[]{1,2,3,4});
        os.writeObject(new byte[]{1,2,3,4,5});

        os.close();
    }

}
