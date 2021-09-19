package com.example.alexthbot.fab.services.api;

import com.example.alexthbot.fab.database.user.model.BotAppointment;
import com.example.alexthbot.fab.database.user.model.ServiceID;
import com.example.alexthbot.fab.database.user.service.TokenService;
import com.example.alexthbot.fab.exeptions.ApiGatewayException;
import com.example.alexthbot.fab.services.api.entities.Appointment;
import com.example.alexthbot.fab.services.api.entities.Doctor;
import com.example.alexthbot.fab.utils.CollectionParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class BotAppointmentServiceApi  implements BotAppointmentService {
    @Autowired
    private TokenService tokenService;
    @Value("${gateway.api.get-appoinments}")
    private String urlGetApps;
    @Value("${gateway.host}")
    private String urlGateWay;
    @Autowired
    private ServiceID serviceID;

@Override
public HttpHeaders postAppointment(BotAppointment botAppointment) {
    try {
        HttpEntity<Void> httpEntity = new HttpEntity<>(tokenService.getToken(serviceID.getIdChat()));
        ResponseEntity<List<Doctor>> doctorsEntity = new RestTemplate().exchange((urlGateWay + "/patient/appointment/" + botAppointment.getId()), HttpMethod.POST, httpEntity, CollectionParams.get());
        log.info("Answer from method getDoctors {}", doctorsEntity.getBody());
        return doctorsEntity.getHeaders();
    }
     catch (RuntimeException e){
        throw ApiGatewayException.doctors();
    }
}

    @Override
    public List<Appointment> getAppointments() {
        HttpEntity<Void> httpEntity = new HttpEntity<>(tokenService.getToken(serviceID.getIdChat()));
        ResponseEntity<List<Appointment>> appResponse = new RestTemplate().exchange(urlGetApps, HttpMethod.GET, httpEntity, CollectionParams.get());
        if (appResponse.getStatusCode() == HttpStatus.OK) {
            log.info("Answer from method getAppointments {}",appResponse.getBody());
            return appResponse.getBody();
        } else {
            throw ApiGatewayException.appointments();
        }
    }

}
