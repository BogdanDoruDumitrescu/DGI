package org.fis.maven.Exceptions;

public class LowAmount extends Exception{
    public LowAmount() {
        super("Amount is under 50!");
    }
}
