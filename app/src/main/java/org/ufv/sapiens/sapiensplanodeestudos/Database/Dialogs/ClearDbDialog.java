package org.ufv.sapiens.sapiensplanodeestudos.Database.Dialogs;

import android.app.Fragment;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.ufv.sapiens.sapiensplanodeestudos.TelaInicial;
import org.ufv.sapiens.sapiensplanodeestudos.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ClearDbDialog.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ClearDbDialog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClearDbDialog extends DialogFragment {


    private static boolean result = false;

    public void onCancel(View view){
        result=false;
        this.dismiss();
    }

    public void onYes(View view){
        result=true;
        this.dismiss();
    }

    private OnFragmentInteractionListener mListener;

    public ClearDbDialog() {
        // Required empty public constructor

        Bundle args = new Bundle();
        args.putBoolean("result", result);
        this.setArguments(args);



    }

    public static ClearDbDialog newInstance() {
        ClearDbDialog fragment = new ClearDbDialog();
        Bundle args = new Bundle();
        args.putBoolean("result", result);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   }


    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
         ((TelaInicial) getActivity()).clearDb(result);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_clear_db_dialog, container, false);
        Button btnYes = (Button) view.findViewById(R.id.btnYes);

        Button btnCancel = (Button) view.findViewById(R.id.btnCancel);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onYes(view);
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCancel(view);
            }
        });

        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }




    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
