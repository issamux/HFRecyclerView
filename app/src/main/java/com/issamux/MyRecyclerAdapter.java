package com.issamux;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by issamux 2016
 */

public class MyRecyclerAdapter extends AbstractRecyclerViewAdapter<RecyclerView.ViewHolder, MyRecyclerAdapter.CustomDataSetVH,
		MyRecyclerAdapter.HeaderViewHolder, MyRecyclerAdapter.FooterViewHolder> {

	private static final String TAG = "MyRecyclerAdapter";
	private String[] mDataSet = new String[]{"IssamuX", "Was", "Here"};
	private CustomDataSetVH dataSetVH;

	public MyRecyclerAdapter(Context context, RecyclerView recyclerView) {
		super(context, recyclerView);
	}

	@Override
	protected FooterViewHolder buildFooterView(LayoutInflater inflater, ViewGroup parent) {
		return null;
	}

	@Override
	protected HeaderViewHolder buildHeaderView(LayoutInflater inflater, ViewGroup parent) {
		View headerView = inflater.inflate(R.layout.list_item, null);
		return new HeaderViewHolder(headerView);
	}

	@Override
	protected HeaderViewHolder buildHeaderFromView(View headerView) {
		return new HeaderViewHolder(headerView);
	}

	@Override
	protected FooterViewHolder buildFooterFromView(View footerView) {
		return new FooterViewHolder(footerView);
	}

	@Override
	protected void onBindFooterViewHolder(FooterViewHolder holder, int position) {
		Log.d(TAG, "instanceof FooterViewHolder ");

	}

	@Override
	protected void onBindHeaderViewHolder(HeaderViewHolder holder, int position) {
		Log.d(TAG, "instanceof HeaderViewHolder  ");
	}

	@Override
	protected void onBindViewItemHolder(CustomDataSetVH holder, int position) {
		dataSetVH.mName.setText(mDataSet[position - 1]);
	}


	@Override
	protected CustomDataSetVH buildItemView(LayoutInflater inflater, ViewGroup parent) {
		View mView = inflater.inflate(R.layout.list_item, null);
		dataSetVH = new CustomDataSetVH(mView);
		return dataSetVH;
	}


	@Override
	protected int dataCount() {
		return mDataSet.length;
	}

	//Example of custom item View Holder class
	public static class CustomDataSetVH extends RecyclerView.ViewHolder {
		TextView mName;
		public CustomDataSetVH(View itemView) {
			super(itemView);
			mName = (TextView) itemView.findViewById(R.id.txtName);
		}
	}

	//Header View Holder class
	public static class HeaderViewHolder extends RecyclerView.ViewHolder {
		public HeaderViewHolder(View itemView) {
			super(itemView);
		}
	}

	//Footer View Holder class
	public static class FooterViewHolder extends RecyclerView.ViewHolder {
		public FooterViewHolder(View itemView) {
			super(itemView);
		}
	}

}
