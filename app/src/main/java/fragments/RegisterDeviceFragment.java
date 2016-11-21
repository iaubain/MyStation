package fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.payfuelpts.oltranz.mystation.R;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import modules.RegisterDeviceModule;
import simplebeans.registerbeans.RegisterRequest;
import simplebeans.registerbeans.RegisterResponse;
import utilities.DataFactory;
import utilities.DeviceIdentity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegisterDeviceFragment.OnRegisterDeviceFrag} interface
 * to handle interaction events.
 * Use the {@link RegisterDeviceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterDeviceFragment extends Fragment implements RegisterDeviceModule.RegisterDeviceModuleInteraction {

    private OnRegisterDeviceFrag mListener;
    private Typeface font;
    @BindViews({R.id.tv, R.id.lblmail, R.id.lblPw, R.id.lblDevice, R.id.lblDeviceRetype, R.id.lblMsisdn, R.id.lblAuxMsisdn})
    List<TextView> labels;

    @BindViews({R.id.mail, R.id.pw, R.id.devName, R.id.devNameRetype, R.id.mainMsisdn, R.id.auxMsisdn})
    List<EditText> fields;

    @BindView(R.id.submit)
    Button submit;

    private Unbinder unbind;

    public RegisterDeviceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RegisterDeviceFragment.
     */
    public static RegisterDeviceFragment newInstance() {
        RegisterDeviceFragment fragment = new RegisterDeviceFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.registerdevice_ui, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbind=ButterKnife.bind(this, view);
        ButterKnife.apply(labels, setFont);
        ButterKnife.apply(fields, setFont);
    }

    final ButterKnife.Action<View> setFont = new ButterKnife.Action<View>() {
        @Override
        public void apply(@NonNull View view, int index) {
            if(view.getClass().getSimpleName().equals(TextView.class.getSimpleName())) {
                TextView v = (TextView) view;
                v.setTypeface(font);
            }
        }
    };

    @OnClick(R.id.submit)
    public void submitForm(View view){
        RegisterRequest registerRequest=new RegisterRequest();
        DataFactory dataFactory=new DataFactory();
        String devName = "", devNameRetype;
        for(EditText field : fields){
            String fieldData=field.getText().toString().trim();
            if(dataFactory.isEmptyField(fieldData)){
                field.setError("Empty field");
                return;
            }

            if(field.getId() == R.id.mail){
                if(!dataFactory.isValidEmail(fieldData)){
                    field.setError("Invalid mail");
                    return;
                }
                registerRequest.setEmail(fieldData);
            }

            if(field.getId() == R.id.mainMsisdn){
                if(!dataFactory.isValidMsisdn(fieldData)){
                    field.setError("Invalid number");
                    return;
                }
                registerRequest.setMsisdn(fieldData);
            }

            if(field.getId() == R.id.devName){
                devName=fieldData;
            }

            if(field.getId() == R.id.devNameRetype){
                devNameRetype=fieldData;

                if(!devName.equals(devNameRetype)){
                    field.setError("Names don't match");
                }
                registerRequest.setDeviceName(fieldData);
            }

            if(field.getId() == R.id.auxMsisdn)
                registerRequest.setAuxMsisdn(fieldData);

            if(field.getId() == R.id.pw)
            registerRequest.setPassword(fieldData);
        }
        registerRequest.setSerialNumber(new DeviceIdentity(getContext()).getSerialNumber());

        RegisterDeviceModule registerDeviceModule=new RegisterDeviceModule(RegisterDeviceFragment.this, getContext(), registerRequest);
        registerDeviceModule.recordDevice();
    }

    private void uiPopUp(final int status, final String message, final RegisterResponse registerResponse){
        try {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage(message)
                    .setTitle(R.string.dialog_title);
            // Add the buttons
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                    mListener.onRegisterDeviceFragInteraction(status, message, registerResponse);
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();


            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbind.unbind();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRegisterDeviceFrag) {
            mListener = (OnRegisterDeviceFrag) context;
            font=Typeface.createFromAsset(context.getAssets(), "font/ubuntu.ttf");
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onRegisterDeviceModuleInteraction(int status, String message, RegisterResponse registerResponse) {
        uiPopUp(status, message, registerResponse);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnRegisterDeviceFrag {
        void onRegisterDeviceFragInteraction(int status, String message, Object object);
    }
}
