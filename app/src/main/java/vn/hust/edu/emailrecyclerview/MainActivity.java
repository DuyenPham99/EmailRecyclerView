package vn.hust.edu.emailrecyclerview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import io.bloco.faker.Faker;

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
        keyword.setInputType(InputType.TYPE_NULL);

        Faker faker = new Faker();
        listEmail= new ArrayList<>();
        for(int i=0; i<10; i++){
            listEmail.add(new ItemModel(faker.name.name(), "12:30 PM", faker.lorem.sentence(), faker.lorem.paragraph(), R.drawable.ic_star_normal));
        }

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

        keyword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyword.setInputType(InputType.TYPE_CLASS_TEXT);
                keyword.setFocusableInTouchMode(true);
            }
        });

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favorite.requestFocus();
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
