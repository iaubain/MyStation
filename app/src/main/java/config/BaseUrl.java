package config;

import entities.Pump;

/**
 * Created by Hp on 11/16/2016.
 */
public class BaseUrl {
    private static final String BASE_URL="http://41.74.172.132:8080/EngenPayFuel/";
    public static final String LOGIN_URL=BASE_URL+"AndroidWebService/pos/login";
    public static final String REGISTER_URL=BASE_URL+"DeviceManagementService/device/register";
    public static final String GET_PUMPS_URL=BASE_URL+"PumpManagementService/pumpsbyuser/";
    public static final String GET_PAYMENTS_URL=BASE_URL+"PaymentModeManagementService/paymentmodes/";
}
