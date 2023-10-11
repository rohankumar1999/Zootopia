package edu.sjsu.android.zootopia;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AnimalDetail extends AppCompatActivity {
    android.widget.TextView name;
    android.widget.ImageView largeImage;
    android.widget.TextView description;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.animal_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = (TextView) this.findViewById(R.id.name2);
        largeImage = (ImageView) this.findViewById(R.id.largeImage);
        description = (TextView) this.findViewById(R.id.description);
        Intent data = getIntent();
        largeImage.setImageResource(data.getIntExtra("imageRes",0));
        name.setText(data.getExtras().getString("name"));
        description.setText(data.getExtras().getString("description"));
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
