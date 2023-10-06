package com.odc.ticket.tools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.usb.UsbDevice;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.odc.ticket.data.models.RatingModel;

import io.michaelrocks.libphonenumber.android.NumberParseException;
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil;
import io.michaelrocks.libphonenumber.android.Phonenumber;

public class AppUtils {

    public static boolean checkPermission(Context context, @NotNull String[] permissions) {
        boolean result = true;
        for (String permission: permissions) {
            int grantResult = context.checkCallingOrSelfPermission(permission);
            if(grantResult!= PackageManager.PERMISSION_GRANTED) result = false;
        }
        return  result;
    }

    public static boolean isNumeric(@NotNull String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public static String getPhoneNumber(String phoneNumberText,Context context){
        String result = null;
        try {
            PhoneNumberUtil phoneUtil = PhoneNumberUtil.createInstance(context);
            Phonenumber.PhoneNumber phoneNumber = phoneUtil.parse(phoneNumberText,"CD");
            result = phoneUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164);
        } catch (NumberParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean isPhoneNumber(String phoneNumberText,Context context){
        boolean result = false;
        try {
            PhoneNumberUtil phoneUtil = PhoneNumberUtil.createInstance(context);
            Phonenumber.PhoneNumber phoneNumber = phoneUtil.parse(phoneNumberText, "CD");
            result = phoneUtil.isValidNumber(phoneNumber);
        } catch (NumberParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean isEmail(@NotNull String email) {
        String regex = "^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    @NotNull
    public static String centerPadding(String first, String last, int length, char ch) {
        String word = first+last;
        return (length > word.length()) ? centerPadding(first,ch + last, length, ch) : word;
    }

    public static String rightPadding(@NotNull String word, int length, char ch) {
        return (length > word.length()) ? rightPadding(  word + ch, length, ch) : word;
    }

    public static void hideKeyboard(@NotNull Activity activity) {
        InputMethodManager methodManager = (InputMethodManager)activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if(methodManager!=null){
            View view = activity.getCurrentFocus()!=null?activity.getCurrentFocus():new View(activity);
            methodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void hideKeyboard(@NotNull Fragment fragment) {
        Activity activity = fragment.getActivity();
        if(activity!=null){
            hideKeyboard(activity);
        }
    }

    public static RatingModel getRating(String ratingText, @NotNull List<RatingModel> ratingModels) {
        RatingModel model = null;
        for (RatingModel ratingModel: ratingModels) {
            if(ratingText.equals(ratingModel.getTitle())){
                model = ratingModel;
                break;
            }
        }
        return model;
    }

    @SuppressLint("PrivateApi")
    public static void declinePhone() {

        try {

            String serviceManagerName = "android.os.ServiceManager";
            String serviceManagerNativeName = "android.os.ServiceManagerNative";
            String telephonyName = "com.android.internal.telephony.ITelephony";
            Class<?> telephonyClass;
            Class<?> telephonyStubClass;
            Class<?> serviceManagerClass;
            Class<?> serviceManagerNativeClass;
            Method telephonyEndCall;
            Object telephonyObject;
            Object serviceManagerObject;
            telephonyClass = Class.forName(telephonyName);
            telephonyStubClass = telephonyClass.getClasses()[0];
            serviceManagerClass = Class.forName(serviceManagerName);
            serviceManagerNativeClass = Class.forName(serviceManagerNativeName);
            Method getService = // getDefaults[29];
                    serviceManagerClass.getMethod("getService", String.class);
            Method tempInterfaceMethod = serviceManagerNativeClass.getMethod("asInterface", IBinder.class);
            Binder tmpBinder = new Binder();
            tmpBinder.attachInterface(null, "fake");
            serviceManagerObject = tempInterfaceMethod.invoke(null, tmpBinder);
            IBinder retBinder = (IBinder) getService.invoke(serviceManagerObject, "phone");
            Method serviceMethod = telephonyStubClass.getMethod("asInterface", IBinder.class);
            telephonyObject = serviceMethod.invoke(null, retBinder);
            telephonyEndCall = telephonyClass.getMethod("endCall");
            telephonyEndCall.invoke(telephonyObject);

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("unable", "msg cant disconnect call....");

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Contract(pure = true)
    public static UsbDevice findUsbDevice(String usb, @NotNull HashMap<String, UsbDevice> list) {
        if (list.size() >= 1){
            List<UsbDevice> models = new ArrayList<>(list.values());
            for(UsbDevice device:models){
                try{
                    String serialNumber = device.getSerialNumber();
                    if(Objects.equals(serialNumber, usb)) return device;
                } catch (Exception ignored){}
            }
        }
        return null;
    }
}
