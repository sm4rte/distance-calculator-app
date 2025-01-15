package kz.qaj.distance_calculator_app.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MyException extends RuntimeException {

    private String message;

    public MyException(String message) {
        super(message);
        this.message = message;
    }
}
