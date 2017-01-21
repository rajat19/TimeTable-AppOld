package in.akgec.timetable;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginFragment extends Fragment implements View.OnClickListener {

    EditText email, password;
    Button btnLogin, btnForgot;
    TextView btnRegister;
    String emailString, passwordString;
    ServerLink link = new ServerLink();
    String VALIDATE_LOGIN = link.VALIDATE_LOGIN;
    private static final Pattern EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        email = (EditText) v.findViewById(R.id.email);
        password = (EditText) v.findViewById(R.id.password);
        btnRegister = (TextView) v.findViewById(R.id.btnRegister);
        btnLogin = (Button) v.findViewById(R.id.btnLogin);
        btnForgot = (Button) v.findViewById(R.id.btnForgot);

        btnLogin.setOnClickListener(this);
        btnForgot.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnForgot:
                break;
            case R.id.btnLogin:
                emailString = email.getText().toString();
                passwordString = password.getText().toString();
                int flag = 0;
                if(emailString.equals("")) {
                    Toast.makeText(getActivity(), "The email field is required.", Toast.LENGTH_SHORT).show();
                    flag++;
                }
                else if(!isEmail(emailString)){
                    Toast.makeText(getActivity(), "Not a valid email address", Toast.LENGTH_SHORT).show();
                    flag++;
                }

                else if(passwordString.equals("")){
                    Toast.makeText(getActivity(), "The password field is required.", Toast.LENGTH_SHORT).show();
                    flag++;
                }
                else if(passwordString.length() < 6) {
                    Toast.makeText(getActivity(), "Password is too short. Must be of length greater than 6", Toast.LENGTH_SHORT).show();
                    flag++;
                }
                if(flag == 0) {
                    /*Start async task to login the user*/
                    String arr[] = {emailString, passwordString};
//                    new Login().execute(arr);
                }
                break;
            case R.id.btnRegister:
                break;
        }
    }

    private static boolean isEmail(String s) {
        Matcher matcher = EMAIL_REGEX.matcher(s);
        return matcher.find();
    }

    private void sendData() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = VALIDATE_LOGIN;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String resp = response;
            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String resp = "";
            }
        });
        queue.add(stringRequest);
    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
