package com.ssverma.iiitkota.admission;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import com.ssverma.iiitkota.R;

/**
 * Created by IIITK on 6/12/2016.
 */
public class Admission_Statistics_Adapter extends RecyclerView.Adapter<Admission_Statistics_Adapter.ViewHolder> {

    private HashMap<String , ArrayList<AdmissionStatisticsWrapper>> rank_map;
    private ArrayList<String> branch_list; //key of map

    Admission_Statistics_Adapter(ArrayList<String> branch_list , HashMap<String , ArrayList<AdmissionStatisticsWrapper>> rank_map) {
        this.branch_list = branch_list;
        this.rank_map = rank_map;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_statistics, parent, false);
        ViewHolder holder = new ViewHolder(rootView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.branch.setText(branch_list.get(position));
        holder.sc_sr.setText(rank_map.get(branch_list.get(position)).get(0).getStartingRank());
        holder.st_sr.setText(rank_map.get(branch_list.get(position)).get(1).getStartingRank());
        holder.obc_sr.setText(rank_map.get(branch_list.get(position)).get(2).getStartingRank());
        holder.gen_sr.setText(rank_map.get(branch_list.get(position)).get(3).getStartingRank());

        holder.sc_cr.setText(rank_map.get(branch_list.get(position)).get(0).getClosingRank());
        holder.st_cr.setText(rank_map.get(branch_list.get(position)).get(1).getClosingRank());
        holder.obc_cr.setText(rank_map.get(branch_list.get(position)).get(2).getClosingRank());
        holder.gen_cr.setText(rank_map.get(branch_list.get(position)).get(3).getClosingRank());

    }

    @Override
    public int getItemCount() {
        return branch_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView sc_sr;
        TextView st_sr;
        TextView obc_sr;
        TextView gen_sr;

        TextView sc_cr;
        TextView st_cr;
        TextView obc_cr;
        TextView gen_cr;

        TextView branch;

        public ViewHolder(View itemView) {
            super(itemView);

            sc_sr = (TextView) itemView.findViewById(R.id.sc_sr);
            st_sr = (TextView) itemView.findViewById(R.id.st_sr);
            obc_sr = (TextView) itemView.findViewById(R.id.obc_sr);
            gen_sr = (TextView) itemView.findViewById(R.id.gen_sr);

            sc_cr = (TextView) itemView.findViewById(R.id.sc_cr);
            st_cr = (TextView) itemView.findViewById(R.id.st_cr);
            obc_cr = (TextView) itemView.findViewById(R.id.obc_cr);
            gen_cr = (TextView) itemView.findViewById(R.id.gen_cr);

            branch = (TextView) itemView.findViewById(R.id.branch_name);
        }
    }
}
