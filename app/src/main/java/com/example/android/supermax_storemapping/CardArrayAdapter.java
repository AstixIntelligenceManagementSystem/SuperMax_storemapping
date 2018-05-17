package com.example.android.supermax_storemapping;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class CardArrayAdapter extends BaseAdapter{
	
	private Context context;
	private LayoutInflater inflater;
	
	private List<String> listStore = null;
	
	private List<String> listOutletId;
	EditText txtTextSearch;
String tagVal;
	private ArrayList<String> listStoreOrigin;
	private ArrayList<String> listStoreIDOrigin;

	ListView listViewOption;

	SearchListCommunicator communicator;
	
	Dialog listDialog;
	TextView textView;
	public CardArrayAdapter(Context context,List<String> listStore,List<String> listOutletId,EditText txtTextSearch,ListView listViewOption,String tagVal,Dialog listDialog,TextView textView)
	{
		this.context=context;
		
		inflater=LayoutInflater.from(context);
		this.listStore=listStore;
		this.listDialog=listDialog;
		this.textView=textView;
		this.txtTextSearch=txtTextSearch;
		this.listOutletId=listOutletId;
		this.listViewOption=listViewOption;
		this.tagVal=tagVal;
		this.listStoreOrigin=new ArrayList<String>();
		
		this.listStoreIDOrigin=new ArrayList<String>();
		
		
		listStoreIDOrigin.addAll(this.listOutletId);
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
			holder.txt_store.setTag(listOutletId.get(position));
			
			communicator=(SearchListCommunicator) context;
			convertView.setTag(holder);
		}
		else
		{
			holder=(ViewHolder) convertView.getTag();
		}
		
		holder.ll_listChild.setTag(listOutletId.get(position));
		holder.txt_store.setText(listStore.get(position));
		
		
		holder.ll_listChild.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				communicator.selectedOption(v.getTag().toString(),holder.txt_store.getText().toString(),txtTextSearch,listViewOption, tagVal,listDialog,textView);
				
			}
		});
		
		
		return convertView;
	}

	
	public void filter(String charText) {
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
		
		}
}
