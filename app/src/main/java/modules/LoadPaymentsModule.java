package modules;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.payfuelpts.oltranz.mystation.R;

import java.util.Date;
import java.util.List;

import apiclient.ClientServices;
import apiclient.ServerClient;
import config.AppInit;
import config.OwnerShip;
import entities.Payment;
import entities.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import simplebeans.loginbeans.LoginResponse;
import simplebeans.paymentbeans.PaymentModeResponse;
import simplebeans.paymentbeans.PaymentResponseBean;

/**
 * Created by Hp on 11/18/2016.
 */
public class LoadPaymentsModule {

    private String tag=getClass().getSimpleName();
    private Context context;
    private int userId;
    private LoadPaymentsInteraction loadPaymentsInteraction;
    private ProgressDialog progressDialog;

    public LoadPaymentsModule(LoadPaymentsInteraction loadPaymentsInteraction, Context context, int userId) {
        this.loadPaymentsInteraction=loadPaymentsInteraction;
        this.context = context;
        this.userId = userId;

        progressDialog = new ProgressDialog(context, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
    }

    public LoadPaymentsModule() {

    }

    public void loadPayment(){
        progressDialog.setMessage("Loading payments...");
        progressDialog.show();
        try {
            ClientServices clientServices = ServerClient.getClient().create(ClientServices.class);
            Call<PaymentModeResponse> callService = clientServices.getPaymentModes(AppInit.APP_VERSION, OwnerShip.CLIENT_NAME, OwnerShip.CLIENT_IDENTIFICATION, userId);
            callService.enqueue(new Callback<PaymentModeResponse>() {
                @Override
                public void onResponse(Call<PaymentModeResponse> call, Response<PaymentModeResponse> response) {

                    //HTTP status code
                    int statusCode = response.code();
                    if(statusCode != 200){
                        uiPop("WebServices is experiencing Some problem Error: "+statusCode);
                        return;
                    }

                    PaymentModeResponse paymentModeResponse=response.body();
                    if(paymentModeResponse == null){
                        uiPop("Contact system administrator");
                        return;
                    }

                    if(paymentModeResponse.getStatusCode() != 100){
                        uiPop("Error: "+ paymentModeResponse.getMessage());
                        return;
                    }

                    try {
                        //int identification, String name, String type, boolean hasExtra, String extraType, boolean needExternal, String externalType
                        List<PaymentResponseBean> paymentModeList=paymentModeResponse.getPaymentModeList();
                        for(PaymentResponseBean mPayment : paymentModeList){
                            Payment payment=new Payment(mPayment.getPaymentModeId(),
                                    mPayment.getName(),
                                    mPayment.getPaymentType(),
                                    true,
                                    "n/a",
                                    true,
                                    "external:core;");
                            Payment.delete(payment);
                            payment.save();
                        }
                    }catch (Exception e){
                        uiPopUp(e.getMessage());
                    }

                    if(progressDialog != null)
                        if(progressDialog.isShowing())
                            progressDialog.dismiss();

                    loadPaymentsInteraction.onLoadPaymentsInteraction(paymentModeResponse.getStatusCode(), paymentModeResponse.getMessage(), paymentModeResponse);
                }

                @Override
                public void onFailure(Call<PaymentModeResponse> call, Throwable t) {
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
                    loadPayment();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();


            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public interface LoadPaymentsInteraction{
        void onLoadPaymentsInteraction(int statusCode, String message, PaymentModeResponse paymentModeResponse);
    }
}
