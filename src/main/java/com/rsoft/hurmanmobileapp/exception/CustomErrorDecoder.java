package com.rsoft.hurmanmobileapp.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String invoqueur, Response reponse) {
        if (reponse.status() == 400) {
            return new BadRequestException("Requete incorrecte ");
        }
        if (reponse.status() == 404) {
            return new NotFoundException("Service non trouvé");
        }
        if (reponse.status() == 401) {
            return new BadRequestException("Impossible d'emvoyer de POST, PUT, DELETE");
        }
        return this.defaultErrorDecoder.decode(invoqueur, reponse);
    }
}
