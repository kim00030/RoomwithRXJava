package demo.dankim.com.roomwithrxjava.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import demo.dankim.com.roomwithrxjava.database.Employee;
import demo.dankim.com.roomwithrxjava.databinding.ItemLayoutBinding;

/**
 * Created by Dan Kim on 2019-01-29
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder> {

    private List<Employee> employeeList;
    private LayoutInflater layoutInflater;
    //Binding object for item_layout.xml
    private ItemLayoutBinding itemLayoutBinding;

    public RecyclerViewAdapter() {

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        itemLayoutBinding = ItemLayoutBinding.inflate(layoutInflater, viewGroup, false);

        return new ItemViewHolder(itemLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int position) {
        Employee employee = this.employeeList.get(position);
        itemViewHolder.bind(employee);
    }

    @Override
    public int getItemCount() {
        return this.employeeList == null ? 0 : this.employeeList.size();
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
        notifyDataSetChanged();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private ItemLayoutBinding itemLayoutBinding;

        public ItemViewHolder(@NonNull ItemLayoutBinding itemLayoutBinding) {
            super(itemLayoutBinding.getRoot());
            this.itemLayoutBinding = itemLayoutBinding;
        }

        public void bind(Employee employee) {
            itemLayoutBinding.setEmployee(employee);
        }
    }

}
