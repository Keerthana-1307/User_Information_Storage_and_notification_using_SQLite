package com.example.dbconnectivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.*;
import android.view.View;
import android.widget.*;
import android.app.*;
import android.content.Context;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {
    EditText e1, e2, e3;
    TextView tv;
    DB db;

    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        e1 = findViewById(R.id.e1);
        e2 = findViewById(R.id.e2);
        e3 = findViewById(R.id.e3);
        tv = findViewById(R.id.tv);
        db = new DB(this);
        
        if (Build.VERSION.SDK_INT >= 33)
            requestPermissions(new String[]{"android.permission.POST_NOTIFICATIONS"}, 1);
    }

    public void sav(View v) {
        db.sav(e1.getText().toString(), e2.getText().toString());
        Toast.makeText(this, "Record Saved", Toast.LENGTH_SHORT).show();
    }

    public void retrieve(View v) {
        tv.setText(db.retrieve(e3.getText().toString()));
    }

    public void showNotification(View v) {
        String id = "student_ch";
        NotificationManager m = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= 26)
            m.createNotificationChannel(new NotificationChannel(id, "Student Updates", NotificationManager.IMPORTANCE_HIGH));
            
        NotificationCompat.Builder b = new NotificationCompat.Builder(this, id)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Database Alert")
                .setContentText("Record operation successful!");
        m.notify(1, b.build());
    }
}
