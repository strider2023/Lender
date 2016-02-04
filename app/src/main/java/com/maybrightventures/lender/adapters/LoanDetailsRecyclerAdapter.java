package com.maybrightventures.lender.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maybrightventures.lender.R;
import com.maybrightventures.lender.dao.RepaymentDAO;
import com.maybrightventures.lender.dao.enums.LoanStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arindamnath on 04/02/16.
 */
public class LoanDetailsRecyclerAdapter extends RecyclerView.Adapter<LoanDetailsRecyclerAdapter.MyViewHolder> {

    private List<RepaymentDAO> repaymentDAOs = new ArrayList<>();

    public LoanDetailsRecyclerAdapter() {

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_repayment_details,
                viewGroup, false);
        return new MyViewHolder(view);
    }

    public void setData(List<RepaymentDAO> data) {
        repaymentDAOs = data;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if(repaymentDAOs.get(position).getStatus() != null) {
            holder.status.setVisibility(View.VISIBLE);
            switch (repaymentDAOs.get(position).getStatus()) {
                case OPEN:
                    holder.status.setText(LoanStatus.OPEN.toString());
                    holder.status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_error_outline_white_24dp, 0 , 0 , 0);
                    break;
                case CLOSED:
                    holder.status.setText(LoanStatus.CLOSED.toString());
                    holder.status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_error_outline_white_24dp, 0, 0, 0);
                    break;
                case RECEIVED:
                    holder.status.setText(LoanStatus.RECEIVED.toString());
                    holder.status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_done_all_white_24dp, 0, 0, 0);
                    break;
                case PENDING:
                    holder.status.setText(LoanStatus.PENDING.toString());
                    holder.status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_error_outline_white_24dp, 0, 0, 0);
                    break;
            }
        }
        holder.date.setText(repaymentDAOs.get(position).getDate());
        holder.loanId.setText(repaymentDAOs.get(position).getTenure());
        holder.amount.setText(repaymentDAOs.get(position).getAmount());
    }

    @Override
    public int getItemCount() {
        return repaymentDAOs == null ? 0 : repaymentDAOs.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView date, loanId, amount, status;

        public MyViewHolder(View itemView) {
            super(itemView);
            status = (TextView) itemView.findViewById(R.id.repayment_details_status_text);
            date = (TextView) itemView.findViewById(R.id.repayment_details_date_text);
            loanId = (TextView) itemView.findViewById(R.id.repayment_details_loan_id_text);
            amount = (TextView) itemView.findViewById(R.id.repayment_details_amount_text);
        }
    }

}
