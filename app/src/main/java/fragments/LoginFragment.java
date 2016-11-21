package fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.payfuelpts.oltranz.mystation.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import entities.MyDevice;
import modules.LoginModule;
import simplebeans.loginbeans.LoginRequest;
import simplebeans.loginbeans.LoginResponse;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnLoginInteractionFrag} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment implements LoginModule.LoginModuleInteraction {

    private OnLoginInteractionFrag mListener;

    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.pin)
    EditText pin;
    @BindView(R.id.login)
    Button login;
    private Unbinder unbind;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LoginFragment.
     */
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
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
        return inflater.inflate(R.layout.login_ui, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbind=ButterKnife.bind(this, view);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Validation Of the field
                if(validateField(pin.getText().toString().trim())){
                    proceedToLogin(pin.getText().toString().trim());
                }else{
                    pin.requestFocus();
                    pin.setError("Provide pin");
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbind.unbind();
    }

    private boolean validateField(String pin){
        if(!TextUtils.isEmpty(pin))
            if(pin.length()>=4 && pin.length()<=8)
                if(TextUtils.isDigitsOnly(pin))
                    return true;

        return false;
    }

    private void proceedToLogin(String pin){
        MyDevice myDevice = null;
        try{
            myDevice = MyDevice.find(MyDevice.class, null, null, null, null, "1").get(1);
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
            mListener.onLoginInteraction(0, "Device Not Registered", new RegisterDeviceFragment());
        }
        if(myDevice != null){
            LoginRequest loginRequest=new LoginRequest(myDevice.getName(), pin);
            LoginModule loginModule=new LoginModule(LoginFragment.this, getContext(), loginRequest);
            loginModule.loginMeIn();
        }else{
            //fake device registration
            mListener.onLoginInteraction(0, "Device Not Registered", new RegisterDeviceFragment());
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLoginInteractionFrag) {
            mListener = (OnLoginInteractionFrag) context;
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
    public void onLoginModuleInteraction(int status, String message, LoginResponse loginResponse) {
        //success full Login
        mListener.onLoginInteraction(status, message, loginResponse);
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
    public interface OnLoginInteractionFrag {
        void onLoginInteraction(int status, String message, Object object);
    }
}
