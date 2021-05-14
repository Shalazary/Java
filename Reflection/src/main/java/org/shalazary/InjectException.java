package org.shalazary;

public class InjectException extends RuntimeException {
    public InjectException(){
        super();
    }

    public InjectException(String massage){
        super(massage);
    }
}
