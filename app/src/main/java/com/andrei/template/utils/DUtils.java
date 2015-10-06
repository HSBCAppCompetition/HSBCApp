package com.andrei.template.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import com.bgmenu.android.R;
import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.security.auth.x500.X500Principal;

import roboguice.util.Ln;

public class DUtils {


    private static String PREFS_NAME = "PREFERENCES";


    public static void caveman(Object message) {
        if (message != null) {
            Ln.e("♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦");
            Ln.v(String.valueOf(message));
            Ln.e("♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦");
        } else {
            Ln.e("♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦");
            Ln.v("NULL ♦ NULL ♦ NULL ♦ NULL ♦ NULL ♦ NULL ♦ NULL ♦ NULL ♦ NULL ♦ NULL ♦ NULL ♦ NULL ♦ NULL ♦ NULL ♦ NULL ♦ NULL ♦ NULL ♦ NULL ♦ NULL ♦ NULL ♦ NULL ♦ NULL ♦ NULL ♦ NULL");
            Ln.e("♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦");
        }
    }


    /**
     * Force show softKeyboard.
     */
    public static void forceShow(@NonNull Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    /**
     * Force hide softKeyboard.
     */
    public static void forceHide(@NonNull Activity activity, @NonNull EditText editText) {
        if (activity.getCurrentFocus() == null || !(activity.getCurrentFocus() instanceof EditText)) {
            editText.requestFocus();
        }
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public static String getStreetCityName(Context ctx, LatLng location) {
        Geocoder geoCoder = new Geocoder(ctx, Locale.getDefault());
        int maxResults = 5;
        try {
            List<Address> addresses = geoCoder.getFromLocation(
                    (location.latitude),
                    (location.longitude), maxResults);
            String street = "";
            String city = "";
            if (addresses.size() > 0) {
                street = addresses.get(0).getAddressLine(0);
                city = addresses.get(0).getLocality();
                addresses.clear();
                return street + ", " + city;
            } else {
                try {
                    addresses = ReverseGeocode.getFromLocation(
                            (location.latitude),
                            (location.longitude), maxResults);
                    if (addresses != null && addresses.size() > 0) {
                        street = addresses.get(0).getAddressLine(0);
                        city = addresses.get(0).getLocality();
                        return street + ", " + city;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return "";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        return "";
    }


    public static Address getAddressFromLatLng(Context ctx, LatLng location) {
        Geocoder geoCoder = new Geocoder(ctx, Locale.getDefault());
        int maxResults = 1;
        try {
            List<Address> addresses = geoCoder.getFromLocation(
                    (location.latitude),
                    (location.longitude), maxResults);

            if (addresses != null) {
                return addresses.get(0);
            } else {
                return null;

            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^07[0-9]{8}$";

        return phoneNumber.matches(regex);
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    private static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }

    public static boolean isNetworkAvailable(Context appContext) {
        ConnectivityManager cm = (ConnectivityManager) appContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


    // Rate my app
    public static void setNumberOfFinishedOrders(Context appContext, int nr) {
        if (appContext instanceof Context) {
            SharedPreferences settings = appContext.getSharedPreferences(
                    PREFS_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("FINISHED_ORDERS_COUNT", nr);
            editor.commit();
        }
    }

    public static int getNumberOfFinishedOrders(Context appContext) {
        if (appContext instanceof Context) {
            SharedPreferences settings = appContext.getSharedPreferences(
                    PREFS_NAME, Context.MODE_PRIVATE);
            return settings.getInt("FINISHED_ORDERS_COUNT", 0);
        }
        return 0;
    }

    // 0 - Pot sa-i arat, 1 - A apasat pe rate, 2 - A dat don't bother me again
    public static void setRateAppResponse(Context appContext, int response) {
        if (appContext instanceof Context) {
            SharedPreferences settings = appContext.getSharedPreferences(
                    PREFS_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("RATE_APP_RESPONSE", response);
            editor.commit();
        }
    }

    public static int getRateAppResponse(Context appContext) {
        if (appContext instanceof Context) {
            SharedPreferences settings = appContext.getSharedPreferences(
                    PREFS_NAME, Context.MODE_PRIVATE);
            return settings.getInt("RATE_APP_RESPONSE", 0);
        }
        return 0;
    }

    // 1 - Arata, 0 - Nu arata - -1 - Nu mai arata EVER
    public static void setShowRateApp(Context appContext, int show) {
        if (appContext instanceof Context) {
            SharedPreferences settings = appContext.getSharedPreferences(
                    PREFS_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("SHOW_RATE_APP", show);
            editor.commit();
        }
    }

    public static int getShowRateApp(Context appContext) {
        if (appContext instanceof Context) {
            SharedPreferences settings = appContext.getSharedPreferences(
                    PREFS_NAME, Context.MODE_PRIVATE);
            return settings.getInt("SHOW_RATE_APP", 0);
        }
        return 0;
    }

    // 1 - Arata, 0 - Nu arata - -1 - Nu mai arata EVER
    public static void setShowRateAppTime(Context appContext, long time) {
        if (appContext instanceof Context) {
            SharedPreferences settings = appContext.getSharedPreferences(
                    PREFS_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putLong("SHOW_RATE_APP_TIME", time);
            editor.commit();
        }
    }

    public static long getShowRateAppTime(Context appContext) {
        if (appContext instanceof Context) {
            SharedPreferences settings = appContext.getSharedPreferences(
                    PREFS_NAME, Context.MODE_PRIVATE);
            return settings.getLong("SHOW_RATE_APP_TIME", 0);
        }
        return 0;
    }

    public static String roundWithTwoDecimals(double number) {
        try {
            // format based on default locale
            DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.US);
            DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
            df.setDecimalFormatSymbols(dfs);
            df.applyPattern("#####0.00");
            return df.format(number);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }


    public static String get_language_id(Context ctx) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        return preferences.getString("prefLanguage", "1");
    }


    public static LatLng getLocation_from_String(Context mContext, String query) {
        LatLng mLatLn = new LatLng(0, 0);
        try {
            List<Address> addresses;
            Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());

            addresses = geocoder.getFromLocationName(query, 1);

            if (addresses.size() > 0) {
                double latitude = addresses.get(0).getLatitude();
                double longitude = addresses.get(0).getLongitude();
                mLatLn = new LatLng(latitude, longitude);

            } else {
                Ln.e("no coordinates found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mLatLn;
    }

    public static String getApplicationVersion(Context context) {
        PackageInfo pInfo;
        try {
            pInfo = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            return pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getRegistrationId(Context ctx) {
        SharedPreferences preferences = ctx.getSharedPreferences("PREFS", 0);
        return preferences.getString("registration_id", "no_registration_id_yet");
    }

    public static String getDeviceKey(Context ctx) {

        final TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        final String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(ctx.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        return deviceUuid.toString();
    }

    public static boolean validateEmail(final String email) {

        String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static void show_error_dialog_not_registered(Context mContext) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(mContext.getString(R.string.app_name));
        builder.setMessage(mContext.getString(R.string.to_add_addresses_you_have_to_be_registered))
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static boolean checkEditText(EditText edit) {
        return edit.getText().toString() != null && !edit.getText().toString().trim().equalsIgnoreCase("")
                && !edit.getText().toString().equalsIgnoreCase("null");
    }

    public static Dialog LoadingSpinner(Context mContext) {
        Dialog pd = new Dialog(mContext, android.R.style.Theme_Black);
        View view = LayoutInflater.from(mContext).inflate(R.layout.aux_progress_spinner, null);
        pd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        pd.getWindow().setBackgroundDrawableResource(R.color.transparent);
        pd.setContentView(view);
        return pd;
    }

    public static Dialog RestaurantLoadingSpinner(Context mContext, String img_url) {

        Dialog pd = new Dialog(mContext, android.R.style.Theme_Black);
        View view = LayoutInflater.from(mContext).inflate(R.layout.restaurant_progress_spinner, null);

        ImageView imageView_restaurant_progress_spinner = (ImageView) view.findViewById(R.id.imageView_restaurant_progress_spinner);

        Picasso.with(mContext).load(img_url)
                //.memoryPolicy(MemoryPolicy.NO_CACHE)
                .error(R.drawable.ic_launcher)
                .transform(new CircleTransform())
                .into(imageView_restaurant_progress_spinner);


        pd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        pd.getWindow().setBackgroundDrawableResource(R.color.transparent);
        pd.setContentView(view);
        return pd;

    }


    /**
     * day- String, (Required), The date for delivery. Format "Y-m-d". Example: "2015-03-23"
     */
    public static String formatDeliveryDate(int year, int monthOfYear, int dayOfMonth) {
        StringBuilder dateBuilder = new StringBuilder();
        dateBuilder.append(year);
        dateBuilder.append("-");
        dateBuilder.append((monthOfYear >= 10) ? monthOfYear : "0" + monthOfYear);
        dateBuilder.append("-");
        dateBuilder.append((dayOfMonth >= 10) ? dayOfMonth : "0" + dayOfMonth);

        return dateBuilder.toString();
    }

    public static String formatDeliveryTime(int hourOfDay, int minute) {
        StringBuilder dateBuilder = new StringBuilder();
        dateBuilder.append(hourOfDay);
        dateBuilder.append(":");
        dateBuilder.append((minute >= 10) ? minute : "0" + minute);

        return dateBuilder.toString();
    }

    public static String list_to_nice_string(List<Object> cuisines) {
        String out = "";
        for (Object aux : cuisines) {
            try {
                JSONObject jsonObj = new JSONObject(String.valueOf(aux));
                out = out + ", " + jsonObj.getString("name");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (out.length() > 4) {
            return out.substring(0, out.length() - 2);
        } else {
            return "";
        }
    }


    public static JSONArray concatArray(JSONArray arr1, JSONArray arr2)
            throws JSONException {
        JSONArray result = new JSONArray();
        for (int i = 0; i < arr1.length(); i++) {
            result.put(arr1.get(i));
        }
        for (int i = 0; i < arr2.length(); i++) {
            result.put(arr2.get(i));
        }
        return result;
    }


    public static boolean isAppInstalled(Context ctx, String uri) {
        PackageManager pm = ctx.getPackageManager();
        boolean installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }


    public static String first_letter_uppercase(String source) {

        StringBuffer res = new StringBuffer();

        String[] strArr = source.split(" ");
        for (String str : strArr) {
            char[] stringArray = str.trim().toCharArray();
            stringArray[0] = Character.toUpperCase(stringArray[0]);
            str = new String(stringArray);

            res.append(str).append(" ");
        }

        return String.valueOf(res);
    }


    public static String getMyPhoneNumber(Context ctx) {

        try {
            TelephonyManager mTelephonyMgr;
            mTelephonyMgr = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
            return mTelephonyMgr.getLine1Number();
        } catch (Exception e) {
            return "";
        }

    }

    public static String getMy10DigitPhoneNumber(Context ctx) {
        try {
            String s = getMyPhoneNumber(ctx);
            return s.substring(2);
        } catch (Exception e) {
            return "";
        }

    }

    // ------- GET USER EMAIL ADDRESS

    public static class UserEmailFetcher {

        public static String getEmail(Context context) {
            AccountManager accountManager = AccountManager.get(context);
            Account account = getAccount(accountManager);

            if (account == null) {
                return null;
            } else {
                return account.name;
            }
        }

        private static Account getAccount(AccountManager accountManager) {
            Account[] accounts = accountManager.getAccountsByType("com.google");
            Account account;
            if (accounts.length > 0) {
                account = accounts[0];
            } else {
                account = null;
            }
            return account;
        }
    }

    @TargetApi(9)
    public static String getMonth(int month, Locale locale) {
        return DateFormatSymbols.getInstance(locale).getMonths()[month - 1];
    }


    public static final String md5(final String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String sha512(String what_to_encode) {
        final MessageDigest sha512;
        try {
            sha512 = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            return "404";
        }
        sha512.update(what_to_encode.getBytes());
        byte byteData[] = sha512.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static boolean isDebuggable(Context ctx) {
        boolean debuggable = false;
        X500Principal DEBUG_DN = new X500Principal("CN=Android Debug,O=Android,C=US");
        try {
            PackageInfo pinfo = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), PackageManager.GET_SIGNATURES);
            android.content.pm.Signature signatures[] = pinfo.signatures;
            CertificateFactory cf = CertificateFactory.getInstance("X.509");

            for (int i = 0; i < signatures.length; i++) {
                ByteArrayInputStream stream = new ByteArrayInputStream(signatures[i].toByteArray());
                X509Certificate cert = (X509Certificate) cf.generateCertificate(stream);
                debuggable = cert.getSubjectX500Principal().equals(DEBUG_DN);
                if (debuggable)
                    break;
            }
        } catch (PackageManager.NameNotFoundException e) {
            //debuggable variable will remain false
        } catch (CertificateException e) {
            //debuggable variable will remain false
        }
        return debuggable;
    }

    public static String get_device_id(Context ctx) {

        final TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);

        String tmDevice = "";
        String tmSerial = null;
        String androidId = null;


        try {
            tmDevice = "" + tm.getDeviceId();
        } catch (Exception ex) {
        }

        try {
            tmSerial = "" + tm.getSimSerialNumber();
        } catch (Exception ex) {
        }
        try {
            androidId = "" + android.provider.Settings.Secure.getString(ctx.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        } catch (Exception ex) {
        }
        try {
            UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
            String deviceId = deviceUuid.toString();

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(deviceId.getBytes());

            byte byteData[] = md.digest();

            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            deviceId = sb.toString();


            return deviceId;
        } catch (Exception ex) {
        }
        return "nodeviceid";
    }


    public static Boolean writeToSDFile(String directory, String file_name, String text) {

        // Find the root of the external storage.
        // See
        // http://developer.android.com/guide/topics/data/data-storage.html#filesExternal

        File root = Environment.getExternalStorageDirectory();

        // See
        // http://stackoverflow.com/questions/3551821/android-write-to-sd-card-folder

        File dir = new File(root.getAbsolutePath() + "/" + directory);
        dir.mkdirs();
        File file = new File(dir, file_name);

        try {
            FileOutputStream f = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(f);
            pw.println(text);
            pw.flush();
            pw.close();
            f.close();
            // Log.v(TAG, "file written to sd card");
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // Log.i(TAG, "******* File not found. Did you" +
            // " add a WRITE_EXTERNAL_STORAGE permission to the manifest?");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method to read in a text file placed in the res/raw directory of the
     * application. The method reads in all lines of the file sequentially.
     */

    public static void readRaw(Context ctx, int res_id) {

        InputStream is = ctx.getResources().openRawResource(res_id);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr, 8192); // 2nd arg is buffer
        // size

        // More efficient (less readable) implementation of above is the
        // composite expression
        /*
         * BufferedReader br = new BufferedReader(new InputStreamReader(
		 * this.getResources().openRawResource(R.raw.textfile)), 8192);
		 */

        try {
            String test;
            while (true) {
                test = br.readLine();
                // readLine() returns null if no more lines in the file
                if (test == null)
                    break;
            }
            isr.close();
            is.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Read a file from assets
     *
     * @return the string from assets
     */

    public static String getfromAssets(Context ctx, String file_name) {

        AssetManager assetManager = ctx.getAssets();
        ByteArrayOutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open(file_name);
            outputStream = new ByteArrayOutputStream();
            byte buf[] = new byte[1024];
            int len;
            try {
                while ((len = inputStream.read(buf)) != -1) {
                    outputStream.write(buf, 0, len);
                }
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
            }
        } catch (IOException e) {
        }
        return outputStream.toString();

    }


    /**
     * Checks if there's an active internet connection
     */

    public static boolean isOnline(Context ctx) {

        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(ctx.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }


    /**
     * **********************************************************************************************
     * Returns size in MegaBytes.
     *
     * @author Dan
     * <p/>
     * If you need calculate external memory, change this: StatFs statFs
     * = new StatFs(Environment.getRootDirectory().getAbsolutePath());
     * to this: StatFs statFs = new
     * StatFs(Environment.getExternalStorageDirectory
     * ().getAbsolutePath());
     * ************************************************************************************************
     */
    public static int TotalMemory() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        int Total = (statFs.getBlockCount() * statFs.getBlockSize()) / 1048576;
        return Total;
    }

    public static int FreeMemory() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        int Free = (statFs.getAvailableBlocks() * statFs.getBlockSize()) / 1048576;
        return Free;
    }

    public static int BusyMemory() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        int Total = (statFs.getBlockCount() * statFs.getBlockSize()) / 1048576;
        int Free = (statFs.getAvailableBlocks() * statFs.getBlockSize()) / 1048576;
        int Busy = Total - Free;
        return Busy;
    }


    public static Bitmap decodeUri(Context ctx, Uri selectedImage) throws FileNotFoundException {

        // Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(ctx.getContentResolver().openInputStream(selectedImage), null, o);

        // The new size we want to scale to
        final int REQUIRED_SIZE = 240;

        // Find the correct scale value. It should be the power of 2.
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(ctx.getContentResolver().openInputStream(selectedImage), null, o2);

    }

    public static File createTemporaryFile(String part, String ext) throws Exception {
        File tempDir = Environment.getExternalStorageDirectory();
        tempDir = new File(tempDir.getAbsolutePath() + "/.temp/");
        if (!tempDir.exists()) {
            tempDir.mkdir();
        }
        return File.createTempFile(part, ext, tempDir);
    }

}
