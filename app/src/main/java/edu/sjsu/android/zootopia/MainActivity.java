package edu.sjsu.android.zootopia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("lion", R.drawable.lion, "Also known as the 'King of the Jungle,' is basically the Leo of the animal kingdom. They're the ones who strut around the savanna with a majestic, flowing mane, thinking they're the supermodels of the Serengeti. 'Check out my luscious locks,' they roar, like they just walked out of a salon."));
        animals.add(new Animal("elephant", R.drawable.elephant, "Meet the elephant, the heavyweight champion of the animal kingdom. This colossal creature has a trunk longer than a giraffe's neck and ears bigger than satellite dishes. They say an elephant never forgets, probably because they've stored a lifetime supply of peanuts in their memory banks.  "));
        animals.add(new Animal("antelope", R.drawable.antelope, "Meet the antelope, nature's own supercharged sprinter! With legs that can make Usain Bolt look like a slow-motion video, these goofy-looking, high-speed hoppers are always ready to dash away from predators faster than a toddler running from bath time."));
        animals.add(new Animal("chimp", R.drawable.chimp, "The chimp, known for its hilarious antics and mischievous personality, is basically nature's stand-up comedian. With a face that looks like it's perpetually ready to deliver a punchline, this furry fellow spends its days swinging from trees, making monkey business look like a sidesplitting sitcom."));
        animals.add(new Animal("t-rex", R.drawable.trex, "A specially designed and fortified facility located in a remote and secure area, with state-of-the-art technology to ensure the safety of both visitors and the T-Rex."));
        mAdapter = new MyAdapter(animals);
        recyclerView.setAdapter(mAdapter);
//        recyclerView.addOnItemTouchListener(new RecyclerViewClickListener(this, recyclerView,
//                new RecyclerViewClickListener.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(View view, int position) {
//                        Animal animal = animals.get(position);
//                        Intent intent = new Intent(AnimalListingActivity.this, AnimalDetail.class);
//                        intent.putExtra("animal", animal);
//                        startActivity(intent);
//                    }
//                }));
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