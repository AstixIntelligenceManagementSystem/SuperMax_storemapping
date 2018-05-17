package com.example.android.supermax_storemapping;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Camera;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.transition.Transition;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CompoundButton.OnCheckedChangeListener;

import org.apache.http.HttpResponse;

public class NewStoreForm extends Fragment  {


	LinkedHashMap<String,String> hmapCityAgainstState;
	String defaultCity="";

	LinkedHashMap<String, String> hmapState_details=new LinkedHashMap<String, String>();
	String previousSlctdState="Select";
	String previousSlctdCity="Select";
	LinkedHashMap<String, String> hmapCity_details=new LinkedHashMap<String, String>();
	List<String> stateNames;
	List<String> cityNames;


	LinkedHashMap<String,Integer> hmapSingleValSpinner=new LinkedHashMap<String,Integer>();
	int flgHasQuote=0;
	ImageView flashImage;
	private boolean isLighOn = false;
	float mDist=0;
	public  Dialog dialog=null;
	ArrayList<Object> arrImageData=new ArrayList<Object>();
	private Camera mCamera;
	private CameraPreview mPreview;
	private Camera.PictureCallback mPicture;
	private Button capture,cancelCam, switchCamera;
	private Context myContext;
	private LinearLayout cameraPreview;
	private boolean cameraFront = false;

	String singleSelectedVal="";
	EditText etLocalArea,etPinCode,etOtherCity;
	TextView etCity,etState;
	TextView  txt_city,txt_state,txt_pincode;
	LinearLayout ll_address_section,ll_local_area;
	String addressKey;
	int flgAllowQuotation=1;
	String clickedTagPhoto;
	int flgSubmitFromQuotation=0;
	LinkedHashMap<String, String> hmapQuestionflgPrvValue=new LinkedHashMap<String, String>();
	LinkedHashMap<String, String> hmapPreviousVisitServerQuestionSavedAns=new LinkedHashMap<String,String>();
	String grpQstId_qstIdForName,grpQstIdForChannel,nameForStore;
	LinkedHashMap<String,String> hmapGetCheckBoxVisible=new LinkedHashMap<String,String>();
	TextView PaymentStageTextView,paymentModeTextviewNew,creditdaysTextboxNew,CreditlimitTextboxNew,percentageTextviewNew,paymentstagetextviewNew, CreditDaysTextbox, PaymentModeTextView, Date,SalesQuoteTypeSpinner,ValFrom,ValTo,SalesQuoteType,ValidityFrom,PaymentTerms,headerstring;
	 Calendar calendarS ;
     DatePickerDialog datePickerDialogs ;
     int Year, Month, Day ;
	int sectionIsShown=1;
	LinkedHashMap<String, String> hmapGrpQstId_DepndentQstOptId=new LinkedHashMap<String, String>();
	ArrayList<String> listFarmerID;
	ArrayList<String> listSelectedStoreOriginTemp;
	int section=0;
	String globalValueOfPaymentStageCheck="0"+"_"+"0"+"_"+"0";
	CheckBox chBoxView,AdvanceBeforeDeliveryCheckBoxNew,OnDeliveryCheckBoxNew,CreditCheckBoxNew;
	
	EditText percentageOfAdvanceBeforeDelivery,percentageOfOnDelivery,creditDaysOfOnDelivery,PercentageOfCredit,creditDaysOfCredit,creditLimitOfCredit;
	LinearLayout MainlayoutParentOfWholePage;
	 String QuestCode,QuestDesc,AnsControlType,AsnControlInputTypeID,AnsControlInputTypeMaxLength,AnsMustRequiredFlg,flgPrvsVisitQstIdWd1,
		QuestBundleFlg,ApplicationTypeID,Sequence,answerHint,QuestBundleGroupId,questGroupId;
	 LinkedHashMap<String,String> hmapZoneDisplayMstr;
	LinearLayout ll_data,parentOfAdvanceBeforeDeliveryPayMentMode,parentOfOnDeliveryPayMentMode,parentOfCreditPayMentMode,parentOfCheckBox;
	LayoutInflater inflaterSection;
	 private Calendar calendar;
	 LinkedHashMap<String, String> hmapsearchAnswerSlctd=new LinkedHashMap<String, String>();
	 private int year, month, day;
	LinkedHashMap<String,String> hmapAddress=new LinkedHashMap<String,String>();
	 LinkedHashMap<String, ArrayList<String>> hmapOptionValues=new LinkedHashMap<String, ArrayList<String>>();
	 public LinkedHashMap<String, ArrayList<String>> hmapSelectedMultipleDepend=new LinkedHashMap<String, ArrayList<String>>();
	 Uri uriSavedImage;
	 String uriStringPath="";
	 ArrayList<String> listimagePath=new ArrayList<String>();
	 String userName,imageName,imagButtonTag;
	 String  allDataToExistingCntct="",visitPlanLocId,newfullFileName,imei,onlyDate,taskGroupSelected,flagNewOld="1",locNodeID="0",locNodeType="0",	 
			 flgScheduleFollowUp="0",followUpScheduleDate="0",locNodeId_NodeTypeSelected,personName="";
	 File imageF;
	 String filePathPhoto;
	 LinkedHashMap<String, String> hmapOptionId=new LinkedHashMap<String, String>();
	LinkedHashMap<String, String> hmapQuestGrpId_GrpId=new LinkedHashMap<String,String>();
	LinkedHashMap<String, String> hmapQuestGrpId_QstId=new LinkedHashMap<String,String>();
	LinkedHashMap<String, Boolean> hmapMustRqrdFiled=new LinkedHashMap<String,Boolean>();
	LinkedHashMap<String, String> hmapOptionalFiled=new LinkedHashMap<String,String>();
	LinkedHashMap<String, String> hmapQuestionSavedAns=new LinkedHashMap<String,String>();
	LinkedHashMap<String, ArrayList<String>> hmapQuestDependVisible=new LinkedHashMap<String, ArrayList<String>>();
	LinkedHashMap<String, String> hmapAllValuesOfPaymentMode;
	LinkedHashMap<String, String> hmapgetOptDepMstr=new LinkedHashMap<String, String>();
	LinkedHashMap<String, ArrayList<String>> hmapGetOptionValDpndnt=new LinkedHashMap<String, ArrayList<String>>();
	
	LinkedHashMap<String, String> hmapImageClkdTempIdData=new LinkedHashMap<String,String>();
	
	LinkedHashMap<String, String> hmapGroupIdCopyAsAbove=new LinkedHashMap<String, String>();

	public LinkedHashMap<String, Integer> hmapisPhotolicked=new LinkedHashMap<String, Integer>();

	 LinearLayout ll_Section;
	 LinkedHashMap<String, String> hmapAnsValues=new LinkedHashMap<String, String>();
	 LinkedHashMap<String, String> hmapQuesIdandGetAnsCntrlType=new LinkedHashMap<String, String>();
	 Button calenderClkdBtn,cameraButton,btn_saveAsDraft;
	 TextView tv_Ques,txt_Username,textStCode,textQntty;
		String VisitStartTS;
		private final int requestCode = 200;
		 String[] dependentValuesQuesId,dependentParentQuesId;
	
		 SimpleDateFormat input = new SimpleDateFormat("dd/MMM/yyyy", Locale.ENGLISH);
		 SimpleDateFormat output = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
		StringBuilder sbMultiple=new StringBuilder();
		 boolean isSelectedSearch=false;
		
	  View headerView;
	  DBAdapterLtFoods helperDb;
	  LinearLayout ll_Image;
	  
	  public String currentStoreName="NA";
	  
	  String fullStringOfDelivery="0";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view=inflater.inflate(R.layout.new_store_form,container,false);
		ll_data=(LinearLayout) view.findViewById(R.id.ll_data);
		MainlayoutParentOfWholePage=(LinearLayout) view.findViewById(R.id.ll_data);
		 long syncTIMESTAMP = System.currentTimeMillis();
			Date dateobj = new Date(syncTIMESTAMP);
			SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
			VisitStartTS = df.format(dateobj);
	

		return view;
	}

	@Override
	public Transition getReturnTransition() {
		return super.getReturnTransition();
	}


	private void setSingleSpinnerVal() {
		if(hmapSingleValSpinner!=null && hmapSingleValSpinner.size()>0)
		{
			for(Map.Entry<String,Integer> entry:hmapSingleValSpinner.entrySet())
			{
				Spinner singleValSpinner= (Spinner) ll_data.findViewWithTag(entry.getKey());
				singleValSpinner.setSelection(1);
			}
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		helperDb=new DBAdapterLtFoods(getActivity());
		inflaterSection=(LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		try {
			TelephonyManager tManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
			imei = tManager.getDeviceId();
		}
		catch (SecurityException e)
		{

		}
		if(CommonInfo.imei.trim().equals(null) || CommonInfo.imei.trim().equals(""))
		{
			CommonInfo.imei=imei;
		}
		else
		{
			imei=CommonInfo.imei.trim();
		}

		getStateDetails();
		getCityDetails();
		defaultCity=helperDb.getDefaultCity();
        hmapCityAgainstState=helperDb.getCityAgainstState();

		hmapAllValuesOfPaymentMode= helperDb.fnGettblSalesQuotePaymentModeMstrAllValues();
		dependentValuesQuesId=helperDb.getAllOptValueDpndntQuest();
		dependentParentQuesId=helperDb.fnGetDependentParentQuesIdr();
		hmapQuesIdandGetAnsCntrlType=helperDb.fnGetQuestionID_AnsCntrlType();
		hmapQuestDependVisible=helperDb.getAllQuestIdDpndnt();
		hmapQuestGrpId_GrpId=helperDb.getAllQstGrpIdAgainstGrp();
		hmapQuestGrpId_QstId=helperDb.getQuestGrpIdLnkWdQstId();
		hmapQuestionSavedAns=helperDb.getQuestAnswer(AddNewStore_DynamicSectionWise.selStoreID);

		hmapQuestionflgPrvValue=helperDb.fnGetQuestionIdFlgPrvValue(AddNewStore_DynamicSectionWise.selStoreID);
		hmapPreviousVisitServerQuestionSavedAns=helperDb.getPDAUserPreviousQuestionAnswerMasterServer(AddNewStore_DynamicSectionWise.selStoreID);



		hmapGroupIdCopyAsAbove=helperDb.getGroupIdCopyAsAbove();
		grpQstIdForChannel=helperDb.getChannelGroupId();
		
		grpQstId_qstIdForName=helperDb.getNameQstGrpId_QstId();
		nameForStore=grpQstId_qstIdForName.split(Pattern.quote("^"))[1]+"^"+hmapQuesIdandGetAnsCntrlType.get(grpQstId_qstIdForName.split(Pattern.quote("^"))[1])+"^"+grpQstId_qstIdForName.split(Pattern.quote("^"))[0];
		hmapgetOptDepMstr=helperDb.getDepOptMstr();
		listimagePath=helperDb.getImagePath(AddNewStore_DynamicSectionWise.selStoreID);
		
		//currentStoreName=AddNewStore_DynamicSectionWise.

		createView();
		fillUnmapData();
		fillViewsValues();
		setSingleSpinnerVal();
		 
	}


	private void fillUnmapData()
	{

		if(AddNewStore_DynamicSectionWise.hmapQuesIdValues!=null && AddNewStore_DynamicSectionWise.hmapQuesIdValues.size()>0)
		{
			for(Entry<String, String> entry:AddNewStore_DynamicSectionWise.hmapQuesIdValues.entrySet())
			{

				if(!hmapQuestionSavedAns.containsKey(entry.getKey()))
				{
					if(hmapQuestionflgPrvValue!=null && hmapQuestionflgPrvValue.size()>0)
					{
						if(hmapQuestionflgPrvValue.containsKey(entry.getKey()))
						{
							if(hmapPreviousVisitServerQuestionSavedAns.containsKey(entry.getKey()))
							{
								if((Integer.parseInt(hmapQuestionflgPrvValue.get(entry.getKey()))==1)||(Integer.parseInt(hmapQuestionflgPrvValue.get(entry.getKey()))==2))
									hmapQuestionSavedAns.put(entry.getKey(), hmapPreviousVisitServerQuestionSavedAns.get(entry.getKey()));
							}
						}
					}

				}
			}
		}


	}
	public boolean nextOrBackSection(int isNextPressed,int sectionToShowOrHide)
	{
		boolean isNextMovedSlide=false;
		if(isNextPressed==0)
		{
			if(validate())
			{

					
					View view=ll_data.findViewWithTag(nameForStore);
					
						EditText edVal=(EditText)view;
						currentStoreName=edVal.getText().toString();
				

				isNextMovedSlide=true;
				saveDynamicQuesAns(true);
				//saveDataToDataBase();
				sectionIsShown=sectionToShowOrHide+1;
				LinearLayout ll_SectionToShow=(LinearLayout) ll_data.findViewWithTag(String.valueOf(sectionToShowOrHide+1)+"_Section");
				LinearLayout ll_SectionToHide=(LinearLayout) ll_data.findViewWithTag(String.valueOf(sectionToShowOrHide)+"_Section");
				ll_SectionToHide.setVisibility(View.GONE);
				ll_SectionToShow.setVisibility(View.VISIBLE);
				
			}
			else
			{
				isNextMovedSlide=false;
			}


		}
		else if(isNextPressed==1)
		{
			isNextMovedSlide=false;	
			sectionIsShown=sectionToShowOrHide;
			LinearLayout ll_SectionToShow=(LinearLayout) ll_data.findViewWithTag(String.valueOf(sectionToShowOrHide)+"_Section");
			LinearLayout ll_SectionToHide=(LinearLayout) ll_data.findViewWithTag(String.valueOf(sectionToShowOrHide+1)+"_Section");
			ll_SectionToHide.setVisibility(View.GONE);
			ll_SectionToShow.setVisibility(View.VISIBLE);
		}
		else if(isNextPressed==2)
		{
			isNextMovedSlide=false;	
			sectionIsShown=sectionToShowOrHide;
			if(sectionIsShown==1)
			{
				if(validateNameFilled())
				{
					
					alertSaveAndSaveExit("Save Data","Data will be saved",false);
				
				}
				
			}
			else{
				alertSaveAndSaveExit("Save Data","Data will be saved!",false);
			}
		}
		else if(isNextPressed==3)
		{
			isNextMovedSlide=false;	
			
				saveDynamicQuesAns(true);
				//saveDataToDataBase();
				helperDb.fnsaveOutletQuestAnsMstrSectionWise(hmapAnsValues,sectionIsShown,AddNewStore_DynamicSectionWise.selStoreID);
		}
		else if(isNextPressed==4)
		{
			isNextMovedSlide=false;	
			sectionIsShown=sectionToShowOrHide;
			
			
				alertSaveAndSaveExit("Exit","Are you sure you want to exit,as all your filled data will be lost?",true);
			
		}
		else if(isNextPressed==5)
		{
			isNextMovedSlide=false;	
			
				saveDynamicQuesAns(true);
				//saveDataToDataBase();
				//helperDb.fnsaveOutletQuestAnsMstrSectionWise(hmapAnsValues,sectionIsShown,AddNewStore_DynamicSectionWise.selStoreID,AddNewStore_DynamicSectionWise.hmapOptionId_OptionValue);
		}
		return isNextMovedSlide;
	}
	
	public void createView()
	{
		  
		
		  // getActivity() loop is for section i.e page
		  for(Entry<String,ArrayList<String>> entry:AddNewStore_DynamicSectionWise.hmapSctnId_GrpId.entrySet())
		  {
			  section=Integer.valueOf(entry.getKey());
			  createSection(entry);
		  }
		
	}
	public void removeErrorMsgwhenclickOnedittext() {
		/*percentageOfAdvanceBeforeDelivery.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				percentageTextviewNew.setError(null);
				percentageTextviewNew.clearFocus();
				
			}
		});*/
		/*percentageOfOnDelivery.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				percentageTextviewNew.setError(null);
				percentageTextviewNew.clearFocus();
				
			}
		});*/
		creditDaysOfOnDelivery.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			creditdaysTextboxNew.setError(null);
			creditdaysTextboxNew.clearFocus();
			
		}
	});
		/*PercentageOfCredit.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			percentageTextviewNew.setError(null);
			percentageTextviewNew.clearFocus();
			
		}
	});*/
		creditDaysOfCredit.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			creditdaysTextboxNew.setError(null);
			creditdaysTextboxNew.clearFocus();
			
		}
	});
		creditLimitOfCredit.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			CreditlimitTextboxNew.setError(null);
			CreditlimitTextboxNew.clearFocus();
			
		}
	});

	}

	

	private void createSection(Entry<String,ArrayList<String>> entry) {

		 
		 ll_Section=new LinearLayout(getActivity());
		  ll_Section.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		 
		  ll_Section.setOrientation(LinearLayout.VERTICAL);
		
		  ll_Section.setTag(entry.getKey()+"_Section");
		  
		
		  ArrayList<String> listGroupIdMpdWdSection=new ArrayList<String>();
		 listGroupIdMpdWdSection=entry.getValue();
		  // getActivity() loop is for section's groupHeading or groupDescription
		  for(String groupId:listGroupIdMpdWdSection)
		  {
			  createGroup(groupId);
		  }
		if(section==1)
		{
			View viewLoc=getActivity().getLayoutInflater().inflate(R.layout.location_details_layout, null);
			ll_Section.addView(viewLoc);

			ll_address_section=(LinearLayout) viewLoc.findViewById(R.id.ll_address_section);
			ll_address_section.setTag("CompleteAddress");
			ll_local_area= (LinearLayout) viewLoc.findViewById(R.id.ll_local_area);
			etLocalArea= (EditText) viewLoc.findViewById(R.id.etLocalArea);

			etPinCode= (EditText) viewLoc.findViewById(R.id.etPinCode);
			etCity= (TextView) viewLoc.findViewById(R.id.etCity);
			etState= (TextView) viewLoc.findViewById(R.id.etState);
			etState.setEnabled(false);
			etOtherCity= (EditText) viewLoc.findViewById(R.id.etOtherCity);
			txt_city= (TextView) viewLoc.findViewById(R.id.txt_city);
			txt_state= (TextView) viewLoc.findViewById(R.id.txt_state);
			txt_pincode= (TextView) viewLoc.findViewById(R.id.txt_pincode);
			SpannableStringBuilder text_Value=textWithMandatory(txt_city.getText().toString());
			txt_city.setText(text_Value);


			SpannableStringBuilder Strtxt_pincode=textWithMandatory(txt_pincode.getText().toString());
			txt_pincode.setText(Strtxt_pincode);
			SpannableStringBuilder Strtxt_state=textWithMandatory(txt_state.getText().toString());
			txt_state.setText(Strtxt_state);


			if(!AddNewStore_DynamicSectionWise.address.equals("NA"))
			{
				etLocalArea.setText(AddNewStore_DynamicSectionWise.address);
			}
			if(!AddNewStore_DynamicSectionWise.pincode.equals("NA"))
			{
				etPinCode.setText(AddNewStore_DynamicSectionWise.pincode);
			}
            if(!AddNewStore_DynamicSectionWise.state.equals("NA"))
            {
                etState.setText(AddNewStore_DynamicSectionWise.state);
            }
            boolean isCityFilled = false;
			if(!AddNewStore_DynamicSectionWise.city.equals("NA")) {


                for (Map.Entry<String, String> entryCity : hmapCity_details.entrySet()) {
                    if (entryCity.getKey().equalsIgnoreCase(AddNewStore_DynamicSectionWise.city.trim())) {
                        etCity.setText(AddNewStore_DynamicSectionWise.city);
                        isCityFilled = true;
                        defaultCity = entryCity.getKey();
                        break;
                    }
                }

                if(isCityFilled)
                {
                    if(!TextUtils.isEmpty(defaultCity))
                    {
                        etCity.setText(defaultCity);
                        if(defaultCity.equalsIgnoreCase("Others") || defaultCity.equalsIgnoreCase("Other"))
                        {
                            etState.setEnabled(true);
							etState.setText("Select");
                        }
                        else
                        {
                            if(hmapCityAgainstState!=null && hmapCityAgainstState.containsKey(defaultCity))
                            {
                                etState.setText(hmapCityAgainstState.get(defaultCity));
                            }
                            else
							{
								etState.setText("Select");
							}
                        }

                    }
                    else
					{
						etState.setText("Select");
					}
                }
                else
                {
                    etState.setText("Select");
                }
				//
			}

			else
			{
				if(!TextUtils.isEmpty(defaultCity))
				{
					etCity.setText(defaultCity);
					if(defaultCity.equalsIgnoreCase("Others") || defaultCity.equalsIgnoreCase("Other"))
					{
						etState.setEnabled(true);
					}
					else
					{
						if(hmapCityAgainstState!=null && hmapCityAgainstState.containsKey(defaultCity))
						{
							etState.setText(hmapCityAgainstState.get(defaultCity));
						}
					}

				}
			}

			etCity.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {

					if(cityNames!=null && cityNames.size()>0)
					{
						customAlertStateCityList(1,cityNames,"Select City",previousSlctdCity);
					}
					else
					{
						showErrorAlert("There is no city mapped to this phone");
					}

				}
			});
			etState.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					//img_State.setEnabled(false);
					if(stateNames!=null && stateNames.size()>0)
					{
						customAlertStateCityList(0,stateNames,"Select State",previousSlctdState);
					}
					else {
						showErrorAlert("There is no State mapped to this phone");
					}


				}
			});
			View view=ll_Section.findViewWithTag(nameForStore);
if(view!=null)
{
	EditText edVal=(EditText)view;

	edVal.setText(AddNewStore_DynamicSectionWise.storeNameToShow);
}

			ll_local_area.setVisibility(View.GONE);
			ll_address_section.setVisibility(View.GONE);
		}
		  //if(section==AddNewStore_DynamicSectionWise.hmapSctnId_GrpId.size())
		    /*{
		     View viewPaymentMode=getActivity().getLayoutInflater().inflate(R.layout.payment_mode_layout, null); 
		     ll_Section.addView(viewPaymentMode);
		     
		   //  parentOfCheckBox= (LinearLayout) viewPaymentMode.findViewById(R.id.ss);
			 parentOfCreditPayMentMode= (LinearLayout) viewPaymentMode.findViewById(R.id.parentOfCreditPayMentMode);
			 parentOfOnDeliveryPayMentMode= (LinearLayout) viewPaymentMode.findViewById(R.id.parentOfOnDeliveryPayMentMode);
			 parentOfAdvanceBeforeDeliveryPayMentMode= (LinearLayout) viewPaymentMode.findViewById(R.id.parentOfAdvanceBeforeDeliveryPayMentMode);
			 paymentModeTextviewNew=(TextView) viewPaymentMode.findViewById(R.id.paymentModeTextviewNew);
			 creditdaysTextboxNew=(TextView) viewPaymentMode.findViewById(R.id.creditdaysTextboxNew);
			 CreditlimitTextboxNew=(TextView) viewPaymentMode.findViewById(R.id.CreditlimitTextboxNew);
			 //PaymentModeTextView=(TextView) viewPaymentMode.findViewById(R.id.PaymentModeTextView);
			 
			 percentageTextviewNew=(TextView) viewPaymentMode.findViewById(R.id.percentageTextviewNew);
			// PaymentStageTextView=(TextView) viewPaymentMode.findViewById(R.id.PaymentStageTextView);
			 paymentstagetextviewNew=(TextView) viewPaymentMode.findViewById(R.id.paymentstagetextviewNew);
			 
			 
			 AdvanceBeforeDeliveryCheckBoxNew =(CheckBox) viewPaymentMode.findViewById(R.id.AdvanceBeforeDeliveryCheckBoxNew );
			 OnDeliveryCheckBoxNew=(CheckBox) viewPaymentMode.findViewById(R.id.OnDeliveryCheckBoxNew);
			 CreditCheckBoxNew=(CheckBox) viewPaymentMode.findViewById(R.id.CreditCheckBoxNew);
			 percentageOfAdvanceBeforeDelivery=(EditText) viewPaymentMode.findViewById(R.id.percentageOfAdvanceBeforeDelivery);
			 percentageOfOnDelivery=(EditText) viewPaymentMode.findViewById(R.id.percentageOfOnDelivery);
			 creditDaysOfOnDelivery=(EditText) viewPaymentMode.findViewById(R.id.creditDaysOfOnDelivery);
			 PercentageOfCredit=(EditText) viewPaymentMode.findViewById(R.id.PercentageOfCredit);
			 creditDaysOfCredit=(EditText) viewPaymentMode.findViewById(R.id.creditDaysOfCredit);
			 creditLimitOfCredit=(EditText) viewPaymentMode.findViewById(R.id.creditLimitOfCredit);
			 checkBoxCreationwhenPageLoading("1");
			 checkBoxCreationwhenPageLoading("2");
			 checkBoxCreationwhenPageLoading("3");
			 whenPaymentStageClickNew();
			 disableAndUncheckPaymntMdOfAdvanceBeforeDelivery();
			 disableAndUncheckPaymntMdOfOnDelivery();
			disableAndUncheckPaymntMdOfCredit();
			removeErrorMsgwhenclickOnedittext();
			
			 MainlayoutParentOfWholePage.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						*//*PaymentModeTextView.setError(null);
						PaymentModeTextView.clearFocus();*//*
						
						paymentstagetextviewNew.setError(null);
						paymentstagetextviewNew.clearFocus();
						
						
						//percentageTextviewNew.setError(null);
						//percentageTextviewNew.clearFocus();
						
						paymentModeTextviewNew.setError(null);
						paymentModeTextviewNew.clearFocus();
						
						
						creditdaysTextboxNew.setError(null);
						creditdaysTextboxNew.clearFocus();
						CreditlimitTextboxNew.setError(null);
						CreditlimitTextboxNew.clearFocus();
						
						
						
							//PaymentModeTextView.setError(null);
						               // PaymentModeTextView.clearFocus();
					}
				});
			 
			 if(helperDb.checkCountIntblNewStoreSalesQuotePaymentDetails(AddNewStore_DynamicSectionWise.selStoreID)==1){
				 String allValuesOfPaymentStageID="0";	
				 fillValuesInPaymentSection(allValuesOfPaymentStageID);
			 }
			
			 
		    }*/
	System.out.println("Section Added = "+section);
		  ll_data.addView(ll_Section);
			if(section>1)
			{
				ll_Section.setVisibility(View.GONE);	
			}
	}
	public void fillValuesInPaymentSection(String allValuesOfPaymentStageID) {
	//String all	helperDb.fngettblNewStoreSalesQuotePaymentDetails(AddNewStore_DynamicSectionWise.selStoreID)
		allValuesOfPaymentStageID=helperDb.fngettblNewStoreSalesQuotePaymentDetails(AddNewStore_DynamicSectionWise.selStoreID);
		
		String pymntStagIDofAdvn="0";
		if(allValuesOfPaymentStageID.split(Pattern.quote("~"))[0].equals("1")){
			percentageOfAdvanceBeforeDelivery.setText("100");
			enablePaymntMdOfAdvanceBeforeDelivery();
			
		pymntStagIDofAdvn=	allValuesOfPaymentStageID.split(Pattern.quote("~"))[0].toString().trim();
		
	String percentageOfAdvn=		allValuesOfPaymentStageID.split(Pattern.quote("~"))[1].toString().trim();
		String paymentModeIdOfADVNC=	allValuesOfPaymentStageID.split(Pattern.quote("~"))[4].toString().trim();
		
		
		enablePaymntMdOfAdvanceBeforeDelivery();
		AdvanceBeforeDeliveryCheckBoxNew .setChecked(true);
		 if(!percentageOfAdvn.equals("0")){
			 percentageOfAdvanceBeforeDelivery.setText(percentageOfAdvn);
			 
			  }
		 percentageOfAdvanceBeforeDelivery.setEnabled(true);
		
		if(paymentModeIdOfADVNC.contains("|")){

			String[] option=	paymentModeIdOfADVNC.split(Pattern.quote("|"));
			for(int i=0;i<option.length;i++){
				String opt=option[i];
				if(hmapAllValuesOfPaymentMode.containsKey(opt)){
				String valueOfChebox=	hmapAllValuesOfPaymentMode.get(opt);
				int count = parentOfAdvanceBeforeDeliveryPayMentMode.getChildCount();
				
				 for (int ui=0;ui<count;ui++) 
				 {
					 
					 View ch = parentOfAdvanceBeforeDeliveryPayMentMode.getChildAt(ui);
			            if (ch instanceof CheckBox)
			            {
			            	
			            		String chkedChkBoxTag=ch.getTag().toString();
			        			int chkedchkQuestId=Integer.parseInt(chkedChkBoxTag.split(Pattern.quote("^"))[0]);
			       			String chkedchkOptionId=chkedChkBoxTag.split(Pattern.quote("^"))[1];
			       			if(valueOfChebox.equals(chkedchkOptionId)){
			       				((CheckBox) ch).setChecked(true);
			       				
			       			}

			            }
				 }
				}
				
			}
			
		}
		else{
			if(hmapAllValuesOfPaymentMode.containsKey(paymentModeIdOfADVNC)){
				String valueOfChebox=	hmapAllValuesOfPaymentMode.get(paymentModeIdOfADVNC);
				int count = parentOfAdvanceBeforeDeliveryPayMentMode.getChildCount();
				
				 for (int ui=0;ui<count;ui++) 
				 {
					 
					 View ch = parentOfAdvanceBeforeDeliveryPayMentMode.getChildAt(ui);
			            if (ch instanceof CheckBox)
			            {
			            	
			            		String chkedChkBoxTag=ch.getTag().toString();
			        			int chkedchkQuestId=Integer.parseInt(chkedChkBoxTag.split(Pattern.quote("^"))[0]);
			       			String chkedchkOptionId=chkedChkBoxTag.split(Pattern.quote("^"))[1];
			       			if(valueOfChebox.equals(chkedchkOptionId)){
			       				((CheckBox) ch).setChecked(true);
			       				
			       			}
			            		




			            }
				 }
				}
				
				
			
		}
		
			
			
			
		}
		String pymntStagIDofDelivery="0";
       if(allValuesOfPaymentStageID.split(Pattern.quote("~"))[0].equals("2")){
    	   
    	   PercentageOfCredit.setText("100");
    	   
     pymntStagIDofDelivery=	   allValuesOfPaymentStageID.split(Pattern.quote("~"))[0].toString().trim();
    		String percentageofDelivery=allValuesOfPaymentStageID.split(Pattern.quote("~"))[1].toString().trim();
    String creditDysofDelivery=	   allValuesOfPaymentStageID.split(Pattern.quote("~"))[2].toString().trim();
    String pymntmodeIDofDElivery=	   allValuesOfPaymentStageID.split(Pattern.quote("~"))[4].toString().trim();
    enablePaymntMdOfOnDelivery();
    if(!percentageofDelivery.equals("0")){
    	percentageOfOnDelivery.setText(percentageofDelivery);
    	
	  }
    percentageOfOnDelivery.setEnabled(true);
	  if(!creditDysofDelivery.equals("0")){
		  creditDaysOfOnDelivery.setText(creditDysofDelivery);
		  creditDaysOfOnDelivery.setEnabled(true);
	  }
	  OnDeliveryCheckBoxNew .setChecked(true);
    if(pymntmodeIDofDElivery.contains("|")){


		String[] option=	pymntmodeIDofDElivery.split(Pattern.quote("|"));
		for(int i=0;i<option.length;i++){
			String opt=option[i];
			if(hmapAllValuesOfPaymentMode.containsKey(opt)){
			String valueOfChebox=	hmapAllValuesOfPaymentMode.get(opt);
			int count = parentOfOnDeliveryPayMentMode.getChildCount();
			
			 for (int ui=0;ui<count;ui++) 
			 {
				 
				 View ch = parentOfOnDeliveryPayMentMode.getChildAt(ui);
		            if (ch instanceof CheckBox)
		            {
		            	
		            		String chkedChkBoxTag=ch.getTag().toString();
		        			int chkedchkQuestId=Integer.parseInt(chkedChkBoxTag.split(Pattern.quote("^"))[0]);
		       			String chkedchkOptionId=chkedChkBoxTag.split(Pattern.quote("^"))[1];
		       			if(valueOfChebox.equals(chkedchkOptionId)){
		       				((CheckBox) ch).setChecked(true);
		       				
		       			}
		            		
		            		
		            		
						
		            	
		            }
			 }
			}
			
		}
		
	
    	
    }
    else{

		if(hmapAllValuesOfPaymentMode.containsKey(pymntmodeIDofDElivery)){
			String valueOfChebox=	hmapAllValuesOfPaymentMode.get(pymntmodeIDofDElivery);
			int count = parentOfOnDeliveryPayMentMode.getChildCount();
			
			 for (int ui=0;ui<count;ui++) 
			 {
				 
				 View ch = parentOfOnDeliveryPayMentMode.getChildAt(ui);
		            if (ch instanceof CheckBox)
		            {
		            	
		            		String chkedChkBoxTag=ch.getTag().toString();
		        			int chkedchkQuestId=Integer.parseInt(chkedChkBoxTag.split(Pattern.quote("^"))[0]);
		       			String chkedchkOptionId=chkedChkBoxTag.split(Pattern.quote("^"))[1];
		       			if(valueOfChebox.equals(chkedchkOptionId)){
		       				((CheckBox) ch).setChecked(true);
		       				
		       			}
		            		
		            		
		            		
						
		            	
		            }
			 }
			}
			
			
		
	
    	
    }
			
		}
       String pymntStagIDofcredit="0";
      if(allValuesOfPaymentStageID.split(Pattern.quote("~"))[0].equals("3")){
    	  
    	  percentageOfOnDelivery.setText("100");
    	  pymntStagIDofcredit=	  allValuesOfPaymentStageID.split(Pattern.quote("~"))[0].toString().trim();
    	  String percentageofcredit=	  allValuesOfPaymentStageID.split(Pattern.quote("~"))[1].toString().trim();
    	  String creditDysofcredit = allValuesOfPaymentStageID.split(Pattern.quote("~"))[2].toString().trim();
    	  String creditLimitofcredit=	  allValuesOfPaymentStageID.split(Pattern.quote("~"))[3].toString().trim();
    	  String pymntmodeIDofcredit = allValuesOfPaymentStageID.split(Pattern.quote("~"))[4].toString().trim();
    	  if(!percentageofcredit.equals("0")){
    		  PercentageOfCredit.setText(percentageofcredit);
    		  
    	  }
    	  PercentageOfCredit.setEnabled(true);
    	  if(!creditDysofcredit.equals("0")){
    		  creditDaysOfCredit.setText(creditDysofcredit);
    		  creditDaysOfCredit.setEnabled(true);
    	  }
    	  
    	  if(!creditLimitofcredit.equals("0")){
    		  creditLimitOfCredit.setText(creditLimitofcredit);
    		  creditLimitOfCredit.setEnabled(true);
    		  
    	  }
    	  CreditCheckBoxNew.setChecked(true);
    	 
    	  enablePaymntMdOfCredit();
    	  if(pymntmodeIDofcredit.contains("|")){


    			String[] option=	pymntmodeIDofcredit.split(Pattern.quote("|"));
    			for(int i=0;i<option.length;i++){
    				String opt=option[i];
    				if(hmapAllValuesOfPaymentMode.containsKey(opt)){
    				String valueOfChebox=	hmapAllValuesOfPaymentMode.get(opt);
    				int count = parentOfCreditPayMentMode.getChildCount();
    				
    				 for (int ui=0;ui<count;ui++) 
    				 {
    					 
    					 View ch = parentOfCreditPayMentMode.getChildAt(ui);
    			            if (ch instanceof CheckBox)
    			            {
    			            	
    			            		String chkedChkBoxTag=ch.getTag().toString();
    			        			int chkedchkQuestId=Integer.parseInt(chkedChkBoxTag.split(Pattern.quote("^"))[0]);
    			       			String chkedchkOptionId=chkedChkBoxTag.split(Pattern.quote("^"))[1];
    			       			if(valueOfChebox.equals(chkedchkOptionId)){
    			       				((CheckBox) ch).setChecked(true);
    			       				
    			       			}
    			            		
    			            		
    			            		
    							
    			            	
    			            }
    				 }
    				}
    				
    			}
    			
    		
    	    	
    	    }
    	    else{

    			if(hmapAllValuesOfPaymentMode.containsKey(pymntmodeIDofcredit)){
    				String valueOfChebox=	hmapAllValuesOfPaymentMode.get(pymntmodeIDofcredit);
    				int count = parentOfCreditPayMentMode.getChildCount();
    				
    				 for (int ui=0;ui<count;ui++) 
    				 {
    					 
    					 View ch = parentOfCreditPayMentMode.getChildAt(ui);
    			            if (ch instanceof CheckBox)
    			            {
    			            	
    			            		String chkedChkBoxTag=ch.getTag().toString();
    			        			int chkedchkQuestId=Integer.parseInt(chkedChkBoxTag.split(Pattern.quote("^"))[0]);
    			       			String chkedchkOptionId=chkedChkBoxTag.split(Pattern.quote("^"))[1];
    			       			if(valueOfChebox.equals(chkedchkOptionId)){
    			       				((CheckBox) ch).setChecked(true);
    			       				
    			       			}
    			            		
    			            		
    			            		
    							
    			            	
    			            }
    				 }
    				}
    				
    				
    			
    		
    	    	
    	    }
	
        }
				
				
       globalValueOfPaymentStageCheck=pymntStagIDofAdvn+"_"+pymntStagIDofDelivery+"_"+pymntStagIDofcredit;
       
		 

	}
	
	public void disableAndUncheckPaymntMdOfOnDelivery() {
		int count = parentOfOnDeliveryPayMentMode.getChildCount();
		
		 for (int ui=0;ui<count;ui++) 
		 {
			 View ch = parentOfOnDeliveryPayMentMode.getChildAt(ui);
	           if (ch instanceof CheckBox)
	           {
	           	((CheckBox) ch).setChecked(false);
	           	ch.setEnabled(false);
	           	
	           	
	           }
		 }
		

	}
	public void disableAndUncheckPaymntMdOfCredit() {
		int count = parentOfCreditPayMentMode.getChildCount();
		
		 for (int ui=0;ui<count;ui++) 
		 {
			 View ch = parentOfCreditPayMentMode.getChildAt(ui);
	           if (ch instanceof CheckBox)
	           {
	           	((CheckBox) ch).setChecked(false);
	           	ch.setEnabled(false);
	           	
	           	
	           }
		 }
		

	}
	public void disableAndUncheckPaymntMdOfAdvanceBeforeDelivery() {
		int count = parentOfAdvanceBeforeDeliveryPayMentMode.getChildCount();
		
		 for (int ui=0;ui<count;ui++) 
		 {
			 View ch = parentOfAdvanceBeforeDeliveryPayMentMode.getChildAt(ui);
	           if (ch instanceof CheckBox)
	           {
	           	((CheckBox) ch).setChecked(false);
	           	ch.setEnabled(false);
	           	
	           	
	           }
		 }
		

	}
	public void enablePaymntMdOfAdvanceBeforeDelivery() {
		int count = parentOfAdvanceBeforeDeliveryPayMentMode.getChildCount();
		
		 for (int ui=0;ui<count;ui++) 
		 {
			 View ch = parentOfAdvanceBeforeDeliveryPayMentMode.getChildAt(ui);
	           if (ch instanceof CheckBox)
	           {
	           	//((CheckBox) ch).setChecked(false);
	           	ch.setEnabled(true);
	           	
	           	
	           }
		 }
		

	}
	public void enablePaymntMdOfOnDelivery() {
		int count = parentOfOnDeliveryPayMentMode.getChildCount();
		
		 for (int ui=0;ui<count;ui++) 
		 {
			 View ch = parentOfOnDeliveryPayMentMode.getChildAt(ui);
	           if (ch instanceof CheckBox)
	           {
	           	//((CheckBox) ch).setChecked(false);
	           	ch.setEnabled(true);
	           	
	           	
	           }
		 }
		

	}
	public void enablePaymntMdOfCredit() {
		int count = parentOfCreditPayMentMode.getChildCount();
		
		 for (int ui=0;ui<count;ui++) 
		 {
			 View ch = parentOfCreditPayMentMode.getChildAt(ui);
	           if (ch instanceof CheckBox)
	           {
	           	//((CheckBox) ch).setChecked(false);
	           	ch.setEnabled(true);
	           	
	           	
	           }
		 }
		

	}
	public void whenPaymentStageClickNew() {
		AdvanceBeforeDeliveryCheckBoxNew .setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				 paymentstagetextviewNew.setError(null);
					paymentstagetextviewNew.clearFocus();
					AdvanceBeforeDeliveryCheckBoxNew.setChecked(true);
				if(AdvanceBeforeDeliveryCheckBoxNew.isChecked()){
					
					//setting percentage 100 because we hide the percent edittext but it also now in validation
					percentageOfAdvanceBeforeDelivery.setText("100");
					
					//uncheck ,disable and delete data of other checkboxes when click on this checkbox
					

					OnDeliveryCheckBoxNew.setChecked(false);
					percentageOfOnDelivery.setText("");
					creditDaysOfOnDelivery.setText("");
					percentageOfOnDelivery.setEnabled(false);
					creditDaysOfOnDelivery.setEnabled(false);
					percentageOfOnDelivery.clearFocus();
					creditDaysOfOnDelivery.clearFocus();
					disableAndUncheckPaymntMdOfOnDelivery();
					String paymntStgAdvnc2=globalValueOfPaymentStageCheck.split(Pattern.quote("_"))[0];
					String paymntStgDelvry2=globalValueOfPaymentStageCheck.split(Pattern.quote("_"))[1];
					paymntStgDelvry2="0";
					String paymntStgCrdt2=globalValueOfPaymentStageCheck.split(Pattern.quote("_"))[2];
					globalValueOfPaymentStageCheck=paymntStgAdvnc2+"_"+paymntStgDelvry2+"_"+paymntStgCrdt2;
					

					CreditCheckBoxNew.setChecked(false);
					PercentageOfCredit.setText("");
					creditDaysOfCredit.setText("");
					creditLimitOfCredit.setText("");
					PercentageOfCredit.setEnabled(false);
					creditDaysOfCredit.setEnabled(false);
					creditLimitOfCredit.setEnabled(false);
					PercentageOfCredit.clearFocus();
					creditDaysOfCredit.clearFocus();
					creditLimitOfCredit.clearFocus();
					disableAndUncheckPaymntMdOfCredit();
					String paymntStgAdvnc3=globalValueOfPaymentStageCheck.split(Pattern.quote("_"))[0];
					String paymntStgDelvry3=globalValueOfPaymentStageCheck.split(Pattern.quote("_"))[1];
					String paymntStgCrdt3=globalValueOfPaymentStageCheck.split(Pattern.quote("_"))[2];
					paymntStgCrdt3="0";
					globalValueOfPaymentStageCheck=paymntStgAdvnc3+"_"+paymntStgDelvry3+"_"+paymntStgCrdt3;
				
				
					
					
				
					
					
					//And now update the enable the checkbox ,which is clicked
					AdvanceBeforeDeliveryCheckBoxNew.setChecked(true);
					percentageOfAdvanceBeforeDelivery.setEnabled(true);
					enablePaymntMdOfAdvanceBeforeDelivery();
					
					String paymntStgAdvnc=globalValueOfPaymentStageCheck.split(Pattern.quote("_"))[0];
					paymntStgAdvnc="1";
					String paymntStgDelvry=globalValueOfPaymentStageCheck.split(Pattern.quote("_"))[1];
					String paymntStgCrdt=globalValueOfPaymentStageCheck.split(Pattern.quote("_"))[2];
					globalValueOfPaymentStageCheck=paymntStgAdvnc+"_"+paymntStgDelvry+"_"+paymntStgCrdt;
					
					
					
				}
				
				
			}
		});
		OnDeliveryCheckBoxNew .setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				 paymentstagetextviewNew.setError(null);
					paymentstagetextviewNew.clearFocus();
					OnDeliveryCheckBoxNew.setChecked(true);
				if(OnDeliveryCheckBoxNew.isChecked()){
					//setting percentage 100 because we hide the percent edittext but it also now in validation
					percentageOfOnDelivery.setText("100");
					
					
		//@@@@@@@@@@@  uncheck ,disable and delete data of other checkboxes when click on this checkbox
					
					AdvanceBeforeDeliveryCheckBoxNew.setChecked(false);
					percentageOfAdvanceBeforeDelivery.setText("");
					percentageOfAdvanceBeforeDelivery.setEnabled(false);
					percentageOfAdvanceBeforeDelivery.clearFocus();
					disableAndUncheckPaymntMdOfAdvanceBeforeDelivery();
					
					String paymntStgAdvnc2=globalValueOfPaymentStageCheck.split(Pattern.quote("_"))[0];
					paymntStgAdvnc2="0";
					String paymntStgDelvry2=globalValueOfPaymentStageCheck.split(Pattern.quote("_"))[1];
					String paymntStgCrdt2=globalValueOfPaymentStageCheck.split(Pattern.quote("_"))[2];
					globalValueOfPaymentStageCheck=paymntStgAdvnc2+"_"+paymntStgDelvry2+"_"+paymntStgCrdt2;
					

					CreditCheckBoxNew.setChecked(false);
					PercentageOfCredit.setText("");
					creditDaysOfCredit.setText("");
					creditLimitOfCredit.setText("");
					PercentageOfCredit.setEnabled(false);
					creditDaysOfCredit.setEnabled(false);
					creditLimitOfCredit.setEnabled(false);
					PercentageOfCredit.clearFocus();
					creditDaysOfCredit.clearFocus();
					creditLimitOfCredit.clearFocus();
					disableAndUncheckPaymntMdOfCredit();
					String paymntStgAdvnc3=globalValueOfPaymentStageCheck.split(Pattern.quote("_"))[0];
					String paymntStgDelvry3=globalValueOfPaymentStageCheck.split(Pattern.quote("_"))[1];
					String paymntStgCrdt3=globalValueOfPaymentStageCheck.split(Pattern.quote("_"))[2];
					paymntStgCrdt3="0";
					globalValueOfPaymentStageCheck=paymntStgAdvnc3+"_"+paymntStgDelvry3+"_"+paymntStgCrdt3;
				
				
					
					
					
					
					//And now update the enable the checkbox ,which is clicked
					
					
					OnDeliveryCheckBoxNew.setChecked(true);
					percentageOfOnDelivery.setEnabled(true);
					//creditDaysOfOnDelivery.setEnabled(true);
					enablePaymntMdOfOnDelivery();
					String paymntStgAdvnc=globalValueOfPaymentStageCheck.split(Pattern.quote("_"))[0];
					String paymntStgDelvry=globalValueOfPaymentStageCheck.split(Pattern.quote("_"))[1];
					paymntStgDelvry="2";
					String paymntStgCrdt=globalValueOfPaymentStageCheck.split(Pattern.quote("_"))[2];
					globalValueOfPaymentStageCheck=paymntStgAdvnc+"_"+paymntStgDelvry+"_"+paymntStgCrdt;
					
				}
				
				
			}
		});
		CreditCheckBoxNew .setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			 paymentstagetextviewNew.setError(null);
				paymentstagetextviewNew.clearFocus();
				CreditCheckBoxNew.setChecked(true);
			if(CreditCheckBoxNew.isChecked()){
				
				//setting percentage 100 because we hide the percent edittext but it also now in validation
			PercentageOfCredit.setText("100");
				
				
				//uncheck ,disable and delete data of other checkboxes when click on this checkbox
				OnDeliveryCheckBoxNew.setChecked(false);
				percentageOfOnDelivery.setText("");
				creditDaysOfOnDelivery.setText("");
				percentageOfOnDelivery.setEnabled(false);
				creditDaysOfOnDelivery.setEnabled(false);
				percentageOfOnDelivery.clearFocus();
				creditDaysOfOnDelivery.clearFocus();
				disableAndUncheckPaymntMdOfOnDelivery();
				String paymntStgAdvnc2=globalValueOfPaymentStageCheck.split(Pattern.quote("_"))[0];
				String paymntStgDelvry2=globalValueOfPaymentStageCheck.split(Pattern.quote("_"))[1];
				paymntStgDelvry2="0";
				String paymntStgCrdt2=globalValueOfPaymentStageCheck.split(Pattern.quote("_"))[2];
				globalValueOfPaymentStageCheck=paymntStgAdvnc2+"_"+paymntStgDelvry2+"_"+paymntStgCrdt2;
				
				

				
				
				 
				
				AdvanceBeforeDeliveryCheckBoxNew.setChecked(false);
				percentageOfAdvanceBeforeDelivery.setText("");
				percentageOfAdvanceBeforeDelivery.setEnabled(false);
				percentageOfAdvanceBeforeDelivery.clearFocus();
				disableAndUncheckPaymntMdOfAdvanceBeforeDelivery();
				
				String paymntStgAdvnc3=globalValueOfPaymentStageCheck.split(Pattern.quote("_"))[0];
				paymntStgAdvnc3="0";
				String paymntStgDelvry3=globalValueOfPaymentStageCheck.split(Pattern.quote("_"))[1];
				String paymntStgCrdt3=globalValueOfPaymentStageCheck.split(Pattern.quote("_"))[2];
				globalValueOfPaymentStageCheck=paymntStgAdvnc3+"_"+paymntStgDelvry3+"_"+paymntStgCrdt3;
				
				
			
				//And now update the enable the checkbox ,which is clicked
				
				CreditCheckBoxNew.setChecked(true);
				PercentageOfCredit.setEnabled(true);
				creditDaysOfCredit.setEnabled(true);
				creditLimitOfCredit.setEnabled(true);
				enablePaymntMdOfCredit();
				String paymntStgAdvnc=globalValueOfPaymentStageCheck.split(Pattern.quote("_"))[0];
				String paymntStgDelvry=globalValueOfPaymentStageCheck.split(Pattern.quote("_"))[1];
				String paymntStgCrdt=globalValueOfPaymentStageCheck.split(Pattern.quote("_"))[2];
				paymntStgCrdt="3";
				globalValueOfPaymentStageCheck=paymntStgAdvnc+"_"+paymntStgDelvry+"_"+paymntStgCrdt;
				
				
			}
			
		}
	});

	}
	public void checkBoxCreationwhenPageLoading(String paymentStageID) {
		hmapZoneDisplayMstr=helperDb.fnGettblSalesQuotePaymentModeMstr(paymentStageID);
		
		for(Entry<String, String> entry:hmapZoneDisplayMstr.entrySet())
		{
        	 int checkBoxDescId=Integer.parseInt(entry.getKey().toString());
			 String checkBoxDesc=entry.getValue().toString().trim();
        LinearLayout.LayoutParams layoutParamss = new LinearLayout.LayoutParams(
			      LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

			 layoutParamss.setMargins(3, 3, 0, 3);
			 chBoxView = new CheckBox(getActivity());
			 chBoxView.setButtonDrawable(getResources().getDrawable(R.drawable.checkbox_button_image));
			 chBoxView.setText("   "+checkBoxDesc);
			 chBoxView.setTextColor(getResources().getColor(R.color.black));
			 chBoxView.setTextSize(10);
			 chBoxView.setId(checkBoxDescId);
			 chBoxView.setTag(checkBoxDescId+"^"+checkBoxDesc);
	    	 chBoxView.setOnClickListener(getOnClickDoSomething(chBoxView));
	    	 
			 if(paymentStageID.equals("1")){
				 
				 parentOfAdvanceBeforeDeliveryPayMentMode.addView(chBoxView,layoutParamss);
			 }
			 if(paymentStageID.equals("2")){
				 parentOfOnDeliveryPayMentMode.addView(chBoxView,layoutParamss);
			 }
			 if(paymentStageID.equals("3")){
				 parentOfCreditPayMentMode.addView(chBoxView,layoutParamss);
			 }
			 
	    	 //  parentOfCheckBox.addView(chBoxView,layoutParamss);
	    	  
    }}
	
	OnClickListener getOnClickDoSomething(final CheckBox button) {
    	return new OnClickListener() {
    		public void onClick(View v) {
    			String idOfAllCheckedBoxes="";
    			paymentModeTextviewNew.setError(null);
    			paymentModeTextviewNew.clearFocus();
    			
    			/*PaymentModeTextView.setError(null);
    			PaymentModeTextView.clearFocus();*/
    			//int count = parentOfCheckBox.getChildCount();
    			String curentSelectedZones="";
    			String chkedChkBoxTag=button.getTag().toString();
    			int chkedchkQuestId=Integer.parseInt(chkedChkBoxTag.split(Pattern.quote("^"))[0]);
   			String chkedchkOptionId=chkedChkBoxTag.split(Pattern.quote("^"))[1];
   			
   			if(OnDeliveryCheckBoxNew.isChecked()){
   				
   				if(chkedchkOptionId.trim().equals("Post Dated Cheque")){
   					if(button.isChecked()){
   						creditDaysOfOnDelivery.setEnabled(true);
   					}
   					else{
   						creditDaysOfOnDelivery.setEnabled(false);
   						creditDaysOfOnDelivery.clearFocus();
   						creditDaysOfOnDelivery.setText("");
   					}
   					
   				}
   				
   			}
   			
   		
   			
    			
    		} };

   
}
	private void createGroup(String groupId) {
		

		   String groupDesc=AddNewStore_DynamicSectionWise.hmapGroupId_Desc.get(groupId);
		 
		   final LinearLayout layGroup = new LinearLayout(getActivity());
			
		   //layGroup.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			/*lay.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
					LayoutParams.WRAP_CONTENT),);*/
		   LinearLayout.LayoutParams layoutParamsGroup = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1f);
		     layoutParamsGroup.setMargins(0, 5, 0, 3);
		     layGroup.setLayoutParams(layoutParamsGroup);
		   LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1f);
			layGroup.setOrientation(LinearLayout.HORIZONTAL);
		//	layoutParams2.setMargins(10, 0, 5, 0);
			layGroup.setPadding(16, 0, 16, 0);
			layGroup.setTag(groupId+"_group");
		   //00BCD4

			TextView txtVw_ques=new TextView(getActivity());
			LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f);
			layoutParams1.setMargins(0, 10, 0, 0);
			txtVw_ques.setGravity(Gravity.CENTER_VERTICAL);
			txtVw_ques.setLayoutParams(layoutParams1);
			txtVw_ques.setTextColor(getResources().getColor(R.color.black));

		//nitika
			layGroup.setBackgroundColor(Color.parseColor("#ED3338"));

			//setTextColor(getResources().getColor(R.color.blue));
			layGroup.setGravity(Gravity.CENTER_HORIZONTAL);
			txtVw_ques.setTextSize(16);
		//	txtVw_ques.setTag(groupId+"_group");
			txtVw_ques.setPadding(16, 3, 16, 3);
			txtVw_ques.setText(groupDesc);
			
			//	headerView.setTag(tag);
				layGroup.addView(txtVw_ques);
			if(hmapGroupIdCopyAsAbove.containsKey(groupId))
			{
			
				CheckBox chBox=new CheckBox(getActivity());
				chBox.setLayoutParams(layoutParams2);
				chBox.setTextSize(14);
				chBox.setText("(Select as above)");
				layGroup.addView(chBox);
				chBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						// TODO Auto-generated method stub
						setCopyOfAboveGroup(layGroup.getTag().toString(),isChecked);
					}
				});
			}
			ll_Section.addView(layGroup);

			 ArrayList<String> listQuesGroup=AddNewStore_DynamicSectionWise.hmapQuesGropKeySection.get(groupId);
			 // getActivity() loop is for section's group's question show
			 boolean isAllQuestHide=false;
			 for(String questionKeyToShow:listQuesGroup)
			 {

				 questGroupId=questionKeyToShow.split(Pattern.quote("^"))[2];
				 System.out.println("Group Added = "+groupDesc+" Question = "+AddNewStore_DynamicSectionWise.hmapQuesIdValues.get(questionKeyToShow));

                 if(dependentValuesQuesId!=null && dependentValuesQuesId.length>0) {
					 if (Arrays.asList(dependentValuesQuesId).contains(questGroupId)) {
						 isAllQuestHide = true;
					 } else {
						 isAllQuestHide = false;
						 break;
					 }
				 }
			 }
			 if(isAllQuestHide)
			 {
				 layGroup.setVisibility(View.GONE);	
			 }

				createQuesDynamic(listQuesGroup);
			
	  
		
	}

	private void createQuesDynamic(ArrayList<String> listQuestToBeDisplay) {
		
		
		ArrayList<String> quesWithBndlFlag3=new ArrayList<String>();
		
		int count=0;
		for(String questionKeyToShow:listQuestToBeDisplay)
		{
			
			String quesWithAllValues=AddNewStore_DynamicSectionWise.hmapQuesIdValues.get(questionKeyToShow);
			String quesId=questionKeyToShow.split(Pattern.quote("^"))[0];
			
			String[] quesVal=quesWithAllValues.split(Pattern.quote("^")) ;
			
			questGroupId=questionKeyToShow.split(Pattern.quote("^"))[2];
			QuestDesc=quesVal[2];
			System.out.println("Question Added = "+QuestDesc);
			AnsControlType=quesVal[4];
			AsnControlInputTypeID=quesVal[5];
			AnsControlInputTypeMaxLength=quesVal[6];
			AnsMustRequiredFlg=quesVal[7];//1=Not Must Required,2=Must Required
			
			QuestBundleFlg=quesVal[8];
			//QuestBundleGroupId=quesVal[13];
		//	flgPrvsVisitQstIdWd1=quesVal[14];
			Sequence=String.valueOf(count);
			if(!quesVal[12].equals("N/A"))
			{
				answerHint=quesVal[12];
			}
			else
			{
				answerHint="";
			}
			if(!AnsControlType.equals("14"))
			{
				if(AnsMustRequiredFlg.equals("2"))
				{
					tv_Ques=getTextView(QuestDesc, Sequence, true, quesId+"^"+AnsControlType+"^"+questGroupId+"?");
					hmapMustRqrdFiled.put(questionKeyToShow, true);


				}
				else
				{
					if(AnsControlType.equals("2") && AsnControlInputTypeID.equals("5") )
					{
						hmapOptionalFiled.put(questionKeyToShow, "Email");
					}
					else
					{
						hmapOptionalFiled.put(questionKeyToShow, "Check Min Length");
					}
					if(AnsControlType.equals("12"))
					{
						if(QuestDesc.contains("$"))
						{

							tv_Ques=getTextView(QuestDesc.split(Pattern.quote("$"))[1].toString(), Sequence, false, quesId+"^"+AnsControlType+"^"+questGroupId+"?");
							textStCode=getTextView(QuestDesc.split(Pattern.quote("$"))[0].toString(), Sequence, false, quesId+"^"+AnsControlType+"^"+questGroupId+"?");
						}
						else
						{
							tv_Ques=getTextView(QuestDesc, Sequence, false, quesId+"^"+AnsControlType+"^"+questGroupId+"?");
						}
					}
					else
					{
						tv_Ques=getTextView(QuestDesc, Sequence, false, quesId+"^"+AnsControlType+"^"+questGroupId+"?");
					}


				}

			}
			else {
				if(AnsMustRequiredFlg.equals("2"))
				{
					tv_Ques=getTextViewSpecial(QuestDesc.split(Pattern.quote("$"))[0].toString(), Sequence, true, quesId+"^"+AnsControlType+"^"+questGroupId+"1?",0.3F);
					textQntty=getTextViewSpecial(QuestDesc.split(Pattern.quote("$"))[1].toString(), Sequence, true, quesId+"^"+AnsControlType+"^"+questGroupId+"2?",0.7F);

					hmapMustRqrdFiled.put(questionKeyToShow, true);


				}
				else
				{

						//hmapOptionalFiled.put(questionKeyToShow, "Check Min Length");


						if(QuestDesc.contains("$"))
						{

							tv_Ques=getTextViewSpecial(QuestDesc.split(Pattern.quote("$"))[0].toString(), Sequence, false, quesId+"^"+AnsControlType+"^"+questGroupId+"1?",0.3F);
							textQntty=getTextViewSpecial(QuestDesc.split(Pattern.quote("$"))[1].toString(), Sequence, false, quesId+"^"+AnsControlType+"^"+questGroupId+"2?",0.7F);
						}
						else
						{
							tv_Ques=getTextView(QuestDesc, Sequence, false, quesId+"^"+AnsControlType+"^"+questGroupId+"?");
						}


				}

			}

			hmapOptionValues.put(questionKeyToShow, helperDb.fnGetOptionMstr(Integer.valueOf(quesId)));
			ArrayList<String> listOptionVal=hmapOptionValues.get(questionKeyToShow);
			
			if(QuestBundleFlg.equals("1"))
			{
				View viewVerticalLayout;
				if(!AnsControlType.equals("12"))
				{
					 viewVerticalLayout=getLinearLayoutVertical(tv_Ques, AnsControlType,AsnControlInputTypeID,Integer.valueOf(AnsControlInputTypeMaxLength),answerHint, quesId,questGroupId,listOptionVal);
				}
				else
				{
					
					 viewVerticalLayout=getLinearLayoutVertical(tv_Ques, AnsControlType,AsnControlInputTypeID,Integer.valueOf(AnsControlInputTypeMaxLength),answerHint, quesId,questGroupId,listOptionVal);
				}
				
				ll_Section.addView(viewVerticalLayout);
				if(dependentValuesQuesId!=null && dependentValuesQuesId.length>0)
				{
					if(Arrays.asList(dependentValuesQuesId).contains(questGroupId))
					{
						viewVerticalLayout.setVisibility(View.GONE);
					}
				}


				
			
				
			}
			
			if(QuestBundleFlg.equals("2"))
			{
			View viewHorizontalLayout=getLinearLayoutHorizontal(tv_Ques, AnsControlType,AsnControlInputTypeID,Integer.valueOf(AnsControlInputTypeMaxLength),answerHint, quesId,questGroupId,listOptionVal);
			ll_Section.addView(viewHorizontalLayout);

				if(dependentValuesQuesId!=null && dependentValuesQuesId.length>0)
				{
					if(Arrays.asList(dependentValuesQuesId).contains(questGroupId))
					{
						viewHorizontalLayout.setVisibility(View.GONE);
					}

				}

			
			}

			if(QuestBundleFlg.equals("3"))
			{
				if(quesWithBndlFlag3!=null)
				{

					if(!quesWithBndlFlag3.contains(questionKeyToShow))
					{
						String otherQues;
						String[] questWithSameGroup =helperDb.fnGetGroupIdQuestionMstr(QuestBundleGroupId,section);
						if(!questWithSameGroup[0].toString().trim().equals(questionKeyToShow))
						{
							otherQues=questWithSameGroup[0];
						}
						else
						{
							otherQues=questWithSameGroup[1];
						}
						quesWithBndlFlag3.add(questWithSameGroup[0].toString().trim());
						quesWithBndlFlag3.add(questWithSameGroup[1].toString().trim());
						
						int index=0;
					
							String[] quesVal2=AddNewStore_DynamicSectionWise.hmapQuesIdValues.get(otherQues).split(Pattern.quote("^")) ;
							String quesId2=quesVal2[0].toString();
							String QuestDesc2=quesVal2[2];
							String questGroupId2=otherQues.split(Pattern.quote("^"))[2];
							String AnsControlType2=quesVal2[4];
							String AsnControlInputTypeID2=quesVal2[5];
							String AnsControlInputTypeMaxLength2=quesVal2[6];
							String AnsMustRequiredFlg2=quesVal2[7];//1=Not Must Required,2=Must Required
							
							String QuestBundleFlg2=quesVal2[8];
							String QuestBundleGroupId2=quesVal2[13];
							String Sequence2=String.valueOf(count);
							String answerHint2;
							if(!quesVal2[12].equals("N/A"))
							{
								answerHint2=quesVal2[12];
							}
							else
							{
								answerHint2="";
							}
							TextView tv_Ques2;
							if(AnsMustRequiredFlg2.equals("2"))
							{
								tv_Ques2=getTextView(QuestDesc2, Sequence2, true, quesId2+"^"+AnsControlType2+"^"+questGroupId2+"?");
								
								
								
							}
							else 
							{
								tv_Ques2=getTextView(QuestDesc2, Sequence2, false, quesId2+"^"+AnsControlType2+"^"+questGroupId2+"?");
								
							}
							
							
							
							hmapOptionValues.put(questionKeyToShow, helperDb.fnGetOptionMstr(Integer.valueOf(quesId)));
							ArrayList<String> listOptionVal2=hmapOptionValues.get(quesId2+"^"+AnsControlType2);
							ll_Section.addView(getLinearLayoutHorizontal2Ques(getLinearLayoutVertical(tv_Ques, AnsControlType,AsnControlInputTypeID,Integer.valueOf(AnsControlInputTypeMaxLength),answerHint, quesId,questGroupId,listOptionVal), getLinearLayoutVertical(tv_Ques2, AnsControlType2,AsnControlInputTypeID2,Integer.valueOf(AnsControlInputTypeMaxLength2),answerHint2, quesId2,questGroupId2,listOptionVal2)));
							
							
							index++;
						
						
					
					}

				
				}
				else
				{

				}
			}
			
			count++;
		}
		
		
	}
	protected void setCopyOfAboveGroup(String groupTag,boolean isChecked) {
		LinkedHashMap<String, String> hmapQuestIDAndValue=new LinkedHashMap<String, String>();
		String groupId=groupTag.split(Pattern.quote("_"))[0];
		if(isChecked)
		{
			String getParentCopyGrpId=hmapGroupIdCopyAsAbove.get(groupId);
			ArrayList<String> listAllQyestInParentCopyGrpId=new ArrayList<String>();
			listAllQyestInParentCopyGrpId=AddNewStore_DynamicSectionWise.hmapQuesGropKeySection.get(getParentCopyGrpId);
			String[] allQuestIdAgainstParentGroup=helperDb.getQuestIdGroupIdCopyAsAbove(Integer.valueOf(getParentCopyGrpId), Integer.valueOf(groupId));
			if(allQuestIdAgainstParentGroup!=null)
			{
				int index=0;
				for(String questKey:listAllQyestInParentCopyGrpId)
				{
					String qustIdOfPrntGrpCpy=questKey.split(Pattern.quote("^"))[0].toString();
					if(Arrays.asList(allQuestIdAgainstParentGroup).contains(qustIdOfPrntGrpCpy))
					{
						copyValueFromParentGroup(questKey,groupId);
					}
					index++;
				}
			}
		}
		else
		{
			
		}
		
	}


	private void copyValueFromParentGroup(String questKey,String groupId) {
		
		
		LinkedHashMap<String, String> hmapCopyValue=new LinkedHashMap<String, String>();
		String questId=questKey.split(Pattern.quote("^"))[0];
		View view=ll_data.findViewWithTag(questKey);
		if(view instanceof EditText)
		{
			EditText edVal=(EditText)view;
			
			
//saveOutletQuestAnsMstr(String OutletID,String QuestID,String AnswerType,String AnswerValue)
			
			if(!TextUtils.isEmpty(edVal.getText().toString()))
			{
				hmapCopyValue.put(questId, TextUtils.htmlEncode(edVal.getText().toString()));
				ArrayList<String> listAllQyestInParentCopyGrpId=new ArrayList<String>();
				listAllQyestInParentCopyGrpId=AddNewStore_DynamicSectionWise.hmapQuesGropKeySection.get(groupId);
			
				for(String toCopyTxtView:listAllQyestInParentCopyGrpId)
				{
				String questIdOfToCpyQuestId=toCopyTxtView.split(Pattern.quote("^"))[0];
				if(questIdOfToCpyQuestId.equals(questId))
				{
					EditText edValToCopy=(EditText) ll_data.findViewWithTag(toCopyTxtView);
					edValToCopy.setText(edVal.getText().toString());
					break;
					
				}
				}
			
			}
		
		}
		
		else if(view instanceof Button)
		{
			if(questKey.split(Pattern.quote("^"))[1].toString().equals("11"))
			{

				Button buttonCalender=(Button)view;
				
				if(!buttonCalender.getText().toString().equals("Select Date"))
				{
					hmapCopyValue.put(questId, TextUtils.htmlEncode(buttonCalender.getText().toString()));
					
					ArrayList<String> listAllQyestInParentCopyGrpId=new ArrayList<String>();
					listAllQyestInParentCopyGrpId=AddNewStore_DynamicSectionWise.hmapQuesGropKeySection.get(groupId);
				
					for(String toCopyTxtView:listAllQyestInParentCopyGrpId)
					{
					String questIdOfToCpyQuestId=toCopyTxtView.split(Pattern.quote("^"))[0];
					if(questIdOfToCpyQuestId.equals(questId))
					{
						Button btnValToCopy=(Button) ll_data.findViewWithTag(toCopyTxtView);
						btnValToCopy.setText(buttonCalender.getText().toString());
						break;
						
					}
					}
				
				
				}
				
			}
		}
		else if(questKey.split(Pattern.quote("^"))[1].toString().equals("4") || questKey.split(Pattern.quote("^"))[1].toString().equals("5"))
		{
			StringBuilder answer=new StringBuilder();
			LinearLayout ll_checkBoxVal=(LinearLayout)view;
			boolean isSingleSelected=true;
			boolean noValSelected=true;
			for(int i=0;i<ll_checkBoxVal.getChildCount();i++)
			{
				if(ll_checkBoxVal.getChildAt(i) instanceof CheckBox)
				{
					CheckBox checkBoxVal=(CheckBox) ll_checkBoxVal.getChildAt(i);
					String ediTextVal="";
					boolean isOtherSelected=false;
					if(checkBoxVal.isChecked())
					{
						noValSelected=false;
						
						if(checkBoxVal.getText().toString().equals("Others") || checkBoxVal.getText().toString().equals("Other"))
						{
							isOtherSelected=true;
							EditText editTextCheckBox=(EditText) ll_data.findViewWithTag(checkBoxVal.getTag().toString()+"_ed");
							if(!TextUtils.isEmpty(editTextCheckBox.getText().toString()))
							{
								
								ediTextVal=editTextCheckBox.getText().toString();
								
							}
							
						}
						
						if(i==0)
						{
						if(isOtherSelected)
						{
							answer.append(hmapCopyValue.get((questId.split(Pattern.quote("^"))[2])+"^"+checkBoxVal.getText().toString())+"~"+ediTextVal);
						}
						else
						{
							answer.append(hmapOptionId.get((questKey.split(Pattern.quote("^"))[2])+"^"+checkBoxVal.getText().toString()));
						}
							
							
							isSingleSelected=false;
						}
						else
						{
							if(isSingleSelected)
							{
								if(isOtherSelected)
								{
									answer.append(hmapOptionId.get((questKey.split(Pattern.quote("^"))[2])+"^"+checkBoxVal.getText().toString())+"~"+ediTextVal);
								}
								else
								{
									answer.append(hmapOptionId.get((questKey.split(Pattern.quote("^"))[2])+"^"+checkBoxVal.getText().toString()));
								}
								
								isSingleSelected=false;
							}
							else
							{
								if(isOtherSelected)
								{
									answer.append("^").append(hmapOptionId.get((questKey.split(Pattern.quote("^"))[2])+"^"+checkBoxVal.getText().toString())+"~"+ediTextVal);
								}
								else
								{
									answer.append("^").append(hmapOptionId.get((questKey.split(Pattern.quote("^"))[2])+"^"+checkBoxVal.getText().toString()));
								}
								
								
							}
							
						}
					}
				}
				
				
			}
			if(noValSelected)
			{
				//tempOutletQuestAnsMstrVal.add(0,OutletID);
				/*tempOutletQuestAnsMstrVal.add(1,entry.getKey().split(Pattern.quote("^"))[0].toString());
				tempOutletQuestAnsMstrVal.add(2,entry.getKey().split(Pattern.quote("^"))[1].toString());
				tempOutletQuestAnsMstrVal.add(3,"");*/
			//hmapAnsValues.put(entry.getKey(), "");
			}
			else
			{
				
				//tempOutletQuestAnsMstrVal.add(0,OutletID);
			/*	tempOutletQuestAnsMstrVal.add(1,entry.getKey().split(Pattern.quote("^"))[0].toString());
				tempOutletQuestAnsMstrVal.add(2,entry.getKey().split(Pattern.quote("^"))[1].toString());
				tempOutletQuestAnsMstrVal.add(3,answer.toString());*/
				hmapCopyValue.put(questKey, TextUtils.htmlEncode( answer.toString()));
				String selectdCheckBox=answer.toString();
				
				
				ArrayList<String> listAllQyestInParentCopyGrpId=new ArrayList<String>();
				listAllQyestInParentCopyGrpId=AddNewStore_DynamicSectionWise.hmapQuesGropKeySection.get(groupId);
			
				for(String toCopyTxtView:listAllQyestInParentCopyGrpId)
				{
				String questIdOfToCpyQuestId=toCopyTxtView.split(Pattern.quote("^"))[0];
				if(questIdOfToCpyQuestId.equals(questId))
				{
					LinearLayout ll_CheckBox=(LinearLayout) ll_data.findViewWithTag(toCopyTxtView);
				
					//	boolean isSingleSelected=true;
					//	boolean noValSelected=true;
						for(int i=0;i<ll_checkBoxVal.getChildCount();i++)
						{
							if(ll_checkBoxVal.getChildAt(i) instanceof CheckBox)
							{
								CheckBox checkBoxVal=(CheckBox) ll_checkBoxVal.getChildAt(i);
								String checkBoxId=checkBoxVal.getTag().toString();
								String ediTextVal="";
								boolean isOtherSelected=false;

								if(selectdCheckBox.contains("^"))
								{
									String[] multipleChecked=selectdCheckBox.split(Pattern.quote("^"));
									for(int index=0;index<multipleChecked.length;index++)
									{
										if(multipleChecked[index].contains(checkBoxId))
										{
											checkBoxVal.setChecked(true);
											String checkBoxValue=checkBoxVal.getText().toString();
											if(checkBoxValue.equals("Others")||checkBoxValue.equals("Other"))
											{
												EditText editTextOther=(EditText) ll_data.findViewWithTag(checkBoxId+"_ed");
												int lengthediTextVal=multipleChecked[index].split(Pattern.quote("~")).length;
												if(lengthediTextVal>1)
												{
													ediTextVal=multipleChecked[index].split(Pattern.quote("~"))[1];
													editTextOther.setText(ediTextVal);
												}
												
														
											}
										}
									}
								}
								else
								{
									if(selectdCheckBox.contains(checkBoxId))
									{
										checkBoxVal.setChecked(true);
										String otherChecked=checkBoxVal.getText().toString();
										if(otherChecked.equals("Others")||otherChecked.equals("Other"))
										{
											EditText editTextOther=(EditText) ll_data.findViewWithTag(checkBoxId+"_ed");
											int lengthediTextVal=selectdCheckBox.split(Pattern.quote("~")).length;
											if(lengthediTextVal>1)
											{
												ediTextVal=selectdCheckBox.split(Pattern.quote("~"))[1];
												editTextOther.setText(ediTextVal);
											}
													
										}
										
									}
								}
								
								
							
							}
							
							
						}
					break;
					
				}
				}
			
			
			
			}
			//hmapAnsValues.put(entry.getKey(), answer.toString());
			
		}
		
		else if(view instanceof RadioGroup)
		{
			RadioGroup radioGroupSelected=(RadioGroup) view;
			 int selectedId = radioGroupSelected.getCheckedRadioButtonId();
			 if(selectedId!=-1)
			 {
				    RadioButton    radioButtonVal = (RadioButton) radioGroupSelected.findViewById(selectedId);
		          //  hmapAnsValues.put(entry.getKey(),radioButtonVal.getText().toString() );
		            //tempOutletQuestAnsMstrVal.add(0,OutletID);
				/*	tempOutletQuestAnsMstrVal.add(1,entry.getKey().split(Pattern.quote("^"))[0].toString());
					tempOutletQuestAnsMstrVal.add(2,entry.getKey().split(Pattern.quote("^"))[1].toString());
					tempOutletQuestAnsMstrVal.add(3,hmapOptionId.get((entry.getKey().split(Pattern.quote("^"))[0])+"^"+radioButtonVal.getText().toString()));*/
					hmapCopyValue.put(questKey, hmapOptionId.get((questKey.split(Pattern.quote("^"))[2])+"^"+radioButtonVal.getText().toString()));
			
			 }
			 else
			 {
				 //tempOutletQuestAnsMstrVal.add(0,OutletID);
				/*	tempOutletQuestAnsMstrVal.add(1,entry.getKey().split(Pattern.quote("^"))[0].toString());
					tempOutletQuestAnsMstrVal.add(2,entry.getKey().split(Pattern.quote("^"))[1].toString());
					tempOutletQuestAnsMstrVal.add(3,"");*/
				// hmapAnsValues.put(entry.getKey(), "");
		    		
			 }
	            // find the radiobutton by returned id
	        
		}
		
		else if(view instanceof Spinner)
		{
			
			Spinner spinnerSelected=(Spinner) view;
			 

	          if(!spinnerSelected.getSelectedItem().equals("Select"))
	          {
	        	if(questKey.split(Pattern.quote("^"))[1].toString().equals("7"))
					{
						if(spinnerSelected.getSelectedItem().toString().equals("Others"))
						{
							EditText editText=(EditText) ll_data.findViewWithTag(questKey.toString()+"_ed");
							
							if(!TextUtils.isEmpty(editText.getText().toString()))
							{
								
				        	  hmapCopyValue.put(questId,"-99"+"~"+editText.getText().toString().trim());
				        		
							}
							ArrayList<String> listAllQyestInParentCopyGrpId=new ArrayList<String>();
							listAllQyestInParentCopyGrpId=AddNewStore_DynamicSectionWise.hmapQuesGropKeySection.get(groupId);
						
							for(String toCopyTxtView:listAllQyestInParentCopyGrpId)
							{
							String questIdOfToCpyQuestId=toCopyTxtView.split(Pattern.quote("^"))[0];
							if(questIdOfToCpyQuestId.equals(questId))
							{
								ArrayAdapter adapter = (ArrayAdapter) spinnerSelected.getAdapter();
								Spinner spinnerSelectedCopy=(Spinner) ll_data.findViewWithTag(toCopyTxtView);
								spinnerSelectedCopy.setSelection(adapter.getPosition("Others"));
								EditText editTextOther=(EditText) ll_data.findViewWithTag(toCopyTxtView+"_ed");
								int lengthediTextVal=editText.getText().toString().trim().length();
								if(lengthediTextVal>1)
								{
									String ediTextVal=editText.getText().toString().trim();
									editTextOther.setText(ediTextVal);
								}
	
							}
							}
							
						}
						else
			        	{
			        	/*	tempOutletQuestAnsMstrVal.add(1,entry.getKey().split(Pattern.quote("^"))[0].toString());
							tempOutletQuestAnsMstrVal.add(2,entry.getKey().split(Pattern.quote("^"))[1].toString());
							tempOutletQuestAnsMstrVal.add(3,hmapOptionId.get((entry.getKey().split(Pattern.quote("^"))[0])+"^"+spinnerSelected.getSelectedItem().toString()));
				    	*/	
							hmapCopyValue.put(questId,hmapOptionId.get((questKey.split(Pattern.quote("^"))[2])+"^"+spinnerSelected.getSelectedItem().toString()) );
							String valueOfParentSpinner=hmapOptionId.get((questKey.split(Pattern.quote("^"))[2])+"^"+spinnerSelected.getSelectedItem().toString());

							
							ArrayList<String> listAllQyestInParentCopyGrpId=new ArrayList<String>();
							listAllQyestInParentCopyGrpId=AddNewStore_DynamicSectionWise.hmapQuesGropKeySection.get(groupId);
						
							for(String toCopyTxtView:listAllQyestInParentCopyGrpId)
							{
							String questIdOfToCpyQuestId=toCopyTxtView.split(Pattern.quote("^"))[0];
							if(questIdOfToCpyQuestId.equals(questId))
							{
								Spinner spinnerSelectedCopy=(Spinner) ll_data.findViewWithTag(toCopyTxtView);
								
								
									ArrayAdapter adapter = (ArrayAdapter) spinnerSelectedCopy.getAdapter();
									String ansName=getOptionNameFromHmap(valueOfParentSpinner);
									spinnerSelectedCopy.setSelection(adapter.getPosition(ansName));
								
								break;
								
							}
							}
						
						
			        	}
						
					}
		            //tempOutletQuestAnsMstrVal.add(0,OutletID);
	        	else
	        	{
	        		/*tempOutletQuestAnsMstrVal.add(1,entry.getKey().split(Pattern.quote("^"))[0].toString());
					tempOutletQuestAnsMstrVal.add(2,entry.getKey().split(Pattern.quote("^"))[1].toString());
					tempOutletQuestAnsMstrVal.add(3,hmapOptionId.get((entry.getKey().split(Pattern.quote("^"))[0])+"^"+spinnerSelected.getSelectedItem().toString()));
		    		*/
	        	//  hmapAnsValues.put(questId,hmapOptionId.get((questKey.split(Pattern.quote("^"))[2])+"^"+spinnerSelected.getSelectedItem().toString()) );
	        	  
	        		String valueOfParentSpinner=hmapOptionId.get((questKey.split(Pattern.quote("^"))[2])+"^"+spinnerSelected.getSelectedItem().toString());

					
					ArrayList<String> listAllQyestInParentCopyGrpId=new ArrayList<String>();
					listAllQyestInParentCopyGrpId=AddNewStore_DynamicSectionWise.hmapQuesGropKeySection.get(groupId);
				
					for(String toCopyTxtView:listAllQyestInParentCopyGrpId)
					{
					String questIdOfToCpyQuestId=toCopyTxtView.split(Pattern.quote("^"))[0];
					if(questIdOfToCpyQuestId.equals(questId))
					{

						
						Spinner spinnerSelectedCopy=(Spinner) ll_data.findViewWithTag(toCopyTxtView);
						
						
						ArrayAdapter adapter = (ArrayAdapter) spinnerSelectedCopy.getAdapter();
						String ansName=getOptionNameFromHmap(valueOfParentSpinner);
						spinnerSelectedCopy.setSelection(adapter.getPosition(ansName));
					
						
						break;
						
					}
					}
				
				
	        	}
					
	          }
	          
	          else
	        	  
	          {
	        	 
		    		  
	          }
		}
		
		 if(questKey.split(Pattern.quote("^"))[1].toString().equals("13"))
		 {/*
			 TextView textView=(TextView) ll_data.findViewWithTag(entry.getKey());
			 if(hmapsearchAnswerSlctd.containsKey(entry.getKey()))
			 {
				  hmapAnsValues.put(entry.getKey(),hmapsearchAnswerSlctd.get(entry.getKey()) );
			 }
			
		 */}
		 if(questKey.split(Pattern.quote("^"))[1].toString().equals("15"))
		 {
			 TextView textView=(TextView) ll_data.findViewWithTag(questKey);
			 if(hmapSelectedMultipleDepend.containsKey(questKey))
			 {
				  ArrayList<String> listSelectedMultiple=new ArrayList<String>();
				  
						  listSelectedMultiple=hmapSelectedMultipleDepend.get(questKey);
					  
				 StringBuilder sbfetchVal=new StringBuilder();
				 for(int i=0;i<listSelectedMultiple.size();i++)
				 {
					 if(i==0)
					 {
						 sbfetchVal.append(listSelectedMultiple.get(i));
					 }
					 else
					 {
						 sbfetchVal.append("^").append(listSelectedMultiple.get(i));
					 }
				 }
				  hmapCopyValue.put(questId,sbfetchVal.toString());
			 }
			
		 }
		 if(questKey.split(Pattern.quote("^"))[1].toString().equals("16"))
		 {
			 TextView textView=(TextView) ll_data.findViewWithTag(questKey);
			 if(hmapSelectedMultipleDepend.containsKey(questKey))
			 {
				  ArrayList<String> listSelectedMultiple=new ArrayList<String>();
				  
						  listSelectedMultiple=hmapSelectedMultipleDepend.get(questKey);
					 
				 StringBuilder sbfetchVal=new StringBuilder();
				 ArrayList<String> listOptionValName=hmapOptionValues.get(questKey);
				 for(int i=0;i<listSelectedMultiple.size();i++)
				 {
					 if(i==0)
					 {
						 sbfetchVal.append(listSelectedMultiple.get(i));
						for(String valueSelected:listOptionValName)
						{
							if(valueSelected.split(Pattern.quote("^"))[0].equals(listSelectedMultiple.get(i)))
							{
								if(valueSelected.split(Pattern.quote("^"))[1].equals("Others")||valueSelected.split(Pattern.quote("^"))[1].equals("Other"))
								{
									 
										 EditText edText=(EditText) ll_data.findViewWithTag(questKey+"_ed");
										 sbfetchVal.append("~").append(edText.getText().toString());
									
								}
							}
						}
						
						
					 }
					 else
					 {
						 sbfetchVal.append("^").append(listSelectedMultiple.get(i));
							for(String valueSelected:listOptionValName)
							{
								if(valueSelected.split(Pattern.quote("^"))[0].equals(listSelectedMultiple.get(i)))
								{
									if(valueSelected.split(Pattern.quote("^"))[1].equals("Others")||valueSelected.split(Pattern.quote("^"))[1].equals("Other"))
									{
										 
											 EditText edText=(EditText) ll_data.findViewWithTag(questKey+"_ed");
											 sbfetchVal.append("~").append(edText.getText().toString());
										
									}
								}
							}
					 }
				 }
				 // hmapAnsValues.put(questId ,sbfetchVal.toString());
			 }
			
		 }
		
		else if(view instanceof LinearLayout)
		{
			StringBuilder sbLandline=new StringBuilder();
			if(questKey.split(Pattern.quote("^"))[1].toString().equals("12"))
			{
				LinearLayout ll_phone=(LinearLayout)view;
				for(int i=0;i<ll_phone.getChildCount();i++)
				{
					EditText edLandLine=(EditText) ll_phone.getChildAt(i);
					sbLandline.append(edLandLine.getText().toString());
					
				}
				
				if(!TextUtils.isEmpty(sbLandline.toString()))
				{
					
				/*	//tempOutletQuestAnsMstrVal.add(0,OutletID);
					tempOutletQuestAnsMstrVal.add(1,entry.getKey().split(Pattern.quote("^"))[0].toString());
					tempOutletQuestAnsMstrVal.add(2,entry.getKey().split(Pattern.quote("^"))[1].toString());
					//tempOutletQuestAnsMstrVal.add(3,edVal.getText().toString());
					 tempOutletQuestAnsMstrVal.add(3,TextUtils.htmlEncode(sbLandline.toString()));*/
					// hmapAnsValues.put(questId,TextUtils.htmlEncode(sbLandline.toString()));
				
				}
				else
				{
					//tempOutletQuestAnsMstrVal.add(0,OutletID);
				/*	tempOutletQuestAnsMstrVal.add(1,entry.getKey().split(Pattern.quote("^"))[0].toString());
					tempOutletQuestAnsMstrVal.add(2,entry.getKey().split(Pattern.quote("^"))[1].toString());
					tempOutletQuestAnsMstrVal.add(3,"");*/
				//	 hmapAnsValues.put(entry.getKey(),"");
				
				}
				
		}
			
			
			if(questKey.split(Pattern.quote("^"))[1].toString().equals("14"))
			{

				StringBuilder answer=new StringBuilder();
				LinearLayout ll_checkBoxVal=(LinearLayout)view;
				ArrayList<String> listTagChckBox=new ArrayList<String>();
				boolean isSelected=false;
				boolean isSingleSelected=true;
				boolean isEditTextFilled=false;
				for(int i=0;i<ll_checkBoxVal.getChildCount();i++)
				{
					LinearLayout checkBoxVal_ll=(LinearLayout) ll_checkBoxVal.getChildAt(i);
					CheckBox chckBox=(CheckBox) checkBoxVal_ll.getChildAt(0);
					
					if(chckBox.isChecked())
					{
						isSelected=true;
						EditText ediText=(EditText) checkBoxVal_ll.getChildAt(1);
						
						String tagChckBox=chckBox.getTag().toString();
						String inputType=ediText.getTag().toString();
						String edtTextVal="0";
						if(!TextUtils.isEmpty(ediText.getText().toString()))
						{
							edtTextVal=ediText.getText().toString();
						}
						
					
							if(isSingleSelected)
							{
								answer.append(hmapOptionId.get((questKey.split(Pattern.quote("^"))[2])+"^"+chckBox.getText().toString())+"~"+edtTextVal);
								isSingleSelected=false;
							}
							else
							{
								
								answer.append("^").append(hmapOptionId.get((questKey.split(Pattern.quote("^"))[2])+"^"+chckBox.getText().toString())+"~"+edtTextVal);
								
							}
							
						
						
					}
					
				}
				if(isSelected)
				{
				//	hmapAnsValues.put(questId, answer.toString());
				}
				
				else
				{
				//	hmapAnsValues.put(entry.getKey(), "");
					
				}
			}
		}
		else
		{
			//hmapAnsValues.put(entry.getKey(), "");
		}
	
		
	}

	public TextView getTextViewSpecial(String quesDes,String sequence,boolean isMustRqrdField,String tagVal,float valueWeight)
	{


		TextView txtVw_ques=new TextView(getActivity());
		LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, valueWeight);
		txtVw_ques.setLayoutParams(layoutParams1);
		txtVw_ques.setTag(tagVal);

		txtVw_ques.setTextColor(getResources().getColor(R.color.blue));
		txtVw_ques.setText(quesDes);
		if(isMustRqrdField)
		{
			SpannableStringBuilder text_Value=textWithMandatory(txtVw_ques.getText().toString());
			txtVw_ques.setText(text_Value);
		}






		return txtVw_ques;
	}


	public TextView getTextView(String quesDes,String sequence,boolean isMustRqrdField,String tagVal)
	{
		
		
		TextView txtVw_ques=new TextView(getActivity());
		LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f);
		txtVw_ques.setLayoutParams(layoutParams1);
		txtVw_ques.setTag(tagVal);
	
		txtVw_ques.setTextColor(getResources().getColor(R.color.blue));
		txtVw_ques.setText(quesDes);
		if(isMustRqrdField)
		{
			 SpannableStringBuilder text_Value=textWithMandatory(txtVw_ques.getText().toString());
			 txtVw_ques.setText(text_Value);
		}
		
		
		
			
		
		
		return txtVw_ques;
	}
	
	
	public TextView getTextViewPreviousData(String quesDes,String sequence,boolean isMustRqrdField,String tagVal)
	{
		
		
		TextView txtVw_ques=new TextView(getActivity());
		LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f);
		txtVw_ques.setLayoutParams(layoutParams1);
		txtVw_ques.setTag(tagVal);
	
		txtVw_ques.setTextColor(Color.parseColor("#BE2000"));
		
		
		 SpannableString content = new SpannableString(quesDes);
		 content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
		 txtVw_ques.setText(quesDes);
		 
		if(isMustRqrdField)
		{
			 SpannableStringBuilder text_Value=textWithMandatory(txtVw_ques.getText().toString());
			 txtVw_ques.setText(text_Value);
		}
		
		
		
			
		
		
		return txtVw_ques;
	}

	public View getHorizontalScrollView(String tagVal)
	{
		
		View viewHorizontal = null;
		//=new EditText(getActivity());
		
		
		viewHorizontal=getActivity().getLayoutInflater().inflate(R.layout.horizontal_scroll_view, null);
			final LinearLayout ll_Image=(LinearLayout) viewHorizontal.findViewById(R.id.ll_imageView);
		ll_Image.setTag(tagVal);
		

		 return viewHorizontal;
		
	}

	public View getEditTextView(boolean isSingleLine,int maxLength,String tagVal,String ed_hint,String ansControlInputTypeID)
	{
		View viewEditText = null;
		EditText editText = null;//=new EditText(getActivity());
		/*if(ansControlInputTypeID.equals("9"))
		{
			addressKey=tagVal;
			viewEditText=getActivity().getLayoutInflater().inflate(R.layout.edit_text_ans_address, null);
			editText=(EditText) viewEditText.findViewById(R.id.et_numeric_alpha_address);
			if(!AddNewStore_DynamicSectionWise.address.equals("NA"))
			{
				editText.setText("\n\n"+AddNewStore_DynamicSectionWise.address);
			}

		}*/
        if(tagVal.split(Pattern.quote("^"))[1].equals("18"))
        {
            addressKey=tagVal;
            viewEditText=getActivity().getLayoutInflater().inflate(R.layout.edit_text_ans_address, null);
            editText=(EditText) viewEditText.findViewById(R.id.et_numeric_alpha_address);
            if(!AddNewStore_DynamicSectionWise.address.equals("NA"))
            {
                editText.setText("\n\n"+AddNewStore_DynamicSectionWise.address);

            }

        }
		else if(ansControlInputTypeID.equals("1"))
		{
			viewEditText=getActivity().getLayoutInflater().inflate(R.layout.edit_text_ans_alpha, null);
			editText=(EditText) viewEditText.findViewById(R.id.et_alphabet);
			editText.setHint(ed_hint);


		}
		else if(ansControlInputTypeID.equals("2"))
		{
			viewEditText=getActivity().getLayoutInflater().inflate(R.layout.edit_text_ans_numeric, null);
			editText=(EditText) viewEditText.findViewById(R.id.et_numeric);
			editText.setHint(ed_hint);
		}
		else if(ansControlInputTypeID.equals("3"))
		{
			viewEditText=getActivity().getLayoutInflater().inflate(R.layout.edit_text_ans_alpha_numeric, null);
			editText=(EditText) viewEditText.findViewById(R.id.et_numeric_alpha);
			editText.setHint(ed_hint);
		}
		else if(ansControlInputTypeID.equals("4"))
		{
			viewEditText=getActivity().getLayoutInflater().inflate(R.layout.edit_text_ans_pswrd, null);
			editText=(EditText) viewEditText.findViewById(R.id.et_password);
			editText.setHint(ed_hint);
		}
		else if(ansControlInputTypeID.equals("5"))
		{
			viewEditText=getActivity().getLayoutInflater().inflate(R.layout.edit_text_email, null);
			editText=(EditText) viewEditText.findViewById(R.id.et_email);
			editText.setHint(ed_hint);
			
		}
		else if(ansControlInputTypeID.equals("7"))
		{
			viewEditText=getActivity().getLayoutInflater().inflate(R.layout.edit_text_ans_upiid, null);
			editText=(EditText) viewEditText.findViewById(R.id.et_numeric_alpha_login);
			editText.setHint(ed_hint);

		}
		else
		{
			viewEditText=getActivity().getLayoutInflater().inflate(R.layout.edit_text_ans_alpha, null);
			editText=(EditText) viewEditText.findViewById(R.id.et_alphabet);
			editText.setHint(ed_hint);
		}
		
		
		System.out.println("AnsCntrolType = "+ansControlInputTypeID);
	//	editText.setBackgroundResource(R.drawable.et_boundary);
		LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f);
		editText.setLayoutParams(layoutParams1);
		editText.setTag(tagVal);
		


		//et_alphabet.setHint(ed_hint);
		InputFilter[] FilterArray = new InputFilter[1];
		FilterArray[0] = new InputFilter.LengthFilter(maxLength);
		editText.setFilters(FilterArray); 
	
		return viewEditText;
	}
	
	public void customAlertStoreList(final List<String> listOption,String sectionHeader,int maxLength,final TextView textView,String tagVal)
	{

        final Dialog listDialog = new Dialog(getActivity());
        listDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        listDialog.setContentView(R.layout.search_list);
        listDialog.setCanceledOnTouchOutside(false);
         listDialog.setCancelable(false);
         LayoutParams parms = listDialog.getWindow().getAttributes();
	        parms.gravity =Gravity.CENTER;
         //there are a lot of settings, for dialog, check them all out!
	        parms.dimAmount = (float) 0.5;
	        isSelectedSearch=false;
			   
	        List<String> listOptinVal=new ArrayList<String>();
			List<String> listOptionId=new ArrayList<String>();
			
			for(int index=0;index <listOption.size();index++)
			{
				

				listOptinVal.add(listOption.get(index).split(Pattern.quote("^"))[1].toString());
				listOptionId.add(listOption.get(index).split(Pattern.quote("^"))[0].toString());
				
			}
			
	        TextView txt_section=(TextView) listDialog.findViewById(R.id.txt_section);
	        txt_section.setText(sectionHeader);
	        TextView txtVwCncl=(TextView) listDialog.findViewById(R.id.txtVwCncl);
	    //    TextView txtVwSubmit=(TextView) listDialog.findViewById(R.id.txtVwSubmit);
	        
	        final EditText ed_search=(AutoCompleteTextView) listDialog.findViewById(R.id.ed_search);
			final ListView list_store=(ListView) listDialog.findViewById(R.id.list_store);
		   final CardArrayAdapter cardArrayAdapter = new CardArrayAdapter(getActivity(),listOptinVal,listOptionId,ed_search,list_store,tagVal,listDialog,textView);

	       

				
			
				
			
			 list_store.setAdapter(cardArrayAdapter);
		//	editText.setBackgroundResource(R.drawable.et_boundary);
			
				
		boolean isSingleLine=true;
			ed_search.setSingleLine(isSingleLine);
			if(!isSingleLine)
			{
				//et_numeric_alpha.setInputType(T)
				ed_search.setInputType(InputType.TYPE_CLASS_TEXT |
	                    InputType.TYPE_TEXT_FLAG_MULTI_LINE);
			}
			//et_alphabet.setHint(ed_hint);
			InputFilter[] FilterArray = new InputFilter[1];
			FilterArray[0] = new InputFilter.LengthFilter(maxLength);
			ed_search.setFilters(FilterArray); 
			  ed_search.addTextChangedListener(new TextWatcher() {
					
					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count)
					{
						
						
					}
					
					@Override
					public void beforeTextChanged(CharSequence s, int start, int count,
							int after) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void afterTextChanged(Editable s) {
						
							cardArrayAdapter.filter(s.toString());
						
						
						
					}
				});
	        
         
			  
			  
	        txtVwCncl.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					listDialog.dismiss();
					isSelectedSearch=false;
					
				}
			});
	       
	        
	     
			
         //now that the dialog is set up, it's time to show it
         listDialog.show();
    
	}
	
	public void customAlertStoreListMultiCheckWithoutOther(final List<String> listOption,String sectionHeader,int maxLength,final TextView textView,final String tagVal)
	{

        final Dialog listDialogMulti = new Dialog(getActivity());
        listDialogMulti.requestWindowFeature(Window.FEATURE_NO_TITLE);
        listDialogMulti.setContentView(R.layout.search_list_multiple);
        listDialogMulti.setCanceledOnTouchOutside(false);
        listDialogMulti.setCancelable(false);
         LayoutParams parms = listDialogMulti.getWindow().getAttributes();
	        parms.gravity =Gravity.CENTER;
         //there are a lot of settings, for dialog, check them all out!
	        parms.dimAmount = (float) 0.5;
	      
			   
	        List<String> listOptinVal=new ArrayList<String>();
			List<String> listOptionId=new ArrayList<String>();
			isSelectedSearch=false;
			for(int index=0;index <listOption.size();index++)
			{
				
			
				listOptinVal.add(listOption.get(index).split(Pattern.quote("^"))[1].toString());
				listOptionId.add(listOption.get(index).split(Pattern.quote("^"))[0].toString());
				
			}
			LinearLayout ll_selectedText=(LinearLayout) listDialogMulti.findViewById(R.id.ll_selectedText);
	        TextView txt_section=(TextView) listDialogMulti.findViewById(R.id.txt_section);
	        txt_section.setText(sectionHeader);
	        TextView txtVwCncl=(TextView) listDialogMulti.findViewById(R.id.txtVwCncl);
	       TextView txtVwSubmit=(TextView) listDialogMulti.findViewById(R.id.txtVwSubmit);
	        
	        final EditText ed_search=(AutoCompleteTextView) listDialogMulti.findViewById(R.id.multpl_ed_search);
			final ListView list_store=(ListView) listDialogMulti.findViewById(R.id.list_store);
		  ArrayList<String> listSelectedMultiple=new ArrayList<String>();
		if(hmapSelectedMultipleDepend!=null)
		  {
			  if(hmapSelectedMultipleDepend.containsKey(tagVal))
			  {
				  listSelectedMultiple=hmapSelectedMultipleDepend.get(tagVal);
			  } 
		  }
		
			final CardArrayAdapterMultipleSelected cardArrayAdapter = new CardArrayAdapterMultipleSelected(getActivity(),listOptinVal,listOptionId,ed_search,list_store,tagVal,listDialogMulti,textView,ll_selectedText,listSelectedMultiple);

	       

				
			
				
			
			 list_store.setAdapter(cardArrayAdapter);
		//	editText.setBackgroundResource(R.drawable.et_boundary);
			
				
		boolean isSingleLine=true;
			ed_search.setSingleLine(isSingleLine);
			if(!isSingleLine)
			{
				//et_numeric_alpha.setInputType(T)
				ed_search.setInputType(InputType.TYPE_CLASS_TEXT |
						InputType.TYPE_TEXT_FLAG_MULTI_LINE);
			}
			//et_alphabet.setHint(ed_hint);
			InputFilter[] FilterArray = new InputFilter[1];
			FilterArray[0] = new InputFilter.LengthFilter(maxLength);
			ed_search.setFilters(FilterArray); 
			  ed_search.addTextChangedListener(new TextWatcher() {
					
					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count)
					{
						
						
					}
					
					@Override
					public void beforeTextChanged(CharSequence s, int start, int count,
							int after) {
						
						
					}
					
					@Override
					public void afterTextChanged(Editable s) {
						
							cardArrayAdapter.filter(s.toString());
						
						
						
					}
				});
	        
         
			  
			  
	        txtVwCncl.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					listDialogMulti.dismiss();
					isSelectedSearch=false;
					
				}
			});
	      txtVwSubmit.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(isSelectedSearch)
					{
						hmapSelectedMultipleDepend.put(tagVal, listFarmerID);
						if(!TextUtils.isEmpty(sbMultiple.toString()))
						{
							textView.setText(sbMultiple.toString());
						}
						else
						{
							textView.setText("Select");
							if(hmapSelectedMultipleDepend.containsKey(tagVal))
							{
								hmapSelectedMultipleDepend.remove(tagVal);
							}
						}	
					listDialogMulti.dismiss();
					isSelectedSearch=false;

						String grpQustId=tagVal.split(Pattern.quote("^"))[2];

						for(int i=0;i<listSelectedStoreOriginTemp.size();i++)
						{
							String notSlctdOptionId=hmapOptionId.get(grpQustId+"^"+listSelectedStoreOriginTemp.get(i));
							if(AddNewStore_DynamicSectionWise.hmapDpndtQustGrpId.containsKey(grpQustId+"^"+notSlctdOptionId))
							{
								String questIdDependent=AddNewStore_DynamicSectionWise.hmapDpndtQustGrpId.get(grpQustId+"^"+notSlctdOptionId);

								if(questIdDependent.contains("^"))
								{

									String[] questionToVisible=questIdDependent.split(Pattern.quote("^"));
									for(int j=0;j<questionToVisible.length;j++)
									{//
//									/

										View view2=ll_data.findViewWithTag(questionToVisible[j]+"ll");

										if(view2 instanceof LinearLayout)
										{
											LinearLayout ll_view=(LinearLayout)view2;
											if(ll_view.getVisibility()==View.VISIBLE)
											{

												ll_view.setVisibility(View.GONE);
												//	hideDpndntQstn(questionToVisible[j]);

											}
											hideOrShowGroup(questionToVisible[j]);

										}
										if(hmapQuestDependVisible.containsKey(questionToVisible[j]))
										{
											ArrayList<String> listQuestDpndOnHideQuest=new ArrayList<String>();
											listQuestDpndOnHideQuest=hmapQuestDependVisible.get(questionToVisible[j]);
											for(int questCount=0;questCount<listQuestDpndOnHideQuest.size();questCount++)
											{
												View viewGrandChild=ll_data.findViewWithTag(listQuestDpndOnHideQuest.get(questCount)+"ll");
												if(viewGrandChild instanceof LinearLayout)
												{
													LinearLayout ll_view=(LinearLayout)viewGrandChild;

													if(ll_view.getVisibility()==View.VISIBLE)
													{

														ll_view.setVisibility(View.GONE);
														//	hideDpndntQstn(listQuestDpndOnHideQuest.get(questCount));
														hideOrShowGroup(listQuestDpndOnHideQuest.get(questCount));

													}

												}
											}
										}

									}


								}
								else
								{

									String questionToVisible=questIdDependent;
									View view2=ll_data.findViewWithTag(questionToVisible+"ll");
									if(view2 instanceof LinearLayout)
									{
										LinearLayout ll_view=(LinearLayout)view2;
										if(ll_view.getVisibility()==View.VISIBLE)
										{
											if(hmapQuesIdandGetAnsCntrlType.get(hmapQuestGrpId_QstId.get(questionToVisible)).equals("7") || hmapQuesIdandGetAnsCntrlType.get(hmapQuestGrpId_QstId.get(questionToVisible)).equals("8"))
											{
												Spinner spinner=(Spinner) ll_data.findViewWithTag(hmapQuestGrpId_QstId.get(questionToVisible)+"^"+hmapQuesIdandGetAnsCntrlType.get(hmapQuestGrpId_QstId.get(questionToVisible))+"^"+questionToVisible);
												if(spinner!=null)
												{
													spinner.setSelection(0);
												}

											}


											ll_view.setVisibility(View.GONE);
											//	hideDpndntQstn(questionToVisible);
											hideOrShowGroup(questionToVisible);

										}


									}
									if(hmapQuestDependVisible.containsKey(questionToVisible))
									{
										ArrayList<String> listQuestDpndOnHideQuest=new ArrayList<String>();
										listQuestDpndOnHideQuest=hmapQuestDependVisible.get(questionToVisible);
										for(int questCount=0;questCount<listQuestDpndOnHideQuest.size();questCount++)
										{
											View viewGrandChild=ll_data.findViewWithTag(listQuestDpndOnHideQuest.get(questCount)+"ll");
											if(viewGrandChild instanceof LinearLayout)
											{
												LinearLayout ll_view=(LinearLayout)viewGrandChild;

												if(ll_view.getVisibility()==View.VISIBLE)
												{

													ll_view.setVisibility(View.GONE);
													//	hideDpndntQstn(listQuestDpndOnHideQuest.get(questCount));
													hideOrShowGroup(listQuestDpndOnHideQuest.get(questCount));
												}

											}
										}
									}
								}

							}

						}

						if(listFarmerID.size()>0)
						{

							for(int i=0;i<listFarmerID.size();i++)
							{



								if(AddNewStore_DynamicSectionWise.hmapDpndtQustGrpId.containsKey(grpQustId+"^"+listFarmerID.get(i))  )
								{


									String questIdDependent=AddNewStore_DynamicSectionWise.hmapDpndtQustGrpId.get(grpQustId+"^"+listFarmerID.get(i));
									if(questIdDependent.contains("^"))
									{

										String[] questionToVisible=questIdDependent.split(Pattern.quote("^"));
										for(int j=0;j<questionToVisible.length;j++)
										{
											View view2=ll_data.findViewWithTag(questionToVisible[j]+"ll");
											if(view2 instanceof LinearLayout)
											{
												hmapGrpQstId_DepndentQstOptId.put(questionToVisible[j], listFarmerID.get(i));
												LinearLayout ll_view=(LinearLayout)view2;

												ll_view.setVisibility(View.VISIBLE);
												//	showDpndntQstn(questionToVisible[j]);
												hideOrShowGroup(questionToVisible[j]);
												if (hmapQuesIdandGetAnsCntrlType.get(hmapQuestGrpId_QstId.get(questionToVisible[j])).equals("14"))
												{
													if(hmapgetOptDepMstr.containsKey(questionToVisible[j]))
													{
														ArrayList<String> listQestValuesDpndnt=new ArrayList<String>();

														listQestValuesDpndnt=helperDb.getDepOtnVal(listFarmerID.get(i),Integer.parseInt(questionToVisible[j]),Integer.parseInt(grpQustId));

														String tagValCheckBox=hmapQuestGrpId_QstId.get(questionToVisible[j])+"^"+hmapQuesIdandGetAnsCntrlType.get(hmapQuestGrpId_QstId.get(questionToVisible[j]))+"^"+questionToVisible[j];
														if(hmapGetCheckBoxVisible!=null && hmapGetCheckBoxVisible.size()>0)
														{
															if(!hmapGetCheckBoxVisible.containsKey(tagValCheckBox))
															{
																hmapGetCheckBoxVisible.put(tagValCheckBox,listFarmerID.get(i));
																getCheckBoxWithEditTextValues(listQestValuesDpndnt,tagValCheckBox);
															}

														}
														else
														{
															hmapGetCheckBoxVisible.put(tagValCheckBox,listFarmerID.get(i));
															getCheckBoxWithEditTextValues(listQestValuesDpndnt,tagValCheckBox);
														}

													}

												}
											}

										}


									}
									else
									{

										String questionToVisible=questIdDependent;
										View view2=ll_data.findViewWithTag(questionToVisible+"ll");
										if(view2 instanceof LinearLayout)
										{
											LinearLayout ll_view=(LinearLayout)view2;
											hmapGrpQstId_DepndentQstOptId.put(questionToVisible, listFarmerID.get(i));
											ll_view.setVisibility(View.VISIBLE);
											//	showDpndntQstn(questionToVisible);
											hideOrShowGroup(questionToVisible);
											if (hmapQuesIdandGetAnsCntrlType.get(hmapQuestGrpId_QstId.get(questionToVisible)).equals("14"))
											{
												if(hmapgetOptDepMstr.containsKey(questionToVisible))
												{
													ArrayList<String> listQestValuesDpndnt=new ArrayList<String>();

													listQestValuesDpndnt=helperDb.getDepOtnVal(listFarmerID.get(i),Integer.parseInt(questionToVisible),Integer.parseInt(grpQustId));

													String tagValCheckBox=hmapQuestGrpId_QstId.get(questionToVisible)+"^"+hmapQuesIdandGetAnsCntrlType.get(hmapQuestGrpId_QstId.get(questionToVisible))+"^"+questionToVisible;
													if(hmapGetCheckBoxVisible!=null && hmapGetCheckBoxVisible.size()>0)
													{
														if(!hmapGetCheckBoxVisible.containsKey(tagValCheckBox))
														{
															hmapGetCheckBoxVisible.put(tagValCheckBox,listFarmerID.get(i));
															getCheckBoxWithEditTextValues(listQestValuesDpndnt,tagValCheckBox);
														}

													}
													else
													{
														hmapGetCheckBoxVisible.put(tagValCheckBox,listFarmerID.get(i));
														getCheckBoxWithEditTextValues(listQestValuesDpndnt,tagValCheckBox);
													}

												}

											}


										}

									}


								}
							}


						}
					}
					else
					{
					Toast.makeText(getActivity(), "Please select value", Toast.LENGTH_SHORT).show();
					}
				}
			});
	        
	     
			
         //now that the dialog is set up, it's time to show it
	      listDialogMulti.show();
    
	}
	public void customAlertStoreListMultiCheck(final List<String> listOption,String sectionHeader,int maxLength,final TextView textView,final String tagVal)
	{

        final Dialog listDialogMulti = new Dialog(getActivity());
        listDialogMulti.requestWindowFeature(Window.FEATURE_NO_TITLE);
        listDialogMulti.setContentView(R.layout.search_list_multiple);
        listDialogMulti.setCanceledOnTouchOutside(false);
        listDialogMulti.setCancelable(false);
         LayoutParams parms = listDialogMulti.getWindow().getAttributes();
	        parms.gravity =Gravity.CENTER;
         //there are a lot of settings, for dialog, check them all out!
	        parms.dimAmount = (float) 0.5;
	      
			   
	        List<String> listOptinVal=new ArrayList<String>();
			List<String> listOptionId=new ArrayList<String>();
			isSelectedSearch=false;
			for(int index=0;index <listOption.size();index++)
			{
				
			
				listOptinVal.add(listOption.get(index).split(Pattern.quote("^"))[1].toString());
				listOptionId.add(listOption.get(index).split(Pattern.quote("^"))[0].toString());
				
			}
			LinearLayout ll_selectedText=(LinearLayout) listDialogMulti.findViewById(R.id.ll_selectedText);
	        TextView txt_section=(TextView) listDialogMulti.findViewById(R.id.txt_section);
	        txt_section.setText(sectionHeader);
	        TextView txtVwCncl=(TextView) listDialogMulti.findViewById(R.id.txtVwCncl);
	       TextView txtVwSubmit=(TextView) listDialogMulti.findViewById(R.id.txtVwSubmit);
	        
	        final EditText ed_search=(AutoCompleteTextView) listDialogMulti.findViewById(R.id.multpl_ed_search);
			final ListView list_store=(ListView) listDialogMulti.findViewById(R.id.list_store);
		  ArrayList<String> listSelectedMultiple=new ArrayList<String>();
		  if(hmapSelectedMultipleDepend!=null)
		  {
			  if(hmapSelectedMultipleDepend.containsKey(tagVal))
			  {
				  listSelectedMultiple=hmapSelectedMultipleDepend.get(tagVal);
			  } 
		  }
		
			final CardArrayAdapterMultipleSelected cardArrayAdapter = new CardArrayAdapterMultipleSelected(getActivity(),listOptinVal,listOptionId,ed_search,list_store,tagVal,listDialogMulti,textView,ll_selectedText,listSelectedMultiple);

	       

				
			
				
			
			 list_store.setAdapter(cardArrayAdapter);
		//	editText.setBackgroundResource(R.drawable.et_boundary);
			
				
		boolean isSingleLine=true;
			ed_search.setSingleLine(isSingleLine);
			if(!isSingleLine)
			{
				//et_numeric_alpha.setInputType(T)
				ed_search.setInputType(InputType.TYPE_CLASS_TEXT |
						InputType.TYPE_TEXT_FLAG_MULTI_LINE);
			}
			//et_alphabet.setHint(ed_hint);
			InputFilter[] FilterArray = new InputFilter[1];
			FilterArray[0] = new InputFilter.LengthFilter(maxLength);
			ed_search.setFilters(FilterArray); 
			  ed_search.addTextChangedListener(new TextWatcher() {
					
					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count)
					{
						
						
					}
					
					@Override
					public void beforeTextChanged(CharSequence s, int start, int count,
							int after) {
						
						
					}
					
					@Override
					public void afterTextChanged(Editable s) {
						
							cardArrayAdapter.filter(s.toString());
						
						
						
					}
				});
	        
         
			  
			  
	        txtVwCncl.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					listDialogMulti.dismiss();
					isSelectedSearch=false;
					
				}
			});
	      txtVwSubmit.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(isSelectedSearch)
					{
						hmapSelectedMultipleDepend.put(tagVal, listFarmerID);
						if(!TextUtils.isEmpty(sbMultiple.toString()))
						{
							textView.setText(sbMultiple.toString());
						}
						else
						{
							textView.setText("Select");
							if(hmapSelectedMultipleDepend.containsKey(tagVal))
							{
								hmapSelectedMultipleDepend.remove(tagVal);
							}
						}
					listDialogMulti.dismiss();
					isSelectedSearch=false;
					/*if(sbMultiple.toString().contains("Others") || sbMultiple.toString().contains("Other"))
					{
						EditText edText=(EditText) ll_data.findViewWithTag(tagVal+"_ed");
						edText.setVisibility(View.VISIBLE);
					}
					else
					{
						EditText edText=(EditText) ll_data.findViewWithTag(tagVal+"_ed");
						edText.setVisibility(View.GONE);
					}*/
					}
					else
					{
					Toast.makeText(getActivity(), "Please select value", Toast.LENGTH_SHORT).show();
					}
				}
			});
	        
	     
			
         //now that the dialog is set up, it's time to show it
	      listDialogMulti.show();
    
	}
	
	public View getSearchEditTextView(boolean isSingleLine,final int maxLength,final String tagVal,String ed_hint,String ansControlInputTypeID,final ArrayList<String> listOption)
	{
		View viewEditText;
		final TextView txtVw_Search=new TextView(getActivity());
		LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f);
		txtVw_Search.setLayoutParams(layoutParams1);
		txtVw_Search.setTag(tagVal);
		txtVw_Search.setText("Select");
		for(int i=0;i<listOption.size();i++)
		{
			hmapOptionId.put(tagVal.split(Pattern.quote("^"))[2]+"^"+listOption.get(i).split(Pattern.quote("^"))[1],listOption.get(i).split(Pattern.quote("^"))[0]);
		}
		txtVw_Search.setTextColor(getResources().getColor(android.R.color.black));
		txtVw_Search.setPadding(5, 5, 0, 5);
		txtVw_Search.setBackgroundDrawable(getResources().getDrawable(R.drawable.search_boundary));
		txtVw_Search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String grpQuestIdValuesDpndnt=tagVal.split(Pattern.quote("^"))[2].toString();
				TextView txtvWQues=(TextView) ll_data.findViewWithTag(tagVal+"?");
				if(hmapgetOptDepMstr!=null && hmapgetOptDepMstr.size()>0)
				{
					if(hmapgetOptDepMstr.containsKey(grpQuestIdValuesDpndnt))
					{
						//hmapSelectedMultipleDepend
						String DepQstId=hmapQuestGrpId_QstId.get(hmapgetOptDepMstr.get(grpQuestIdValuesDpndnt));
						String DepAnsCntrlType=hmapQuesIdandGetAnsCntrlType.get(hmapQuestGrpId_QstId.get(hmapgetOptDepMstr.get(grpQuestIdValuesDpndnt)));
						String DepGrpQstId=hmapgetOptDepMstr.get(grpQuestIdValuesDpndnt);
						if(hmapSelectedMultipleDepend.containsKey(DepQstId+"^"+DepAnsCntrlType+"^"+DepGrpQstId))
							
						{
							ArrayList<String> listQestValuesDpndnt=new ArrayList<String>();
							
								listQestValuesDpndnt=helperDb.getDepOtnVal(hmapGrpQstId_DepndentQstOptId.get(grpQuestIdValuesDpndnt),Integer.parseInt(grpQuestIdValuesDpndnt),Integer.parseInt(DepGrpQstId));
							
							
							 if(listQestValuesDpndnt!=null && listQestValuesDpndnt.size()>0)
							 {
								 if(tagVal.split(Pattern.quote("^"))[1].toString().equals("13"))
								 {
									 customAlertStoreList(listQestValuesDpndnt, txtvWQues.getText().toString(), maxLength, txtVw_Search, tagVal);
								 }
								 else if(tagVal.split(Pattern.quote("^"))[1].toString().equals("15"))
								 {
									 customAlertStoreListMultiCheckWithoutOther(listQestValuesDpndnt, txtvWQues.getText().toString(), maxLength, txtVw_Search, tagVal); }
								 else if( tagVal.split(Pattern.quote("^"))[1].toString().equals("16"))
								 {
									 
									 customAlertStoreListMultiCheck(listQestValuesDpndnt, txtvWQues.getText().toString(), maxLength, txtVw_Search, tagVal); 
								 }
								 //
								 
							 }
							
						}
					}
					else
					{
						if(tagVal.split(Pattern.quote("^"))[1].toString().equals("13"))
						 {
							customAlertStoreList(listOption, txtvWQues.getText().toString(), maxLength, txtVw_Search, tagVal);
						 }
						 else if(tagVal.split(Pattern.quote("^"))[1].toString().equals("15"))
						 {
							 customAlertStoreListMultiCheckWithoutOther(listOption, txtvWQues.getText().toString(), maxLength, txtVw_Search, tagVal); }
						 else if( tagVal.split(Pattern.quote("^"))[1].toString().equals("16"))
						 {
							 
							 customAlertStoreListMultiCheck(listOption, txtvWQues.getText().toString(), maxLength, txtVw_Search, tagVal); 
						 }
						 
						
						//
						
					}
				
				}
				
				else
				{
					if(tagVal.split(Pattern.quote("^"))[1].toString().equals("13"))
					 {
						customAlertStoreList(listOption, txtvWQues.getText().toString(), maxLength, txtVw_Search, tagVal);
					 }
					 else if(tagVal.split(Pattern.quote("^"))[1].toString().equals("15"))
					 {
						 customAlertStoreListMultiCheckWithoutOther(listOption, txtvWQues.getText().toString(), maxLength, txtVw_Search, tagVal); }
					 else if( tagVal.split(Pattern.quote("^"))[1].toString().equals("16"))
					 {
						 
						 customAlertStoreListMultiCheck(listOption, txtvWQues.getText().toString(), maxLength, txtVw_Search, tagVal); 
					 }
					 
					
					//
					
				}
			}
		});
		
		
		
		
		return txtVw_Search;
	}
	
	public Button getCalenderView(String btnText,String tagVal)
	{
		
		Button btn_calender=new Button(getActivity());
		btn_calender.setTag(tagVal);
		LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f);
		btn_calender.setLayoutParams(layoutParams1);
        btn_calender.setBackground(getResources().getDrawable(R.drawable.btn_calender_background));
		btn_calender.setCompoundDrawablesWithIntrinsicBounds(R.drawable.calendar, 0, 0, 0);
		String date=(new StringBuilder().append(day).append("/")
				   .append(month+1).append("/").append(year)).toString();
		try {
			Date oneWayTripDate = input.parse(date);                 // parse input 
			Calendar c = Calendar.getInstance();
			c.setTime(oneWayTripDate);
			c.add(Calendar.DAY_OF_MONTH, 7);

			//btn_calender.setText(output.format(c.getTime()));
			btn_calender.setText("Select Date");
		} catch (Exception e) {
			// TODO: handle exception
		}
		

		btn_calender.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				calenderClkdBtn=(Button)v;
				calendarS = Calendar.getInstance();

			       Year = calendarS.get(Calendar.YEAR) ;
			       Month = calendarS.get(Calendar.MONTH);
			       Day = calendarS.get(Calendar.DAY_OF_MONTH);
			       datePickerDialogs = DatePickerDialog.newInstance(new OnDateSetListener() {
					
					@Override
					public void onDateSet(DatePickerDialog view, int year, int monthOfYear,
							int dayOfMonth) {
						
						String[] MONTHS = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
					    String mon=MONTHS[monthOfYear];
					   //
					    calenderClkdBtn.setText(dayOfMonth+"/"+mon+"/"+year);
					 
					   // TODO Auto-generated method stub
					   //String questIdClndr= calenderClkdBtn.getTag().toString().split(Pattern.quote("^"))[0];
					  // getCalenderQuesDepndnt(questIdClndr);	
					}
				}, Year, Month, Day);

			       datePickerDialogs.setThemeDark(false);

			       datePickerDialogs.showYearPickerFirst(false);

			       datePickerDialogs.setAccentColor(Color.parseColor("#009688"));

			       datePickerDialogs.setTitle("Select Date From DatePickerDialog");
			       Calendar calendar = Calendar.getInstance();
			       calendar.setTimeInMillis(System.currentTimeMillis()+24*60*60*1000);
			      
			       datePickerDialogs.setMaxDate(Calendar.getInstance());
			      // datePickerDialogs.setMinDate(calendar);
			      
			       datePickerDialogs.show(getFragmentManager(), "DatePickerDialog");
				
				 // showDialog(999);
				
			}
		});
		
		return btn_calender;
	}
	
		
	
	public LinearLayout getCheckBoxView(final ArrayList<String> chckBoxVal,final boolean isSingleChecked,String tagVal)
	{
		final LinearLayout lay = new LinearLayout(getActivity());
		lay.setTag(tagVal);
		lay.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		
		lay.setOrientation(LinearLayout.VERTICAL);
		lay.removeAllViews();
		for(int index=0;index <chckBoxVal.size();index++)
		{
			CheckBox chBox=new CheckBox(getActivity());
			String quesIdForkey=tagVal.split(Pattern.quote("^"))[0];
			String grpQuestId=tagVal.split(Pattern.quote("^"))[2];
			String checkBoxVal=chckBoxVal.get(index).split(Pattern.quote("^"))[1];
			
			String checkBoxValNoneOptionNo=chckBoxVal.get(index).split(Pattern.quote("^"))[0];
			chBox.setTextSize(14);
			
			chBox.setTag(checkBoxValNoneOptionNo);
			
			chBox.setText(checkBoxVal);
			
			hmapOptionId.put(grpQuestId+"^"+checkBoxVal, chckBoxVal.get(index).split(Pattern.quote("^"))[0]);
			
			 RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams
		        		((int)LayoutParams.WRAP_CONTENT, (int) LayoutParams.WRAP_CONTENT);
		        params2.leftMargin = 16;
		        params2.topMargin = 0;
		        chBox.setLayoutParams(params2);
		        lay.addView(chBox);
		       
		    	if(checkBoxVal.equals("Others") || checkBoxVal.equals("Other"))
				{
					View viewSpinnerEditText=getEditTextView(true,200, checkBoxValNoneOptionNo+"_ed", "Type Here", "3");
					 lay.addView(viewSpinnerEditText);
					 viewSpinnerEditText.setVisibility(View.GONE);
				}
		     
		      



		}
		
	        return lay;
	}
	
	
	
	public LinearLayout getCheckBoxViewWithEditText(final ArrayList<String> chckBoxVal,boolean isSingleChecked,String tagVal,String AsnControlInputTypeID,int maxLengthEditText)
	{
		final LinearLayout lay = new LinearLayout(getActivity());
		lay.setTag(tagVal);
		lay.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		lay.setPadding(0, 2, 0, 2);
		lay.setOrientation(LinearLayout.VERTICAL);
		lay.removeAllViews();

	        return lay;
	}
	
	public RadioGroup getRadioView(ArrayList<String> radioVal,String tagVal,boolean isParntDpntdQues)
	{
		
		// create radio button final
		RadioButton[] rb = new RadioButton[radioVal.size()]; 
		RadioGroup rg = new RadioGroup(getActivity()); 
		rg.setTag(tagVal);
	
		rg.setOrientation(RadioGroup.HORIZONTAL); 
		for (int i = 0; i < radioVal.size(); i++) 
		{
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,1f);
			rb[i] = new RadioButton(getActivity());
			rb[i].setId(i);
			hmapOptionId.put(tagVal.split(Pattern.quote("^"))[2]+"^"+radioVal.get(i).split(Pattern.quote("^"))[1], radioVal.get(i).split(Pattern.quote("^"))[0]);


	//		rb[i].setButtonDrawable(getActivity().getResources().getDrawable(R.drawable.custom_radiobutton));
			rb[i].setTextSize(14);
			rb[i].setTag(radioVal.get(i).split(Pattern.quote("^"))[0]);
			rb[i].setText(radioVal.get(i).split(Pattern.quote("^"))[1]);
         //  rb[i].setLayoutParams(params);
            rg.addView(rb[i]);
        }
		if(isParntDpntdQues)
		{

			
			rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
		
					RadioButton    radioButtonVal = (RadioButton) group.findViewById(checkedId);
					
					String selectedVal=radioButtonVal.getText().toString();
					String tagValueOfSpinner=group.getTag().toString();
					String questionId=tagValueOfSpinner.split(Pattern.quote("^"))[0];
					String grpQuestId=tagValueOfSpinner.split(Pattern.quote("^"))[2];
					
					String optionId=hmapOptionId.get(grpQuestId+"^"+selectedVal);
					
					if(grpQstIdForChannel.equals(grpQuestId))
					{
						//clearAllValues(grpQuestId);
						for(Entry<String, String> entry:hmapQuestGrpId_QstId.entrySet())
						{
							if(!entry.getKey().equals(grpQstIdForChannel))
							{
								View view2=ll_data.findViewWithTag(entry.getKey()+"ll");
								if(view2 instanceof LinearLayout)
								{
									
									LinearLayout ll_view=(LinearLayout)view2;
									if(ll_view.getVisibility()==View.VISIBLE)
									{
										
										ll_view.setVisibility(View.GONE);
										
										hideOrShowGroup(entry.getKey());
										
										
									}
									
								}
							}
						}
					}
					else
					{
						for(int i=0;i<group.getChildCount();i++)
						{
						
							RadioButton unChckdOtherRadio=(RadioButton) group.getChildAt(i);
							if(unChckdOtherRadio.getId()!=checkedId)
							{
								
								
								
								String notSelectedVal=unChckdOtherRadio.getText().toString();
								String tagValueOfRadio=group.getTag().toString();
								String notSlctdquestionId=tagValueOfRadio.split(Pattern.quote("^"))[0];
								String notSlctdgrpQuestionId=tagValueOfRadio.split(Pattern.quote("^"))[2];
								
								String notSlctdoptionId=hmapOptionId.get(notSlctdgrpQuestionId+"^"+notSelectedVal);
								if(AddNewStore_DynamicSectionWise.hmapDpndtQustGrpId.containsKey(notSlctdgrpQuestionId+"^"+notSlctdoptionId))
								{
									String questIdDependent=AddNewStore_DynamicSectionWise.hmapDpndtQustGrpId.get(notSlctdgrpQuestionId+"^"+notSlctdoptionId);
									if(questIdDependent.contains("^"))
									{
										String[] questionToVisible=questIdDependent.split(Pattern.quote("^"));
										for(int j=0;j<questionToVisible.length;j++)
										{
											View view2=ll_data.findViewWithTag(questionToVisible[j]+"ll");
											if(view2 instanceof LinearLayout)
											{
												
												LinearLayout ll_view=(LinearLayout)view2;
												if(ll_view.getVisibility()==View.VISIBLE)
												{
													
													ll_view.setVisibility(View.GONE);
													
													hideOrShowGroup(questionToVisible[j]);
													
													
												}
												
											}
											
											
										}
									}
									else
									{
										String questionToVisible=questIdDependent;
										View view2=ll_data.findViewWithTag(questionToVisible+"ll");
										if(view2 instanceof LinearLayout)
										{
											LinearLayout ll_view=(LinearLayout)view2;
											if(ll_view.getVisibility()==View.VISIBLE)
											{
											
												ll_view.setVisibility(View.GONE);
											//	hideDpndntQstn(questionToVisible);
												hideOrShowGroup(questionToVisible);
												
												break;
											}
										}
									}
									
								}
							
						}
							
							
						}	
					}
					
					if(AddNewStore_DynamicSectionWise.hmapDpndtQustGrpId.containsKey(grpQuestId+"^"+optionId))
					{
						String questIdDependent=AddNewStore_DynamicSectionWise.hmapDpndtQustGrpId.get(grpQuestId+"^"+optionId);
						if(grpQstIdForChannel.equals(grpQuestId))
						{
							ll_address_section.setVisibility(View.VISIBLE);
						}

						if(questIdDependent.contains("^"))
						{
							
							String[] questionToVisible=questIdDependent.split(Pattern.quote("^"));
							for(int i=0;i<questionToVisible.length;i++)
							{
								View view2=ll_data.findViewWithTag(questionToVisible[i]+"ll");
								if(view2 instanceof LinearLayout)
								{
									LinearLayout ll_view=(LinearLayout)view2;
									
									ll_view.setVisibility(View.VISIBLE);
									//showDpndntQstn(questionToVisible[i]);
									hideOrShowGroup(questionToVisible[i]);
								}
							}
						
							
						}
						else
						{
							String questionToVisible=questIdDependent;
							View view2=ll_data.findViewWithTag(questionToVisible+"ll");
							if(view2 instanceof LinearLayout)
							{
								LinearLayout ll_viewNext=(LinearLayout)view2;

								ll_viewNext.setVisibility(View.VISIBLE);
							//	showDpndntQstn(questionToVisible);
								hideOrShowGroup(questionToVisible);
							}
						}
						
					}
					
					
					
					
				
					
				}
			});
		
		}
		
		
		return rg;
		
	}
	
	
	public View getSpinnerView(final String[] list,String tagVal,boolean isParentDpndnt,String descPrompt)
	{
		
		
		final View viewSpinner=getActivity().getLayoutInflater().inflate(R.layout.spinner_layout, null);
		final Spinner spinner_view=(Spinner) viewSpinner.findViewById(R.id.spinner_view);
		
		spinner_view.setTag(tagVal);
		if(list.length==2)
		{
			hmapSingleValSpinner.put(tagVal,list.length);
		}
		
		spinner_view.setPrompt(descPrompt.split(Pattern.quote("*"))[0].toString());
		LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f);
		spinner_view.setLayoutParams(layoutParams1);
		ArrayAdapter 	adapter=new ArrayAdapter(getActivity(),R.layout.initial_spinner_text,list);
		adapter.setDropDownViewResource(R.layout.spina);
		spinner_view.setAdapter(adapter);
		final String ansCntrlType=tagVal.split(Pattern.quote("^"))[1];
		if(isParentDpndnt || ansCntrlType.equals("7"))
		{
			spinner_view.setOnItemSelectedListener(new OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					String tagValueOfSpinner=spinner_view.getTag().toString();
					String questionId=tagValueOfSpinner.split(Pattern.quote("^"))[0];
					String grpQustId=tagValueOfSpinner.split(Pattern.quote("^"))[2];
					for(int i=0;i<list.length;i++)
					{
						String notSelectedOptionVal=list[i].toString();
						if(!notSelectedOptionVal.equals(spinner_view.getSelectedItem().toString()))
						{
							String notSlctdOptionId=hmapOptionId.get(grpQustId+"^"+notSelectedOptionVal);
							if(AddNewStore_DynamicSectionWise.hmapDpndtQustGrpId.containsKey(grpQustId+"^"+notSlctdOptionId))
							{
								String questIdDependent=AddNewStore_DynamicSectionWise.hmapDpndtQustGrpId.get(grpQustId+"^"+notSlctdOptionId);
								if(questIdDependent.contains("^"))
								{
									
									String[] questionToVisible=questIdDependent.split(Pattern.quote("^"));
									for(int j=0;j<questionToVisible.length;j++)
									{//
//										/
										
										View view2=ll_data.findViewWithTag(questionToVisible[j]+"ll");
										
										if(view2 instanceof LinearLayout)
										{
											LinearLayout ll_view=(LinearLayout)view2;
											if(ll_view.getVisibility()==View.VISIBLE)
											{
												if(hmapQuesIdandGetAnsCntrlType.get(hmapQuestGrpId_QstId.get(questionToVisible[j])).equals("7") || hmapQuesIdandGetAnsCntrlType.get(hmapQuestGrpId_QstId.get(questionToVisible[j])).equals("8"))
												{
													Spinner spinner=(Spinner) ll_data.findViewWithTag(hmapQuestGrpId_QstId.get(questionToVisible[j])+"^"+hmapQuesIdandGetAnsCntrlType.get(hmapQuestGrpId_QstId.get(questionToVisible[j]))+"^"+questionToVisible[j]);
													if(spinner!=null)
													{
														spinner.setSelection(0);
													}
													
												}
											
												
												ll_view.setVisibility(View.GONE);
												//hideDpndntQstn(questionToVisible[j]);
												hideOrShowGroup(questionToVisible[j]);
												
											}
											
										}
										if(hmapQuestDependVisible.containsKey(questionToVisible[j]))
										{
											ArrayList<String> listQuestDpndOnHideQuest=new ArrayList<String>();
											listQuestDpndOnHideQuest=hmapQuestDependVisible.get(questionToVisible[j]);
											for(int questCount=0;questCount<listQuestDpndOnHideQuest.size();questCount++)
											{
												View viewGrandChild=ll_data.findViewWithTag(listQuestDpndOnHideQuest.get(questCount)+"ll");
												if(viewGrandChild instanceof LinearLayout)
												{
													LinearLayout ll_view=(LinearLayout)viewGrandChild;
													
													if(ll_view.getVisibility()==View.VISIBLE)
													{
														
														ll_view.setVisibility(View.GONE);
														//hideDpndntQstn(questionToVisible[j]);
														hideOrShowGroup(questionToVisible[j]);
														
													}
													
												}
											}
										}
	
									}
								
									
								}
								else
								{

									String questionToVisible=questIdDependent;
									View view2=ll_data.findViewWithTag(questionToVisible+"ll");
									if(view2 instanceof LinearLayout)
									{
										LinearLayout ll_view=(LinearLayout)view2;
										if(ll_view.getVisibility()==View.VISIBLE)
										{
											if(hmapQuesIdandGetAnsCntrlType.get(hmapQuestGrpId_QstId.get(questionToVisible)).equals("7") || hmapQuesIdandGetAnsCntrlType.get(hmapQuestGrpId_QstId.get(questionToVisible)).equals("8"))
											{
												Spinner spinner=(Spinner) ll_data.findViewWithTag(hmapQuestGrpId_QstId.get(questionToVisible)+"^"+hmapQuesIdandGetAnsCntrlType.get(hmapQuestGrpId_QstId.get(questionToVisible))+"^"+questionToVisible);
												if(spinner!=null)
												{
													spinner.setSelection(0);
												}
												
											}
										
											
											ll_view.setVisibility(View.GONE);
										//	hideDpndntQstn(questionToVisible);
											hideOrShowGroup(questionToVisible);
											
										}
										
										
									}
									if(hmapQuestDependVisible.containsKey(questionToVisible))
									{
										ArrayList<String> listQuestDpndOnHideQuest=new ArrayList<String>();
										listQuestDpndOnHideQuest=hmapQuestDependVisible.get(questionToVisible);
										for(int questCount=0;questCount<listQuestDpndOnHideQuest.size();questCount++)
										{
											View viewGrandChild=ll_data.findViewWithTag(listQuestDpndOnHideQuest.get(questCount)+"ll");
											if(viewGrandChild instanceof LinearLayout)
											{
												LinearLayout ll_view=(LinearLayout)viewGrandChild;
												
												if(ll_view.getVisibility()==View.VISIBLE)
												{
													
													ll_view.setVisibility(View.GONE);
												//	hideDpndntQstn(listQuestDpndOnHideQuest.get(questCount));
													hideOrShowGroup(listQuestDpndOnHideQuest.get(questCount));
													
												}
												
											}
										}
									}
								}
							}
						}
						
					}
					if(!spinner_view.getSelectedItem().toString().equals("Select"))
					{
						
					
							if(!spinner_view.getSelectedItem().toString().equals("Others") || ansCntrlType.equals("8"))
							{
								String optionId=hmapOptionId.get(grpQustId+"^"+spinner_view.getSelectedItem().toString());
								
								
								if(AddNewStore_DynamicSectionWise.hmapDpndtQustGrpId.containsKey(grpQustId+"^"+optionId))
								{

									String questIdDependent=AddNewStore_DynamicSectionWise.hmapDpndtQustGrpId.get(grpQustId+"^"+optionId);
									if(questIdDependent.contains("^"))
									{
										
										String[] questionToVisible=questIdDependent.split(Pattern.quote("^"));
										for(int i=0;i<questionToVisible.length;i++)
										{
											View view2=ll_data.findViewWithTag(questionToVisible[i]+"ll");
											if(view2 instanceof LinearLayout)
											{
												LinearLayout ll_view=(LinearLayout)view2;
											
												ll_view.setVisibility(View.VISIBLE);
											//	showDpndntQstn(questionToVisible[i]);
												hideOrShowGroup(questionToVisible[i]);
											}
											
										}
									
										
									}
									else
									{

										String questionToVisible=questIdDependent;
										View view2=ll_data.findViewWithTag(questionToVisible+"ll");
										if(view2 instanceof LinearLayout)
										{
											LinearLayout ll_view=(LinearLayout)view2;
										
											ll_view.setVisibility(View.VISIBLE);
										//	showDpndntQstn(questionToVisible);
											hideOrShowGroup(questionToVisible);
										
										}
									
									}
								
								}
								
								
								
							
							}
							
					
					}
					else
					{
						
						if(ansCntrlType.equals("7"))
						{
							EditText editText=(EditText) ll_data.findViewWithTag(spinner_view.getTag().toString()+"_ed");
							if(editText.getVisibility()==View.VISIBLE)
							{
								editText.setVisibility(View.GONE);
							}
						}
						
						
					}
					
				}
				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					// TODO Auto-generated method stub
				}
			});
		}
		return viewSpinner;
	}
	

	public Button getButtonView(String buttonDesc,String tagValue)
	{
		
		Button btn_view=new Button(getActivity());
		btn_view.setTag(tagValue);
		
		btn_view.setText(buttonDesc);
		
		return btn_view;
	}
	
	
	
	public Button getCameraView(String tagValue)
	{


		Button btn_view=new Button(getActivity());
		btn_view.setTag(tagValue);
		LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		btn_view.setLayoutParams(layoutParams1);
		btn_view.setBackground(getResources().getDrawable(R.drawable.btn_calender_background));;
		btn_view.setText("Click Photo");
		imagButtonTag=tagValue.split(Pattern.quote("^"))[0].toString();
		btn_view.setTextColor(Color.BLACK);
		//	btn_view.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.camera_icon, 0);
		cameraButton=btn_view;
		btn_view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				clickedTagPhoto=v.getTag().toString();

				openCustomCamara();
				/*Intent imageIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				saveImage();
				 imageIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
	        startActivityForResult(imageIntent,
	        		requestCode );*/
				
			}
		});
	
		
		return btn_view;
	}
	
	public View getEditTextPhoneNumView(int maxLength,String tagVal,String ed_hint)
	{
		View etPhone_view=getActivity().getLayoutInflater().inflate(R.layout.phone_num, null);
		final LinearLayout ll_landline=(LinearLayout) etPhone_view.findViewById(R.id.ll_landline);
		final EditText ed_std_code=(EditText) etPhone_view.findViewById(R.id.ed_std_code);
		final EditText ed_phone_num=(EditText) etPhone_view.findViewById(R.id.ed_phone_num);
		
		ll_landline.setTag(tagVal);
	
		
		InputFilter[] FilterArrayStdCode = new InputFilter[1];
		FilterArrayStdCode[0] = new InputFilter.LengthFilter(5);
		InputFilter[] FilterArrayLandLine = new InputFilter[1];
		FilterArrayLandLine[0] = new InputFilter.LengthFilter(maxLength-5);
		//final StringBuilder sb=new StringBuilder();
		ed_std_code.setFilters(FilterArrayStdCode);
		ed_phone_num.setFilters(FilterArrayLandLine);
		ed_phone_num.addTextChangedListener(new TextWatcher() {
			 @Override
	             public void onTextChanged(CharSequence s, int start, int before, int count) {
	                 // TODO Auto-generated method stub
				 if(ed_phone_num.length()==0)
                 {
                    
				   ed_phone_num.clearFocus();
                     ed_std_code.requestFocus();
                     ed_std_code.setCursorVisible(true);
                  

                 }
	             }
	             @Override
	             public void beforeTextChanged(CharSequence s, int start, int count,
	                     int after) {

	            	
	                
	             }

	           

				@Override
				public void afterTextChanged(Editable s) {
					// TODO Auto-generated method stub
					  
					
				}
	         });
		ed_std_code.addTextChangedListener(new TextWatcher() {
			 @Override
	             public void onTextChanged(CharSequence s, int start, int before, int count) {
	                 // TODO Auto-generated method stub
	                 if(ed_std_code.length()==5)
	                 {
	                    
	                     ed_std_code.clearFocus();
	                     ed_phone_num.requestFocus();
	                     ed_phone_num.setCursorVisible(true);
	                  

	                 }
	             }
	             @Override
	             public void beforeTextChanged(CharSequence s, int start, int count,
	                     int after) {

	            	
	                
	             }

	           

				@Override
				public void afterTextChanged(Editable s) {
					// TODO Auto-generated method stub
					

				}
	         });
		return etPhone_view;
	}
	private LinearLayout getLinearLayoutVertical(TextView tv,String AnsControlType,String AsnControlInputTypeID,int AnsControlInputTypeMaxLength,String answerHint,String quesId,String questGroupId,ArrayList<String> listOptionVal) {
		LinearLayout lay = new LinearLayout(getActivity());
		
		lay.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		/*lay.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT),);*/
	
		lay.setOrientation(LinearLayout.VERTICAL);
		lay.addView(tv);
	//	lay.setPadding(16, 0, 16, 0);
		lay.setTag(questGroupId+"ll");
		if(AnsControlType.equals("14"))
		{
			lay.removeAllViews();
			LinearLayout layHor = new LinearLayout(getActivity());
			layHor.removeAllViews();
			layHor.setOrientation(LinearLayout.HORIZONTAL);
			LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 0.3f);

			tv.setLayoutParams(layoutParams1);
			LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 0.7f);
			layoutParams2.setMargins(10, 0, 0, 0);
			textQntty.setLayoutParams(layoutParams2);

			layHor.addView(tv);
			layHor.addView(textQntty);
			lay.addView(layHor);

		}
		if(AnsControlType.equals("12"))
		{
			lay.removeAllViews();
			LinearLayout layHor = new LinearLayout(getActivity());
			layHor.removeAllViews();
			layHor.setOrientation(LinearLayout.HORIZONTAL);
			LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f); 
			
			textStCode.setLayoutParams(layoutParams1);
			LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f);
			layoutParams2.setMargins(10, 0, 0, 0);
			tv.setLayoutParams(layoutParams2);
			
			layHor.addView(textStCode);
			layHor.addView(tv);
			lay.addView(layHor);
		}
	
		
		
		if(AnsControlType.equals("2") || AnsControlType.equals("18"))
		{
			 if(AsnControlInputTypeID.equals("6"))
			{
				 lay.addView(getCalenderView("Calender",quesId+"^"+AnsControlType+"^"+questGroupId));
			
			}
			 else
			 {
				
				 lay.addView(getEditTextView(true,AnsControlInputTypeMaxLength, quesId+"^"+AnsControlType+"^"+questGroupId, answerHint, AsnControlInputTypeID)); 
			 }
			
		}
		else if(AnsControlType.equals("3"))
		{
			
			lay.addView(getEditTextView(false, AnsControlInputTypeMaxLength, quesId+"^"+AnsControlType+"^"+questGroupId, answerHint, AsnControlInputTypeID));
		}
		else if(AnsControlType.equals("4"))
		{
			lay.addView(getCheckBoxView(listOptionVal, true, quesId+"^"+AnsControlType+"^"+questGroupId));
		}
		else if(AnsControlType.equals("5"))
		{
			lay.addView(getCheckBoxView(listOptionVal, false, quesId+"^"+AnsControlType+"^"+questGroupId));
		}
		else if(AnsControlType.equals("6"))
		{
			boolean isParentDpndQues=false;
			if(Arrays.asList(dependentParentQuesId).contains(questGroupId))
			{
				isParentDpndQues=true;
			}
				lay.addView(getRadioView(listOptionVal, quesId+"^"+AnsControlType+"^"+questGroupId, isParentDpndQues));
			

		}
		else if(AnsControlType.equals("7") || AnsControlType.equals("8"))
		{
			
			String[] SpinnerValWithOther=null;//;
			if(listOptionVal!=null && listOptionVal.size()>0)
			{
				if(AnsControlType.equals("8"))
				{
					SpinnerValWithOther=new String[listOptionVal.size()+1];
					for(int index=0;index<listOptionVal.size();index++)
					{
						if(index==0)
						{
							SpinnerValWithOther[0]="Select";
						}
						hmapOptionId.put(questGroupId+"^"+listOptionVal.get(index).split(Pattern.quote("^"))[1].toString(), listOptionVal.get(index).split(Pattern.quote("^"))[0].toString());
						SpinnerValWithOther[index+1]=listOptionVal.get(index).split(Pattern.quote("^"))[1].toString();
					}
				}
				else if(AnsControlType.equals("7"))
				{

					SpinnerValWithOther=new String[listOptionVal.size()+2];
					for(int index=0;index<listOptionVal.size();index++)
					{
						if(index==0)
						{
							SpinnerValWithOther[0]="Select";
						}
						
							hmapOptionId.put(questGroupId+"^"+listOptionVal.get(index).split(Pattern.quote("^"))[1].toString(), listOptionVal.get(index).split(Pattern.quote("^"))[0].toString());
							SpinnerValWithOther[index+1]=listOptionVal.get(index).split(Pattern.quote("^"))[1].toString();
						
						
						if(index==(listOptionVal.size()-1))
						{
							SpinnerValWithOther[index+2]="Others";
						}
					}
					
				}
					
			}
			else
			{
		/*		if(Arrays.asList(dependentValuesQuesId).contains(quesId))
				{
		*/			SpinnerValWithOther=new String[1];
					SpinnerValWithOther[0]="Select";
		//		}
			}
			
			boolean isParentDpndQues=false;
			
			if(Arrays.asList(dependentParentQuesId).contains(questGroupId) )
			{
				isParentDpndQues=true;
			}
			
				lay.addView(getSpinnerView(SpinnerValWithOther, quesId+"^"+AnsControlType+"^"+questGroupId, isParentDpndQues,tv.getText().toString()));
				if(AnsControlType.equals("7"))
				{
					View viewSpinnerEditText=getEditTextView(true,200, quesId+"^"+AnsControlType+"^"+questGroupId+"_ed", "Type Here", "3");
					LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)lay.getLayoutParams();
					   params.setMargins(0, 3, 0, 0); 
					   lay.setLayoutParams(params);
					lay.addView(viewSpinnerEditText);
					
					viewSpinnerEditText.setVisibility(View.GONE);
				}
				
			
		
		}
		else if(AnsControlType.equals("10"))
		{
			lay.addView(getCameraView(quesId+"^"+AnsControlType+"^"+questGroupId));
			lay.addView(getHorizontalScrollView(quesId+"^"+AnsControlType+"^"+questGroupId+"_camera"));
			
		}
		
		else if(AnsControlType.equals("11"))
		{
			lay.addView(getCalenderView("Calender", quesId+"^"+AnsControlType+"^"+questGroupId));
		}
		
		else if(AnsControlType.equals("12"))
		{
			lay.addView(getEditTextPhoneNumView(AnsControlInputTypeMaxLength, quesId+"^"+AnsControlType+"^"+questGroupId, answerHint));
		}
		else if(AnsControlType.equals("13"))
		{
			//listOptionVal
			lay.addView(getSearchEditTextView(true,Integer.valueOf(AnsControlInputTypeMaxLength), quesId+"^"+AnsControlType+"^"+questGroupId, answerHint, AsnControlInputTypeID,listOptionVal));
		}
		else if(AnsControlType.equals("14"))
		{
			
			lay.addView(getCheckBoxViewWithEditText(listOptionVal, false, quesId+"^"+AnsControlType+"^"+questGroupId,AsnControlInputTypeID,AnsControlInputTypeMaxLength));
		}
		else if(AnsControlType.equals("15") || AnsControlType.equals("16"))
		{
			//listOptionVal
			lay.addView(getSearchEditTextView(true,Integer.valueOf(AnsControlInputTypeMaxLength), quesId+"^"+AnsControlType+"^"+questGroupId, answerHint, AsnControlInputTypeID,listOptionVal));
			if(AnsControlType.equals("16"))
			{
				View viewSpinnerEditText=getEditTextView(true,200, quesId+"^"+AnsControlType+"^"+questGroupId+"_ed", "Type Here", "3");
				LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)lay.getLayoutParams();
				   params.setMargins(0, 3, 0, 0); 
				   lay.setLayoutParams(params);
				lay.addView(viewSpinnerEditText);
				
				viewSpinnerEditText.setVisibility(View.GONE);
			}
		}
	
	
		
		return lay;

	}
	
	private LinearLayout getLinearLayoutHorizontal(TextView tv,String AnsControlType,String AsnControlInputTypeID,int AnsControlInputTypeMaxLength,String answerHint,String quesId,String questGroupId,ArrayList<String> listOptionVal) {
		LinearLayout lay = new LinearLayout(getActivity());

		lay.setOrientation(LinearLayout.HORIZONTAL);
		
		lay.addView(tv);
		lay.setTag(questGroupId+"ll");
		if(AnsControlType.equals("2"))
		{
			 
			lay.addView(getEditTextView(true,AnsControlInputTypeMaxLength, quesId+"^"+AnsControlType+"^"+questGroupId, answerHint, AsnControlInputTypeID));
		}
		else if(AnsControlType.equals("3"))
		{
		
			lay.addView(getEditTextView(false, AnsControlInputTypeMaxLength, quesId+"^"+AnsControlType+"^"+questGroupId, answerHint, AsnControlInputTypeID));
		}
		else if(AnsControlType.equals("5"))
		{
			lay.addView(getCheckBoxView(listOptionVal, false, quesId+"^"+AnsControlType+"^"+questGroupId));
		}
		else if(AnsControlType.equals("6"))
		{
			boolean isParentDpndQues=false;
			if(Arrays.asList(dependentParentQuesId).contains(questGroupId))
			{
				isParentDpndQues=true;
			}
				lay.addView(getRadioView(listOptionVal, quesId+"^"+AnsControlType+"^"+questGroupId, isParentDpndQues));


		}
		else if(AnsControlType.equals("7") || AnsControlType.equals("8"))
		{

			String[] SpinnerValWithOther=null;//;
			if(listOptionVal!=null && listOptionVal.size()>0)
			{
				if(AnsControlType.equals("8"))
				{
					SpinnerValWithOther=new String[listOptionVal.size()+1];
					for(int index=0;index<listOptionVal.size();index++)
					{
						if(index==0)
						{
							SpinnerValWithOther[0]="Select";
						}
						hmapOptionId.put(questGroupId+"^"+listOptionVal.get(index).split(Pattern.quote("^"))[1].toString(), listOptionVal.get(index).split(Pattern.quote("^"))[0].toString());
						SpinnerValWithOther[index+1]=listOptionVal.get(index).split(Pattern.quote("^"))[1].toString();
					}
				}
				else if(AnsControlType.equals("7"))
				{

					SpinnerValWithOther=new String[listOptionVal.size()+2];
					for(int index=0;index<listOptionVal.size();index++)
					{
						if(index==0)
						{
							SpinnerValWithOther[0]="Select";
						}
						
							hmapOptionId.put(questGroupId+"^"+listOptionVal.get(index).split(Pattern.quote("^"))[1].toString(), listOptionVal.get(index).split(Pattern.quote("^"))[0].toString());
							SpinnerValWithOther[index+1]=listOptionVal.get(index).split(Pattern.quote("^"))[1].toString();
						
						
						if(index==(listOptionVal.size()-1))
						{
							SpinnerValWithOther[index+2]="Others";
						}
					}

				}

			}
			else
			{
				if(dependentValuesQuesId!=null && dependentValuesQuesId.length>0)
				{
					if(Arrays.asList(dependentValuesQuesId).contains(quesId))
					{
						SpinnerValWithOther=new String[1];
						SpinnerValWithOther[0]="Select";
					}
				}

			}
			
			boolean isParentDpndQues=false;
			if(Arrays.asList(dependentParentQuesId).contains(questGroupId))
			{
				isParentDpndQues=true;
			}
		
			
			
			
				lay.addView(getSpinnerView(SpinnerValWithOther, quesId+"^"+AnsControlType+"^"+questGroupId, isParentDpndQues,tv.getText().toString()));
				if(AnsControlType.equals("7"))
				{
					View viewSpinnerEditText=getEditTextView(true,200, quesId+"^"+AnsControlType+"^"+questGroupId+"_ed", "Type Here", "3");
					LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)lay.getLayoutParams();
					   params.setMargins(0, 3, 0, 0); 
					   lay.setLayoutParams(params);
					lay.addView(viewSpinnerEditText);
					
					viewSpinnerEditText.setVisibility(View.GONE);
				}
				
			
		
		}

		else if(AnsControlType.equals("11"))
		{
			lay.addView(getCalenderView("Calender", quesId+"^"+AnsControlType+"^"+questGroupId));
		}
		
		else if(AnsControlType.equals("12"))
		{
			lay.addView(getEditTextPhoneNumView(AnsControlInputTypeMaxLength, quesId+"^"+AnsControlType+"^"+questGroupId, answerHint));
		}

		return lay;

	}
	private LinearLayout getLinearLayoutHorizontal2Ques(LinearLayout lay1,LinearLayout lay2) {
		LinearLayout lay = new LinearLayout(getActivity());
	
		lay.setOrientation(LinearLayout.HORIZONTAL);
		LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f); 
		lay1.setLayoutParams(layoutParams1);
		LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f);
		layoutParams2.setMargins(10, 0, 0, 0);
		lay2.setLayoutParams(layoutParams2);
		lay.addView(lay1);
		lay.addView(lay2);
		

		return lay;

	}
	
public SpannableStringBuilder textWithMandatory(String text_Value)
	
	{
		String simple = text_Value;
		String colored = "*";
		SpannableStringBuilder builder = new SpannableStringBuilder();

		builder.append(simple);
		int start = builder.length();
		builder.append(colored);
		int end = builder.length();

		builder.setSpan(new ForegroundColorSpan(Color.RED), start, end, 
		                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

		//text.setText(builder);
		
		return builder;

	}
	
private InputFilter filter = new InputFilter() {

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
    	  if(source.equals("")){ // for backspace
              return source;
         }
         if(source.toString().matches("[a-zA-Z ]+")){
              return source;
         }
         return "";
    	
    }
};

/*@Override
public void selectedOption(String optId, String optionVal, EditText editext,ListView listViewOption,String tagVal,Dialog dialog,TextView textView) {
	hmapsearchAnswerSlctd.put(tagVal, optId);
	editext.setText(optionVal);
	isSelectedSearch=true;
	textView.setText(optionVal);
	dialog.dismiss();
	listViewOption.setVisibility(View.GONE);
	
	
}*/


@Override
public void onSaveInstanceState(Bundle outState) {
	// TODO Auto-generated method stub
	super.onSaveInstanceState(outState);
	if(imageF!=null)
	{
			CommonInfo.imageF_savedInstance=null;
			CommonInfo.imageName_savedInstance=null;
			CommonInfo.clickedTagPhoto_savedInstance=null;
			CommonInfo.uriSavedImage_savedInstance=null;

			outState.putString("imageClkdPath", imageF.getAbsolutePath());
			outState.putString("imageName", imageName);
			outState.putString("clickedTagPhoto", clickedTagPhoto);
			outState.putString("uriSavedImage", uriSavedImage.toString());

			CommonInfo.imageF_savedInstance=imageF;
			CommonInfo.imageName_savedInstance=imageName;
			CommonInfo.clickedTagPhoto_savedInstance=clickedTagPhoto;
			CommonInfo.uriSavedImage_savedInstance=uriSavedImage;
	}
	

	
	
}



		@Override
		public void onActivityResult(int requestCode, int resultCode, Intent data) {
			// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);

			imageF=CommonInfo.imageF_savedInstance;
		imageName=CommonInfo.imageName_savedInstance;
		clickedTagPhoto=CommonInfo.clickedTagPhoto_savedInstance;
		uriSavedImage= CommonInfo.uriSavedImage_savedInstance;

		
		
			 if(requestCode == requestCode && resultCode == getActivity().RESULT_OK){
				 
					
					 if(imageF!=null && imageF.getAbsolutePath()!=null)
					 {
						 File file=imageF;
			         	System.out.println("File +++"+imageF);

			            Bitmap bmp = decodeSampledBitmapFromFile(file.getAbsolutePath(), 80, 80);

			            ByteArrayOutputStream stream = new ByteArrayOutputStream();

			               bmp.compress(Bitmap.CompressFormat.JPEG, 70, stream);
			               byte[] byteArray = stream.toByteArray();

			               // Convert ByteArray to Bitmap::\
			               //
			               long syncTIMESTAMP = System.currentTimeMillis();
							Date dateobj = new Date(syncTIMESTAMP);
							SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
							String clkdTime = df.format(dateobj);
						//	String valueOfKey=imagButtonTag+"~"+tempId+"~"+file.getAbsolutePath()+"~"+clkdTime+"~"+"2";
							String valueOfKey=clickedTagPhoto+"~"+AddNewStore_DynamicSectionWise.selStoreID+"~"+uriSavedImage.toString()+"~"+clkdTime+"~"+"1";
			            //   helperDb.insertImageInfo(tempId,imagButtonTag, imageName, file.getAbsolutePath(), 2);
			               Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
			                       byteArray.length);

			              // 
			               setSavedImageToScrollView(bitmap, imageName,valueOfKey,clickedTagPhoto);
				 }
					 


				 else
				 {
					// Context context=this;
					// Toast.makeText(getActivity(),"Please click another photo,this photo is too heavy to load",Toast.LENGTH_SHORT).show();

					// make alert

					 
						 AlertDialog.Builder alertDialogSyncOK = new AlertDialog.Builder(getActivity());
						 alertDialogSyncOK.setTitle("Alert");
						 alertDialogSyncOK.setCancelable(false);
						 alertDialogSyncOK.setMessage("Please click another photo,this photo is too heavy to load");


						 alertDialogSyncOK.setNeutralButton("OK",new DialogInterface.OnClickListener()
								 {
									 public void onClick(DialogInterface dialog, int which)
									 {
                                       dialog.dismiss();
									 }
								 });
						 alertDialogSyncOK.setIcon(R.drawable.info_ico);

						 AlertDialog alert = alertDialogSyncOK.create();
						 alert.show();

					 

				 }
				
/*
             ImageView image = new ImageView(getActivity());
            
               //btn.setLayoutParams(new android.view.ViewGroup.LayoutParams(80,60));
             
               
               image.setImageBitmap(bitmap);
               LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(90, 90);
               params.setMargins(10,0,10,0);
              
               ll_Image.addView(image,params);*/
               }
		}
		public void saveImage()
		{
			long syncTIMESTAMP = System.currentTimeMillis();
			   Date datefromat = new Date(syncTIMESTAMP);
			   SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
			   onlyDate=df.format(datefromat);
			   onlyDate=onlyDate.replace(":","").trim().replace("-", "").replace(" ","").trim().toString();
			   File imagesFolder = new File(Environment.getExternalStorageDirectory(), CommonInfo.RSPLImagesnFolder);
				if (!imagesFolder.exists()) 
				{
					 imagesFolder.mkdirs();
				}
				imageName=onlyDate+".jpg";
				imageF = new File(imagesFolder,imageName);
			
				try {
					FileOutputStream fo = new FileOutputStream(imageF);
					
				/*	fo.write(bmp);
					fo.close();*/
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
	            uriSavedImage = Uri.fromFile(imageF);
	           
	                          
	       
		}
		
		
		 private boolean isValidEmail(String email) {
				String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
						+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

				Pattern pattern = Pattern.compile(EMAIL_PATTERN);
				Matcher matcher = pattern.matcher(email);
				return matcher.matches();
			}
		
		public void showErrorAlert(String message) 
		{
		 AlertDialog.Builder alertDialogNoConn = new AlertDialog.Builder(getActivity());
		 alertDialogNoConn.setTitle("Error");
		 alertDialogNoConn.setMessage(message);
		 alertDialogNoConn.setNeutralButton("OK",
		   new DialogInterface.OnClickListener() 
		           {
		     public void onClick(DialogInterface dialog, int which) 
		     {
		                        dialog.dismiss();
		                       // finish();
		                    }
		     });
		
		 AlertDialog alert = alertDialogNoConn.create();
		 alert.show();
		 
		}
		
		
		
		/* public String genTempID()
			{
				//store ID generation <x>
				
				String cxz;
						cxz = UUID.randomUUID().toString();
						cxz.split("^([^-]*,[^-]*,[^-]*,[^-]*),(.*)$");
						//System.out.println("cxz (BEFORE split): "+cxz);
						StringTokenizer tokens = new StringTokenizer(String.valueOf(cxz), "-");
						
						String val1 = tokens.nextToken().trim();
						String val2 = tokens.nextToken().trim();
						String val3 = tokens.nextToken().trim();
						String val4 = tokens.nextToken().trim();
						cxz = tokens.nextToken().trim();
						
						//System.out.println("cxz (AFTER split): "+cxz);
						
						TelephonyManager tManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
						String imei = tManager.getDeviceId();
						String IMEIid =  imei.substring(9);
						
						cxz = IMEIid +"-"+cxz+"-"+VisitStartTS.replace(" ", "").replace(":", "").trim();
						
						
						return cxz;
						//-_
			}
*/




	public void setSavedImageToScrollView(Bitmap bitmap,String imageValidName,String valueOfKey,final String tagOfClkdPic){
	    	//clickedTagPhoto
	    	
	    	 LayoutInflater inflater=(LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	           View viewStoreLocDetail=inflater.inflate(R.layout.store_loc_display,null);
	         
	           
	           final RelativeLayout rl_photo=(RelativeLayout) viewStoreLocDetail.findViewById(R.id.rl_photo);
	          final ImageView img_thumbnail=(ImageView)viewStoreLocDetail.findViewById(R.id.img_thumbnail);
	          img_thumbnail.setImageBitmap(bitmap);
	          img_thumbnail.setTag(imageValidName);
			 if(hmapisPhotolicked!=null)
			 {
				 if(hmapisPhotolicked.containsKey(tagOfClkdPic))
				 {
					 int count=hmapisPhotolicked.get(tagOfClkdPic);
					 count=count+1;
					 hmapisPhotolicked.put(tagOfClkdPic,count);
				 }
				 else
				 {
					 hmapisPhotolicked.put(tagOfClkdPic,1);
				 }
			 }
			 else
			 {
				 hmapisPhotolicked.put(tagOfClkdPic,1);
			 }

			 hmapImageClkdTempIdData.put(imageValidName,valueOfKey );
	          final ImageView imgCncl=(ImageView) viewStoreLocDetail.findViewById(R.id.imgCncl);
	        
	         img_thumbnail.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View view) {
						Intent intent = new Intent();
						intent.setAction(Intent.ACTION_VIEW);
						if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
							intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
							String filePathName="";
							if(hmapImageClkdTempIdData.get(view.getTag().toString()).split(Pattern.quote("~"))[2].contains("file:")){
								filePathName=hmapImageClkdTempIdData.get(view.getTag().toString()).split(Pattern.quote("~"))[2].replace("file:","");
							}
							else {
								filePathName=hmapImageClkdTempIdData.get(view.getTag().toString()).split(Pattern.quote("~"))[2];
							}
							File file = new File(filePathName);
							Uri intentUri = FileProvider.getUriForFile(getActivity().getBaseContext(), getActivity().getApplicationContext().getPackageName() + ".provider", file);
							intent.setDataAndType(intentUri, "image/*");
							startActivity(intent);

						}
						else{
							Uri intentUri = Uri.parse(hmapImageClkdTempIdData.get(view.getTag().toString()).split(Pattern.quote("~"))[2]);
							intent.setDataAndType(intentUri, "image/*");
							startActivity(intent);
						}


					}
				});
	          
	          imgCncl.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {

						hmapImageClkdTempIdData.remove(img_thumbnail.getTag());
						// dbegine.deleteImageData(img_thumbnail.getTag().toString(),storeId);
						if(hmapisPhotolicked!=null)
						{
							if(hmapisPhotolicked.containsKey(tagOfClkdPic))
							{
								int count=hmapisPhotolicked.get(tagOfClkdPic);
								count=count-1;
								hmapisPhotolicked.put(tagOfClkdPic,count);
							}

						}
						helperDb.deleteImageData(img_thumbnail.getTag().toString(),AddNewStore_DynamicSectionWise.selStoreID);
						 String file_dj_path = Environment.getExternalStorageDirectory() + "/" + CommonInfo.RSPLImagesnFolder + "/" +img_thumbnail.getTag().toString();
					        File fdelete = new File(file_dj_path);
					        if (fdelete.exists()) {
					            if (fdelete.delete()) {
					               
					                callBroadCast();
					            } else {
					               
					            }
					        }
						ViewGroup parent = (ViewGroup) rl_photo.getParent();
						parent.removeView(rl_photo);
					}
				});
	      

	         LinearLayout ll_ImageToSet= (LinearLayout) ll_data.findViewWithTag(clickedTagPhoto+"_camera");
			if(ll_ImageToSet!=null)
			{
				ll_ImageToSet.addView(viewStoreLocDetail);
			}

	         
	       	   
	       	   
	         
	        
	         
	    }
		 
		 public void callBroadCast() {
		        if (Build.VERSION.SDK_INT >= 14) {
		            Log.e("-->", " >= 14");
		            MediaScannerConnection.scanFile(getActivity(), new String[]{Environment.getExternalStorageDirectory().toString()}, null, new MediaScannerConnection.OnScanCompletedListener() {
		              
		                public void onScanCompleted(String path, Uri uri) {
		                
		                }
		            });
		        } else {
		            Log.e("-->", " < 14");
		            getActivity().sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
		                    Uri.parse("file://" + Environment.getExternalStorageDirectory())));
		        }
		    }
		 
		 public static Bitmap decodeSampledBitmapFromFile(String path, int reqWidth, int reqHeight) 
		 { // BEST QUALITY MATCH
		      
		     //First decode with inJustDecodeBounds=true to check dimensions
		     final BitmapFactory.Options options = new BitmapFactory.Options();
		     options.inJustDecodeBounds = true;
		     BitmapFactory.decodeFile(path, options);
		  
		     // Calculate inSampleSize, Raw height and width of image
		     final int height = options.outHeight;
		     final int width = options.outWidth;
		     options.inPreferredConfig = Bitmap.Config.RGB_565;
		     int inSampleSize = 1;

		     if (height > reqHeight) 
		     {
		         inSampleSize = Math.round((float)height / (float)reqHeight);
		     }
		     int expectedWidth = width / inSampleSize;
		  
		     if (expectedWidth > reqWidth) 
		     {
		         //if(Math.round((float)width / (float)reqWidth) > inSampleSize) // If bigger SampSize..
		         inSampleSize = Math.round((float)width / (float)reqWidth);
		     }

		     options.inSampleSize = inSampleSize;
		  
		     // Decode bitmap with inSampleSize set
		     options.inJustDecodeBounds = false;
		  
		     return BitmapFactory.decodeFile(path, options);
		 }
		 
		 public void hideOrShowGroup(String questGrpId)
		 {
				if(hmapQuestGrpId_GrpId.containsKey(questGrpId))
				{
					String groupId=hmapQuestGrpId_GrpId.get(questGrpId);
					 ArrayList<String> listQuesGroup=AddNewStore_DynamicSectionWise.hmapQuesGropKeySection.get(groupId);
					 // getActivity() loop is for section's group's question show
					 boolean isAllQuestHide=false;
					 for(String questionKeyToShow:listQuesGroup)
					 {
						 String questGroupIdHideOrVisible=questionKeyToShow.split(Pattern.quote("^"))[2];
						 View viewGroup=ll_data.findViewWithTag(questGroupIdHideOrVisible+"ll");
						 if(viewGroup.getVisibility()==View.GONE || viewGroup.getVisibility()==View.INVISIBLE)
							{
							 isAllQuestHide=true;
							}
						 else
						 {
							 isAllQuestHide=false;
							 break;
						 }
					 }
					 if(isAllQuestHide)
					 {
						 LinearLayout textViewGroupHeader=(LinearLayout) ll_data.findViewWithTag(groupId+"_group");
						 if(textViewGroupHeader!=null)
						 {
							 textViewGroupHeader.setVisibility(View.GONE);	 
						 }
						 
					 }
					 else
					 {
						 LinearLayout textViewGroupHeader=(LinearLayout) ll_data.findViewWithTag(groupId+"_group");
						 if(textViewGroupHeader!=null)
						 {
							 textViewGroupHeader.setVisibility(View.VISIBLE);
							 
						 }
					 }
					
				}
		 }
		 public boolean validatecheckboxofAdvance() {

				boolean ckeckbox=false;
				int count = parentOfAdvanceBeforeDeliveryPayMentMode.getChildCount();
				
				 for (int ui=0;ui<count;ui++) 
				 {
					 View ch = parentOfAdvanceBeforeDeliveryPayMentMode.getChildAt(ui);
			            if (ch instanceof CheckBox)
			            {
			            	if(((CompoundButton) ch).isChecked()==true)
							{
			            		ckeckbox=true;
			            		
			            		break;
							}
			            	
			            }
				 }
				 
					 return ckeckbox;		

			}
			
			public boolean validatecheckboxofDelivery() {

				boolean ckeckbox=false;
				int count = parentOfOnDeliveryPayMentMode.getChildCount();
				
				 for (int ui=0;ui<count;ui++) 
				 {
					 View ch = parentOfOnDeliveryPayMentMode.getChildAt(ui);
			            if (ch instanceof CheckBox)
			            {
			            	if(((CompoundButton) ch).isChecked()==true)
							{
			            		ckeckbox=true;
			            		
			            		break;
							}
			            	
			            }
				 }
				 
					 return ckeckbox;		

			}
			public boolean validatecheckboxofCredit() {

				boolean ckeckbox=false;
				int count = parentOfCreditPayMentMode.getChildCount();
				
				 for (int ui=0;ui<count;ui++) 
				 {
					 View ch = parentOfCreditPayMentMode.getChildAt(ui);
			            if (ch instanceof CheckBox)
			            {
			            	if(((CompoundButton) ch).isChecked()==true)
							{
			            		ckeckbox=true;
			            		
			            		break;
							}
			            	
			            }
				 }
				 
					 return ckeckbox;		

			} 
		 
			
			public void saveDataToDataBase() {
				
				getActivity().getWindow().setFlags(LayoutParams.FLAG_NOT_TOUCHABLE, LayoutParams.FLAG_NOT_TOUCHABLE);
				
				String PaymntTermEditString="0";  //CreditLimit
				String SalesQuoteTypeString="0";
				String PaymntTermSpinnerString="0"; //CREDITdays
				
				String storeIDString="0";
				
		    	String PAYMENT_STAGEID_Values="0";
		    	String PaymentModeId="0";
		    	
		    	String paymntStgAdvnc=globalValueOfPaymentStageCheck.split(Pattern.quote("_"))[0];
				
				String paymntStgDelvry=globalValueOfPaymentStageCheck.split(Pattern.quote("_"))[1];
				String paymntStgCrdt=globalValueOfPaymentStageCheck.split(Pattern.quote("_"))[2];
				
				String fullStringOfAdvnc="0";
				String perOfAd="0";
				String checkBoxOfAdvance="0";
				
				if(paymntStgAdvnc.equals("1")){
					if(!percentageOfAdvanceBeforeDelivery.getText().toString().trim().equals("")){
						perOfAd=percentageOfAdvanceBeforeDelivery.getText().toString().trim();
						
					}
					int count = parentOfAdvanceBeforeDeliveryPayMentMode.getChildCount();
					
					 for (int ui=0;ui<count;ui++) 
					 {
						 View ch = parentOfAdvanceBeforeDeliveryPayMentMode.getChildAt(ui);
				            if (ch instanceof CheckBox)
				            {
				            	if(((CompoundButton) ch).isChecked()==true)
								{
				            		
				            		int chkedchkQuestId=Integer.parseInt(ch.getTag().toString().trim().split(Pattern.quote("^"))[0]);
				            		
				            		if(checkBoxOfAdvance.equals("0")){
				            			checkBoxOfAdvance=String.valueOf(chkedchkQuestId);
				            		}
				            		else{
				            			checkBoxOfAdvance=checkBoxOfAdvance+"|"+String.valueOf(chkedchkQuestId);
				            			
				            		}
				            		
								}
				            	
				            }
					 }
					
					
				}
				 fullStringOfAdvnc=paymntStgAdvnc+"~"+perOfAd+"~"+"0"+"~"+"0"+"~"+checkBoxOfAdvance;
				 
				/*String fullStringOfDelivery="0";*/
				 fullStringOfDelivery="0";
				String perOfDelivery="0";
				String checkBoxOfDelivery="0";
				String creditDaysOfDelvry="0";
				
				if(paymntStgDelvry.equals("2")){

					if(!percentageOfOnDelivery.getText().toString().trim().equals("")){
						perOfDelivery=percentageOfOnDelivery.getText().toString().trim();
						
					}
					int count = parentOfOnDeliveryPayMentMode.getChildCount();
					
					 for (int ui=0;ui<count;ui++) 
					 {
						 View ch = parentOfOnDeliveryPayMentMode.getChildAt(ui);
				            if (ch instanceof CheckBox)
				            {
				            	if(((CompoundButton) ch).isChecked()==true)
								{
				            		
				            		int chkedchkQuestId=Integer.parseInt(ch.getTag().toString().trim().split(Pattern.quote("^"))[0]);
				            		
				            		if(checkBoxOfDelivery.equals("0")){
				            			checkBoxOfDelivery=String.valueOf(chkedchkQuestId);
				            		}
				            		else{
				            			checkBoxOfDelivery=checkBoxOfDelivery+"|"+String.valueOf(chkedchkQuestId);
				            			
				            		}
				            		
								}
				            	
				            }
					 }
					 if(creditDaysOfOnDelivery.isEnabled() && !creditDaysOfOnDelivery.getText().toString().trim().equals("")){
						 
						 creditDaysOfDelvry=	 creditDaysOfOnDelivery.getText().toString().trim();
						 
					 }
					
					
				
					
				}
				 fullStringOfDelivery=paymntStgDelvry+"~"+perOfDelivery+"~"+creditDaysOfDelvry+"~"+"0"+"~"+checkBoxOfDelivery;
				 
				String fullStringOfCredit="0";
				String perOfCredit="0";
				String checkBoxOfCredit="0";
				String creditDaysOfCreditString="0";
				String creditLimitOfCreditString="0";
				
				if(paymntStgCrdt.equals("3")){
					


					if(!PercentageOfCredit.getText().toString().trim().equals("")){
						perOfCredit=PercentageOfCredit.getText().toString().trim();
						
					}
					int count = parentOfCreditPayMentMode.getChildCount();
					
					 for (int ui=0;ui<count;ui++) 
					 {
						 View ch = parentOfCreditPayMentMode.getChildAt(ui);
				            if (ch instanceof CheckBox)
				            {
				            	if(((CompoundButton) ch).isChecked()==true)
								{
				            		
				            		int chkedchkQuestId=Integer.parseInt(ch.getTag().toString().trim().split(Pattern.quote("^"))[0]);
				            		
				            		if(checkBoxOfCredit.equals("0")){
				            			checkBoxOfCredit=String.valueOf(chkedchkQuestId);
				            		}
				            		else{
				            			checkBoxOfCredit=checkBoxOfCredit+"|"+String.valueOf(chkedchkQuestId);
				            			
				            		}
				            		
								}
				            	
				            }
					 }
					 if(!creditDaysOfCredit.getText().toString().trim().equals("")){
						 
						 creditDaysOfDelvry=	 creditDaysOfCredit.getText().toString().trim();
						 
					 }
		 if(!creditLimitOfCredit.getText().toString().trim().equals("")){
						 
			 creditLimitOfCreditString=	 creditLimitOfCredit.getText().toString().trim();
						 
					 }
					 

					
				
					
				
					
				}
				 fullStringOfCredit=paymntStgCrdt+"~"+"100"+"~"+creditDaysOfDelvry+"~"+creditLimitOfCreditString+"~"+checkBoxOfCredit;
				
				 PAYMENT_STAGEID_Values=fullStringOfAdvnc+"$"+fullStringOfDelivery+"$"+fullStringOfCredit;
				 
				/// if you want multiple selection again comment these lises
				   if(paymntStgAdvnc.equals("1")){
				    
				    PAYMENT_STAGEID_Values=fullStringOfAdvnc;
				   } 
				   if(paymntStgDelvry.equals("2")){
				    
				    PAYMENT_STAGEID_Values=fullStringOfDelivery;
				   } 
				   if(paymntStgCrdt.equals("3")){
				    
				    PAYMENT_STAGEID_Values=fullStringOfCredit;
				   }
				   // end here
		    	
		    
			 storeIDString=	AddNewStore_DynamicSectionWise.selStoreID;
			
			
			
			helperDb.open();
			helperDb.fndeleteNewStoreSalesQuotePaymentDetails(storeIDString);
			helperDb.fnsaveNewStoreSalesQuotePaymentDetails(storeIDString,PAYMENT_STAGEID_Values);
		
			helperDb.close();
			
			 getActivity().getWindow().clearFlags(LayoutParams.FLAG_NOT_TOUCHABLE);
			
			}
			
			
			public boolean validatePaymentStageID()
		 {
			 boolean returnResult=true;
			 if(globalValueOfPaymentStageCheck.equals("0"+"_"+"0"+"_"+"0")){
					
					paymentstagetextviewNew.clearFocus();
					paymentstagetextviewNew.requestFocus();
					
					/*String estring = "Please Select Payment Stage";
					   ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.BLACK);
					   SpannableStringBuilder ssbuilder = new SpannableStringBuilder(estring);
					   ssbuilder.setSpan(fgcspan, 0, estring.length(), 0);
					   paymentstagetextviewNew.setError(ssbuilder);*/
					paymentstagetextviewNew.setError("Please Select Payment Stage");
					returnResult= false;
					
				}
				/*else if(!validatePercentage()){
					percentageOfAdvanceBeforeDelivery.clearFocus();

					percentageOfOnDelivery.clearFocus();
					creditDaysOfOnDelivery.clearFocus();

					PercentageOfCredit.clearFocus();
					creditDaysOfCredit.clearFocus();
					creditLimitOfCredit.clearFocus();
					
					percentageTextviewNew.clearFocus();
					percentageTextviewNew.requestFocus();
					percentageTextviewNew.setError("Percentage is not 100%");  
					
					return false;
					
				}
						
				*/else if(!validatecheckboxofAdvance() && globalValueOfPaymentStageCheck.split(Pattern.quote("_"))[0].equals("1")){
					paymentModeTextviewNew.clearFocus();
					paymentModeTextviewNew.requestFocus();
					
					/*String estring = "Select Payment Mode";
					   ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.BLACK);
					   SpannableStringBuilder ssbuilder = new SpannableStringBuilder(estring);
					   ssbuilder.setSpan(fgcspan, 0, estring.length(), 0);
					   paymentModeTextviewNew.setError(ssbuilder);*/

					paymentModeTextviewNew.setError("Select Payment Mode");
					
					returnResult= false;
					
					
				}
				else if(!validatecheckboxofDelivery() && globalValueOfPaymentStageCheck.split(Pattern.quote("_"))[1].equals("2")){
					paymentModeTextviewNew.clearFocus();
					paymentModeTextviewNew.requestFocus();
					
					/*String estring = "Select Payment Mode";
					   ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.BLACK);
					   SpannableStringBuilder ssbuilder = new SpannableStringBuilder(estring);
					   ssbuilder.setSpan(fgcspan, 0, estring.length(), 0);
					   paymentModeTextviewNew.setError(ssbuilder);*/
					paymentModeTextviewNew.setError("Select Payment Mode");
					
					returnResult= false;
					
					
				}
				else if(!validatecheckboxofCredit() && globalValueOfPaymentStageCheck.split(Pattern.quote("_"))[2].equals("3")){
					paymentModeTextviewNew.clearFocus();
					paymentModeTextviewNew.requestFocus();
					/*String estring = "Select Payment Mode";
					   ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.BLACK);
					   SpannableStringBuilder ssbuilder = new SpannableStringBuilder(estring);
					   ssbuilder.setSpan(fgcspan, 0, estring.length(), 0);
					   paymentModeTextviewNew.setError(ssbuilder);*/
					paymentModeTextviewNew.setError("Select Payment Mode");
					
					returnResult= false;
					
					
				}
				else if(creditDaysOfOnDelivery.isEnabled() && creditDaysOfOnDelivery.getText().toString().trim().equals("")){
					
					creditdaysTextboxNew.clearFocus();
					creditdaysTextboxNew.requestFocus();
					/*String estring = "Credit days is empty";
					   ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.BLACK);
					   SpannableStringBuilder ssbuilder = new SpannableStringBuilder(estring);
					   ssbuilder.setSpan(fgcspan, 0, estring.length(), 0);
					   creditdaysTextboxNew.setError(ssbuilder);*/
					
					creditdaysTextboxNew.setError("Credit days is empty");
					returnResult= false;
				}
				else if(CreditCheckBoxNew.isChecked() && creditDaysOfCredit.getText().toString().trim().equals("")){
					
					creditdaysTextboxNew.clearFocus();
					creditdaysTextboxNew.requestFocus();
					/*String estring = "Credit days is empty";
					   ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.BLACK);
					   SpannableStringBuilder ssbuilder = new SpannableStringBuilder(estring);
					   ssbuilder.setSpan(fgcspan, 0, estring.length(), 0);
					   creditdaysTextboxNew.setError(ssbuilder);*/
					
					creditdaysTextboxNew.setError("Credit days is empty");
					returnResult= false;
				}
				else if(CreditCheckBoxNew.isChecked() && creditLimitOfCredit.getText().toString().trim().equals("")){
					
					CreditlimitTextboxNew.clearFocus();
					CreditlimitTextboxNew.requestFocus();
					/*String estring = "Credit limit is empty";
					   ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.BLACK);
					   SpannableStringBuilder ssbuilder = new SpannableStringBuilder(estring);
					   ssbuilder.setSpan(fgcspan, 0, estring.length(), 0);
					   CreditlimitTextboxNew.setError(ssbuilder);*/
					CreditlimitTextboxNew.setError("Credit limit is empty");
					returnResult= false;
				}
						
				//else if(creditDaysOfOnDelivery.isEnabled() && creditDaysOfOnDelivery.getText().toString().trim().equals("0")){
					else if(creditDaysOfOnDelivery.isEnabled() && Double.parseDouble(creditDaysOfOnDelivery.getText().toString().trim())==0.0){
					
					creditDaysOfOnDelivery.clearFocus();
					creditdaysTextboxNew.clearFocus();
					creditdaysTextboxNew.requestFocus();
					/*String estring = "Credit days can not be zero.";
					   ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.BLACK);
					   SpannableStringBuilder ssbuilder = new SpannableStringBuilder(estring);
					   ssbuilder.setSpan(fgcspan, 0, estring.length(), 0);
					   creditdaysTextboxNew.setError(ssbuilder);*/
					creditdaysTextboxNew.setError("Credit days can not be zero.");
					returnResult= false;
				}
				else if(CreditCheckBoxNew.isChecked() && Double.parseDouble(creditDaysOfCredit.getText().toString().trim())==0.0){
					
					creditDaysOfCredit.clearFocus();
					creditdaysTextboxNew.clearFocus();
					creditdaysTextboxNew.requestFocus();
					/*String estring = "Credit days can not be zero.";
					   ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.BLACK);
					   SpannableStringBuilder ssbuilder = new SpannableStringBuilder(estring);
					   ssbuilder.setSpan(fgcspan, 0, estring.length(), 0);
					   creditdaysTextboxNew.setError(ssbuilder);*/
					creditdaysTextboxNew.setError("Credit days can not be zero.");
					returnResult= false;
				}
				else if(CreditCheckBoxNew.isChecked() && Double.parseDouble(creditLimitOfCredit.getText().toString().trim())==0.0){
					
					creditLimitOfCredit.clearFocus();
					CreditlimitTextboxNew.clearFocus();
					CreditlimitTextboxNew.requestFocus();
					/*String estring = "Credit limit can not be zero.";
					   ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.BLACK);
					   SpannableStringBuilder ssbuilder = new SpannableStringBuilder(estring);
					   ssbuilder.setSpan(fgcspan, 0, estring.length(), 0);
					   CreditlimitTextboxNew.setError(ssbuilder);*/

					CreditlimitTextboxNew.setError("Credit limit can not be zero.");
					returnResult= false;
				}
			 return returnResult;
		 }
		
			public boolean validate()
			{


				/*boolean isStateCityValidate=fnValidateStateCity();
				if(!isStateCityValidate)
				{
					return isStateCityValidate;
				}*/

				for(Entry<String, Boolean> entry:hmapMustRqrdFiled.entrySet())
				{
					LinearLayout ll_view=(LinearLayout) ll_data.findViewWithTag(entry.getKey().split(Pattern.quote("^"))[2].toString()+"ll");
					ArrayList<String> listKeyMapdWithSection=new ArrayList<String>();
					listKeyMapdWithSection=AddNewStore_DynamicSectionWise.hmapSection_key.get(String.valueOf(sectionIsShown));
					View view=ll_data.findViewWithTag(entry.getKey());
					if(ll_view.getVisibility()==View.VISIBLE && listKeyMapdWithSection.contains(entry.getKey()))
						
					{
						if(view instanceof EditText)
						{
							EditText edVal=(EditText)view;
							
							if(TextUtils.isEmpty(edVal.getText().toString()))
							{
								edVal.requestFocus();
								showErrorAlert("Please Fill "+((TextView)ll_data.findViewWithTag(entry.getKey()+"?")).getText().toString());
								return false;
							}
							else
							{
								//(edVal.getText().toString()).length()!=0
								if(!((AddNewStore_DynamicSectionWise.hmapQuesIdValues.get(entry.getKey())).split(Pattern.quote("^"))[11]).equals("0"))
								{
									if(edVal.getText().toString().length()<Integer.valueOf((AddNewStore_DynamicSectionWise.hmapQuesIdValues.get(entry.getKey())).split(Pattern.quote("^"))[11]))
									{
										edVal.requestFocus();
										showErrorAlert("Please Provide Proper "+((TextView)ll_data.findViewWithTag(entry.getKey()+"?")).getText().toString());
										return false;
									}
									else
									{
										validateNameFilled();
										//hmapMustRqrdFiled.put(entry.getKey(), true);
									}
									
								}
								
								else if(((AddNewStore_DynamicSectionWise.hmapQuesIdValues.get(entry.getKey())).split(Pattern.quote("^"))[5]).equals("5"))
								{
									if(!isValidEmail(edVal.getText().toString()))
									{
										edVal.requestFocus();
										showErrorAlert("Please Provide Proper "+((TextView)ll_data.findViewWithTag(entry.getKey()+"?")).getText().toString());
										return false;
									}
									else
									{
										//hmapMustRqrdFiled.put(entry.getKey(), true);
									}
								}
								else
								{
									//hmapMustRqrdFiled.put(entry.getKey(), true);
								}
							
							}
							
						}
						
						 if(entry.getKey().split(Pattern.quote("^"))[1].toString().equals("4") || entry.getKey().split(Pattern.quote("^"))[1].toString().equals("5"))
						{
							StringBuilder answer=new StringBuilder();
							LinearLayout ll_checkBoxVal=(LinearLayout)view;
							boolean isSelected=false;
							boolean isCheckedOtherClkd=false;
							boolean isEditTextFilled=false;
							for(int i=0;i<ll_checkBoxVal.getChildCount();i++)
							{
								if(ll_checkBoxVal.getChildAt(i) instanceof CheckBox)
								{
									CheckBox checkBoxVal=(CheckBox) ll_checkBoxVal.getChildAt(i);
									if(checkBoxVal.isChecked())
									{
										if(checkBoxVal.getText().toString().equals("Others") || checkBoxVal.getText().toString().equals("Other"))
										{
											isSelected=true;
											isCheckedOtherClkd=true;
											EditText editTextCheckBox=(EditText) ll_data.findViewWithTag(checkBoxVal.getTag().toString()+"_ed");
											if(TextUtils.isEmpty(editTextCheckBox.getText().toString()))
											{
												isEditTextFilled=false;
											
												break;
											}
											else
											{
												isEditTextFilled=true;
											}
										}
										else
										{
											isSelected=true;
											
										}
										
									}
								}
								
								
							}
							if(isSelected)
							{
								if(isCheckedOtherClkd)
								{
									if(!isEditTextFilled)
									{
										showErrorAlert("Please Fill "+((TextView)ll_data.findViewWithTag(entry.getKey()+"?")).getText().toString()+" for Others");
										return false;	
									}
									else
									{
										hmapMustRqrdFiled.put(entry.getKey(), true);
									}
								}
								else
								{
									hmapMustRqrdFiled.put(entry.getKey(), true);
								}
								
							}
							else
							{
								showErrorAlert("Please Select "+((TextView)ll_data.findViewWithTag(entry.getKey()+"?")).getText().toString());
								return false;
							}
						
						}
						
						 if(view instanceof RadioGroup)
						{
							RadioGroup radioGroupSelected=(RadioGroup) view;
							 int selectedId = radioGroupSelected.getCheckedRadioButtonId();

							 if (selectedId == -1)
							 {
							   // no radio buttons are checked
								 showErrorAlert("Please Select "+((TextView)ll_data.findViewWithTag(entry.getKey()+"?")).getText().toString());
									return false;
								 
							 }
							 else
							 {
							   // one of the radio buttons is checked
								 hmapMustRqrdFiled.put(entry.getKey(), true);
							 }
						}
						 
						
						 if(view instanceof Spinner)
						{
							Spinner spinnerSelected=(Spinner) view;
							 
								if(spinnerSelected.getSelectedItem().toString().equals("Select"))
								{
									showErrorAlert("Please Select "+((TextView)ll_data.findViewWithTag(entry.getKey()+"?")).getText().toString());
									return false;
								}
								else if(entry.getKey().split(Pattern.quote("^"))[1].toString().equals("7"))
								{
									if(spinnerSelected.getSelectedItem().toString().equals("Others"))
									{
										EditText editText=(EditText) ll_data.findViewWithTag(entry.getKey().toString()+"_ed");
										if(TextUtils.isEmpty(editText.getText().toString()))
										{
											showErrorAlert("Please Fill "+((TextView)ll_data.findViewWithTag(entry.getKey()+"?")).getText().toString());
											return false;	
										}
									}
									
								}
								else
								{
									 hmapMustRqrdFiled.put(entry.getKey(), true);
								}
					          
					           
					         
					            
						}
					 if(entry.getKey().split(Pattern.quote("^"))[1].toString().equals("10"))
							{
								if(hmapisPhotolicked!=null && hmapisPhotolicked.size()>0)
								{
									if(hmapisPhotolicked.containsKey(entry.getKey()))
									{
										if(hmapisPhotolicked.get(entry.getKey())>0)
										{

										}
										else
										{
											showErrorAlert("Please click atleast one photo of "+((TextView)ll_data.findViewWithTag(entry.getKey()+"?")).getText().toString());
											return false;
										}
									}
									else
									{
										showErrorAlert("Please click atleast one photo of "+((TextView)ll_data.findViewWithTag(entry.getKey()+"?")).getText().toString());
										return false;
									}
								}
								else
								{
									showErrorAlert("Please click atleast one photo of "+((TextView)ll_data.findViewWithTag(entry.getKey()+"?")).getText().toString());
									return false;
								}
							}
					 if(entry.getKey().split(Pattern.quote("^"))[1].toString().equals("11"))
					 {
						 Button buttonCalender=(Button) ll_data.findViewWithTag(entry.getKey());
						 if(buttonCalender.getText().toString().equals("Select Date"))
						 {
							 showErrorAlert("Please Select "+((TextView)ll_data.findViewWithTag(entry.getKey()+"?")).getText().toString());
								return false;
						 }
					 }
						 if(entry.getKey().split(Pattern.quote("^"))[1].toString().equals("13"))
						 {
							 TextView textView=(TextView) ll_data.findViewWithTag(entry.getKey());
							 if(textView.getText().toString().equals("Select"))
							 {
								 showErrorAlert("Please Select "+((TextView)ll_data.findViewWithTag(entry.getKey()+"?")).getText().toString());
									return false;
							 }
						 }
						 if(entry.getKey().split(Pattern.quote("^"))[1].toString().equals("15"))
						 {
							 TextView textView=(TextView) ll_data.findViewWithTag(entry.getKey());
							 if(textView.getText().toString().equals("Select"))
							 {
								 showErrorAlert("Please Select "+((TextView)ll_data.findViewWithTag(entry.getKey()+"?")).getText().toString());
									return false;
							 }
						 }
						 
						 
						 if(entry.getKey().split(Pattern.quote("^"))[1].toString().equals("16"))
						 {
							 TextView textView=(TextView) ll_data.findViewWithTag(entry.getKey());
							 if(textView.getText().toString().equals("Select"))
							 {
								 showErrorAlert("Please Select "+((TextView)ll_data.findViewWithTag(entry.getKey()+"?")).getText().toString());
								 return false;
							 }
							 else if(textView.getText().toString().contains("Others")||textView.getText().toString().contains("Other"))
							 {
								 EditText edText=(EditText) ll_data.findViewWithTag(entry.getKey()+"_ed");
									if(TextUtils.isEmpty(edText.getText().toString()))
									{
										 showErrorAlert("Please Fill "+((TextView)ll_data.findViewWithTag(entry.getKey()+"?")).getText().toString());
										 return false;
									}
							 }
						 }
						
						 if(view instanceof LinearLayout)
						{
							if(entry.getKey().split(Pattern.quote("^"))[1].toString().equals("12"))
							{
								boolean errorAlert=false;
								LinearLayout ll_landline_phone=(LinearLayout) view;
								int totalLengthOfPhn=0;
								for(int i=0;i<ll_landline_phone.getChildCount();i++)
								{
									EditText edVal=(EditText) ll_landline_phone.getChildAt(i);
									if(TextUtils.isEmpty(edVal.getText().toString()))
									{
										errorAlert=true;
									}	
									else if(!((AddNewStore_DynamicSectionWise.hmapQuesIdValues.get(entry.getKey())).split(Pattern.quote("^"))[11]).equals("0"))
									{
										totalLengthOfPhn=edVal.getText().toString().length()+totalLengthOfPhn;
										
										
									}
								}
								
								if(errorAlert)
								{
									
									showErrorAlert("Please Fill STD code And Landline No.");
									return false;
								}
								else
								{
									if(!((AddNewStore_DynamicSectionWise.hmapQuesIdValues.get(entry.getKey())).split(Pattern.quote("^"))[11]).equals("0"))
									{
										//11= MinLength 6= MaxLength
										if(totalLengthOfPhn<Integer.valueOf((AddNewStore_DynamicSectionWise.hmapQuesIdValues.get(entry.getKey())).split(Pattern.quote("^"))[11]) || totalLengthOfPhn>Integer.valueOf((AddNewStore_DynamicSectionWise.hmapQuesIdValues.get(entry.getKey())).split(Pattern.quote("^"))[6]))
										{
											showErrorAlert("Please Provide Proper STD code And Landline No.");
											return false;
										}
									}
									
								}
							
							}
							
							
							if(entry.getKey().split(Pattern.quote("^"))[1].toString().equals("14"))
							{

								StringBuilder answer=new StringBuilder();
								LinearLayout ll_checkBoxVal=(LinearLayout)view;
								ArrayList<String> listTagChckBox=new ArrayList<String>();
								boolean isSelected=false;
								boolean isEditTextFilled=false;
								for(int i=0;i<ll_checkBoxVal.getChildCount();i++)
								{
									LinearLayout checkBoxVal_ll=(LinearLayout) ll_checkBoxVal.getChildAt(i);
									CheckBox chckBox=(CheckBox) checkBoxVal_ll.getChildAt(0);
									
									if(chckBox.isChecked())
									{
										EditText ediText=(EditText) checkBoxVal_ll.getChildAt(1);
										isSelected=true;
										String tagChckBox=chckBox.getTag().toString();
										String inputType=ediText.getTag().toString();
										String edtTextVal=ediText.getText().toString();
										
											if(TextUtils.isEmpty(edtTextVal))
											{
												ediText.requestFocus();
												isEditTextFilled=false;
												break;
											}
											else
											{
												isEditTextFilled=true;
											}
									
										
									}
									
								}
								if(isSelected)
								{
									if(isEditTextFilled)
									{
										hmapMustRqrdFiled.put(entry.getKey(), true);
									}
									else
									{
										showErrorAlert("Please Fill "+((TextView)ll_data.findViewWithTag(entry.getKey()+"?")).getText().toString());
										return false;
									}
								
									
								}
								
								else
								{
									showErrorAlert("Please Select "+((TextView)ll_data.findViewWithTag(entry.getKey()+"?")).getText().toString());
									return false;
								}
							
							
							}
							
							
						}
						
						
					}
					
				}
				for(Entry<String, String> entry:hmapOptionalFiled.entrySet())
				{
					LinearLayout ll_view=(LinearLayout) ll_data.findViewWithTag(entry.getKey().split(Pattern.quote("^"))[2].toString()+"ll");
					ArrayList<String> listKeyMapdWithSection=new ArrayList<String>();
					listKeyMapdWithSection=AddNewStore_DynamicSectionWise.hmapSection_key.get(String.valueOf(sectionIsShown));
					
					View view=ll_data.findViewWithTag(entry.getKey());
					if(ll_view.getVisibility()==View.VISIBLE && listKeyMapdWithSection.contains(entry.getKey()))
					{
					
						if(view instanceof EditText)
						{
							EditText edVal=(EditText)view;
							
							if(!TextUtils.isEmpty(edVal.getText().toString().trim()))
							{
								//(edVal.getText().toString()).length()!=0
								if(!((AddNewStore_DynamicSectionWise.hmapQuesIdValues.get(entry.getKey())).split(Pattern.quote("^"))[11]).equals("0"))
								{
									if(edVal.getText().toString().length()<Integer.valueOf((AddNewStore_DynamicSectionWise.hmapQuesIdValues.get(entry.getKey())).split(Pattern.quote("^"))[11]))
									{
										showErrorAlert("Please Provide Proper "+((TextView)ll_data.findViewWithTag(entry.getKey()+"?")).getText().toString());
										return false;
									}
									else
									{
										//hmapMustRqrdFiled.put(entry.getKey(), true);
									}
									
								}
								else if(hmapOptionalFiled.get(entry.getKey()).equals("Email"))
								{
									if(!isValidEmail(edVal.getText().toString()))
									{
										showErrorAlert("Please Provide Proper "+((TextView)ll_data.findViewWithTag(entry.getKey()+"?")).getText().toString());
										return false;
									}
									else
									{
										
									}
								}
								else
								{
									
								}
							
							
							}
						
							
						}
						
						else if(view instanceof LinearLayout)
						{
							if(entry.getKey().split(Pattern.quote("^"))[1].toString().equals("12"))
							{
								
								LinearLayout ll_landline_phone=(LinearLayout) view;
								int totalLengthOfPhn=0;
								for(int i=0;i<ll_landline_phone.getChildCount();i++)
								{
									EditText edVal=(EditText) ll_landline_phone.getChildAt(i);
									 if(!((AddNewStore_DynamicSectionWise.hmapQuesIdValues.get(entry.getKey())).split(Pattern.quote("^"))[11]).equals("0"))
									{
										totalLengthOfPhn=edVal.getText().toString().length()+totalLengthOfPhn;
										
										
									}
								}
								
								
									if(!((AddNewStore_DynamicSectionWise.hmapQuesIdValues.get(entry.getKey())).split(Pattern.quote("^"))[11]).equals("0"))
									{
										//11= MinLength 6= MaxLength
										if(totalLengthOfPhn>0)
										{
											if(totalLengthOfPhn<Integer.valueOf((AddNewStore_DynamicSectionWise.hmapQuesIdValues.get(entry.getKey())).split(Pattern.quote("^"))[11]) || totalLengthOfPhn>Integer.valueOf((AddNewStore_DynamicSectionWise.hmapQuesIdValues.get(entry.getKey())).split(Pattern.quote("^"))[6]))
											{
												showErrorAlert("Please Provide Proper STD code And Landline No.");
												return false;
											}
										}
										
									}
									
								
							
							}
							
							
						}
						
					}
				}

				if(ll_address_section.getVisibility()==View.VISIBLE)
				{



					if(TextUtils.isEmpty(etPinCode.getText().toString().trim()))
					{
						showErrorAlert("Please Fill Pincode");
						return false;
					}
					else if(etPinCode.getText().toString().trim().length()<6)
					{
						showErrorAlert("Please Fill Proper Pincode");
						return false;
					}

					else if(TextUtils.isEmpty(etCity.getText().toString().trim()))
					{
						showErrorAlert("Please Fill City");
						return false;
					}

					else if(TextUtils.isEmpty(etState.getText().toString().trim()))
					{
						showErrorAlert("Please Fill State");
						return false;
					}

				}
				return true;
			}
		 
		 public void saveDynamicQuesAns(boolean isSaveAsDraft)
		 {

				/*if(ll_address_section.getVisibility()==View.VISIBLE)
				{*/

					if(addressKey!=null)
					{
						EditText edAddress= (EditText) ll_data.findViewWithTag(addressKey);
						if(edAddress!=null)
						{
							if(!TextUtils.isEmpty(edAddress.getText().toString().trim()))
							{
								hmapAddress.put("0",edAddress.getText().toString().trim());
							}
							else
							{
								hmapAddress.put("0","NA");
							}
						}
					}

					else if(!TextUtils.isEmpty(etLocalArea.getText().toString().trim()))
					{
						hmapAddress.put("0",etLocalArea.getText().toString().trim());
					}
					else
					{
						hmapAddress.put("0","NA");
					}
					if(!TextUtils.isEmpty(etPinCode.getText().toString().trim()))
					{
						hmapAddress.put("1",etPinCode.getText().toString().trim());
					}
					else
					{
						hmapAddress.put("1","NA");
					}
					if(!etCity.getText().toString().trim().equals("Select"))
					{
						if((!etCity.getText().toString().trim().equalsIgnoreCase("Other")) && (!etCity.getText().toString().trim().equalsIgnoreCase("Others")) )
						{
							hmapAddress.put("2",etCity.getText().toString().trim());
							hmapAddress.put("4",hmapCity_details.get(etCity.getText().toString().trim()));
						}
						else
						{
							if(etOtherCity.getVisibility()==View.VISIBLE)
							{
								hmapAddress.put("2",etOtherCity.getText().toString().trim());
								hmapAddress.put("4",hmapCity_details.get(etCity.getText().toString().trim()));
							}

						}

					}
					else
					{
						hmapAddress.put("2","NA");
					}
					if(!etState.getText().toString().trim().equals("Select"))
					{
						hmapAddress.put("3",etState.getText().toString().trim());
						hmapAddress.put("5",hmapState_details.get(etState.getText().toString().trim()));
					}
					else
					{
						hmapAddress.put("3","NA");
					}

					////new field added
					if(!AddNewStore_DynamicSectionWise.address.equals("NA"))
					{
						hmapAddress.put("6",AddNewStore_DynamicSectionWise.address);

					}
					else
					{
						hmapAddress.put("6","NA");
					}
					if(!AddNewStore_DynamicSectionWise.city.equals("NA"))
					{
						hmapAddress.put("7",AddNewStore_DynamicSectionWise.city);

					}
					else
					{
						hmapAddress.put("7","NA");
					}
					if(!AddNewStore_DynamicSectionWise.pincode.equals("NA"))
					{
						hmapAddress.put("8",AddNewStore_DynamicSectionWise.pincode);

					}
					else
					{
						hmapAddress.put("8","NA");
					}


					if(!AddNewStore_DynamicSectionWise.state.equals("NA"))
					{
						hmapAddress.put("9",AddNewStore_DynamicSectionWise.state);

					}
					else
					{
						hmapAddress.put("9","NA");
					}

				//}
			
				for(Entry<String, String> entry:AddNewStore_DynamicSectionWise.hmapQuesIdValues.entrySet())
				{
					
						ArrayList<String> tempOutletQuestAnsMstrVal=new ArrayList<String>();
						View ll_view=ll_data.findViewWithTag(entry.getKey().split(Pattern.quote("^"))[2].toString()+"ll");
						View view=ll_data.findViewWithTag(entry.getKey());
						ArrayList<String> listKeyMapdWithSection=new ArrayList<String>();
						listKeyMapdWithSection=AddNewStore_DynamicSectionWise.hmapSection_key.get(String.valueOf(sectionIsShown));
						if(ll_view.getVisibility()==View.VISIBLE && listKeyMapdWithSection.contains(entry.getKey()))
						{
							if(view instanceof EditText)
							{
								EditText edVal=(EditText)view;
								
								
			//saveOutletQuestAnsMstr(String OutletID,String QuestID,String AnswerType,String AnswerValue)
								
								if(!TextUtils.isEmpty(edVal.getText().toString()))
								{
									hmapAnsValues.put(entry.getKey(), TextUtils.htmlEncode(edVal.getText().toString()));
									/*//tempOutletQuestAnsMstrVal.add(0,OutletID);
									tempOutletQuestAnsMstrVal.add(1,entry.getKey().split(Pattern.quote("^"))[0].toString());
									tempOutletQuestAnsMstrVal.add(2,entry.getKey().split(Pattern.quote("^"))[1].toString());
									//tempOutletQuestAnsMstrVal.add(3,edVal.getText().toString());
									 tempOutletQuestAnsMstrVal.add(3,TextUtils.htmlEncode(edVal.getText().toString()));
								*/
								}
								else
								{
								//	hmapAnsValues.put(entry.getKey(), "");
								/*	//tempOutletQuestAnsMstrVal.add(0,OutletID);
									tempOutletQuestAnsMstrVal.add(1,entry.getKey().split(Pattern.quote("^"))[0].toString());
									tempOutletQuestAnsMstrVal.add(2,entry.getKey().split(Pattern.quote("^"))[1].toString());
									tempOutletQuestAnsMstrVal.add(3,"");*/
								}
							
							}
							
							else if(view instanceof Button)
							{
								if(entry.getKey().split(Pattern.quote("^"))[1].toString().equals("11"))
								{

									Button buttonCalender=(Button)view;
									
									
									//saveOutletQuestAnsMstr(String OutletID,String QuestID,String AnswerType,String AnswerValue)
									
									if(!buttonCalender.getText().toString().equals("Select Date"))
									{
										hmapAnsValues.put(entry.getKey(), TextUtils.htmlEncode(buttonCalender.getText().toString()));
										
									}
									/*else
									{
										hmapAnsValues.put(entry.getKey(), "");
										
									}*/
								}
							}
							else if(entry.getKey().split(Pattern.quote("^"))[1].toString().equals("4") || entry.getKey().split(Pattern.quote("^"))[1].toString().equals("5"))
							{
								StringBuilder answer=new StringBuilder();
								LinearLayout ll_checkBoxVal=(LinearLayout)view;
								boolean isSingleSelected=true;
								boolean noValSelected=true;
								for(int i=0;i<ll_checkBoxVal.getChildCount();i++)
								{
									if(ll_checkBoxVal.getChildAt(i) instanceof CheckBox)
									{
										CheckBox checkBoxVal=(CheckBox) ll_checkBoxVal.getChildAt(i);
										String ediTextVal="";
										boolean isOtherSelected=false;
										if(checkBoxVal.isChecked())
										{
											noValSelected=false;
											
											if(checkBoxVal.getText().toString().equals("Others") || checkBoxVal.getText().toString().equals("Other"))
											{
												isOtherSelected=true;
												EditText editTextCheckBox=(EditText) ll_data.findViewWithTag(checkBoxVal.getTag().toString()+"_ed");
												if(!TextUtils.isEmpty(editTextCheckBox.getText().toString()))
												{
													
													ediTextVal=editTextCheckBox.getText().toString();
													
												}
												
											}
											
											if(i==0)
											{
											if(isOtherSelected)
											{
												answer.append(hmapOptionId.get((entry.getKey().split(Pattern.quote("^"))[2])+"^"+checkBoxVal.getText().toString())+"~"+ediTextVal);
											}
											else
											{
												answer.append(hmapOptionId.get((entry.getKey().split(Pattern.quote("^"))[2])+"^"+checkBoxVal.getText().toString()));
											}
												
												
												isSingleSelected=false;
											}
											else
											{
												if(isSingleSelected)
												{
													if(isOtherSelected)
													{
														answer.append(hmapOptionId.get((entry.getKey().split(Pattern.quote("^"))[2])+"^"+checkBoxVal.getText().toString())+"~"+ediTextVal);
													}
													else
													{
														answer.append(hmapOptionId.get((entry.getKey().split(Pattern.quote("^"))[2])+"^"+checkBoxVal.getText().toString()));
													}
													
													isSingleSelected=false;
												}
												else
												{
													if(isOtherSelected)
													{
														answer.append("^").append(hmapOptionId.get((entry.getKey().split(Pattern.quote("^"))[2])+"^"+checkBoxVal.getText().toString())+"~"+ediTextVal);
													}
													else
													{
														answer.append("^").append(hmapOptionId.get((entry.getKey().split(Pattern.quote("^"))[2])+"^"+checkBoxVal.getText().toString()));
													}
													
													
												}
												
											}
										}
									}
									
									
								}
								if(noValSelected)
								{
									//tempOutletQuestAnsMstrVal.add(0,OutletID);
									/*tempOutletQuestAnsMstrVal.add(1,entry.getKey().split(Pattern.quote("^"))[0].toString());
									tempOutletQuestAnsMstrVal.add(2,entry.getKey().split(Pattern.quote("^"))[1].toString());
									tempOutletQuestAnsMstrVal.add(3,"");*/
								//hmapAnsValues.put(entry.getKey(), "");
								}
								else
								{
									
									//tempOutletQuestAnsMstrVal.add(0,OutletID);
								/*	tempOutletQuestAnsMstrVal.add(1,entry.getKey().split(Pattern.quote("^"))[0].toString());
									tempOutletQuestAnsMstrVal.add(2,entry.getKey().split(Pattern.quote("^"))[1].toString());
									tempOutletQuestAnsMstrVal.add(3,answer.toString());*/
									hmapAnsValues.put(entry.getKey(), TextUtils.htmlEncode( answer.toString()));
								}
								//hmapAnsValues.put(entry.getKey(), answer.toString());
								
							}
							
							else if(view instanceof RadioGroup)
							{
								RadioGroup radioGroupSelected=(RadioGroup) view;
								 int selectedId = radioGroupSelected.getCheckedRadioButtonId();
								 if(selectedId!=-1)
								 {
									    RadioButton    radioButtonVal = (RadioButton) radioGroupSelected.findViewById(selectedId);
							          //  hmapAnsValues.put(entry.getKey(),radioButtonVal.getText().toString() );
							            //tempOutletQuestAnsMstrVal.add(0,OutletID);
									/*	tempOutletQuestAnsMstrVal.add(1,entry.getKey().split(Pattern.quote("^"))[0].toString());
										tempOutletQuestAnsMstrVal.add(2,entry.getKey().split(Pattern.quote("^"))[1].toString());
										tempOutletQuestAnsMstrVal.add(3,hmapOptionId.get((entry.getKey().split(Pattern.quote("^"))[0])+"^"+radioButtonVal.getText().toString()));*/
										hmapAnsValues.put(entry.getKey(), hmapOptionId.get((entry.getKey().split(Pattern.quote("^"))[2])+"^"+radioButtonVal.getText().toString()));
								
								 }
								 else
								 {
									 //tempOutletQuestAnsMstrVal.add(0,OutletID);
									/*	tempOutletQuestAnsMstrVal.add(1,entry.getKey().split(Pattern.quote("^"))[0].toString());
										tempOutletQuestAnsMstrVal.add(2,entry.getKey().split(Pattern.quote("^"))[1].toString());
										tempOutletQuestAnsMstrVal.add(3,"");*/
									// hmapAnsValues.put(entry.getKey(), "");
							    		
								 }
						            // find the radiobutton by returned id
						        
							}
							
							else if(view instanceof Spinner)
							{
								
								Spinner spinnerSelected=(Spinner) view;
								 

						          if(!spinnerSelected.getSelectedItem().equals("Select"))
						          {
						        	if(entry.getKey().split(Pattern.quote("^"))[1].toString().equals("7"))
										{
											if(spinnerSelected.getSelectedItem().toString().equals("Others"))
											{
												EditText editText=(EditText) ll_data.findViewWithTag(entry.getKey().toString()+"_ed");
												
												if(!TextUtils.isEmpty(editText.getText().toString()))
												{
												/*	tempOutletQuestAnsMstrVal.add(1,entry.getKey().split(Pattern.quote("^"))[0].toString());
													tempOutletQuestAnsMstrVal.add(2,entry.getKey().split(Pattern.quote("^"))[1].toString());
													tempOutletQuestAnsMstrVal.add(3,hmapOptionId.get((entry.getKey().split(Pattern.quote("^"))[0])+"^"+spinnerSelected.getSelectedItem().toString()));
										    	*/	
									        	  hmapAnsValues.put(entry.getKey(),"-99"+"~"+editText.getText().toString().trim());
									        		
												}
												/*else
												{
													  hmapAnsValues.put(entry.getKey(),"-99"+"~"+"" );
												}*/
												
											}
											else
								        	{
								        	/*	tempOutletQuestAnsMstrVal.add(1,entry.getKey().split(Pattern.quote("^"))[0].toString());
												tempOutletQuestAnsMstrVal.add(2,entry.getKey().split(Pattern.quote("^"))[1].toString());
												tempOutletQuestAnsMstrVal.add(3,hmapOptionId.get((entry.getKey().split(Pattern.quote("^"))[0])+"^"+spinnerSelected.getSelectedItem().toString()));
									    	*/	
								        	  hmapAnsValues.put(entry.getKey(),hmapOptionId.get((entry.getKey().split(Pattern.quote("^"))[2])+"^"+spinnerSelected.getSelectedItem().toString()) );
								        	}
											
										}
							            //tempOutletQuestAnsMstrVal.add(0,OutletID);
						        	else
						        	{
						        		/*tempOutletQuestAnsMstrVal.add(1,entry.getKey().split(Pattern.quote("^"))[0].toString());
										tempOutletQuestAnsMstrVal.add(2,entry.getKey().split(Pattern.quote("^"))[1].toString());
										tempOutletQuestAnsMstrVal.add(3,hmapOptionId.get((entry.getKey().split(Pattern.quote("^"))[0])+"^"+spinnerSelected.getSelectedItem().toString()));
							    		*/
						        	  hmapAnsValues.put(entry.getKey(),hmapOptionId.get((entry.getKey().split(Pattern.quote("^"))[2])+"^"+spinnerSelected.getSelectedItem().toString()) );
						        	}
										
						          }
						          
						          else
						        	  
						          {
						        	 
							    		  
						          }
							}
							
							 if(entry.getKey().split(Pattern.quote("^"))[1].toString().equals("13"))
							 {/*
								 TextView textView=(TextView) ll_data.findViewWithTag(entry.getKey());
								 if(hmapsearchAnswerSlctd.containsKey(entry.getKey()))
								 {
									  hmapAnsValues.put(entry.getKey(),hmapsearchAnswerSlctd.get(entry.getKey()) );
								 }
								
							 */}
							 if(entry.getKey().split(Pattern.quote("^"))[1].toString().equals("15"))
							 {
								 TextView textView=(TextView) ll_data.findViewWithTag(entry.getKey());
								 if(hmapSelectedMultipleDepend.containsKey(entry.getKey()))
								 {
									  ArrayList<String> listSelectedMultiple=new ArrayList<String>();
									  
											  listSelectedMultiple=hmapSelectedMultipleDepend.get(entry.getKey());
										  
									 StringBuilder sbfetchVal=new StringBuilder();
									 for(int i=0;i<listSelectedMultiple.size();i++)
									 {
										 if(i==0)
										 {
											 sbfetchVal.append(listSelectedMultiple.get(i));
										 }
										 else
										 {
											 sbfetchVal.append("^").append(listSelectedMultiple.get(i));
										 }
									 }
									  hmapAnsValues.put(entry.getKey(),sbfetchVal.toString());
								 }
								
							 }
							 if(entry.getKey().split(Pattern.quote("^"))[1].toString().equals("16"))
							 {
								 TextView textView=(TextView) ll_data.findViewWithTag(entry.getKey());
								 if(hmapSelectedMultipleDepend.containsKey(entry.getKey()))
								 {
									  ArrayList<String> listSelectedMultiple=new ArrayList<String>();
									  
											  listSelectedMultiple=hmapSelectedMultipleDepend.get(entry.getKey());
										  
									 StringBuilder sbfetchVal=new StringBuilder();
									 ArrayList<String> listOptionValName=hmapOptionValues.get(entry.getKey());
									 for(int i=0;i<listSelectedMultiple.size();i++)
									 {
										 if(i==0)
										 {
											 sbfetchVal.append(listSelectedMultiple.get(i));
											for(String valueSelected:listOptionValName)
											{
												if(valueSelected.split(Pattern.quote("^"))[0].equals(listSelectedMultiple.get(i)))
												{
													if(valueSelected.split(Pattern.quote("^"))[1].equals("Others")||valueSelected.split(Pattern.quote("^"))[1].equals("Other"))
													{
														 
															 EditText edText=(EditText) ll_data.findViewWithTag(entry.getKey()+"_ed");
															 sbfetchVal.append("~").append(edText.getText().toString());
														
													}
												}
											}
											
											
										 }
										 else
										 {
											 sbfetchVal.append("^").append(listSelectedMultiple.get(i));
												for(String valueSelected:listOptionValName)
												{
													if(valueSelected.split(Pattern.quote("^"))[0].equals(listSelectedMultiple.get(i)))
													{
														if(valueSelected.split(Pattern.quote("^"))[1].equals("Others")||valueSelected.split(Pattern.quote("^"))[1].equals("Other"))
														{
															 
																 EditText edText=(EditText) ll_data.findViewWithTag(entry.getKey()+"_ed");
																 sbfetchVal.append("~").append(edText.getText().toString());
															
														}
													}
												}
										 }
									 }
									  hmapAnsValues.put(entry.getKey(),sbfetchVal.toString());
								 }
								
							 }
							
							else if(view instanceof LinearLayout)
							{
								StringBuilder sbLandline=new StringBuilder();
								if(entry.getKey().split(Pattern.quote("^"))[1].toString().equals("12"))
								{
									LinearLayout ll_phone=(LinearLayout)view;
									for(int i=0;i<ll_phone.getChildCount();i++)
									{
										EditText edLandLine=(EditText) ll_phone.getChildAt(i);
										sbLandline.append(edLandLine.getText().toString());
										
									}
									
									if(!TextUtils.isEmpty(sbLandline.toString()))
									{
										
									/*	//tempOutletQuestAnsMstrVal.add(0,OutletID);
										tempOutletQuestAnsMstrVal.add(1,entry.getKey().split(Pattern.quote("^"))[0].toString());
										tempOutletQuestAnsMstrVal.add(2,entry.getKey().split(Pattern.quote("^"))[1].toString());
										//tempOutletQuestAnsMstrVal.add(3,edVal.getText().toString());
										 tempOutletQuestAnsMstrVal.add(3,TextUtils.htmlEncode(sbLandline.toString()));*/
										 hmapAnsValues.put(entry.getKey(),TextUtils.htmlEncode(sbLandline.toString()));
									
									}
									else
									{
										//tempOutletQuestAnsMstrVal.add(0,OutletID);
									/*	tempOutletQuestAnsMstrVal.add(1,entry.getKey().split(Pattern.quote("^"))[0].toString());
										tempOutletQuestAnsMstrVal.add(2,entry.getKey().split(Pattern.quote("^"))[1].toString());
										tempOutletQuestAnsMstrVal.add(3,"");*/
									//	 hmapAnsValues.put(entry.getKey(),"");
									
									}
									
							}
								
								
								if(entry.getKey().split(Pattern.quote("^"))[1].toString().equals("14"))
								{

									StringBuilder answer=new StringBuilder();
									LinearLayout ll_checkBoxVal=(LinearLayout)view;
									ArrayList<String> listTagChckBox=new ArrayList<String>();
									boolean isSelected=false;
									boolean isSingleSelected=true;
									boolean isEditTextFilled=false;
									for(int i=0;i<ll_checkBoxVal.getChildCount();i++)
									{
										LinearLayout checkBoxVal_ll=(LinearLayout) ll_checkBoxVal.getChildAt(i);
										CheckBox chckBox=(CheckBox) checkBoxVal_ll.getChildAt(0);
										
										if(chckBox.isChecked())
										{
											isSelected=true;
											EditText ediText=(EditText) checkBoxVal_ll.getChildAt(1);
											
											String tagChckBox=chckBox.getTag().toString();
											String inputType=ediText.getTag().toString();
											String edtTextVal="0";
											if(!TextUtils.isEmpty(ediText.getText().toString()))
											{
												edtTextVal=ediText.getText().toString();
											}
											
										
												if(isSingleSelected)
												{
													answer.append(chckBox.getTag().toString().split(Pattern.quote("#"))[0]+"~"+edtTextVal);
													isSingleSelected=false;
												}
												else
												{
													
													answer.append("^").append(chckBox.getTag().toString().split(Pattern.quote("#"))[0]+"~"+edtTextVal);
													
												}
												
											
											
										}
										
									}
									if(isSelected)
									{
										hmapAnsValues.put(entry.getKey(), answer.toString());
									}
									
									else
									{
									//	hmapAnsValues.put(entry.getKey(), "");
										
									}
								}
							}
							else
							{
								//hmapAnsValues.put(entry.getKey(), "");
							}
						}
							
				System.out.println("Savind Data = "+entry.getKey()+" Value = "+hmapAnsValues.get(entry.getKey()));
				}
				
				if(hmapAnsValues!=null && hmapAnsValues.size()>0)
				{
					//helperDb.fnsaveOutletQuestAnsMstrSectionWise(hmapAnsValues,sectionIsShown,tempId);
				//	helperDb.deleteResetDataQuest(tempId,nodeId,nodeType,false);
					/*if(TextUtils.isEmpty(personName))
					{
						View view=ll_data.findViewWithTag("2^2");
						EditText edVal=(EditText)view;
						personName=edVal.getText().toString();
						
					}*/
					
					if(hmapImageClkdTempIdData!=null && hmapImageClkdTempIdData.size()>0)
					{
						
						 helperDb.insertImageInfo(AddNewStore_DynamicSectionWise.selStoreID,hmapImageClkdTempIdData);
						// hmapImageClkdTempIdData=new LinkedHashMap<String, String>();
						 
					}
				if(isSaveAsDraft)
				{
					
				
				//	helperDb.insertExistingCntctMstr(visitPlanLocMapTaskId, nodeId, nodeType, remark, personName, flagNewOld, tempId, taskGrpId,flgScheduleFollowUp,followUpScheduleDate,locNodeID,locNodeType,visitPlanLocId,sequence,2);
				
					//helperDb.fnUpdateOutletQuestAnsMstrServerAnsValueFeild(nodeId,nodeType,nodeId+"_"+nodeType);
				//	Toast.makeText(NewReatilerForm.this, "Data has been successfully saved", Toast.LENGTH_SHORT).show();
				}
				else
				{
					
				//	 fnSaveFilledDataToDatabase();
				}
					
				}
				
			
		 }
		 
		 private void fillViewsValues() {
				if(hmapQuestionSavedAns!=null && hmapQuestionSavedAns.size()>0)
				{
					for(Entry<String, String> entry:hmapQuestionSavedAns.entrySet())
					{

						int QuestionflgPrvValue=0;

						if(hmapQuestionflgPrvValue!=null && hmapQuestionflgPrvValue.size()>0)
						{
							if(hmapQuestionflgPrvValue.containsKey(entry.getKey()))
							{
								QuestionflgPrvValue=Integer.parseInt(hmapQuestionflgPrvValue.get(entry.getKey()));

							}
						}


						ArrayList<String> tempOutletQuestAnsMstrVal=new ArrayList<String>();
							View ll_view=ll_data.findViewWithTag(entry.getKey().split(Pattern.quote("^"))[2].toString()+"ll");
							View view=ll_data.findViewWithTag(entry.getKey());
						//	if(ll_view.getVisibility()==View.VISIBLE)
								if(true)
							{
								if(view instanceof EditText)
								{
									EditText edVal=(EditText)view;
									edVal.setText(entry.getValue());

									if(QuestionflgPrvValue==1)
									{
										edVal.setEnabled(false);
									}
									
								}
								
								else if(view instanceof Button)
								{
									if(entry.getKey().split(Pattern.quote("^"))[1].toString().equals("11"))
									{
										
										Button buttonCalender=(Button)view;
										buttonCalender.setText(entry.getValue());

										if(QuestionflgPrvValue==1)
										{
											buttonCalender.setEnabled(false);
										}
									}
								}
								else if(entry.getKey().split(Pattern.quote("^"))[1].toString().equals("4") || entry.getKey().split(Pattern.quote("^"))[1].toString().equals("5"))
								{
									StringBuilder answer=new StringBuilder();
									LinearLayout ll_checkBoxVal=(LinearLayout)view;
									boolean isSingleSelected=true;
									boolean noValSelected=true;
									for(int i=0;i<ll_checkBoxVal.getChildCount();i++)
									{
										if(ll_checkBoxVal.getChildAt(i) instanceof CheckBox)
										{
											CheckBox checkBoxVal=(CheckBox) ll_checkBoxVal.getChildAt(i);
											String checkBoxId=checkBoxVal.getTag().toString();
											String ediTextVal="";
											boolean isOtherSelected=false;

											if(entry.getValue().contains("^"))
											{
												String[] multipleChecked=entry.getValue().split(Pattern.quote("^"));
												for(int index=0;index<multipleChecked.length;index++)
												{
													if(multipleChecked[index].contains(checkBoxId))
													{
														checkBoxVal.setChecked(true);
														String checkBoxValue=checkBoxVal.getText().toString();
														if(checkBoxValue.equals("Others")||checkBoxValue.equals("Other"))
														{
															EditText editTextOther=(EditText) ll_data.findViewWithTag(checkBoxId+"_ed");
															int lengthediTextVal=multipleChecked[index].split(Pattern.quote("~")).length;
															if(lengthediTextVal>1)
															{
																ediTextVal=multipleChecked[index].split(Pattern.quote("~"))[1];
																editTextOther.setText(ediTextVal);
															}
															
																	
														}
													}
												}
											}
											else
											{
												if(entry.getValue().contains(checkBoxId))
												{
													checkBoxVal.setChecked(true);
													String otherChecked=checkBoxVal.getText().toString();
													if(otherChecked.equals("Others")||otherChecked.equals("Other"))
													{
														EditText editTextOther=(EditText) ll_data.findViewWithTag(checkBoxId+"_ed");
														int lengthediTextVal=entry.getValue().split(Pattern.quote("~")).length;
														if(lengthediTextVal>1)
														{
															ediTextVal=entry.getValue().split(Pattern.quote("~"))[1];
															editTextOther.setText(ediTextVal);
														}
																
													}
													
												}
											}
											
											
										
										}
										
										
									}
									
								}
								
								else if(view instanceof RadioGroup)
								{

									RadioGroup radioGroupSelected=(RadioGroup) view;
									for(int j=0;j<radioGroupSelected.getChildCount();j++)
									{
										RadioButton radioButton=(RadioButton) radioGroupSelected.getChildAt(j);
										if(entry.getValue().equals(radioButton.getTag().toString()))
										{
											radioButton.setChecked(true);
										}
										
									}
									if(entry.getKey().equals("1^6^1"))
									{
										View view2=ll_data.findViewWithTag(entry.getKey().split(Pattern.quote("^"))[2]+"ll");
										if(view2 instanceof LinearLayout)
										{
											if(view2.getVisibility()==View.VISIBLE)
											{
												view2.setVisibility(View.GONE);
											}
										}
									}
								}
								
								
								
								else if(view instanceof Spinner)
								{
									Spinner spinnerSelected=(Spinner) view;
									 
									if(entry.getValue().contains("~"))
									{
										ArrayAdapter adapter = (ArrayAdapter) spinnerSelected.getAdapter();
										if(entry.getValue().equals("-99"))
										{
											spinnerSelected.setSelection(adapter.getPosition("Others"));
											EditText editTextOther=(EditText) ll_data.findViewWithTag(entry.getKey()+"_ed");
											int lengthediTextVal=entry.getValue().split(Pattern.quote("~")).length;
											if(lengthediTextVal>1)
											{
												String ediTextVal=entry.getValue().split(Pattern.quote("~"))[1];
												editTextOther.setText(ediTextVal);
											}
											
													
										}
									}
									else
									{
										ArrayAdapter adapter = (ArrayAdapter) spinnerSelected.getAdapter();
										String ansName=getOptionNameFromHmap(entry.getValue());
										spinnerSelected.setSelection(adapter.getPosition(ansName));
									}

									if(QuestionflgPrvValue==1)
									{
										spinnerSelected.setEnabled(false);
									}
								}
								
								 if(entry.getKey().split(Pattern.quote("^"))[1].toString().equals("13"))
								 {
									 TextView textView=(TextView) ll_data.findViewWithTag(entry.getKey());
									 ArrayList<String> listOptionVal=hmapOptionValues.get(entry.getKey());
									 for(String alertVal:listOptionVal)
									 {
										 if(entry.getValue().equals(alertVal.split(Pattern.quote("^"))[0].toString()))
										 {
											 hmapsearchAnswerSlctd.put(entry.getKey(), entry.getValue());
											 textView.setText(alertVal.split(Pattern.quote("^"))[1].toString());

											 if(hmapQuestDependVisible.containsKey(entry.getKey().split(Pattern.quote("^"))[2]))
											 {
												 String questIdDependent=AddNewStore_DynamicSectionWise.hmapDpndtQustGrpId.get(entry.getKey().split(Pattern.quote("^"))[2]+"^"+alertVal.split(Pattern.quote("^"))[0].toString());
												 if(questIdDependent!=null)
												 {
													 if(questIdDependent.contains("^"))
													 {

														 String[] questionToVisible=questIdDependent.split(Pattern.quote("^"));
														 for(int index=0;index<questionToVisible.length;index++)
														 {
															 View view2=ll_data.findViewWithTag(questionToVisible[index]+"ll");
															 if(view2 instanceof LinearLayout)
															 {
																 hmapGrpQstId_DepndentQstOptId.put(questionToVisible[index], alertVal.split(Pattern.quote("^"))[0].toString());
																 LinearLayout ll_viewDpndnt=(LinearLayout)view2;

																 ll_viewDpndnt.setVisibility(View.VISIBLE);

																 hideOrShowGroup(questionToVisible[index]);

															 }

														 }


													 }
													 else
													 {

														 String questionToVisible=questIdDependent;
														 View view2=ll_data.findViewWithTag(questionToVisible+"ll");
														 if(view2 instanceof LinearLayout)
														 {
															 LinearLayout ll_viewDpndnt=(LinearLayout)view2;
															 hmapGrpQstId_DepndentQstOptId.put(questionToVisible, alertVal.split(Pattern.quote("^"))[0].toString());
															 ll_viewDpndnt.setVisibility(View.VISIBLE);
															 //	showDpndntQstn(questionToVisible);
															 hideOrShowGroup(questionToVisible);


														 }

													 }

												 }
											 }
										 }
										
									 }
									 if(QuestionflgPrvValue==1)
									 {
										 textView.setEnabled(false);
										 textView.setOnClickListener(null);
									 }
								 }
								 if(entry.getKey().split(Pattern.quote("^"))[1].toString().equals("15"))
								 {
									 String gepQstIdFor15=entry.getKey().split(Pattern.quote("^"))[2];
									 TextView textView=(TextView) ll_data.findViewWithTag(entry.getKey());
									 ArrayList<String> listOptionVal=new ArrayList<String>();
									 ArrayList<String> listOptionValName=hmapOptionValues.get(entry.getKey());
									 if(entry.getValue().contains("^"))
										 {
											 StringBuilder sBuilderEntry=new StringBuilder();
											 String[] multipleVal=entry.getValue().toString().split(Pattern.quote("^"));
											 for(int i=0;i<multipleVal.length;i++)
											 {
												
												 listOptionVal.add(multipleVal[i]);
											 }
											 for(int j=0;j<listOptionVal.size();j++)
											 {
												 for(String alertVal:listOptionValName)
												 {
													 
													
												
														 if(listOptionVal.get(j).equals(alertVal.split(Pattern.quote("^"))[0].toString()))
														 { 
														 if(j==0)
														 {
															 sBuilderEntry.append(alertVal.split(Pattern.quote("^"))[1].toString());	 
														 }
														 else
														 {
															 sBuilderEntry.append(",").append(alertVal.split(Pattern.quote("^"))[1].toString());
														 }

                                                             if(AddNewStore_DynamicSectionWise.hmapDpndtQustGrpId.containsKey(entry.getKey().split(Pattern.quote("^"))[2]+"^"+alertVal.split(Pattern.quote("^"))[0].toString()))
                                                             {


                                                                 String questIdDependent=AddNewStore_DynamicSectionWise.hmapDpndtQustGrpId.get(entry.getKey().split(Pattern.quote("^"))[2]+"^"+alertVal.split(Pattern.quote("^"))[0].toString());
																 if(questIdDependent!=null)
																 {
																	 if(questIdDependent.contains("^"))
																	 {

																		 String[] questionToVisible=questIdDependent.split(Pattern.quote("^"));
																		 for(int index=0;index<questionToVisible.length;index++)
																		 {
																			 View view2=ll_data.findViewWithTag(questionToVisible[index]+"ll");
																			 if(view2 instanceof LinearLayout)
																			 {
																				 hmapGrpQstId_DepndentQstOptId.put(questionToVisible[index], alertVal.split(Pattern.quote("^"))[0].toString());
																				 LinearLayout ll_viewDpndnt=(LinearLayout)view2;

																				 ll_viewDpndnt.setVisibility(View.VISIBLE);

																				 hideOrShowGroup(questionToVisible[index]);

																				 if (hmapQuesIdandGetAnsCntrlType.get(hmapQuestGrpId_QstId.get(questionToVisible[index])).equals("14"))
																				 {
																					 if(hmapgetOptDepMstr.containsKey(questionToVisible[index]))
																					 {
																						 ArrayList<String> listQestValuesDpndnt=new ArrayList<String>();

																						 listQestValuesDpndnt=helperDb.getDepOtnVal(alertVal.split(Pattern.quote("^"))[0].toString(),Integer.parseInt(questionToVisible[index]),Integer.parseInt(gepQstIdFor15));

																						 String tagValCheckBox=hmapQuestGrpId_QstId.get(questionToVisible[index])+"^"+hmapQuesIdandGetAnsCntrlType.get(hmapQuestGrpId_QstId.get(questionToVisible[index]))+"^"+questionToVisible[index];
																						 if(hmapGetCheckBoxVisible!=null && hmapGetCheckBoxVisible.size()>0)
																						 {
																							 if(!hmapGetCheckBoxVisible.containsKey(tagValCheckBox))
																							 {
																								 hmapGetCheckBoxVisible.put(tagValCheckBox,alertVal.split(Pattern.quote("^"))[0].toString());
																								 getCheckBoxWithEditTextValues(listQestValuesDpndnt,tagValCheckBox);
																							 }

																						 }
																						 else
																						 {
																							 hmapGetCheckBoxVisible.put(tagValCheckBox,alertVal.split(Pattern.quote("^"))[0].toString());
																							 getCheckBoxWithEditTextValues(listQestValuesDpndnt,tagValCheckBox);
																						 }

																					 }

																				 }

																			 }

																		 }


																	 }
																	 else
																	 {

																		 String questionToVisible=questIdDependent;
																		 View view2=ll_data.findViewWithTag(questionToVisible+"ll");
																		 if(view2 instanceof LinearLayout)
																		 {
																			 LinearLayout ll_viewDpndnt=(LinearLayout)view2;
																			 hmapGrpQstId_DepndentQstOptId.put(questionToVisible, alertVal.split(Pattern.quote("^"))[0].toString());
																			 ll_viewDpndnt.setVisibility(View.VISIBLE);
																			 //	showDpndntQstn(questionToVisible);
																			 hideOrShowGroup(questionToVisible);
																			 if (hmapQuesIdandGetAnsCntrlType.get(hmapQuestGrpId_QstId.get(questionToVisible)).equals("14"))
																			 {
																				 if(hmapgetOptDepMstr.containsKey(questionToVisible))
																				 {
																					 ArrayList<String> listQestValuesDpndnt=new ArrayList<String>();

																					 listQestValuesDpndnt=helperDb.getDepOtnVal(alertVal.split(Pattern.quote("^"))[0].toString(),Integer.parseInt(questionToVisible),Integer.parseInt(gepQstIdFor15));

																					 String tagValCheckBox=hmapQuestGrpId_QstId.get(questionToVisible)+"^"+hmapQuesIdandGetAnsCntrlType.get(hmapQuestGrpId_QstId.get(questionToVisible))+"^"+questionToVisible;
																					 if(hmapGetCheckBoxVisible!=null && hmapGetCheckBoxVisible.size()>0)
																					 {
																						 if(!hmapGetCheckBoxVisible.containsKey(tagValCheckBox))
																						 {
																							 hmapGetCheckBoxVisible.put(tagValCheckBox,alertVal.split(Pattern.quote("^"))[0].toString());
																							 getCheckBoxWithEditTextValues(listQestValuesDpndnt,tagValCheckBox);
																						 }

																					 }
																					 else
																					 {
																						 hmapGetCheckBoxVisible.put(tagValCheckBox,alertVal.split(Pattern.quote("^"))[0].toString());
																						 getCheckBoxWithEditTextValues(listQestValuesDpndnt,tagValCheckBox);
																					 }

																				 }

																			 }


																		 }

																	 }
																 }




                                                             }
														 }


													
												 } 
												
											 }
											 hmapSelectedMultipleDepend.put(entry.getKey(), listOptionVal);
											 textView.setText(sBuilderEntry.toString());
										 }
										 else
										 {
											 
											
												 listOptionVal.add(entry.getValue().toString());
												 hmapSelectedMultipleDepend.put(entry.getKey(), listOptionVal);
												 for(String alertVal:listOptionValName)
												 {
													 if(entry.getValue().equals(alertVal.split(Pattern.quote("^"))[0].toString()))
													 {
														 textView.setText(alertVal.split(Pattern.quote("^"))[1].toString());
														 if(AddNewStore_DynamicSectionWise.hmapDpndtQustGrpId.containsKey(entry.getKey().split(Pattern.quote("^"))[2]+"^"+alertVal.split(Pattern.quote("^"))[0].toString()))
														 {


															 String questIdDependent=AddNewStore_DynamicSectionWise.hmapDpndtQustGrpId.get(entry.getKey().split(Pattern.quote("^"))[2]+"^"+alertVal.split(Pattern.quote("^"))[0].toString());
															 if(questIdDependent!=null)
															 {
																 if(questIdDependent.contains("^"))
																 {

																	 String[] questionToVisible=questIdDependent.split(Pattern.quote("^"));
																	 for(int index=0;index<questionToVisible.length;index++)
																	 {
																		 View view2=ll_data.findViewWithTag(questionToVisible[index]+"ll");
																		 if(view2 instanceof LinearLayout)
																		 {
																			 hmapGrpQstId_DepndentQstOptId.put(questionToVisible[index], alertVal.split(Pattern.quote("^"))[0].toString());
																			 LinearLayout ll_viewDpndnt=(LinearLayout)view2;

																			 ll_viewDpndnt.setVisibility(View.VISIBLE);

																			 hideOrShowGroup(questionToVisible[index]);

																		 }

																	 }


																 }
																 else
																 {

																	 String questionToVisible=questIdDependent;
																	 View view2=ll_data.findViewWithTag(questionToVisible+"ll");
																	 if(view2 instanceof LinearLayout)
																	 {
																		 LinearLayout ll_viewDpndnt=(LinearLayout)view2;
																		 hmapGrpQstId_DepndentQstOptId.put(questionToVisible, alertVal.split(Pattern.quote("^"))[0].toString());
																		 ll_viewDpndnt.setVisibility(View.VISIBLE);
																		 //	showDpndntQstn(questionToVisible);
																		 hideOrShowGroup(questionToVisible);
																		 if (hmapQuesIdandGetAnsCntrlType.get(hmapQuestGrpId_QstId.get(questionToVisible)).equals("14"))
																		 {
																			 if(hmapgetOptDepMstr.containsKey(questionToVisible))
																			 {
																				 ArrayList<String> listQestValuesDpndnt=new ArrayList<String>();

																				 listQestValuesDpndnt=helperDb.getDepOtnVal(alertVal.split(Pattern.quote("^"))[0].toString(),Integer.parseInt(questionToVisible),Integer.parseInt(gepQstIdFor15));

																				 String tagValCheckBox=hmapQuestGrpId_QstId.get(questionToVisible)+"^"+hmapQuesIdandGetAnsCntrlType.get(hmapQuestGrpId_QstId.get(questionToVisible))+"^"+questionToVisible;
																				 if(hmapGetCheckBoxVisible!=null && hmapGetCheckBoxVisible.size()>0)
																				 {
																					 if(!hmapGetCheckBoxVisible.containsKey(tagValCheckBox))
																					 {
																						 hmapGetCheckBoxVisible.put(tagValCheckBox,alertVal.split(Pattern.quote("^"))[0].toString());
																						 getCheckBoxWithEditTextValues(listQestValuesDpndnt,tagValCheckBox);
																					 }

																				 }
																				 else
																				 {
																					 hmapGetCheckBoxVisible.put(tagValCheckBox,alertVal.split(Pattern.quote("^"))[0].toString());
																					 getCheckBoxWithEditTextValues(listQestValuesDpndnt,tagValCheckBox);
																				 }

																			 }

																		 }


																	 }

																 }
															 }



														 }
													 }
												 }
												
											
											
										 }


									 if(QuestionflgPrvValue==1)
									 {
										 textView.setEnabled(false);
										 textView.setOnClickListener(null);
									 }
								 }
								 if(entry.getKey().split(Pattern.quote("^"))[1].toString().equals("16"))
								 {
									 TextView textView=(TextView) ll_data.findViewWithTag(entry.getKey());
									 ArrayList<String> listOptionVal=new ArrayList<String>();
									 ArrayList<String> listOptionValName=hmapOptionValues.get(entry.getKey());
									 if(entry.getValue().contains("^"))
										 {
											 StringBuilder sBuilderEntry=new StringBuilder();
											 String[] multipleVal=entry.getValue().toString().split(Pattern.quote("^"));
											 for(int i=0;i<multipleVal.length;i++)
											 {
												 if(multipleVal[i].contains("~")) 
												 {
													 listOptionVal.add(multipleVal[i].split(Pattern.quote("~"))[0]);
													
													 String[] othersSelected=multipleVal[i].split(Pattern.quote("~"));
													 for(String alertVal:listOptionValName)
													 {
														 if(othersSelected[0].equals(alertVal.split(Pattern.quote("^"))[0].toString()))
														 {
															 
															 
																	 EditText edText=(EditText) ll_data.findViewWithTag(entry.getKey()+"_ed");
																	 if(othersSelected.length>1)
																	 {
																		 edText.setText(othersSelected[1]);
																		 if(alertVal.split(Pattern.quote("^"))[1].toString().equals("Others")||alertVal.split(Pattern.quote("^"))[1].toString().equals("Other"))
																			{
																		 edText.setVisibility(View.VISIBLE);
																			}
																	 }
																	 
																	 
														 } 
													 }
													 
												 }
												 else
												 {
													 listOptionVal.add(multipleVal[i]); 
												 }
												
											 }
											 for(int j=0;j<listOptionVal.size();j++)
											 {
												 for(String alertVal:listOptionValName)
												 {
													 
													
														 if(listOptionVal.get(j).equals(alertVal.split(Pattern.quote("^"))[0].toString()))
														 { 
														 if(j==0)
														 {
															
																 sBuilderEntry.append(alertVal.split(Pattern.quote("^"))[1].toString()); 
															 }
															 	 
														 
														 else
														 {

															
																 sBuilderEntry.append(",").append(alertVal.split(Pattern.quote("^"))[1].toString()); 
															
															 	 
														 
															 
														 } 
														// textView.setText(alertVal.split(Pattern.quote("^"))[1].toString());
														 } 
													 
														 else if(listOptionVal.get(j).contains("~"))
														 {
															 if(listOptionVal.get(j).split(Pattern.quote("~"))[0].equals(alertVal.split(Pattern.quote("^"))[0].toString()))
															 { 
															 if(j==0)
															 {
																 if(listOptionVal.get(j).contains("~")) 
																 {
																	 String[] othersSelected=listOptionVal.get(j).split(Pattern.quote("~"));
																	 sBuilderEntry.append(alertVal.split(Pattern.quote("^"))[1].toString());
																	
																 }
																 else
																 {
																	 sBuilderEntry.append(alertVal.split(Pattern.quote("^"))[1].toString()); 
																 }
																 	 
															 }
															 else
															 {

																 if(listOptionVal.get(j).contains("~")) 
																 {
																	 String[] othersSelected=listOptionVal.get(j).split(Pattern.quote("~"));
																	 sBuilderEntry.append(",").append(alertVal.split(Pattern.quote("^"))[1].toString());
																	
																 }
																 else
																 {
																	 sBuilderEntry.append(",").append(alertVal.split(Pattern.quote("^"))[1].toString()); 
																 }
																 	 
															 
																 
															 } 
															// textView.setText(alertVal.split(Pattern.quote("^"))[1].toString());
															 }  
														 }
													
													
												 } 
												
											 }
											 
											 hmapSelectedMultipleDepend.put(entry.getKey(), listOptionVal);
											 textView.setText(sBuilderEntry.toString());
										 }
										 else
										 {
											 if(entry.getValue().contains("~")) 
											 {
												 listOptionVal.add(entry.getValue().toString());
												 hmapSelectedMultipleDepend.put(entry.getKey(), listOptionVal);
												 String[] othersSelected=entry.getValue().split(Pattern.quote("~"));
												 for(String alertVal:listOptionValName)
												 {
													 if(othersSelected[0].equals(alertVal.split(Pattern.quote("^"))[0].toString()))
													 {
														 
														 textView.setText(alertVal.split(Pattern.quote("^"))[1].toString());
														
														 
																 EditText edText=(EditText) ll_data.findViewWithTag(entry.getKey()+"_ed");
																 if(othersSelected.length>1)
																 {
																	 
																	 edText.setText(othersSelected[1]); 
																	 if(alertVal.split(Pattern.quote("^"))[1].toString().equals("Others")||alertVal.split(Pattern.quote("^"))[1].toString().equals("Other"))
																		{
																				 edText.setVisibility(View.VISIBLE);
																		}
																		
																 }
													
															
													 } 
												 }
												 
											 }
											 
											 else
											 {
												 listOptionVal.add(entry.getValue().toString());
												 hmapSelectedMultipleDepend.put(entry.getKey(), listOptionVal);
												 for(String alertVal:listOptionValName)
												 {
													 if(entry.getValue().equals(alertVal.split(Pattern.quote("^"))[0].toString()))
													 {
														 textView.setText(alertVal.split(Pattern.quote("^"))[1].toString());
													 }
												 }
												
											 }
											
										 }


									 if(QuestionflgPrvValue==1)
									 {
										 textView.setEnabled(false);
										 textView.setOnClickListener(null);
									 }
									
								 }
								
								else if(view instanceof LinearLayout)
								{
									StringBuilder sbLandline=new StringBuilder();
									if(entry.getKey().split(Pattern.quote("^"))[1].toString().equals("12"))
									{
										LinearLayout ll_phone=(LinearLayout)view;
										for(int i=0;i<ll_phone.getChildCount();i++)
										{
											EditText edLandLine=(EditText) ll_phone.getChildAt(i);
											if(i==0)
											{
												if(entry.getValue().length()>4)
												{
													edLandLine.setText(entry.getValue().substring(0, Math.min(entry.getValue().length(), 4)));
												}
												
											}
											else
											{
												if(entry.getValue().length()>4)
												{
													edLandLine.setText(entry.getValue().substring(4, Math.min(entry.getValue().length(), entry.getValue().length())));
												}
												
											}
											if(QuestionflgPrvValue==1)
											{
												edLandLine.setEnabled(false);

											}
											
										}
										
										
										
								}
									
									
									if(entry.getKey().split(Pattern.quote("^"))[1].toString().equals("14"))
									{

										StringBuilder answer=new StringBuilder();
										LinearLayout ll_checkBoxVal=(LinearLayout)view;
										ArrayList<String> listTagChckBox=new ArrayList<String>();
									
										for(int i=0;i<ll_checkBoxVal.getChildCount();i++)
										{
											LinearLayout checkBoxVal_ll=(LinearLayout) ll_checkBoxVal.getChildAt(i);
											CheckBox checkBoxVal=(CheckBox) checkBoxVal_ll.getChildAt(0);
											
											
												String checkBoxId=checkBoxVal.getTag().toString().split(Pattern.quote("#"))[0];
												EditText ediText=(EditText) checkBoxVal_ll.getChildAt(1);
												
												
												//String inputType=ediText.getTag().toString();
												String ediTextVal;
											
												if(entry.getValue().contains("^"))
												{
													String[] multipleChecked=entry.getValue().split(Pattern.quote("^"));
													for(int index=0;index<multipleChecked.length;index++)
													{
														if(multipleChecked[index].contains(checkBoxId))
														{
															checkBoxVal.setChecked(true);
															
															if(multipleChecked[index].contains("~"))
															{
																ediTextVal=multipleChecked[index].split(Pattern.quote("~"))[1];
																ediText.setText(ediTextVal);
																			
															}
															
															
														}
													}
												}
												else
												{
													if(entry.getValue().contains(checkBoxId))
													{
														checkBoxVal.setChecked(true);
														String otherChecked=checkBoxVal.getText().toString();
														if(entry.getValue().contains("~"))
														{
															ediTextVal=entry.getValue().split(Pattern.quote("~"))[1];
															ediText.setText(ediTextVal);
																		
														}
														
																	
													
														
													}
												}
													
												
												
											
											
										}
										
									
									}
				
									
								
								}
								
								else
								{
									//hmapAnsValues.put(entry.getKey(), "");
								}
								
							
							}
								
					
					}
					
					if(listimagePath!=null && listimagePath.size()>0)
					{
						for(String valueForKey:listimagePath)
						{
							
							/*
							  Bitmap bmp = decodeSampledBitmapFromFile(imagePath, 160, 160);
					          
							    ByteArrayOutputStream stream = new ByteArrayOutputStream();
					            	
					               bmp.compress(Bitmap.CompressFormat.JPEG, 70, stream);
					               byte[] byteArray = stream.toByteArray();

					               // Convert ByteArray to Bitmap::
					              
					               helperDb.insertImageInfo(tempId,imagButtonTag, imageName, file.getAbsolutePath(), 2);
					               Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
					                       byteArray.length);

					              // 

					             ImageView image = new ImageView(NewReatilerForm.this);
					            
					               //btn.setLayoutParams(new android.view.ViewGroup.LayoutParams(80,60));
					             
					               
					               image.setImageBitmap(bmp);
					               LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(250, 250);
					               params.setMargins(10,0,10,0);
					              
					               ll_Image.addView(image,params);*/
							Bitmap bmp = decodeSampledBitmapFromFile(Uri.parse(valueForKey.split(Pattern.quote("~"))[2]).getPath(), 160, 160);
							   String[] imageName=(valueForKey.split(Pattern.quote("~"))[2]).split(Pattern.quote("/"));
							clickedTagPhoto=valueForKey.split(Pattern.quote("~"))[0];
					           setSavedImageToScrollView(bmp,(valueForKey.split(Pattern.quote("~"))[2]).split(Pattern.quote("/"))[imageName.length-1],valueForKey,clickedTagPhoto);
				
						}
						 
					}
				}
				
			}
		 
			
			
			
			 public String getOptionNameFromHmap(String optionId)
			 {
				 String optionName="";
				 for(Entry<String, String> entry:hmapOptionId.entrySet())
				 {
					 if(optionId.equals(entry.getValue()))
					 {
						 optionName=entry.getKey().split(Pattern.quote("^"))[1];
					 }
					 
				 }
				 return optionName;
			 }

			 public String getCurrentStoreName()
			 {
			 	String storeName="NA";
				 View vieweStoreName=ll_data.findViewWithTag(nameForStore);
				 EditText edVal=(EditText)vieweStoreName;
				 if(edVal!=null)
				 {
					 storeName=edVal.getText().toString();
				 }
				 return storeName;
			 }
			 public boolean validateNameFilled()
				{
					//Name EditText hardcoded Value
				 boolean chekResult=false;

			
						 
					if(currentStoreName.equals("NA"))
					{
						
						View view=ll_data.findViewWithTag(nameForStore);
						if(view.getVisibility()==View.VISIBLE)
						{
							EditText edVal=(EditText)view;
							
							if(TextUtils.isEmpty(edVal.getText().toString().trim()))
							{
								showErrorAlert("Please Fill Name of Outlet to Save and Exit");
								edVal.requestFocus();
								
								
								//AddNewStore_DynamicSectionWise.StoreName=personName;
								chekResult= false;
							}
							else
							{
								currentStoreName=edVal.getText().toString();
								
								chekResult= true;
							}
						}
						
					
					}
					else
					{

						chekResult= true;
					}
					
					return chekResult;
				}
			 
			 public void alertSaveAndSaveExit(String title,String message,final boolean isExitClkd)
			 {AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
			 
		        // Setting Dialog Title
		        alertDialog.setTitle(title);
		 
		        // Setting Dialog Message
		        alertDialog.setMessage(message);
				 alertDialog.setCancelable(false);
		       
		 
		        // Setting Positive "Yes" Button
		        alertDialog.setPositiveButton("Exit without Save", new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog,int which) {
		 
		            // Write your code here to invoke YES event
		          
			                    // User pressed YES button. Write Logic Here


			                    		if(helperDb.checkCountIntblNewStoreMainTable(AddNewStore_DynamicSectionWise.selStoreID)==0)
			                    		{
			                    			helperDb.open();
			                    		//	helperDb.fndeleteNewStoreSalesQuotePaymentDetails(AddNewStore_DynamicSectionWise.selStoreID);
			                    			helperDb.close();
			                    		}
										Intent intent = new Intent(getActivity(), LauncherActivity.class);
										intent.putExtra("FROM", "AddNewStore_DynamicSectionWise");
										getActivity().startActivity(intent);
										getActivity().finish();

			    						
			                    
		            }
		        });

				 alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					 @Override
					 public void onClick(DialogInterface dialog, int which) {
						 dialog.dismiss();
					 }
				 });
		        // Setting Negative "NO" Button
		       /* alertDialog.setNegativeButton("Save and Exit", new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int which) {
		           
			                    // User pressed No button. Write Logic Here
							if(validateNameFilled())
							{


									View view=ll_data.findViewWithTag(nameForStore);

									EditText edVal=(EditText)view;
									currentStoreName=edVal.getText().toString();


								saveDynamicQuesAns(true);
								saveDataToDataBase();
								helperDb.fnsaveOutletQuestAnsMstrSectionWise(hmapAnsValues,sectionIsShown,AddNewStore_DynamicSectionWise.selStoreID);
								long syncTIMESTAMP = System.currentTimeMillis();
								Date datefromat = new Date(syncTIMESTAMP);
								SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

								SimpleDateFormat onlyDate = new SimpleDateFormat("dd-MMM-yyyy");
								String onlyDateString = onlyDate.format(datefromat);


								String VisitStartTSEND = df.format(datefromat);
								// int DatabaseVersion=CommonInfo.DATABASE_VERSIONID;
								int ApplicationID = CommonInfo.Application_TypeID;
								String allValuesOfPaymentStageID = helperDb.fngettblNewStoreSalesQuotePaymentDetails(AddNewStore_DynamicSectionWise.selStoreID);

                                if(AddNewStore_DynamicSectionWise.ProviderFromLauncher.equals("Fused"))
                                {
                                    AddNewStore_DynamicSectionWise.fnAddressFromLauncher=AddNewStore_DynamicSectionWise.FusedAddressFromLauncher;
                                }
                                else if(AddNewStore_DynamicSectionWise.ProviderFromLauncher.equals("Gps"))
                                {
                                    AddNewStore_DynamicSectionWise.fnAddressFromLauncher=AddNewStore_DynamicSectionWise.GpsAddressFromLauncher;
                                }
                                else if(AddNewStore_DynamicSectionWise.ProviderFromLauncher.equals("Network"))
                                {
                                    AddNewStore_DynamicSectionWise.fnAddressFromLauncher=AddNewStore_DynamicSectionWise.NetwAddressFromLauncher;
                                }


                                helperDb.saveLatLngToTxtFile(AddNewStore_DynamicSectionWise.selStoreID,AddNewStore_DynamicSectionWise.LattitudeFromLauncher, AddNewStore_DynamicSectionWise.LongitudeFromLauncher,AddNewStore_DynamicSectionWise.AccuracyFromLauncher,AddNewStore_DynamicSectionWise.ProviderFromLauncher,AddNewStore_DynamicSectionWise.GpsLatFromLauncher,AddNewStore_DynamicSectionWise.GpsLongFromLauncher,AddNewStore_DynamicSectionWise.GpsAccuracyFromLauncher,AddNewStore_DynamicSectionWise.NetworkLatFromLauncher,AddNewStore_DynamicSectionWise.NetworkLongFromLauncher,AddNewStore_DynamicSectionWise.NetworkAccuracyFromLauncher,AddNewStore_DynamicSectionWise.FusedLatFromLauncher,AddNewStore_DynamicSectionWise.FusedLongFromLauncher,AddNewStore_DynamicSectionWise.FusedAccuracyFromLauncher,1,"0",AddNewStore_DynamicSectionWise.fnAddressFromLauncher,AddNewStore_DynamicSectionWise.AllProvidersLocationFromLauncher,AddNewStore_DynamicSectionWise.GpsAddressFromLauncher,AddNewStore_DynamicSectionWise.NetwAddressFromLauncher,AddNewStore_DynamicSectionWise.FusedAddressFromLauncher,AddNewStore_DynamicSectionWise.FusedLocationLatitudeWithFirstAttemptFromLauncher
                                        ,AddNewStore_DynamicSectionWise.FusedLocationLongitudeWithFirstAttemptFromLauncher,AddNewStore_DynamicSectionWise.FusedLocationAccuracyWithFirstAttemptFromLauncher);


									//String prvsStoreID,String CrntStoreID,String isSavedOrSubmittedStore,String MsgToRestartPopUpShown,String isRestartDoneByDSR,String Sstat)
								String lastprvsStoreId=helperDb.PrvsStoreMsgShownAndRestrtDone();
								if(!TextUtils.isEmpty(lastprvsStoreId.trim()))
								{
									helperDb.updateCurrentStoreId(AddNewStore_DynamicSectionWise.selStoreID,lastprvsStoreId);
								}
									helperDb.insertRestartStoreInfo(AddNewStore_DynamicSectionWise.selStoreID,AddNewStore_DynamicSectionWise.selStoreID,"1","0","0",1,VisitStartTSEND);

								if(hmapAddress!=null && hmapAddress.size()>0)
								{
									helperDb.fnInsertOrUpdate_tblStoreDeatils(AddNewStore_DynamicSectionWise.selStoreID, currentStoreName, AddNewStore_DynamicSectionWise.latitudeToSave, AddNewStore_DynamicSectionWise.longitudeToSave, AddNewStore_DynamicSectionWise.VisitStartTS, VisitStartTSEND, AddNewStore_DynamicSectionWise.ProviderFromLauncher, AddNewStore_DynamicSectionWise.accuracyToSave, "" + AddNewStore_DynamicSectionWise.battLevel, 0, allValuesOfPaymentStageID, 0, hmapAddress.get("0"), hmapAddress.get("2"), hmapAddress.get("1"), hmapAddress.get("3"), 1,AddNewStore_DynamicSectionWise.flgLocationServicesOnOff,AddNewStore_DynamicSectionWise.flgGPSOnOff,AddNewStore_DynamicSectionWise.flgNetworkOnOff,AddNewStore_DynamicSectionWise.flgFusedOnOff,AddNewStore_DynamicSectionWise.flgInternetOnOffWhileLocationTracking,AddNewStore_DynamicSectionWise.flgRestart,AddNewStore_DynamicSectionWise.flgStoreOrder,hmapAddress.get("4"),hmapAddress.get("5"),hmapAddress.get("6"),hmapAddress.get("7"),hmapAddress.get("8"),hmapAddress.get("9"));
								}
								else
								{
									helperDb.fnInsertOrUpdate_tblStoreDeatils(AddNewStore_DynamicSectionWise.selStoreID, currentStoreName, AddNewStore_DynamicSectionWise.latitudeToSave, AddNewStore_DynamicSectionWise.longitudeToSave, AddNewStore_DynamicSectionWise.VisitStartTS, VisitStartTSEND, AddNewStore_DynamicSectionWise.ProviderFromLauncher, AddNewStore_DynamicSectionWise.accuracyToSave, "" + AddNewStore_DynamicSectionWise.battLevel, 0, allValuesOfPaymentStageID, 0, "NA","NA", "NA", "NA", 1,AddNewStore_DynamicSectionWise.flgLocationServicesOnOff,AddNewStore_DynamicSectionWise.flgGPSOnOff,AddNewStore_DynamicSectionWise.flgNetworkOnOff,AddNewStore_DynamicSectionWise.flgFusedOnOff,AddNewStore_DynamicSectionWise.flgInternetOnOffWhileLocationTracking,AddNewStore_DynamicSectionWise.flgRestart,AddNewStore_DynamicSectionWise.flgStoreOrder,hmapAddress.get("4"),hmapAddress.get("5"),hmapAddress.get("6"),hmapAddress.get("7"),hmapAddress.get("8"),hmapAddress.get("9"));
								}

								//String StoreID,String ActualLatitude,String ActualLongitude,String VisitStartTS,String VisitEndTS,String LocProvider,String Accuracy,String BateryLeftStatus,int IsStoreDataCompleteSaved,String PaymentStage,int flgLocationTrackEnabled,String StoreAddress,String StoreCity,String StorePinCode,String StoreState,int Sstat)



								if (AddNewStore_DynamicSectionWise.FLAG_NEW_UPDATE.equals("UPDATE")) {
									helperDb.open();
									helperDb.UpdateStoreReturnphotoFlag(AddNewStore_DynamicSectionWise.selStoreID, currentStoreName,4);
									helperDb.close();
								} else {
									String storeCountDeatails=helperDb.getTodatAndTotalStores();
									int  totaltarget = Integer.parseInt(storeCountDeatails.split(Pattern.quote("^"))[0]);
									int todayTarget = Integer.parseInt(storeCountDeatails.split(Pattern.quote("^"))[1]);
									helperDb.fnDeletesaveNewOutletFromOutletMstr(AddNewStore_DynamicSectionWise.selStoreID);
									helperDb.open();
									helperDb.deletetblStoreCountDetails();
									totaltarget=totaltarget+1;
									todayTarget=todayTarget+1;
									helperDb.saveTblStoreCountDetails(String.valueOf(totaltarget),String.valueOf(todayTarget));

									helperDb.saveTblPreAddedStores(AddNewStore_DynamicSectionWise.selStoreID, currentStoreName, AddNewStore_DynamicSectionWise.latitudeToSave, AddNewStore_DynamicSectionWise.longitudeToSave, onlyDateString, 1,4, 1);
									helperDb.close();
								}
								Toast.makeText(getActivity(), "Data has been completely saved", Toast.LENGTH_SHORT).show();
								Intent intent = new Intent(getActivity(), LauncherActivity.class);
								intent.putExtra("FROM", "AddNewStore_DynamicSectionWise");
								getActivity().startActivity(intent);
								getActivity().finish();
							}
							else
							{
								Toast.makeText(getActivity(), "Please Fill Name To Save", Toast.LENGTH_SHORT).show();
							}

			                    	  
		            }
		        });*/
		 
		        // Showing Alert Message
		        alertDialog.show();}
			 
				
				public void selectedStoreMultiple(String optId, String optionVal,
						EditText txtVw, ListView listViewOption, String tagVal,
						Dialog dialog, TextView textView, LinearLayout ll_SlctdOpt,
						ArrayList<String> listSelectedOpt,ArrayList<String> listSelectedStoreID,ArrayList<String> listSelectedStoreOrigin) {


					ll_SlctdOpt.removeAllViews();
					sbMultiple=new StringBuilder();
					listFarmerID=new ArrayList<String>();
					listSelectedStoreOriginTemp=new ArrayList<String>();
					listFarmerID=listSelectedStoreID;
					listSelectedStoreOriginTemp=listSelectedStoreOrigin;
					String grpQustId=tagVal.split(Pattern.quote("^"))[2];
					if(listSelectedOpt.size()>0) {

						for (int i = 0; i < listSelectedOpt.size(); i++) {

							isSelectedSearch = true;
							TextView txtVwSlctdList = new TextView(getActivity());
							txtVwSlctdList.setTextColor(Color.parseColor("#000000"));
							if (i == 0) {
								//textView.setText(listSelectedOpt.get(i));
								sbMultiple.append(listSelectedOpt.get(i));
								txtVwSlctdList.setText(listSelectedOpt.get(i));
							} else {
								sbMultiple.append(", " + listSelectedOpt.get(i));
								//textView.setText(", "+listSelectedOpt.get(i));
								txtVwSlctdList.setText(", " + listSelectedOpt.get(i));
							}
							txtVwSlctdList.setPadding(5, 0, 5, 0);
							ll_SlctdOpt.addView(txtVwSlctdList);
						}
					}

					else
					{
						isSelectedSearch=true;
						
					}
				
					
				
					
				
					
				}

				
				public  void selectedOption(String optId, String optionVal,
						EditText txtVw, ListView listViewOption, String tagVal,
						Dialog dialog, TextView textView) {
					hmapsearchAnswerSlctd.put(tagVal, optId);
					txtVw.setText(optionVal);
					singleSelectedVal=optionVal;
					isSelectedSearch=true;
					textView.setText(optionVal);
					dialog.dismiss();
					listViewOption.setVisibility(View.GONE);
					 if( tagVal.split(Pattern.quote("^"))[1].toString().equals("17"))
				        {
						 if(singleSelectedVal.contains("Others") || singleSelectedVal.contains("Other"))
							{
								EditText edText=(EditText) ll_data.findViewWithTag(tagVal+"_ed");
								edText.setVisibility(View.VISIBLE);
							}
							else
							{
								EditText edText=(EditText) ll_data.findViewWithTag(tagVal+"_ed");
								edText.setVisibility(View.GONE);
							}
				        }
				/*	if(hmapOptionaDepend.containsKey(tagVal.split(Pattern.quote("^"))[0]))
					{
							ArrayList<String> listQuestIdDpndOnIt=new ArrayList<String>();
							listQuestIdDpndOnIt=hmapOptionaDepend.get(tagVal.split(Pattern.quote("^"))[0]);
							if(listQuestIdDpndOnIt!=null && listQuestIdDpndOnIt.size()>0)
							{
								for(int i=0;i<listQuestIdDpndOnIt.size();i++)
								{
									String tempQuestId=listQuestIdDpndOnIt.get(i);
									String ansCntrlType=hmapQuesIdandGetAnsCntrlType.get(tempQuestId);
									if(ansCntrlType.equals("13"))
									{
										TextView txtVw_Search=(TextView) ll_form.findViewWithTag(tempQuestId+"^"+ansCntrlType);
										txtVw_Search.setText("Select");
									}
									
								}
							}
					}*/
					
}
				
				/*public void hideDpndntQstn(String qstnGrpToHide)
				{
					ArrayList<String> listQstnGrp=new ArrayList<String>();
					if(hmapQuestDependVisible.containsKey(qstnGrpToHide))
						{
						listQstnGrp=hmapQuestDependVisible.get(qstnGrpToHide);
						if(listQstnGrp!=null && listQstnGrp.size()>0)
						{
							for(String qstnGrp:listQstnGrp)
							{
								View viewGrp=ll_data.findViewWithTag(qstnGrp+"ll");
								if(viewGrp instanceof LinearLayout)
								{
									
									LinearLayout ll_view=(LinearLayout)viewGrp;
									if(ll_view.getVisibility()==View.VISIBLE)
									{
										
										ll_view.setVisibility(View.GONE);
										hideDpndntQstn(qstnGrp);
									hideOrShowGroup(qstnGrp);
										
										
									}
									
								}
							}
						}
						}
				}
				*/
				
				/*public void showDpndntQstn(String qstnGrpToHide)
				{
					ArrayList<String> listQstnGrp=new ArrayList<String>();
					if(hmapQuestDependVisible.containsKey(qstnGrpToHide))
						{
						listQstnGrp=hmapQuestDependVisible.get(qstnGrpToHide);
						if(listQstnGrp!=null && listQstnGrp.size()>0)
						{
							for(String qstnGrp:listQstnGrp)
							{
								View viewGrp=ll_data.findViewWithTag(qstnGrp+"ll");
								if(viewGrp instanceof LinearLayout)
								{
									
									LinearLayout ll_view=(LinearLayout)viewGrp;
									if(ll_view.getVisibility()==View.GONE)
									{
										
										ll_view.setVisibility(View.VISIBLE);
										showDpndntQstn(qstnGrp);
									hideOrShowGroup(qstnGrp);
										
										
									}
									
								}
							}
						}
						}
				}
				*/
				
				 public void clearAllValues(String grpQuestId)
				 {

						
					
						for(Entry<String, String> entry:AddNewStore_DynamicSectionWise.hmapQuesIdValues.entrySet())
						{
							if(!grpQuestId.equals(entry.getKey().split(Pattern.quote("^"))[2]) && !((nameForStore.split(Pattern.quote("^"))[2]).equals(entry.getKey().split(Pattern.quote("^"))[2])) && !(entry.getKey().split(Pattern.quote("^"))[2]).equals("6"))
							{
								ArrayList<String> tempOutletQuestAnsMstrVal=new ArrayList<String>();
								View ll_view=ll_data.findViewWithTag(entry.getKey().split(Pattern.quote("^"))[2].toString()+"ll");
								View view=ll_data.findViewWithTag(entry.getKey());
								ArrayList<String> listKeyMapdWithSection=new ArrayList<String>();
								listKeyMapdWithSection=AddNewStore_DynamicSectionWise.hmapSection_key.get(String.valueOf(sectionIsShown));
								
									if(view instanceof EditText)
									{
										EditText edVal=(EditText)view;
										
										
					//saveOutletQuestAnsMstr(String OutletID,String QuestID,String AnswerType,String AnswerValue)
										
										if(!TextUtils.isEmpty(edVal.getText().toString()))
										{
											edVal.setText("");
										}
										
									}
									
									else if(view instanceof Button)
									{
										if(entry.getKey().split(Pattern.quote("^"))[1].toString().equals("11"))
										{

											Button buttonCalender=(Button)view;
											
											
											//saveOutletQuestAnsMstr(String OutletID,String QuestID,String AnswerType,String AnswerValue)
											
											if(!buttonCalender.getText().toString().equals("Select Date"))
											{
												buttonCalender.setText("Select Date");
												
											}
											/*else
											{
												hmapAnsValues.put(entry.getKey(), "");
												
											}*/
										}
									}
									else if(entry.getKey().split(Pattern.quote("^"))[1].toString().equals("4") || entry.getKey().split(Pattern.quote("^"))[1].toString().equals("5"))
									{
										StringBuilder answer=new StringBuilder();
										LinearLayout ll_checkBoxVal=(LinearLayout)view;
										boolean isSingleSelected=true;
										boolean noValSelected=true;
										for(int i=0;i<ll_checkBoxVal.getChildCount();i++)
										{
											if(ll_checkBoxVal.getChildAt(i) instanceof CheckBox)
											{
												CheckBox checkBoxVal=(CheckBox) ll_checkBoxVal.getChildAt(i);
												String ediTextVal="";
												boolean isOtherSelected=false;
												if(checkBoxVal.isChecked())
												{
													noValSelected=false;
													checkBoxVal.setChecked(false);
													if(checkBoxVal.getText().toString().equals("Others") || checkBoxVal.getText().toString().equals("Other"))
													{
														isOtherSelected=true;
														EditText editTextCheckBox=(EditText) ll_data.findViewWithTag(checkBoxVal.getTag().toString()+"_ed");
														if(!TextUtils.isEmpty(editTextCheckBox.getText().toString()))
														{
															
															editTextCheckBox.setText("");
															
														}
														
													}
													
													
												}
											}
											
											
										}
										
										
										
									}
									
									else if(view instanceof RadioGroup)
									{
										RadioGroup radioGroupSelected=(RadioGroup) view;
										 int selectedId = radioGroupSelected.getCheckedRadioButtonId();
										 if(selectedId!=-1)
										 {
											    RadioButton    radioButtonVal = (RadioButton) radioGroupSelected.findViewById(selectedId);
											    radioButtonVal.setChecked(false);
										
										 }
									}
									else if(view instanceof Spinner)
									{
										
										Spinner spinnerSelected=(Spinner) view;
										ArrayAdapter adapter = (ArrayAdapter) spinnerSelected.getAdapter();
										
										

								          if(!spinnerSelected.getSelectedItem().equals("Select"))
								          {
								        	 
								        	  if(entry.getKey().split(Pattern.quote("^"))[1].toString().equals("7"))
												{
													if(spinnerSelected.getSelectedItem().toString().equals("Others"))
													{
														EditText editText=(EditText) ll_data.findViewWithTag(entry.getKey().toString()+"_ed");
														
														if(!TextUtils.isEmpty(editText.getText().toString()))
														{
															editText.setText("");
											        		
														}
														/*else
														{
															  hmapAnsValues.put(entry.getKey(),"-99"+"~"+"" );
														}*/
														
													}
													else
										        	{
										        		
										        	 
										        	}
													
												}
									            
								        	  spinnerSelected.setSelection(adapter.getPosition("Select"));
												
								          }
								          
								          
									}
									
									 if(entry.getKey().split(Pattern.quote("^"))[1].toString().equals("13"))
									 {
										 TextView textView=(TextView) ll_data.findViewWithTag(entry.getKey());
										 if(hmapsearchAnswerSlctd.containsKey(entry.getKey()))
										 {
											 textView.setText("Select");
											 hmapsearchAnswerSlctd.remove(entry.getKey());
										 }
										
									 }
									 if(entry.getKey().split(Pattern.quote("^"))[1].toString().equals("15"))
									 {
										 TextView textView=(TextView) ll_data.findViewWithTag(entry.getKey());
										 if(hmapSelectedMultipleDepend.containsKey(entry.getKey()))
										 {
											 textView.setText("Select");
											 hmapSelectedMultipleDepend.remove(entry.getKey());
										 }
										
									 }
									 if(entry.getKey().split(Pattern.quote("^"))[1].toString().equals("16"))
									 {
										 TextView textView=(TextView) ll_data.findViewWithTag(entry.getKey());
										 if(hmapSelectedMultipleDepend.containsKey(entry.getKey()))
										 {
											
											
											 
											 ArrayList<String> listSelectedMultiple=new ArrayList<String>();
											  
													  listSelectedMultiple=hmapSelectedMultipleDepend.get(entry.getKey());
												  
											 StringBuilder sbfetchVal=new StringBuilder();
											 ArrayList<String> listOptionValName=hmapOptionValues.get(entry.getKey());
											 for(int i=0;i<listSelectedMultiple.size();i++)
											 {
												 
													 sbfetchVal.append(listSelectedMultiple.get(i));
													for(String valueSelected:listOptionValName)
													{
														if(valueSelected.split(Pattern.quote("^"))[0].equals(listSelectedMultiple.get(i)))
														{
															if(valueSelected.split(Pattern.quote("^"))[1].equals("Others")||valueSelected.split(Pattern.quote("^"))[1].equals("Other"))
															{
																 
																	 EditText edText=(EditText) ll_data.findViewWithTag(entry.getKey()+"_ed");
																	 edText.setText("");
																	 
																
															}
														}
													}
													
													
												
											 }
											 textView.setText("Select");
											 hmapSelectedMultipleDepend.remove(entry.getKey());
										 }
										
									 }
									
									else if(view instanceof LinearLayout)
									{
										StringBuilder sbLandline=new StringBuilder();
										if(entry.getKey().split(Pattern.quote("^"))[1].toString().equals("12"))
										{
											LinearLayout ll_phone=(LinearLayout)view;
											for(int i=0;i<ll_phone.getChildCount();i++)
											{
												EditText edLandLine=(EditText) ll_phone.getChildAt(i);
												sbLandline.append(edLandLine.getText().toString());
												
											}
											
											
											
									}
										
										
										if(entry.getKey().split(Pattern.quote("^"))[1].toString().equals("14"))
										{

											StringBuilder answer=new StringBuilder();
											LinearLayout ll_checkBoxVal=(LinearLayout)view;
											ArrayList<String> listTagChckBox=new ArrayList<String>();
											boolean isSelected=false;
											boolean isSingleSelected=true;
											boolean isEditTextFilled=false;
											for(int i=0;i<ll_checkBoxVal.getChildCount();i++)
											{
												LinearLayout checkBoxVal_ll=(LinearLayout) ll_checkBoxVal.getChildAt(i);
												CheckBox chckBox=(CheckBox) checkBoxVal_ll.getChildAt(0);
												
												if(chckBox.isChecked())
												{
													isSelected=true;
													EditText ediText=(EditText) checkBoxVal_ll.getChildAt(1);
													
													String tagChckBox=chckBox.getTag().toString();
													String inputType=ediText.getTag().toString();
													String edtTextVal="0";
													if(!TextUtils.isEmpty(ediText.getText().toString()))
													{
														ediText.setText("");
													}
													
													chckBox.setChecked(false);
													
												}
												
											}
											
										}
									}
									else
									{
										//hmapAnsValues.put(entry.getKey(), "");
									}
								}
					
						
						if(hmapAnsValues!=null && hmapAnsValues.size()>0)
						{
							//helperDb.fnsaveOutletQuestAnsMstrSectionWise(hmapAnsValues,sectionIsShown,tempId);
						//	helperDb.deleteResetDataQuest(tempId,nodeId,nodeType,false);
							/*if(TextUtils.isEmpty(personName))
							{
								View view=ll_data.findViewWithTag("2^2");
								EditText edVal=(EditText)view;
								personName=edVal.getText().toString();
								
							}*/
							
							if(hmapImageClkdTempIdData!=null && hmapImageClkdTempIdData.size()>0)
							{
								
								 //helperDb.insertImageInfo(tempId,hmapImageClkdTempIdData);
								 hmapImageClkdTempIdData=new LinkedHashMap<String, String>();
								 
							}
						
						
					
				 }
							}
						
				 }


	public void getCheckBoxWithEditTextValues(ArrayList<String> chckBoxVal, final String tagVal)
	{
		String quesWithAllValues=AddNewStore_DynamicSectionWise.hmapQuesIdValues.get(tagVal);


		String[] quesVal=quesWithAllValues.split(Pattern.quote("^")) ;




		String asnControlInputTypeID=quesVal[5];
		int ansControlInputTypeMaxLength=Integer.parseInt(quesVal[6]);
		LinearLayout lay= (LinearLayout) ll_data.findViewWithTag(tagVal);
		lay.removeAllViews();
		if(lay!=null)
		{
			for(int index=0;index <chckBoxVal.size();index++)
			{
				final LinearLayout layHorizontal = new LinearLayout(getActivity());
				layHorizontal.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
						LayoutParams.WRAP_CONTENT));

				LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 0.7f);
				LinearLayout.LayoutParams layoutParamseditText = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 0.3f);
				layoutParams2.setMargins(10, 0, 5, 0);
				layHorizontal.setOrientation(LinearLayout.HORIZONTAL);
				CheckBox chBox=new CheckBox(getActivity());
				chBox.setLayoutParams(layoutParamseditText);

				//Edittext


				String quesIdForkey=tagVal.split(Pattern.quote("^"))[0];
				String grpQuesIdForkey=tagVal.split(Pattern.quote("^"))[2];
				String checkBoxVal=chckBoxVal.get(index).split(Pattern.quote("^"))[1];
				String checkBoxValNoneOptionNo=chckBoxVal.get(index).split(Pattern.quote("^"))[0];
				chBox.setTextSize(14);
				chBox.setTag(checkBoxValNoneOptionNo+"#"+tagVal);



				final View editText=getEditTextView(true,ansControlInputTypeMaxLength, checkBoxValNoneOptionNo+"#"+tagVal+"_chcBoxEd","",asnControlInputTypeID);
				editText.setEnabled(false);

				editText.setBackgroundResource(R.drawable.et_boundary_disable);
//				editText.setBackgroundColor(Color.parseColor("#EEEEEEE"));
				chBox.setText(checkBoxVal);

				editText.setLayoutParams(layoutParams2);
				hmapOptionId.put(grpQuesIdForkey+"^"+checkBoxVal, chckBoxVal.get(index).split(Pattern.quote("^"))[0]);


				layHorizontal.addView(chBox);
				layHorizontal.addView(editText);
				lay.addView(layHorizontal);


				chBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

						if(isChecked)
						{
							EditText edText=(EditText) ll_data.findViewWithTag(buttonView.getTag().toString()+"_chcBoxEd");
							edText.setEnabled(true);

							edText.setBackgroundResource(R.drawable.et_boundary_enable);
//							edText.setBackgroundColor(Color.parseColor("#FFFFFFF"));

						}
						else
						{
							EditText edText=(EditText) ll_data.findViewWithTag(buttonView.getTag().toString()+"_chcBoxEd");
							edText.setText("");
							edText.setEnabled(false);

							edText.setBackgroundResource(R.drawable.et_boundary_disable);
//							edText.setBackgroundColor(Color.parseColor("#EEEEEEE"));

						}

					}
				});



			}
		}


	}

	public  void setFreshAddress()
	{
		if(!AddNewStore_DynamicSectionWise.address.equals("NA"))
		{
			EditText edAddress= (EditText) ll_data.findViewWithTag(addressKey);
			if(edAddress!=null)
			{
				edAddress.setText("\n\n"+AddNewStore_DynamicSectionWise.address);
			}

		}
		if(!AddNewStore_DynamicSectionWise.pincode.equals("NA"))
		{
			etPinCode.setText(AddNewStore_DynamicSectionWise.pincode);
		}
		if(!AddNewStore_DynamicSectionWise.state.equals("NA"))
		{
			etState.setText(AddNewStore_DynamicSectionWise.state);
		}
		boolean isCityFilled = false;
		if(!AddNewStore_DynamicSectionWise.city.equals("NA")) {


			for (Map.Entry<String, String> entryCity : hmapCity_details.entrySet()) {
				if (entryCity.getKey().equalsIgnoreCase(AddNewStore_DynamicSectionWise.city.trim())) {
					etCity.setText(AddNewStore_DynamicSectionWise.city);
					isCityFilled = true;
					defaultCity = entryCity.getKey();
					break;
				}
			}

			if(isCityFilled)
			{
				if(!TextUtils.isEmpty(defaultCity))
				{
					etCity.setText(defaultCity);
					if(defaultCity.equalsIgnoreCase("Others") || defaultCity.equalsIgnoreCase("Other"))
					{
						etState.setEnabled(true);
						etState.setText("Select");
					}
					else
					{
						if(hmapCityAgainstState!=null && hmapCityAgainstState.containsKey(defaultCity))
						{
							etState.setText(hmapCityAgainstState.get(defaultCity));
						}
						else
						{
							etState.setText("Select");
						}
					}

				}
				else
				{
					etState.setText("Select");
				}
			}
			else
			{
				etState.setText("Select");
			}
			//
		}

		else
		{
			if(!TextUtils.isEmpty(defaultCity))
			{
				etCity.setText(defaultCity);
				if(defaultCity.equalsIgnoreCase("Others") || defaultCity.equalsIgnoreCase("Other"))
				{
					etState.setEnabled(true);
				}
				else
				{
					if(hmapCityAgainstState!=null && hmapCityAgainstState.containsKey(defaultCity))
					{
						etState.setText(hmapCityAgainstState.get(defaultCity));
					}
				}

			}
		}

		/*if(!AddNewStore_DynamicSectionWise.city.equals("NA"))
		{
			etCity.setText(AddNewStore_DynamicSectionWise.city);
		}
		if(!AddNewStore_DynamicSectionWise.state.equals("NA"))
		{
			etState.setText(AddNewStore_DynamicSectionWise.state);
		}*/
	}


	public void   openCustomCamara()
	{
		if(dialog!=null)
		{
			if(!dialog.isShowing())
			{
				openCamera();


			}

		}
		else
		{
			openCamera();

		}

	}

	private void handleZoom(MotionEvent event, Camera.Parameters params) {
		int maxZoom = params.getMaxZoom();
		int zoom = params.getZoom();
		float newDist = getFingerSpacing(event);
		if (newDist > mDist) {
			// zoom in
			if (zoom < maxZoom)
				zoom++;
		} else if (newDist < mDist) {
			// zoom out
			if (zoom > 0)
				zoom--;
		}
		mDist = newDist;
		params.setZoom(zoom);
		mCamera.setParameters(params);
	}
	public void handleFocus(MotionEvent event, Camera.Parameters params) {
		int pointerId = event.getPointerId(0);
		int pointerIndex = event.findPointerIndex(pointerId);
		// Get the pointer's current position
		float x = event.getX(pointerIndex);
		float y = event.getY(pointerIndex);

		List<String> supportedFocusModes = params.getSupportedFocusModes();
		if (supportedFocusModes != null
				&& supportedFocusModes
				.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
			mCamera.autoFocus(new Camera.AutoFocusCallback() {
				@Override
				public void onAutoFocus(boolean b, Camera camera) {
					// currently set to auto-focus on single touch
				}
			});
		}
	}

	@Override
	public void onStop() {
		super.onStop();
		if(mCamera!=null){
			mCamera.release();
			mCamera=null;
			if(dialog!=null){
				if(dialog.isShowing()){
					dialog.dismiss();

				}
			}
		}
	}

	private float getFingerSpacing(MotionEvent event) {
		// ...
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		return (float)Math.sqrt(x * x + y * y);
	}
	private void setCameraDisplayOrientation(Activity activity,
											 int cameraId, Camera camera) {
		Camera.CameraInfo info =
				new Camera.CameraInfo();
		Camera.getCameraInfo(cameraId, info);
		int rotation = activity.getWindowManager().getDefaultDisplay()
				.getRotation();
		int degrees = 0;
		switch (rotation) {
			case Surface.ROTATION_0: degrees = 0; break;
			case Surface.ROTATION_90: degrees = 90; break;
			case Surface.ROTATION_180: degrees = 180; break;
			case Surface.ROTATION_270: degrees = 270; break;
		}

		int result;
		if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
			result = (info.orientation + degrees) % 360;
			result = (360 - result) % 360;  // compensate the mirror
		} else {  // back-facing
			result = (info.orientation - degrees + 360) % 360;
		}
		camera.setDisplayOrientation(result);
	}
	private Camera.PictureCallback getPictureCallback() {
		Camera.PictureCallback picture = new Camera.PictureCallback() {

			@Override
			public void onPictureTaken(byte[] data, Camera camera) {
				//make a new picture file
				File pictureFile = getOutputMediaFile();

				Camera.Parameters params = mCamera.getParameters();
				params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
				mCamera.setParameters(params);
				isLighOn = false;

				if (pictureFile == null) {
					return;
				}
				try {
					//write the file
					FileOutputStream fos = new FileOutputStream(pictureFile);
					fos.write(data);
					fos.close();

					//Toast toast = Toast.makeText(getActivity(), "Picture saved: " + pictureFile.getName(), Toast.LENGTH_LONG);
					//toast.show();
					//put data here

					arrImageData.add(0,pictureFile);
					arrImageData.add(1,pictureFile.getName());
					dialog.dismiss();
					if(pictureFile!=null)
					{
						File file=pictureFile;
						System.out.println("File +++"+pictureFile);
						imageName=pictureFile.getName();
						CommonFunction cm=new CommonFunction();
						cm.normalizeImageForUri(getActivity(),Uri.fromFile(pictureFile));
						Bitmap bmp = decodeSampledBitmapFromFile(file.getAbsolutePath(), 80, 80);

						ByteArrayOutputStream stream = new ByteArrayOutputStream();
						uriSavedImage = Uri.fromFile(pictureFile);
						bmp.compress(Bitmap.CompressFormat.JPEG, 70, stream);
						byte[] byteArray = stream.toByteArray();

						// Convert ByteArray to Bitmap::\
						//
						long syncTIMESTAMP = System.currentTimeMillis();
						Date dateobj = new Date(syncTIMESTAMP);
						SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
						String clkdTime = df.format(dateobj);
						//	String valueOfKey=imagButtonTag+"~"+tempId+"~"+file.getAbsolutePath()+"~"+clkdTime+"~"+"2";
						String valueOfKey=clickedTagPhoto+"~"+AddNewStore_DynamicSectionWise.selStoreID+"~"+uriSavedImage.toString()+"~"+clkdTime+"~"+"1";
						//   helperDb.insertImageInfo(tempId,imagButtonTag, imageName, file.getAbsolutePath(), 2);
						Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
								byteArray.length);

						//
						setSavedImageToScrollView(bitmap, imageName,valueOfKey,clickedTagPhoto);
					}



//Show dialog here
//...
//Hide dialog here

				} catch (FileNotFoundException e) {
				} catch (IOException e) {
				}

				//refresh camera to continue preview--------------------------------------------------------------
				//	mPreview.refreshCamera(mCamera);
				//if want to release camera
				if(mCamera!=null){
					mCamera.release();
					mCamera=null;
				}
			}
		};
		return picture;
	}


	OnClickListener captrureListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			v.setEnabled(false);
			getActivity().getWindow().setFlags(LayoutParams.FLAG_NOT_TOUCHABLE, LayoutParams.FLAG_NOT_TOUCHABLE);
			cancelCam.setEnabled(false);
			flashImage.setEnabled(false);
			if(cameraPreview!=null)
			{
				cameraPreview.setEnabled(false);
			}

			if(mCamera!=null)
			{
				mCamera.takePicture(null, null, mPicture);
			}
			else
			{
				dialog.dismiss();
			}
			getActivity().getWindow().clearFlags(LayoutParams.FLAG_NOT_TOUCHABLE);

		}
	};



	private boolean hasCamera(Context context) {
		//check if the device has camera
		if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			return true;
		} else {
			return false;
		}
	}
	private int findFrontFacingCamera() {
		int cameraId = -1;
		// Search for the front facing camera
		int numberOfCameras = Camera.getNumberOfCameras();
		for (int i = 0; i < numberOfCameras; i++) {
			Camera.CameraInfo info = new Camera.CameraInfo();
			Camera.getCameraInfo(i, info);
			if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
				cameraId = i;
				cameraFront = true;
				break;
			}
		}
		return cameraId;
	}
	private int findBackFacingCamera() {
		int cameraId = -1;
		//Search for the back facing camera
		//get the number of cameras
		int numberOfCameras = Camera.getNumberOfCameras();
		//for every camera check
		for (int i = 0; i < numberOfCameras; i++) {
			Camera.CameraInfo info = new Camera.CameraInfo();
			Camera.getCameraInfo(i, info);
			if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
				cameraId = i;
				cameraFront = false;
				break;
			}
		}
		return cameraId;
	}


	private static File getOutputMediaFile() {
		//make a new file directory inside the "sdcard" folder
		File mediaStorageDir = new File("/sdcard/", CommonInfo.RSPLImagesnFolder);

		//if this "JCGCamera folder does not exist
		if (!mediaStorageDir.exists()) {
			//if you cannot make this folder return
			if (!mediaStorageDir.mkdirs()) {
				return null;
			}
		}

		//take the current timeStamp
		String timeStamp = new SimpleDateFormat("yyyyMMMdd_HHmmss", Locale.ENGLISH).format(new Date());
		File mediaFile;
		//and make a media file:
		//mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
		mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" +CommonInfo.imei+"_"+ timeStamp + ".jpg");


		return mediaFile;
	}


	/*private class OpenCameraAsyncTask extends AsyncTask<Void, Void, Void>
	{

		ProgressDialog pDialogOpenCamera=new ProgressDialog(getActivity());

		@Override
		protected void onPreExecute()
		{
			super.onPreExecute();




			pDialogOpenCamera.setTitle(getText(R.string.genTermPleaseWaitNew));
			pDialogOpenCamera.setMessage(getText(R.string.cameraOpen));
			pDialogOpenCamera.setIndeterminate(false);
			pDialogOpenCamera.setCancelable(false);
			pDialogOpenCamera.setCanceledOnTouchOutside(false);
			pDialogOpenCamera.show();

		}

		@Override
		protected Void doInBackground(Void... args)
		{


			try
			{

			}
			catch (Exception e)
			{
				if(pDialogOpenCamera.isShowing())
				{
					pDialogOpenCamera.dismiss();
				}
			}

			return null;
		}


		@Override
		protected void onPostExecute(Void result)
		{
			super.onPostExecute(result);


			if(pDialogOpenCamera.isShowing())
			{
				pDialogOpenCamera.dismiss();
			}

		}
	}*/
	public void openCamera()
	{
		getActivity().getWindow().setFlags(LayoutParams.FLAG_NOT_TOUCHABLE, LayoutParams.FLAG_NOT_TOUCHABLE);
		arrImageData.clear();
		getActivity().getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON);

		dialog = new Dialog(getActivity());
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

		//dialog.setTitle("Calculation");
		dialog.setCancelable(false);
		dialog.setContentView(R.layout.activity_main);
		LayoutParams parms = dialog.getWindow().getAttributes();


		parms.height=parms.MATCH_PARENT;
		parms.width=parms.MATCH_PARENT;
		cameraPreview = (LinearLayout)dialog. findViewById(R.id.camera_preview);

		mPreview = new CameraPreview(getActivity(), mCamera);
		cameraPreview.addView(mPreview);
		//onResume code
		if (!hasCamera(getActivity())) {
			Toast toast = Toast.makeText(getActivity(), "Sorry, your phone does not have a camera!", Toast.LENGTH_LONG);
			toast.show();

		}
		if (mCamera == null) {
			//if the front facing camera does not exist
			if (findFrontFacingCamera() < 0) {
				Toast.makeText(getActivity(), "No front facing camera found.", Toast.LENGTH_LONG).show();
				switchCamera.setVisibility(View.GONE);
			}

			//mCamera = Camera.open(findBackFacingCamera());
			mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
      /*if(mCamera==null){
         mCamera=Camera.open(0);
      }*/

			boolean isParameterSet=false;
			try {
				Camera.Parameters params= mCamera.getParameters();


				List<Camera.Size> sizes = params.getSupportedPictureSizes();
				Camera.Size size = sizes.get(0);
//Camera.Size size1 = sizes.get(0);
				for(int i=0;i<sizes.size();i++)
				{

					if(sizes.get(i).width > size.width)
						size = sizes.get(i);


				}

//System.out.println(size.width + "mm" + size.height);

				params.setPictureSize(size.width, size.height);
				params.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
				// params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
				params.setSceneMode(Camera.Parameters.SCENE_MODE_AUTO);
				params.setWhiteBalance(Camera.Parameters.WHITE_BALANCE_AUTO);

				// params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);

				isLighOn = false;
				int minExpCom=params.getMinExposureCompensation();
				int maxExpCom=params.getMaxExposureCompensation();

				if( maxExpCom > 4 && minExpCom < 4)
				{
					params.setExposureCompensation(4);
				}
				else
				{
					params.setExposureCompensation(0);
				}
				params.setAutoExposureLock(false);
				params.setAutoWhiteBalanceLock(false);
				//String supportedIsoValues = params.get("iso-values");
				// String newVAlue = params.get("iso");
				//  params.set("iso","1600");
				params.setColorEffect("none");
				params.set("scene-mode","auto");


				params.setPictureFormat(ImageFormat.JPEG);
				params.setJpegQuality(70);
				params.setRotation(90);


				mCamera.setParameters(params);
				isParameterSet=true;
			}
			catch (Exception e)
			{

			}
			if(!isParameterSet)
			{
				Camera.Parameters params2= mCamera.getParameters();
				params2.setPictureFormat(ImageFormat.JPEG);
				params2.setJpegQuality(70);
				params2.setRotation(90);

				mCamera.setParameters(params2);
			}






			setCameraDisplayOrientation(getActivity(), Camera.CameraInfo.CAMERA_FACING_BACK,mCamera);
			mPicture = getPictureCallback();
			mPreview.refreshCamera(mCamera);
		}


		capture = (Button)dialog.  findViewById(R.id.button_capture);

		flashImage= (ImageView)dialog.  findViewById(R.id.flashImage);
		flashImage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (isLighOn) {
					// turn off flash
					Camera.Parameters params = mCamera.getParameters();

					if (mCamera == null || params == null) {
						return;
					}


					params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
					mCamera.setParameters(params);
					flashImage.setImageResource(R.drawable.flash_off);
					isLighOn=false;
				} else {

					// turn on flash
					Camera.Parameters params = mCamera.getParameters();

					if (mCamera == null || params == null) {
						return;
					}

					params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);

					flashImage.setImageResource(R.drawable.flash_on);
					mCamera.setParameters(params);

					isLighOn=true;
				}
			}
		});

		final Button cancleCamera= (Button)dialog.  findViewById(R.id.cancleCamera);
		cancelCam=cancleCamera;
		cancleCamera.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().getWindow().setFlags(LayoutParams.FLAG_NOT_TOUCHABLE, LayoutParams.FLAG_NOT_TOUCHABLE);
				v.setEnabled(false);
				capture.setEnabled(false);
				cameraPreview.setEnabled(false);
				flashImage.setEnabled(false);

				Camera.Parameters params = mCamera.getParameters();
				params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
				mCamera.setParameters(params);
				isLighOn = false;
				dialog.dismiss();
				getActivity().getWindow().clearFlags(LayoutParams.FLAG_NOT_TOUCHABLE);
			}
		});
		capture.setOnClickListener(captrureListener);

		cameraPreview.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// Get the pointer ID
				Camera.Parameters params = mCamera.getParameters();
				int action = event.getAction();

				if (event.getPointerCount() > 1) {
					// handle multi-touch events
					if (action == MotionEvent.ACTION_POINTER_DOWN) {
						mDist = getFingerSpacing(event);
					} else if (action == MotionEvent.ACTION_MOVE
							&& params.isZoomSupported()) {
						mCamera.cancelAutoFocus();
						handleZoom(event, params);
					}
				} else {
					// handle single touch events
					if (action == MotionEvent.ACTION_UP) {
						handleFocus(event, params);
					}
				}
				return true;
			}
		});

		dialog.show();
		getActivity().getWindow().clearFlags(LayoutParams.FLAG_NOT_TOUCHABLE);


	}

	private void getStateDetails()
	{

		hmapState_details=helperDb.fngetDistinctState();

		int index=0;
		if(hmapState_details!=null && hmapState_details.size()>0)
		{
			stateNames=new ArrayList<String>();
			for(Map.Entry<String, String> entry:hmapState_details.entrySet())
			{
				stateNames.add(entry.getKey().toString());
			}

		}


	}

	private void getCityDetails()
	{

		hmapCity_details=helperDb.fngetCityList();

		int index=0;
		if(hmapCity_details!=null && hmapCity_details.size()>0)
		{
			cityNames=new ArrayList<String>();
			for(Map.Entry<String, String> entry:hmapCity_details.entrySet())
			{
				cityNames.add(entry.getKey().toString());
			}
		}


	}


	public void customAlertStateCityList(int flgCityState,final List<String> listOption, String sectionHeader,String StateCityName)
	{

		final Dialog listDialog = new Dialog(getActivity());
		listDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		listDialog.setContentView(R.layout.search_list);
		listDialog.setCanceledOnTouchOutside(false);
		listDialog.setCancelable(false);
		LayoutParams parms = listDialog.getWindow().getAttributes();
		parms.gravity =Gravity.CENTER;
		//there are a lot of settings, for dialog, check them all out!
		parms.dimAmount = (float) 0.5;
		isSelectedSearch=false;



		TextView txt_section=(TextView) listDialog.findViewById(R.id.txt_section);
		txt_section.setText(sectionHeader);
		TextView txtVwCncl=(TextView) listDialog.findViewById(R.id.txtVwCncl);
		//    TextView txtVwSubmit=(TextView) listDialog.findViewById(R.id.txtVwSubmit);

		final EditText ed_search=(AutoCompleteTextView) listDialog.findViewById(R.id.ed_search);
		final ListView list_store=(ListView) listDialog.findViewById(R.id.list_store);
		final CardArrayAdapterCityState cardArrayAdapter = new CardArrayAdapterCityState(getActivity(),flgCityState,listOption,listDialog,StateCityName);







		list_store.setAdapter(cardArrayAdapter);
		//	editText.setBackgroundResource(R.drawable.et_boundary);


		boolean isSingleLine=true;

		txtVwCncl.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				listDialog.dismiss();
				isSelectedSearch=false;

			}
		});




		//now that the dialog is set up, it's time to show it
		listDialog.show();

	}

	public void selectedCityState(String selectedCategory, Dialog dialog, int flgCityState)
	{
		dialog.dismiss();
		if(flgCityState==1)
		{
			etCity.setText(selectedCategory);
			helperDb.updateAllDefaultCity(hmapCity_details.get(selectedCategory));
			previousSlctdCity=selectedCategory;
			if(selectedCategory.equalsIgnoreCase("Others") || selectedCategory.equalsIgnoreCase("Other"))
			{
				if(etOtherCity.getVisibility()==View.GONE)
				{
					etOtherCity.setVisibility(View.VISIBLE);

				}
				etState.setText("Select");
				etState.setEnabled(true);
			}
			else
			{
				if(etOtherCity.getVisibility()==View.VISIBLE)
				{
					etOtherCity.setVisibility(View.GONE);
				}

				if(hmapCityAgainstState!=null && hmapCityAgainstState.containsKey(selectedCategory))
				{
					etState.setText(hmapCityAgainstState.get(selectedCategory));
				}
				etState.setEnabled(false);
			}
		}
		else
		{
			previousSlctdCity=selectedCategory;
			etState.setText(selectedCategory);
		}

	}


	public boolean fnValidateStateCity()
	{
		boolean boolValidateStateCity=true;
		if((!(etCity.getText().toString()).equalsIgnoreCase("Select")) && (!(etState.getText().toString()).equalsIgnoreCase("Select")) && (!(etCity.getText().toString()).equalsIgnoreCase("Others"))&& (!(etCity.getText().toString()).equalsIgnoreCase("Other")))
		{
			boolValidateStateCity=true;
		}
		else if(((etCity.getText().toString()).equalsIgnoreCase("Others")) || ((etCity.getText().toString()).equalsIgnoreCase("Other")))
		{
			if(TextUtils.isEmpty(etOtherCity.getText().toString().trim()))
			{
				boolValidateStateCity= false;
				showErrorAlert("Please Fill Other City Name");
			}
			else
			{

				if((etCity.getText().toString()).equalsIgnoreCase("Select"))
				{
					showErrorAlert("Please Select City Name");
					boolValidateStateCity= false;

				}
				else if((etState.getText().toString()).equalsIgnoreCase("Select"))
				{
					showErrorAlert("Please Select State Name");
					boolValidateStateCity= false;

				}


			}
		}
		else
		{

			if((etCity.getText().toString()).equalsIgnoreCase("Select"))
			{
				showErrorAlert("Please Select City Name");
				boolValidateStateCity= false;

			}
			else if((etState.getText().toString()).equalsIgnoreCase("Select"))
			{
				showErrorAlert("Please Select State Name");
				boolValidateStateCity= false;

			}


		}
		return boolValidateStateCity;
	}
}
