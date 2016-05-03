package com.lsinf.uclove;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by henryv96 on 3/05/16.
 */

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    int day,start;

    public void set(int i,int j)
    {
        day = i;
        start = j;
    }
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        this.start = start;
        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        ((SettingsActivity)getActivity()).setDate(day,start,hourOfDay+":"+minute);
       /* if(start==1){
        TextView tv1 = (TextView) getActivity().findViewById(R.id.textView1);
        tv1.setText(view.getCurrentHour() + " : " + view.getCurrentMinute());
        }
        if(end==1){
        TextView tv2 = (TextView) getActivity().findViewById(R.id.textView2);

        tv2.setText(view.getCurrentHour() + " : " + view.getCurrentMinute());
*/
    }
}
