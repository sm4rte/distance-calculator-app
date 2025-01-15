package kz.qaj.distance_calculator_app.exception;

public class RouteExistsException extends RuntimeException {

    private final MyServerErrorCode myServerErrorCode;


    public RouteExistsException(MyServerErrorCode myServerErrorCode) {
        super(myServerErrorCode.getMessage());
        this.myServerErrorCode = myServerErrorCode;
    }

    public MyServerErrorCode getServerError() {
        return myServerErrorCode;
    }

}
