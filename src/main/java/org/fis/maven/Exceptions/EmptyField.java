package org.fis.maven.Exceptions;

public class EmptyField extends Exception{
    public EmptyField() {
        super("Field is empty");
    }
}
