package com.example.cafemoa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;


public class SignupActivity extends AppCompatActivity {


    private EditText et_id, et_pass, et_email, et_phone, et_sort;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button idcheckButton=(Button)findViewById(R.id.idcheckButton);
        TextView registerText =(TextView) findViewById(R.id.registerText);

        //아이디값 찾아주기
        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        et_email = findViewById(R.id.et_email);
        et_phone = findViewById(R.id.et_phone);
        //et_sort = findViewById(R.id.et_sort);


        //회원가입 버튼 클릭 시 수행
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = et_id.getText().toString();
                String userPass = et_pass.getText().toString();
                String email = et_email.getText().toString();
                String phone = et_phone.getText().toString();
                //int sort = Integer.parseInt(et_sort.getText()toString());
                int sort = 1;

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success != null && success.equals("1")) {  // 회원가입 완료
                                Toast.makeText(getApplicationContext(),"회원가입 성공!",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(),"회원가입 실패!",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"회원가입 처리시 에러발생!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                };

                // Volley 로 회원양식 웹으로 전송
                SignupRequest registerRequest = new SignupRequest(userID, userPass, email, phone, sort, responseListener, errorListener);
                registerRequest.setShouldCache(false);

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(registerRequest);
                //서버로 Volley를 이용해서 요청
                /*SignupRequest signupRequest = new SignupRequest(userID, userPass, email, phone, sort, responseListener, errorListener);
                RequestQueue queue = Volley.newRequestQueue(SignupActivity.this);
                queue.add(signupRequest);
*/

                /*Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                SignupActivity.this.startActivity(intent);
*/
            }
        });

    }

}
