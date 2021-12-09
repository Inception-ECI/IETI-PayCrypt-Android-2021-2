package com.inception.paycrypt.Service;
import com.inception.paycrypt.Dto.TokenDto;
import com.inception.paycrypt.Dto.LoginDto;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface AuthService {

    @POST("/v1/auth")
    Observable<TokenDto> auth(@Body LoginDto loginDto);

}
