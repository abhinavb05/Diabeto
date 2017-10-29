package com.example.yolo.diabeto;

import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.junit.experimental.theories.DataPoint;

import java.text.DateFormat;
import java.util.Date;

public class Graph extends AppCompatActivity {

    Button submit;
    EditText t;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        submit=(Button)findViewById(R.id.button2);
        t=(EditText)findViewById(R.id.sugar);
        GraphView graph = (GraphView)findViewById(R.id.graph);
        LineGraphSeries<com.jjoe64.graphview.series.DataPoint> series = new LineGraphSeries<com.jjoe64.graphview.series.DataPoint>(new com.jjoe64.graphview.series.DataPoint[]{
                new com.jjoe64.graphview.series.DataPoint(0,1),
                new com.jjoe64.graphview.series.DataPoint(1,5),
                new com.jjoe64.graphview.series.DataPoint(2,3),
                new com.jjoe64.graphview.series.DataPoint(3,2),
                new com.jjoe64.graphview.series.DataPoint(4,6)
        });
        graph.addSeries(series);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String bsl = t.getText().toString();
                if (bsl.contains("[a-zA-Z]+")&&(bsl.contains(".")))
                {
                    Toast.makeText(getApplicationContext(),"Input should be numeric",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
                    mDatabase.child("users").child(currentFirebaseUser.getUid()).child("sugarlevels").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            /*int c = (int) (dataSnapshot.getChildrenCount()+1);
                            String s = "S"+Integer.toString(c);
                            String time = DateFormat.getDateTimeInstance().format(new Date());
                            String r = bsl+" "+time;
                            dataSnapshot.child(s).getRef().setValue(r);*/
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    //FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
                    //
                    //mDatabase.child("users").child(currentFirebaseUser.getUid()).child("Sugar Levels").child(mDatabase.)setValue(s);
                }
            }
        });
    }
}
