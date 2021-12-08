package com.inception.paycrypt;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inception.paycrypt.databinding.FragmentLoginBinding;


public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

            binding = FragmentLoginBinding.inflate(inflater, container, false);
            return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validForm()){
                    NavHostFragment.findNavController(LoginFragment.this)
                            .navigate(R.id.action_FirstFragment_to_SecondFragment);
                }


            }
        });

        binding.buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(LoginFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }


    private boolean validForm(){
        boolean valid = false;
        Editable userText =binding.inputUsername.getText();
        Editable passwordText = binding.inputPassword.getText();
        if(userText.length()==0 && !Patterns.EMAIL_ADDRESS.matcher(userText).matches()){
            binding.inputUsername.setError(getString(R.string.invalid_email));
        }else if(passwordText.length()==0){
            binding.inputUsername.setError(null);
            binding.inputPassword.setError(getString(R.string.invalid_password));
        }else{
            binding.inputUsername.setError(null);
            binding.inputPassword.setError(null);
            valid = true;
        }

        return valid;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}