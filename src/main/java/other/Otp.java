package other;

import java.util.Random;

public class Otp {
    private static final int OTP_LENGTH = 4;

    public static String generateOtp(String userId) {
        String otp = String.format("%0" + OTP_LENGTH + "d", new Random().nextInt((int) Math.pow(10, OTP_LENGTH)));

        return otp;
    }
}
