package app.safety.hsbc.safeguard.utils;

/**
 * Created by steevehuang on 5/11/2017.
 * This class is for calling the ASTRI API.
 * To Utilize this class, simply call execute(String ...) on a HttpRequester object.
 * The first parameter should specify the function, that is. createAccount, updateAccount, or removeAccount.
 * The remaining parameters should input all or part of the user information as shown below.
 */

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;


public class HttpRequester extends AsyncTask<String, Void, Void> {
    private static String domain = "10.89.142.30:10007";
    private Exception exception;
    private static Boolean nullOrEmpty(String str){
        return str == null || str.isEmpty();
    }
    public void createAccount(String id, String name, String phoneNumber, String email,
                                      String residentialAddress, String correspondenceAddress, String job)
            throws IOException {
        PutUtility put = new PutUtility("http://" + domain + "/api/account/createAccount");
        String requestBody = "{"
                + "\"id\" : \"" + id + "\", "
                + "\"name\" : \"" + name + "\", "
                + "\"phoneNumber\" : \"" + phoneNumber + "\", "
                + "\"email\" : \"" + email + "\", "
                + "\"residentialAddress\" : \"" + residentialAddress + "\", "
                + "\"correspondenceAddress\" : \"" + correspondenceAddress + "\", "
                + "\"job\" : \"" + job + "\""
                + "}";

        System.out.println(requestBody);
        put.setContentType("application/json;charset=UTF-8");
        put.setRequestBody(requestBody);
        System.out.println(put.getResponse());
    }

    private void updateAccount(String id, String name, String phoneNumber, String email,
                                      String residentialAddress, String correspondenceAddress, String job)
            throws IOException {
        PutUtility put = new PutUtility("http://" + domain + "/api/account/updateAccount");
        String requestBody = "{"
                + "\"id\" : \"" + id + "\"";
        if (!nullOrEmpty(name)) {
            requestBody += ", \"name\" : \"" + name + "\"";
        }

        if (!nullOrEmpty(phoneNumber)) {
            requestBody += ", \"phoneNumber\" : \"" + phoneNumber + "\"";
        }

        if (!nullOrEmpty(email)) {
            requestBody += ", \"email\" : \"" + email + "\"";
        }

        if (!nullOrEmpty(residentialAddress)) {
            requestBody += ", \"residentialAddress\" : \"" + residentialAddress + "\"";
        }

        if (!nullOrEmpty(correspondenceAddress)) {
            requestBody += ", \"correspondenceAddress\" : \"" + correspondenceAddress + "\"";
        }

        if (!nullOrEmpty(job)) {
            requestBody += ", \"job\" : \"" + job + "\"";
        }

        requestBody += "}";

        System.out.println(requestBody);
        put.setContentType("application/json;charset=UTF-8");
        put.setRequestBody(requestBody);
        System.out.println(put.getResponse());
    }

    private static void removeAccount(String id) throws IOException {
        PutUtility put = new PutUtility("http://" + domain + "/api/account/removeAccount");
        String requestBody = "{\"id\" : \"" + id + "\"}";

        put.setContentType("application/json;charset=UTF-8");
        put.setRequestBody(requestBody);
        System.out.println(put.getResponse());
    }





    @Override
    protected Void doInBackground(String... strings) {
        try {

            switch( strings[0]){
                case "createAccount":
                    Log.w("wow","the fuck?");
                    createAccount(strings[1], strings[2],strings[3],strings[4],
                            strings[5],strings[6],strings[7]);
                    break;
                case "updaetAccount":
                    updateAccount(strings[1], strings[2],strings[3],strings[4],
                            strings[5],strings[6],strings[7]);
                    break;
                case "removeAccount":
                    removeAccount("2");
                    break;


            }




        } catch (Exception e) {
            this.exception = e;

        }
        return null;
    }
}
