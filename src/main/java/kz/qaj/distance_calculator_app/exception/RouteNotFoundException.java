package kz.qaj.distance_calculator_app.exception;

public class RouteNotFoundException extends RuntimeException {

    private final MyServerErrorCode myServerErrorCode;

    public RouteNotFoundException(MyServerErrorCode myServerErrorCode) {
        super(myServerErrorCode.getMessage());
        this.myServerErrorCode = myServerErrorCode;
    }

    public MyServerErrorCode getServerError() {
        return myServerErrorCode;
    }
}
