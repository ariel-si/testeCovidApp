package com.drommond.testecovidapp.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.drommond.testecovidapp.R;

import java.text.DecimalFormat;

public class TesteFragment extends Fragment {

    RadioGroup radioGroupA, radioGroupB, radioGroupC, radioGroupD, radioGroupE, radioGroupF;

    Double gripe;
    Double resfriado;
    Double covid;

    Button btnVerifica;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    final DecimalFormat df = new DecimalFormat("0.##");

    public TesteFragment(){
    }

    public static TesteFragment newInstance(String param1, String param2) {
        TesteFragment fragment = new TesteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_teste, container, false);

        radioGroupA = view.findViewById(R.id.groupFebre);
        radioGroupB = view.findViewById(R.id.groupCansaco);
        radioGroupC = view.findViewById(R.id.groupTosse);
        radioGroupD = view.findViewById(R.id.groupEspirro);
        radioGroupE = view.findViewById(R.id.groupFaltaDeAr);
        radioGroupF = view.findViewById(R.id.groupDiarreia);

        btnVerifica = view.findViewById(R.id.btnVerifica);

        btnVerifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gripe = 0.0;
                resfriado = 0.0;
                covid = 0.0;

                int itemRadioGroupA = radioGroupA.getCheckedRadioButtonId();
                int itemRadioGroupB = radioGroupB.getCheckedRadioButtonId();
                int itemRadioGroupC = radioGroupC.getCheckedRadioButtonId();
                int itemRadioGroupD = radioGroupD.getCheckedRadioButtonId();
                int itemRadioGroupE = radioGroupE.getCheckedRadioButtonId();
                int itemRadioGroupF = radioGroupF.getCheckedRadioButtonId();

                if (itemRadioGroupA != -1) {
                    RadioButton radioSelecionado = view.findViewById(itemRadioGroupA);
                    String radioSelecinadoVal = radioSelecionado.getText().toString();

                    if (radioSelecinadoVal.equals("Raro")) {

                    } else if (radioSelecinadoVal.equals("Comum")) {

                        covid = covid + 1;
                        resfriado = resfriado + 1;
                        gripe = gripe + 1;

                    } else {


                    }
                }

                if (itemRadioGroupB != -1) {
                    RadioButton radioSelecionado = view.findViewById(itemRadioGroupB);
                    String radioSelecinadoVal = radioSelecionado.getText().toString();

                    if (radioSelecinadoVal.equals("Raro")) {



                    } else if (radioSelecinadoVal.equals("Comum")) {

                        covid = covid + 1;

                    } else {

                        resfriado = resfriado + 1;
                        gripe = gripe + 1;
                    }
                }
                if (itemRadioGroupC != -1) {
                    RadioButton radioSelecionado = view.findViewById(itemRadioGroupC);
                    String radioSelecinadoVal = radioSelecionado.getText().toString();

                    if (radioSelecinadoVal.equals("Raro")) {



                    } else if (radioSelecinadoVal.equals("Comum")) {

                        gripe = gripe + 1;
                        covid = covid + 1;

                    } else {

                        resfriado = resfriado + 1;
                    }
                }
                if (itemRadioGroupD != -1) {
                    RadioButton radioSelecionado = view.findViewById(itemRadioGroupD);
                    String radioSelecinadoVal = radioSelecionado.getText().toString();

                    if (radioSelecinadoVal.equals("Raro")) {



                    } else if (radioSelecinadoVal.equals("Comum")) {

                        resfriado = resfriado + 1;

                    } else {

                        gripe = gripe + 1;
                        covid = covid + 1;
                    }
                }
                if (itemRadioGroupE != -1) {
                    RadioButton radioSelecionado = view.findViewById(itemRadioGroupE);
                    String radioSelecinadoVal = radioSelecionado.getText().toString();

                    if (radioSelecinadoVal.equals("Raro")) {

                        resfriado = resfriado + 1;
                        gripe = gripe + 1;

                    } else if (radioSelecinadoVal.equals("Comum")) {

                        covid = covid + 1;

                    } else {


                    }
                }
                if (itemRadioGroupF != -1) {
                    RadioButton radioSelecionado = view.findViewById(itemRadioGroupF);
                    String radioSelecinadoVal = radioSelecionado.getText().toString();

                    if (radioSelecinadoVal.equals("Raro")) {

                        resfriado = resfriado + 1;
                        gripe = gripe + 1;

                    } else if (radioSelecinadoVal.equals("Comum")) {

                    } else {

                        covid = covid + 1;
                    }
                }

                Log.i("informa", resfriado+" / "+gripe+" / "+covid);


                gripe = gripe*90/6;
                resfriado = resfriado*90/6;
                covid = covid*95/6;

                new AlertDialog.Builder(getActivity()).setTitle("STATUS DO PACIENTE")
                        .setMessage("Gripe: "
                                +df.format(gripe)+"%\nResfriado: "
                                +df.format(resfriado)+"%\nCovid-19: "
                                +df.format(covid)+"%").show();

            }
        });

        return view;
    }

}