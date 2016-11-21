package com.payfuelpts.oltranz.mystation;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import config.BaseUrl;
import entities.MyDevice;
import fragments.LoginFragment;
import fragments.RegisterDeviceFragment;

public class Home extends AppCompatActivity implements LoginFragment.OnLoginInteractionFrag, RegisterDeviceFragment.OnRegisterDeviceFrag {
    private String tag=getClass().getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView titleBar;
    Typeface font;

    @BindDrawable(R.drawable.login_selector)
    Drawable loginIcon;
    @BindDrawable(R.drawable.admin_selector)
    Drawable adminIcon;
    @BindDrawable(R.drawable.register_selector)
    Drawable registerIcon;
    @BindString(R.string.titleLogin)
    String titleLogin;
    @BindString(R.string.titleRegister)
    String titleRegister;

    @BindView(R.id.register)
    ImageView register;
    @BindView(R.id.admin)
    ImageView admin;

    private boolean isRegisterClicked=false;
    private LoginFragment loginFrag;
    private RegisterDeviceFragment registerFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_ui);
        ButterKnife.bind(this);
        font=Typeface.createFromAsset(this.getAssets(), "font/ubuntu.ttf");

        toolbar.setVisibility(View.VISIBLE);
        toolbar.setTitle("");
        titleBar.setTypeface(font);

        setSupportActionBar(toolbar);
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            loginFrag = new LoginFragment();
            registerFrag = new RegisterDeviceFragment();
            // Add the fragment to the 'fragment_container' FrameLayout
            if(!isDeviceRegistered())
                fragmentHandler(registerFrag);
            else
                fragmentHandler(loginFrag);

            admin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(tag, "Help triggered");
                    Intent intent = getPackageManager().getLaunchIntentForPackage("com.olranz.payfuel.myadmin");
                    if (intent != null) {
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else {
                        uiPopUp("ENGEN Admin App Is Missing...!");
                    }
                }
            });

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!isRegisterClicked) {
                        fragmentHandler(registerFrag);
                        isRegisterClicked = true;
                        register.setBackground(loginIcon);
                    } else {
                        fragmentHandler(loginFrag);
                        isRegisterClicked = false;
                        register.setBackground(registerIcon);
                    }
                }
            });
        } else {
            //popup window
        }
    }

    private boolean isDeviceRegistered(){
        MyDevice myDevice = null;
        try{
            myDevice = MyDevice.find(MyDevice.class, null, null, null, null, "1").get(1);
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
            return false;
        }
        return myDevice != null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager=getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
        setMyTitleBar(fragmentManager.findFragmentById(R.id.fragment_container).getClass());

        try {

            Fragment currentFrag = fragmentManager.findFragmentById(R.id.fragment_container);
            if (currentFrag.getClass().getSimpleName().equals(LoginFragment.class.getSimpleName())) {
                isRegisterClicked = false;
                register.setBackground(registerIcon);
            }

            if (currentFrag.getClass().getSimpleName().equals(RegisterDeviceFragment.class.getSimpleName())) {
                isRegisterClicked = true;
                register.setBackground(loginIcon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setMyTitleBar(Object object){
        if(object.getClass() == LoginFragment.class)
            titleBar.setText(titleLogin);
        if(object.getClass() == RegisterDeviceFragment.class)
            titleBar.setText(titleRegister);
    }

    private void fragmentHandler(Object object) {
        Fragment fragment = (Fragment) object;
        String backStateName = fragment.getClass().getSimpleName();
        String fragmentTag = backStateName;
        FragmentManager fragmentManager=getSupportFragmentManager();

        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped && fragmentManager.findFragmentByTag(fragmentTag) == null) { //fragment not in back stack, create it.
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.fragment_container, fragment, fragmentTag);
            ft.addToBackStack(backStateName);
            ft.commit();
        } else {
            Log.d(tag, "Fragment already There");
        }
        setMyTitleBar(fragment);
    }
    private void uiPopUp(String message){
        try {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
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


            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoginInteraction(int status, String message, Object object) {
        Toast.makeText(this,"Login Note: "+ message, Toast.LENGTH_SHORT).show();
        //go to next step
    }

    @Override
    public void onRegisterDeviceFragInteraction(int status, String message, Object object) {
        Toast.makeText(this,"Register Note: "+ message, Toast.LENGTH_SHORT).show();
        if(status == 100)
            fragmentHandler(loginFrag);
    }
}
