package kz.qaj.distance_calculator_app.exception;

public class CityNotFoundException extends RuntimeException {

    private final MyServerErrorCode myServerErrorCode;

    public CityNotFoundException(MyServerErrorCode myServerErrorCode) {
        super(myServerErrorCode.getMessage());
        this.myServerErrorCode = myServerErrorCode;
    }

    public MyServerErrorCode getServerError() {
        return myServerErrorCode;
    }
}
