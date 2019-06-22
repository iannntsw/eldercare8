package com.example.eldercare.HealthMonitor;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eldercare.DayPlanner.DoesAdapter;
import com.example.eldercare.DayPlanner.UpdateDayPlanActivity;
import com.example.eldercare.R;

import java.util.ArrayList;

public class HealthAdapter extends RecyclerView.Adapter<HealthAdapter.MyViewHolder> {

    Context context;
    ArrayList<MyHealth> myHealth;

    public HealthAdapter(Context c, ArrayList<MyHealth> p){
        context = c;
        myHealth = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_health,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.dateHealth.setText("Date: " + myHealth.get(i).getDateHealth());
        myViewHolder.systolicHealth.setText("Systolic/Dialostic: " + myHealth.get(i).getSystolicHealth());
        myViewHolder.heartHealth.setText("Heart Rate: " + myHealth.get(i).getHeartHealth());
        myViewHolder.mapHealth.setText("Map: " + myHealth.get(i).getMapHealth());
        myViewHolder.weightHealth.setText("Weight: " + myHealth.get(i).getWeightHealth());

        final String getDateHealth = myHealth.get(i).getDateHealth();
        final String getHeartHealth = myHealth.get(i).getHeartHealth();
        final String getMapHealth = myHealth.get(i).getMapHealth();
        final String getSystolicHealth = myHealth.get(i).getSystolicHealth();
        final String getWeightHealth = myHealth.get(i).getWeightHealth();
        final String getHealthId = myHealth.get(i).getHealthId();

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (context, UpdateHealthActivity.class);
                intent.putExtra("dateHealth", getDateHealth);
                intent.putExtra("systolicHealth", getSystolicHealth);
                intent.putExtra("mapHealth", getMapHealth);
                intent.putExtra("weightHealth", getWeightHealth);
                intent.putExtra("heartHealth", getHeartHealth);
                intent.putExtra("healthId", getHealthId);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myHealth.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView dateHealth, systolicHealth, heartHealth, mapHealth, weightHealth, healthId;

        public MyViewHolder(View itemView) {
            super(itemView);
            dateHealth = (TextView) itemView.findViewById(R.id.dateHealth);
            systolicHealth = (TextView) itemView.findViewById(R.id.systolicHealth);
            heartHealth = (TextView) itemView.findViewById(R.id.heartHealth);
            mapHealth = (TextView) itemView.findViewById(R.id.mapHealth);
            weightHealth = (TextView) itemView.findViewById(R.id.weightHealth);

        }
    }
}
