package kz.qaj.distance_calculator_app.exception;

public class CityAlreadyExistsException extends RuntimeException {

    private final MyServerErrorCode myServerErrorCode;

    public CityAlreadyExistsException(MyServerErrorCode myServerErrorCode) {
        super(myServerErrorCode.getMessage());
        this.myServerErrorCode = myServerErrorCode;
    }

    public MyServerErrorCode getServerError() {
        return myServerErrorCode;
    }
}
