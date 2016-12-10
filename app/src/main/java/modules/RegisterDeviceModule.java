package modules;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.payfuelpts.oltranz.mystation.R;

import apiclient.ClientServices;
import apiclient.ServerClient;
import config.AppInit;
import config.BaseUrl;
import config.OwnerShip;
import entities.MyDevice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import simplebeans.registerbeans.RegisterRequest;
import simplebeans.registerbeans.RegisterResponse;

/**
 * Created by Hp on 11/16/2016.
 */
public class RegisterDeviceModule {
    private String tag=getClass().getSimpleName();
    private Context context;
    private RegisterRequest registerRequest;
    private RegisterDeviceModuleInteraction registerDeviceModuleInteraction;
    private ProgressDialog progressDialog;

    public RegisterDeviceModule(RegisterDeviceModuleInteraction registerDeviceModuleInteraction, Context context, RegisterRequest registerRequest) {
        this.registerDeviceModuleInteraction=registerDeviceModuleInteraction;
        this.context = context;
        this.registerRequest = registerRequest;

        progressDialog = new ProgressDialog(context, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
    }

    public RegisterDeviceModule() {

    }

    public void recordDevice(){
        try {
            String message="Device name: "+registerRequest.getDeviceName()+"\n";
            message+="Main Tel: "+registerRequest.getMsisdn()+"\n";
            message+="Other Tel: "+registerRequest.getAuxMsisdn()+"\n";
            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(message)
                    .setTitle("Confirm");
            // Add the buttons
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                    proceed(registerRequest);
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();


            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            uiPopUp("Error: "+ e.getLocalizedMessage());
        }
    }

    public void proceed(final RegisterRequest registerRequest){
        progressDialog.setMessage("Recording device...");
        progressDialog.show();
        try {
            ClientServices clientServices = ServerClient.getClient().create(ClientServices.class);
            Call<RegisterResponse> callService = clientServices.universalPost(AppInit.APP_VERSION, OwnerShip.CLIENT_NAME, OwnerShip.CLIENT_IDENTIFICATION, registerRequest, BaseUrl.REGISTER_URL);
            callService.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                    //HTTP status code
                    int statusCode = response.code();
                    if(statusCode != 200){
                        uiPop("WebServices is experiencing Some problem Error: "+statusCode);
                        return;
                    }

                    RegisterResponse registerResponse=response.body();
                    if(registerResponse == null){
                        uiPop("Contact system administrator");
                        return;
                    }

                    if(registerResponse.getStatusCode() != 100){
                        uiPop("Error: "+ registerResponse.getMessage());
                        return;
                    }

                    try{
                        MyDevice.deleteAll(MyDevice.class);

                        //Record device in local database
                        MyDevice myDevice=new MyDevice(""+registerResponse.getDevdata().getDeviceId(),
                                registerResponse.getDevdata().getDeviceName(),
                                registerRequest.getSerialNumber(),
                                registerRequest.getMsisdn(),
                                registerRequest.getAuxMsisdn());
                        myDevice.save();
                    }catch (Exception e){
                        uiPopUp(e.getMessage());
                        return;
                    }

                    if(progressDialog != null)
                        if(progressDialog.isShowing())
                            progressDialog.dismiss();

                    registerDeviceModuleInteraction.onRegisterDeviceModuleInteraction(registerResponse.getStatusCode(), registerResponse.getMessage(), registerResponse);
                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {
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
                    recordDevice();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();


            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public interface RegisterDeviceModuleInteraction{
        void onRegisterDeviceModuleInteraction(int status, String message, RegisterResponse registerResponse);
    }
}
