package com.ssverma.iiitkota.admission;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ssverma.iiitkota.R;

/**
 * Created by IIITK on 6/9/2016.
 */
public class Admission_Home_Adapter extends RecyclerView.Adapter<Admission_Home_Adapter.ViewHolder>{
    private String[] icon_names;
    private int[] icons;

    private RCVClickListener listener;

    Admission_Home_Adapter(String[] icon_names , int[] icons){
        this.icon_names = icon_names;
        this.icons = icons;
    }

    public void setRCVClickListener(RCVClickListener listener){
        this.listener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_admission_home , parent , false);
        ViewHolder holder = new ViewHolder(rootView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.icon_name.setText(icon_names[position]);
        holder.icon.setImageResource(icons[position]);
    }

    @Override
    public int getItemCount() {
        return icons.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView icon_name;
        ImageView icon;
        LinearLayout itemHolder;

        public ViewHolder(View itemView) {
            super(itemView);

            icon_name = (TextView) itemView.findViewById(R.id.home_icon_name);
            icon = (ImageView) itemView.findViewById(R.id.home_icon);

            itemHolder = (LinearLayout) itemView.findViewById(R.id.home_item_holder);
            itemHolder.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (listener != null){
                listener.onRCVClick(v , getAdapterPosition());
            }
        }
    }

    interface RCVClickListener {
        void onRCVClick(View v, int position);
    }
}
