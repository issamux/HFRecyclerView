package com.issamux;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by issamux
 */
public abstract class AbstractRecyclerViewAdapter<VH extends RecyclerView.ViewHolder, VHItem extends RecyclerView.ViewHolder,
		VHHeader extends RecyclerView.ViewHolder, VHFooter extends RecyclerView.ViewHolder>
		extends RecyclerView.Adapter<VH> {

	//constants
	protected static final int TYPE_HEADER = 0;
	protected static final int TYPE_ITEM = 1;
	protected static final int TYPE_FOOTER = 2;
	//
	protected Context context;
	//flags
	protected boolean hasFooter;
	protected boolean hasHeader;
	//
	private VHFooter footer;
	private VHHeader header;
	private VHItem generics;
	//
	protected RecyclerView recyclerView;

	public AbstractRecyclerViewAdapter(Context ctx, RecyclerView recyclerView) {
		context = ctx;
		this.recyclerView = recyclerView;
	}


	@Override
	public VH onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		if (viewType == TYPE_ITEM) {
			//generics
			return buildItemView(inflater, parent);
		} else if (viewType == TYPE_HEADER) {
			if (header == null)
				header = buildHeaderView(inflater, parent);
			return (VH) header;

		} else if (viewType == TYPE_FOOTER) {
			if (footer == null)
				footer = buildFooterView(inflater, parent);
			return (VH) footer;
		}
		throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
	}

	protected abstract VHFooter buildFooterView(LayoutInflater inflater, ViewGroup parent);

	protected abstract VHHeader buildHeaderView(LayoutInflater inflater, ViewGroup parent);

	protected abstract VH buildItemView(LayoutInflater inflater, ViewGroup parent);

	protected abstract VHHeader buildHeaderFromView(View headerView);

	protected abstract VHFooter buildFooterFromView(View footerView);

	protected abstract void onBindFooterViewHolder(VHFooter holder, int position);

	protected abstract void onBindHeaderViewHolder(VHHeader holder, int position);

	protected abstract void onBindViewItemHolder(VHItem holder, int position);

	public void detachHeader() {
		if (hasHeader) {
			recyclerView.getLayoutManager().removeViewAt(0);
			header = null;
			hasHeader = false;
			notifyItemRemoved(0);
		}
	}

	public void detachFooter() {
		if (hasFooter) {
			recyclerView.getLayoutManager().removeViewAt(getItemCount() - 1);
			footer = null;
			hasFooter = false;
			notifyItemRemoved(getItemCount());
		}
	}


	@Override
	public int getItemCount() {
		int size = dataCount();
		if (hasHeader)
			size++;
		if (hasFooter)
			size++;
		return size;
	}


	protected abstract int dataCount();

	@Override
	public int getItemViewType(int position) {
		if (hasHeader & isPositionHeader(position)) {
			return TYPE_HEADER;
		} else if (hasFooter & isPositionFooter(position)) {
			return TYPE_FOOTER;
		} else {
			return TYPE_ITEM;
		}
	}

	private boolean isPositionHeader(int position) {
		return position == 0;
	}

	private boolean isPositionFooter(int position) {
		return position == getItemCount() - 1;
	}


	protected void attachHeader(int headerLayout) {
		if (!hasHeader) {
			hasHeader = true;
			LayoutInflater inflater = LayoutInflater.from(context);
			View headerView = inflater.inflate(headerLayout, null);
			header = buildHeaderFromView(headerView);
			notifyItemInserted(0);
		}
	}

	protected void attachFooter(int footerLayout) {
		if (!hasFooter) {
			hasFooter = true;
			LayoutInflater inflater = LayoutInflater.from(context);
			View footerView = inflater.inflate(footerLayout, null);
			footer = buildFooterFromView(footerView);
			notifyItemInserted(getItemCount());
		}
	}


	@Override
	public void onBindViewHolder(VH holder, int position) {
		if (isPositionHeader(position) && hasHeader) {
			onBindHeaderViewHolder((VHHeader) holder, position--);
		} else if (isPositionFooter(position) && hasFooter) {
			onBindFooterViewHolder((VHFooter) holder, position--);
		} else
			onBindViewItemHolder((VHItem) holder, position);
	}


}
