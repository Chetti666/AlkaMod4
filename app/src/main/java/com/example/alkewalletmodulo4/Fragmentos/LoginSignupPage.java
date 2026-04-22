package com.example.alkewalletmodulo4.Fragmentos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.alkewalletmodulo4.R;


public class LoginSignupPage extends Fragment {


    public LoginSignupPage() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_signup_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        Button btnCrearCuenta = view.findViewById(R.id.btnCrearCuenta);
        TextView btnCuentaCreada = view.findViewById(R.id.enlaceYaTienesCuenta);

        btnCrearCuenta.setOnClickListener(v -> {
            navController.navigate(R.id.signupPage);
        });
        btnCuentaCreada.setOnClickListener(v -> {
            navController.navigate(R.id.loginPage);
        });

    }
}