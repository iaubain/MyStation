package utilities;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Hp on 11/16/2016.
 */
public class DataFactory {
    private String tag=getClass().getSimpleName();
    private String jsonData;
    private ObjectMapper mapper;

    public DataFactory() {
    }

    public String mapping(Object object){
        Log.d(tag, "mapping object starts...");
        mapper= new ObjectMapper();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            jsonData=mapper.writeValueAsString(object);
            Log.d(tag,"mapping result: "+jsonData);
            return jsonData;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(tag, "Erroneous mapping object: " + e.getMessage());
            return e.getMessage();
        }
    }

    public boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isEmptyField(String field){
        return TextUtils.isEmpty(field);
    }

    public boolean isValidMsisdn(String msisdn){
        return !TextUtils.isEmpty(msisdn) && Patterns.PHONE.matcher(msisdn).matches();
    }
}
