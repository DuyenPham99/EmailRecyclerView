package vn.hust.edu.emailrecyclerview;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<ItemModel> listItem;

    public ItemAdapter(List<ItemModel> listItem) {
        this.listItem = listItem;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new EmailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EmailViewHolder viewHolder= (EmailViewHolder) holder;

        ItemModel item= listItem.get(position);

        viewHolder.textView.setText(item.getName().substring(0,1));
        Drawable background = viewHolder.textView.getBackground();
        background.setColorFilter(new PorterDuffColorFilter(item.getColor(), PorterDuff.Mode.SRC_ATOP));
        viewHolder.txtSubject.setText(item.getSubject());
        viewHolder.txtContent.setText(item.getContent());
        viewHolder.txtTime.setText(item.getTime());
        viewHolder.txtWho.setText(item.getName());
        if(item.isSelected){
            viewHolder.imageFavour.setImageResource(R.drawable.ic_star_favourite);
        }
        else viewHolder.imageFavour.setImageResource(R.drawable.ic_star_normal);
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public  void filterList(ArrayList<ItemModel> filteredList){
        listItem= filteredList;
        notifyDataSetChanged();
    }

    public  void filterFavorite(ArrayList<ItemModel> filteredFavourite){
        listItem = filteredFavourite;
        notifyDataSetChanged();
    }


    public class EmailViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView txtSubject;
        TextView txtContent;
        TextView txtWho;
        TextView txtTime;
        ImageView imageFavour;
        public EmailViewHolder(@NonNull final View itemView) {
            super(itemView);
            textView= itemView.findViewById(R.id.txtground);
            txtSubject= itemView.findViewById(R.id.txtsubject);
            txtTime= itemView.findViewById(R.id.txttime);
            txtContent= itemView.findViewById(R.id.txtcontent);
            txtWho= itemView.findViewById(R.id.txtname);
            imageFavour =itemView.findViewById(R.id.image_favour);
            imageFavour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isSelected = listItem.get(getAdapterPosition()).isSelected();
                    listItem.get(getAdapterPosition()).setSelected(!isSelected);
                    notifyDataSetChanged();
                }
            });
        }
    }
}
