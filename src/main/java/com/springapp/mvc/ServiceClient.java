package com.springapp.mvc;

import com.springapp.mvc.wsdl.AuthRequest;
import com.springapp.mvc.wsdl.AuthResponse;
import com.springapp.mvc.wsdl.GetCityRequest;
import com.springapp.mvc.wsdl.GetCityResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

/**
 * Created by savenkov on 23.03.2015.
 */
public class ServiceClient extends WebServiceGatewaySupport {

    private String login;
    private String password;

    public AuthResponse authorization() {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEMail(login);
        authRequest.setPass(password);
        return (AuthResponse) getWebServiceTemplate().marshalSendAndReceive(authRequest, new SoapActionCallback(
                "http://www.abelski.by/ws/Auth"));
    }

    public GetCityResponse getCities(String session) {
        GetCityRequest request = new GetCityRequest();
        request.setSession(session);
        return (GetCityResponse) getWebServiceTemplate().marshalSendAndReceive(request, new SoapActionCallback(
                "http://www.abelski.by/ws/GetCity"));
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
