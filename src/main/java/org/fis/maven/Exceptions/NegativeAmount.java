package org.fis.maven.Exceptions;

public class NegativeAmount extends Exception{
    public NegativeAmount() {
        super("The amount is below 0!");
    }
}
