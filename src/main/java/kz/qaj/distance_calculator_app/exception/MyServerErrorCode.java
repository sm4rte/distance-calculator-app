package kz.qaj.distance_calculator_app.exception;

public enum MyServerErrorCode {

    ROUTE_EXISTS(1, "Route with this City found. Delete the routes first, then try again."),
    SAME_ROUTE_EXISTS(2, "Route with this parameters found, you can update it if you want"),
    CITY_NOT_FOUND(3, "City not found."),
    ROUTE_NOT_FOUND(4, "Route not found."),
    CITY_EXISTS(5, "City already exists with such name");


    private final int code;
    private final String message;

    MyServerErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

