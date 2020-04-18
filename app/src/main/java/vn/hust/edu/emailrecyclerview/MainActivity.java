package vn.hust.edu.emailrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<ItemModel> listEmail;
    EditText keyword;
    Button favorite;
    ItemAdapter adapter;
    boolean isfavourite = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        keyword = findViewById(R.id.txt_keyword);
        favorite= findViewById(R.id.btn_favorite);

        listEmail= new ArrayList<>();
        listEmail.add(new ItemModel("Instructables","12:59 PM", "Modern Ceiling LED Lamp", "Musical Instruments to Make at Home, Origami Infinite Supernova", R.drawable.ic_star_normal));
        listEmail.add(new ItemModel("Anstructables", "12:59 PM", "DropArt - Precision Two Drop Photographic Collider", "Musical Instruments to Make at Home, Origami Infinite Supernova", R.drawable.ic_star_normal));
        listEmail.add(new ItemModel("Onstructables", "12:59 PM", "Easy Tensegrity Sculpture: Floating Table Top", "Musical Instruments to Make at Home, Origami Infinite Supernova", R.drawable.ic_star_normal));
        listEmail.add(new ItemModel("Unstructables",  "12:59 PM", "Distance Learning With Tinkercad Contest ", "Musical Instruments to Make at Home, Origami Infinite Supernova", R.drawable.ic_star_normal));
        listEmail.add(new ItemModel("Enstructables", "12:59 PM", "How to 3D Print a Surfboard", "Musical Instruments to Make at Home, Origami Infinite Supernova", R.drawable.ic_star_normal));
        listEmail.add(new ItemModel("Dnstructables",  "12:59 PM", "Modern Ceiling LED Lamp", "Musical Instruments to Make at Home, Origami Infinite Supernova", R.drawable.ic_star_normal));
        listEmail.add(new ItemModel("Pnstructables", "12:59 PM", "Easy Tensegrity Sculpture: Floating Table Top", "Musical Instruments to Make at Home, Origami Infinite Supernova", R.drawable.ic_star_normal));
        //Khai báo RecycleView
        final RecyclerView recyclerView= (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        //Khai báo kiểu layour manage
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Khai báo Adapter
        adapter= new ItemAdapter(listEmail);
        recyclerView.setAdapter(adapter);

        keyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterFavourite(isfavourite,listEmail);
                isfavourite= (! isfavourite);
            }
        });
    }

    private  void filter(String text){
        ArrayList<ItemModel> filteredList = new ArrayList<>();
        for(ItemModel item: listEmail){
            if(item.getName().toLowerCase().trim().contains(text.toLowerCase().trim())||
                item.getContent().toLowerCase().trim().contains(text.toLowerCase().trim())||
                item.getSubject().toLowerCase().trim().contains(text.toLowerCase().trim())){
                    filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }

    private  void filterFavourite(boolean isFavourite,List<ItemModel> list){
        ArrayList<ItemModel> filteredFavourite = new ArrayList<>();
        if(isFavourite ==true){
            for(ItemModel item: list){
                    if(item.isSelected()){
                        filteredFavourite.add(item);
                    }
                }
        } else {
            filteredFavourite.addAll(list);
        }
        adapter.filterFavorite(filteredFavourite);
    }
}
