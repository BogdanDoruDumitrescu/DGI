package org.fis.maven.Exceptions;

public class AlreadyExists extends Exception{
    public AlreadyExists(){
        super("Account already exists!");
    }
}
