package KU_BBA_subjectlists;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import utils.GlobalClass;
import utils.Listdata;
import com.application.kurukshetrauniversitypapers.Pdflist;
import com.application.kurukshetrauniversitypapers.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import Adapters.Listadapter;

public class Bba_fourthsem_subjectlist extends AppCompatActivity {
    ListView listView;
    List<Listdata> subjectlist;
    Button downloadall;
    TextView subjectname;
    TextView papercount;
    TextView textView;
    String key;
    static int bb_twopapercount;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bba_fourthsem_subjectlist);
        downloadall=findViewById(R.id.download_btn);
        subjectname=findViewById(R.id.subjectname);
        papercount=findViewById(R.id.papercount);
        textView=findViewById(R.id.textView);
        listView=findViewById(R.id.list);
        Intent intent=getIntent();

        key=intent.getStringExtra("key");
        textView.setText(key);

        GlobalClass globalClass=(GlobalClass)getApplicationContext();
        globalClass.setBranch("BB");
        globalClass.setSemester("04");
        globalClass.setBoard("KU");

        subjectlist = new ArrayList<>();
        ref= FirebaseDatabase.getInstance().getReference("IN/KU/BB/04");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s)
            {
                if(dataSnapshot.getKey().equals("HB")) {
                    bb_twopapercount=(int)dataSnapshot.getChildrenCount();
                    subjectlist.add(new Listdata("Human behaviour at work", bb_twopapercount+""));

                }
                if(dataSnapshot.getKey().equals("MT")) {
                    bb_twopapercount=(int)dataSnapshot.getChildrenCount();
                    subjectlist.add(new Listdata("Micro business environment", bb_twopapercount+""));

                }
                if(dataSnapshot.getKey().equals("BS")) {
                    bb_twopapercount=(int)dataSnapshot.getChildrenCount();
                    subjectlist.add(new Listdata("Business statistics", bb_twopapercount+""));

                }
                if(dataSnapshot.getKey().equals("MK")) {
                    bb_twopapercount=(int)dataSnapshot.getChildrenCount();
                    subjectlist.add(new Listdata("Marketing management", bb_twopapercount+""));

                }
                if(dataSnapshot.getKey().equals("FZ")) {
                    bb_twopapercount =(int)dataSnapshot.getChildrenCount();
                    subjectlist.add(new Listdata("Financial management", bb_twopapercount+""));
                }

                Listadapter adapter = new Listadapter(getBaseContext(), R.layout.row, subjectlist);
                listView.setAdapter(adapter);

            }


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) {
                    Intent intent=new Intent(Bba_fourthsem_subjectlist.this, Pdflist.class);
                    intent.putExtra("subject","IN/KU/BB/04/BS");
                    startActivity(intent);
                }
                if(position==1) {
                    Intent intent=new Intent(Bba_fourthsem_subjectlist.this, Pdflist.class);
                    intent.putExtra("subject","IN/KU/BB/04/FZ");
                    startActivity(intent);
                }
                if(position==2) {
                    Intent intent=new Intent(Bba_fourthsem_subjectlist.this, Pdflist.class);
                    intent.putExtra("subject","IN/KU/BB/04/HB");
                    startActivity(intent);
                }
                if(position==3) {
                    Intent intent=new Intent(Bba_fourthsem_subjectlist.this, Pdflist.class);
                    intent.putExtra("subject","IN/KU/BB/04/MK");
                    startActivity(intent);
                }
                if(position==4) {
                    Intent intent=new Intent(Bba_fourthsem_subjectlist.this, Pdflist.class);
                    intent.putExtra("subject","IN/KU/BB/04/MT");
                    startActivity(intent);
                }


            }
        });


    }
}

