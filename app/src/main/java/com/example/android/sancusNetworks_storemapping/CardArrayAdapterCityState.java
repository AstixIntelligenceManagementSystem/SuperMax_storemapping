package com.example.android.sancusNetworks_storemapping;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CardArrayAdapterCityState extends BaseAdapter{

	private Context context;
	private LayoutInflater inflater;

	private List<String> listStore = null;



;
	private ArrayList<String> listStoreOrigin;


	ListView listViewOption;

	CategoryCommunicatorCityState communicator;
	String previousSlctdCtgry;
	Dialog listDialog;
	int flgCityState;
	TextView textView;
	public CardArrayAdapterCityState(Context context, int flgCityState,List<String> listStore, Dialog listDialog, String previousSlctdCtgry)
	{
		this.context=context;
		this.previousSlctdCtgry=previousSlctdCtgry;
		inflater=LayoutInflater.from(context);
		this.listStore=listStore;
		this.listDialog=listDialog;
		this.textView=textView;
		this.flgCityState=flgCityState;
		this.listStoreOrigin=new ArrayList<String>();
		

		
		

		this.listStoreOrigin.addAll(this.listStore);
	


		
	}

	public class ViewHolder
	{
		TextView txt_store;
	
		LinearLayout ll_listChild;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listStore.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listStore.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if(convertView==null)
		{
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.custom_listview, null);
			
			holder.txt_store=(TextView) convertView.findViewById(R.id.txt_store);
			
			
			holder.ll_listChild=(LinearLayout) convertView.findViewById(R.id.ll_listChild);


			communicator=(CategoryCommunicatorCityState) context;
			convertView.setTag(holder);
		}
		else
		{
			holder=(ViewHolder) convertView.getTag();
		}
		

		holder.txt_store.setText(listStore.get(position));
		if(!TextUtils.isEmpty(previousSlctdCtgry))
		{
			if(previousSlctdCtgry.equals(listStore.get(position)))
			{
				holder.ll_listChild.setBackground(context.getResources().getDrawable(R.drawable.card_state_pressed));
			}
			else
			{
				holder.ll_listChild.setBackground(context.getResources().getDrawable(R.drawable.card_background_selector));
			}
		}
		else
		{
			holder.ll_listChild.setBackground(context.getResources().getDrawable(R.drawable.card_background_selector));
		}

		holder.ll_listChild.setTag(listStore.get(position));
		
		holder.ll_listChild.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				communicator.selectedCityState(v.getTag().toString().trim(),listDialog,flgCityState);
				
			}
		});
		
		
		return convertView;
	}

	
	/*public void filter(String charText) {
		charText=charText.toLowerCase();
		listStore.clear();
		listOutletId.clear();
		if(charText.length()==0)
		{
			
			listStore.addAll(listStoreOrigin);
		
			listOutletId.addAll(listStoreIDOrigin);


			
		
		}
		else
		{
			
			int ownerPositin=0;
			for(String storeString: listStoreOrigin)
			{
				if(storeString.toLowerCase().contains(charText))
				{
					
					listStore.add(storeString);
				
					listOutletId.add(listStoreIDOrigin.get(ownerPositin));
					
				
				}
				ownerPositin++;
			}
			
		
		}
		
		notifyDataSetChanged();
		
		}*/
}
