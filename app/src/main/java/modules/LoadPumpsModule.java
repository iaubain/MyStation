package modules;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.payfuelpts.oltranz.mystation.R;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import apiclient.ClientServices;
import apiclient.ServerClient;
import config.AppInit;
import config.OwnerShip;
import entities.Nozzle;
import entities.Payment;
import entities.Pump;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import simplebeans.paymentbeans.PaymentModeResponse;
import simplebeans.paymentbeans.PaymentResponseBean;
import simplebeans.pumpbeans.NozzleBean;
import simplebeans.pumpbeans.PumpResponse;
import simplebeans.pumpbeans.PumpResponseBean;

/**
 * Created by Hp on 11/18/2016.
 */
public class LoadPumpsModule {
    private String tag=getClass().getSimpleName();
    private Context context;
    private int userId;
    private LoadPumpsInteraction loadPumpsInteraction;
    private ProgressDialog progressDialog;

    public LoadPumpsModule(LoadPumpsInteraction loadPumpsInteraction, Context context, int userId) {
        this.loadPumpsInteraction=loadPumpsInteraction;
        this.context = context;
        this.userId = userId;

        progressDialog = new ProgressDialog(context, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
    }

    public LoadPumpsModule() {

    }

    public void loadPumps(){
        progressDialog.setMessage("Loading pumps...");
        progressDialog.show();
        try {
            ClientServices clientServices = ServerClient.getClient().create(ClientServices.class);
            Call<PumpResponse> callService = clientServices.getPumps(AppInit.APP_VERSION, OwnerShip.CLIENT_NAME, OwnerShip.CLIENT_IDENTIFICATION, userId);
            callService.enqueue(new Callback<PumpResponse>() {
                @Override
                public void onResponse(Call<PumpResponse> call, Response<PumpResponse> response) {

                    //HTTP status code
                    int statusCode = response.code();
                    if(statusCode != 200){
                        uiPop("WebServices is experiencing Some problem Error: "+statusCode);
                        return;
                    }

                    PumpResponse pumpResponse=response.body();
                    if(pumpResponse == null){
                        uiPop("Contact system administrator");
                        return;
                    }

                    if(pumpResponse.getStatusCode() != 100){
                        uiPop("Error: "+ pumpResponse.getMessage());
                        return;
                    }

                    try {
                        //int identification, String name, String type, boolean hasExtra, String extraType, boolean needExternal, String externalType
                        List<PumpResponseBean> pumpResponseList=pumpResponse.getPumps();
                        for(PumpResponseBean mPump : pumpResponseList){
                            Pump pump=new Pump(mPump.getPumpId(), mPump.getPumpName());
                            Pump.delete(pump);
                            pump.save();
                            List<NozzleBean> nozzleBeanList = mPump.getNozzles();
                            for(NozzleBean mNozzle : nozzleBeanList){
                                //int nozzleId, String nozzleName, BigDecimal nozzleIndex, int pumpId, int productId, String productName, int unitPrice,
                                // int statusCode, String userName, int userId) {
                                Nozzle nozzle = new Nozzle(mNozzle.getNozzleId(),
                                        mNozzle.getNozzleName(),
                                        new BigDecimal(mNozzle.getNozzleIndex(), MathContext.DECIMAL64),
                                        mPump.getPumpId(),
                                        mNozzle.getProductId(),
                                        mNozzle.getProductName(),
                                        mNozzle.getUnitPrice(),
                                        mNozzle.getStatus(),
                                        mNozzle.getUserName(),
                                        userId);
                                Nozzle.delete(nozzle);
                                nozzle.save();
                            }
                        }
                    }catch (Exception e){
                        uiPopUp(e.getMessage());
                    }

                    if(progressDialog != null)
                        if(progressDialog.isShowing())
                            progressDialog.dismiss();

                    loadPumpsInteraction.onLoadPumpsInteraction(pumpResponse.getStatusCode(), pumpResponse.getMessage(), pumpResponse);
                }

                @Override
                public void onFailure(Call<PumpResponse> call, Throwable t) {
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
                    loadPumps();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();


            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public interface LoadPumpsInteraction{
        void onLoadPumpsInteraction(int statusCode, String message, PumpResponse pumpResponse);
    }
}
