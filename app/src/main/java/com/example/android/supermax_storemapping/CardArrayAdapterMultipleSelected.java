package com.example.android.supermax_storemapping;

import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class CardArrayAdapterMultipleSelected extends BaseAdapter{
	
	private Context context;
	private LayoutInflater inflater;
	
	private List<String> listStore = null;
	LinkedHashMap<String, String> hmapCheckedVal=new LinkedHashMap<String, String>();
	LinkedHashMap<String, String> hmapCheckedValId_Text=new LinkedHashMap<String, String>();
	private List<String> listOutletId;
	EditText txtTextSearch;
String tagVal;
	private ArrayList<String> listStoreOrigin;
	private ArrayList<String> listStoreIDOrigin;
	private ArrayList<String> listSelectedStore=new ArrayList<String>();
	private ArrayList<String> listSelectedStoreID=new ArrayList<String>();
	private ArrayList<String> listSelectedStoreSetChecked=new ArrayList<String>();
	LinearLayout llSlctdView;
	ListView listViewOption;

	SearchListCommunicator communicator;
	
	Dialog listDialog;
	TextView textView;
	public CardArrayAdapterMultipleSelected(Context context,List<String> listStore,List<String> listOutletId,EditText txtTextSearch,ListView listViewOption,String tagVal,Dialog listDialog,TextView textView,LinearLayout llSlctdView,ArrayList<String> listSelectedStoreSetChecked)
	{
		this.context=context;
		
		inflater=LayoutInflater.from(context);
		this.listStore=listStore;
		this.listDialog=listDialog;
		this.textView=textView;
		this.txtTextSearch=txtTextSearch;
		this.listOutletId=listOutletId;
		this.listSelectedStoreSetChecked=listSelectedStoreSetChecked;
		this.listViewOption=listViewOption;
		this.tagVal=tagVal;
		this.listStoreOrigin=new ArrayList<String>();
		this.llSlctdView=llSlctdView;
		this.listStoreIDOrigin=new ArrayList<String>();
		
		
		this.listStoreIDOrigin.addAll(this.listOutletId);
		this.listStoreOrigin.addAll(this.listStore);
	
	for(int i=0;i<listStoreIDOrigin.size();i++)
	{
		
		
		if(listSelectedStoreSetChecked.contains(listStoreIDOrigin.get(i)))
		{
			hmapCheckedVal.put(listStoreIDOrigin.get(i), "1");
			listSelectedStoreID.add(listStoreIDOrigin.get(i));
			listSelectedStore.add(listStoreOrigin.get(i));
		}
		else
		{
			hmapCheckedVal.put(listStoreIDOrigin.get(i), "0");
		}
		
	}

		
	}

	public class ViewHolder
	{
		TextView txt_store;
		ImageView img_slctd_disslctd;
		
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
			convertView=inflater.inflate(R.layout.custom_listview_multiple, null);
			communicator=(SearchListCommunicator) context;
			holder.txt_store=(TextView) convertView.findViewById(R.id.txt_store);
			holder.img_slctd_disslctd=(ImageView) convertView.findViewById(R.id.img_slctd_disslctd);
			
			
			
			holder.ll_listChild=(LinearLayout) convertView.findViewById(R.id.ll_listChild);
			
			
		
			convertView.setTag(holder);
		}
		else
		{
			holder=(ViewHolder) convertView.getTag();
		}
		if(hmapCheckedVal.get(listOutletId.get(position)).equals("1"))
		{
			
			holder.img_slctd_disslctd.setImageResource(R.drawable.checked_box);
			holder.img_slctd_disslctd.setTag(listOutletId.get(position)+"^true");
		
		}
		else
		{
			holder.img_slctd_disslctd.setTag(listOutletId.get(position)+"^false");
			holder.img_slctd_disslctd.setImageResource(R.drawable.unchecked_box);
		}
		
		holder.txt_store.setTag(listStore.get(position));
		holder.ll_listChild.setTag(listOutletId.get(position));
		holder.txt_store.setText(listStore.get(position));
		
		holder.img_slctd_disslctd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View vCheckBox) {
				ImageView imgChckBox=(ImageView)vCheckBox;
				String getCheckBoxTag=vCheckBox.getTag().toString();
				String idOfCheckedText=getCheckBoxTag.split(Pattern.quote("^"))[0];
				
				boolean isCheckedText=Boolean.valueOf(getCheckBoxTag.split(Pattern.quote("^"))[1]);
				if(!isCheckedText)
				{
					listSelectedStoreID.add(idOfCheckedText);
					listSelectedStore.add(holder.txt_store.getText().toString());
					hmapCheckedVal.put(idOfCheckedText, "1");
					imgChckBox.setImageResource(R.drawable.checked_box);
					vCheckBox.setTag(idOfCheckedText+"^true");
				}
				else
				{
					if(listSelectedStore!=null)
					{
						for(int i=0;i<listSelectedStore.size();i++)
						{
							if(holder.txt_store.getText().toString().equals(listSelectedStore.get(i)))
							{
								hmapCheckedVal.put(idOfCheckedText, "0");
								listSelectedStoreID.remove(i);
								listSelectedStore.remove(i);
							}
						}
					}
					imgChckBox.setImageResource(R.drawable.unchecked_box);
					vCheckBox.setTag(idOfCheckedText+"^false");
				}
				
				if(listSelectedStore!=null)
				{
					communicator.selectedStoreMultiple(idOfCheckedText,holder.txt_store.getText().toString(),txtTextSearch,listViewOption, tagVal,listDialog,textView,llSlctdView,listSelectedStore,listSelectedStoreID,listStoreOrigin);
				}
							}
		});
		/*holder.ll_listChild.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				communicator.selectedStoreMultiple(v.getTag().toString(),holder.txt_store.getText().toString(),txtTextSearch,listViewOption, tagVal,listDialog,textView,llSlctdView,listSelectedStore);
				
			}
		});
		*/
		
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
