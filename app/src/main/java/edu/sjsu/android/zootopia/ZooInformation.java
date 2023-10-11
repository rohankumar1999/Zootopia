package edu.sjsu.android.zootopia;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ZooInformation extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent infoIntent = getIntent();
        String value = infoIntent.getStringExtra("zooName");
        TextView zooName = findViewById(R.id.contactName);
        zooName.setText(value);
        String phone = infoIntent.getStringExtra("contact");
        TextView phn = findViewById(R.id.phoneNo);
        phn.setText(phone);
    }

    public void call(View view){
        Uri number = Uri.parse("tel:"+"888-8888");
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        startActivity(callIntent);
        return;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.information) {
            Intent intent = new Intent(this, ZooInformation.class);
            intent.putExtra("zooName","Zootopia National Park");
            intent.putExtra("contact","888-8888");
            this.startActivity(intent);
            return true;
        } else if (id == R.id.uninstall) {
            Toast.makeText(this, "uninstall clicked", Toast.LENGTH_SHORT).show();
            Uri packageUri = Uri.parse("package:edu.sjsu.android.zootopia");
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Do you want to Uninstall this Application?").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent uninstallIntent =
                            new Intent(Intent.ACTION_DELETE);
                    uninstallIntent.setData(packageUri);
                    startActivity(uninstallIntent);
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            }).create().show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
