package net.dmorozov.numberconverter.domain;

public class IllegalParametersException extends RuntimeException {
    public IllegalParametersException(String message) {
        super(message);
    }

    public IllegalParametersException(Throwable cause) {
        super(cause);
    }
}
