package modules;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.payfuelpts.oltranz.mystation.R;

import java.util.Date;

import apiclient.ClientServices;
import apiclient.ServerClient;
import config.AppInit;
import config.OwnerShip;
import entities.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import simplebeans.loginbeans.LoginRequest;
import simplebeans.loginbeans.LoginResponse;

/**
 * Created by Hp on 11/16/2016.
 */
public class LoginModule {
    private String tag=getClass().getSimpleName();
    private Context context;
    private LoginRequest loginRequest;
    private LoginModuleInteraction loginModuleInteraction;
    private ProgressDialog progressDialog;

    public LoginModule(LoginModuleInteraction loginModuleInteraction, Context context, LoginRequest loginRequest) {
        this.loginModuleInteraction=loginModuleInteraction;
        this.context = context;
        this.loginRequest = loginRequest;

        progressDialog = new ProgressDialog(context, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
    }

    public LoginModule() {

    }

    public void loginMeIn(){
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();
        try {
            ClientServices clientServices = ServerClient.getClient().create(ClientServices.class);
            Call<LoginResponse> callService = clientServices.loginUser(AppInit.APP_VERSION, OwnerShip.CLIENT_NAME, OwnerShip.CLIENT_IDENTIFICATION, loginRequest);
            callService.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                    //HTTP status code
                    int statusCode = response.code();
                    if(statusCode != 200){
                        uiPop("WebServices is experiencing Some problem Error: "+statusCode);
                        return;
                    }

                    LoginResponse loginResponse=response.body();
                    if(loginResponse == null){
                        uiPop("Contact system administrator");
                        return;
                    }

                    if(loginResponse.getStatusCode() != 100){
                        uiPop("Error: "+ loginResponse.getMessage());
                        return;
                    }

                    try {
                        User user=new User(loginResponse.getLoginResponseBean().getUserId(), loginResponse.getLoginResponseBean().getShiftId(), new Date(), null, 0, loginRequest.getUserPin());
                        User.delete(user);
                        user.save();
                    }catch (Exception e){
                        uiPopUp(e.getMessage());
                        return;
                    }

                    if(progressDialog != null)
                        if(progressDialog.isShowing())
                            progressDialog.dismiss();

                    loginModuleInteraction.onLoginModuleInteraction(loginResponse.getStatusCode(), loginResponse.getMessage(), loginResponse);
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    // Log error here since request failed
                    Log.e(tag, t.toString());
                    uiPopUp("Connectivity Error");
                    //loginListener.onLoginInteraction(500, t.getMessage(), msisdn, null);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            uiPopUp(e.getMessage());
            //loginListener.onLoginInteraction(500, e.getMessage(), msisdn, null);
        }
    }

    private void uiPop(String message){
        if(progressDialog != null)
            if(progressDialog.isShowing())
                progressDialog.dismiss();
        try {
            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(message)
                    .setTitle(R.string.dialog_title);
            // Add the buttons
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();


            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void uiPopUp(String message){
        if(progressDialog != null)
            if(progressDialog.isShowing())
                progressDialog.dismiss();
        try {
            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(message)
                    .setTitle(R.string.dialog_title);
            // Add the buttons
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
            builder.setNegativeButton(R.string.refresh, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    dialog.dismiss();
                    loginMeIn();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();


            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public interface LoginModuleInteraction{
        void onLoginModuleInteraction(int status, String message, LoginResponse loginResponse);
    }
}
