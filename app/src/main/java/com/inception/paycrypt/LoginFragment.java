package com.inception.paycrypt;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.inception.paycrypt.Dto.TokenDto;
import com.inception.paycrypt.Dto.LoginDto;
import com.inception.paycrypt.Service.AuthService;
import com.inception.paycrypt.databinding.FragmentLoginBinding;

import retrofit2.Retrofit;
import rx.Scheduler;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


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
                    //sendAuthRequest();
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
    private void sendAuthRequest(){
        Retrofit retrofit = RetrofitGenerator.getInstance();
        AuthService authService = retrofit.create(AuthService.class);
        LoginDto loginDto = new LoginDto("memo","12345");
        Action1<TokenDto> successAction = tokenDto -> onSuccess(tokenDto.getAccessToken());
        Action1<Throwable> failedAction = throwable -> Log.e("Developer","Auth error",throwable);
        authService.auth(loginDto)
                .observeOn(Schedulers.from(ContextCompat.getMainExecutor(requireContext())))
                .subscribe(successAction,failedAction);
    }
    private void onSuccess(String token){
        Log.d("Developer","TokenDto"+token);
        binding.textviewFirst.setText(token);

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