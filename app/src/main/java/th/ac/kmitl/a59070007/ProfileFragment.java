package th.ac.kmitl.a59070007;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class ProfileFragment extends Fragment {
    SQLiteDatabase myDB;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDB = getActivity().openOrCreateDatabase("my DB", Context.MODE_PRIVATE, null);
        myDB.execSQL("CREATE TABLE IF NOT EXISTS user (_id INTERGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(200), age INT, password VARCHAR(20) )");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initSaveBtn();
    }

    private void initSaveBtn() {
        Button _registerBtn = (Button) getView().findViewById(R.id.register_register_btn);
        _registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText _id = (EditText) getView().findViewById(R.id.profile_id);
                EditText _password = (EditText) getView().findViewById(R.id.profile_password);
                EditText _name = (EditText) getView().findViewById(R.id.profile_name);
                EditText _age = (EditText) getView().findViewById(R.id.profile_age);
                EditText _quote = (EditText) getView().findViewById(R.id.profile_quote);

                String _idString = _id.getText().toString();
                String _passwordString = _password.getText().toString();
                String _nameString = _name.getText().toString();
                String _ageString = _age.getText().toString();
                String _quoteString = _quote.getText().toString();
                int _ageInt = Integer.parseInt(_ageString);

                if (_idString.isEmpty() || _passwordString.isEmpty() || _nameString.isEmpty() || _ageString.isEmpty()) {
                    Toast.makeText(getContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    Log.d("register", "some field is empty");
                } else if (_idString.length() < 6 || _idString.length() > 12) {
                    Toast.makeText(getContext(), "id ต้องมีความยาว 6-12 ตัว", Toast.LENGTH_SHORT).show();
                    Log.d("register", "password length too short");
                } else if (_passwordString.length() < 6) {
                    Toast.makeText(getContext(), "password ต้องยาวอย่างน้อย 6 ตัวอักษร", Toast.LENGTH_SHORT).show();
                    Log.d("register", "password length too short");
                } else if (_ageInt < 10 || _ageInt > 80) {
                    Toast.makeText(getContext(), "age ต้องอยู่ในช่วง 10-80", Toast.LENGTH_SHORT).show();
                    Log.d("register", "password length too short");
                } else {
                    ContentValues row1 = new ContentValues();
                    row1.put("name", _nameString);
                    row1.put("age", _ageInt);
                    row1.put("password", _passwordString);
                    myDB.update("user", row1, "_id=" + _idString, null);
                    File path = getContext().getFilesDir();
                    File file = new File(path, "quote.txt");
                    FileOutputStream stream = null;
                    try {
                        stream = new FileOutputStream(file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    try {
                        stream.write(_quoteString.getBytes());
                    } catch (IOException e){}


                }
            }
        });
    }
}
