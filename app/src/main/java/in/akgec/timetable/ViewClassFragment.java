package in.akgec.timetable;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class ViewClassFragment extends Fragment {

    Spinner fillClass, fillDay;
    Button btnView;
    TableLayout layoutTable;
    CardView viewCard;
    ServerLink link = new ServerLink();
    String VIEW_CLASS = link.VIEW_CLASS;
    public ViewClassFragment() {
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_class, container, false);
        fillClass = (Spinner) view.findViewById(R.id.fillClass);
        fillDay = (Spinner) view.findViewById(R.id.fillDay);
        btnView = (Button) view.findViewById(R.id.btnView);
        layoutTable = (TableLayout) view.findViewById(R.id.layoutTable);
        viewCard  = (CardView) view.findViewById(R.id.viewCard);

        new GetClasses().execute();
        populateClasses();
        populateDays();
        return view;
    }

    private void populateClasses() {

    }

    private void populateDays() {
        List<String> lables = new ArrayList<>();
        String params[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        for(int i=0; i<params.length; i++) {
            lables.add(params[i]);
        }
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, lables);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fillDay.setAdapter(spinnerAdapter);
    }

    private class GetClasses extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            
            return null;
        }
    }
}
