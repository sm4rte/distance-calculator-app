package kz.qaj.distance_calculator_app.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyErrorResponse {

    private int status;
    private String message;
    private int code;

    /*public MyErrorResponse(String message) {
        super();
        this.message = message;
    }*/
}
