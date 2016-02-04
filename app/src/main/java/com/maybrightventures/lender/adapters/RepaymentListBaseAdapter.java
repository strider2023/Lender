package com.maybrightventures.lender.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.maybrightventures.lender.R;
import com.maybrightventures.lender.dao.RepaymentDAO;
import com.maybrightventures.lender.dao.enums.LoanStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arindamnath on 13/01/16.
 */
public class RepaymentListBaseAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<RepaymentDAO> repaymentDAOs = new ArrayList<>();

    public RepaymentListBaseAdapter(Context contenxt) {
        this.context = contenxt;
        layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<RepaymentDAO> data) {
        repaymentDAOs = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return repaymentDAOs.size();
    }

    @Override
    public RepaymentDAO getItem(int position) {
        return repaymentDAOs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.adapter_repayment_details, parent, false);
            holder.status = (TextView) convertView.findViewById(R.id.repayment_details_status_text);
            holder.date = (TextView) convertView.findViewById(R.id.repayment_details_date_text);
            holder.loanId = (TextView) convertView.findViewById(R.id.repayment_details_loan_id_text);
            holder.amount = (TextView) convertView.findViewById(R.id.repayment_details_amount_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if(repaymentDAOs.get(position).getStatus() != null) {
            holder.status.setVisibility(View.VISIBLE);
            switch (repaymentDAOs.get(position).getStatus()) {
                case OPEN:
                    holder.status.setText(LoanStatus.OPEN.toString());
                    holder.status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_error_outline_white_18dp, 0 , 0 , 0);
                    break;
                case CLOSED:
                    holder.status.setText(LoanStatus.CLOSED.toString());
                    holder.status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_error_outline_white_18dp, 0, 0, 0);
                    break;
                case RECEIVED:
                    holder.status.setText(LoanStatus.RECEIVED.toString());
                    holder.status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_done_all_white_18dp, 0, 0, 0);
                    break;
                case PENDING:
                    holder.status.setText(LoanStatus.PENDING.toString());
                    holder.status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_error_outline_white_18dp, 0, 0, 0);
                    break;
            }
        }
        holder.date.setText(repaymentDAOs.get(position).getDate());
        holder.loanId.setText(repaymentDAOs.get(position).getTenure());
        holder.amount.setText(repaymentDAOs.get(position).getAmount());
        return convertView;
    }

    static class ViewHolder {
        TextView date, loanId, amount, status;
    }
}
