package com.rsoft.hurmanmobileapp.service;

import com.flextrade.jfixture.JFixture;
import com.rsoft.hurmanmobileapp.proxy.DefaultProxy;
import com.rsoft.lib.model.SalaireEmploye;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PositionServiceTest {
    private static final JFixture J_FIXTURE = new JFixture();
//    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
//    private static MockWebServer mockWebServer;
    @InjectMocks
    private PositionService positionService;
    private static SalaireEmploye salaireEmploye;
    @Mock
    private DefaultProxy defaultProxy;
    @BeforeEach
    void setUp() throws IOException {
//        mockWebServer = new MockWebServer();
//        mockWebServer.setDispatcher(dispatcher);
//        mockWebServer.start();
    }

    @BeforeAll
    static void beforeAll() {
        salaireEmploye = J_FIXTURE.create(SalaireEmploye.class);

    }

//    @Test
//    void getPositionFromService_succes() {
//        // Get the current date
//        Calendar calendar = Calendar.getInstance();
//
//        // Set the calendar to the first day of the week
//        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
//        Date firstDayOfWeek = calendar.getTime();
//        System.out.println("First Day of Week: " + firstDayOfWeek);
//
//        // Set the calendar to the last day of the week
//        calendar.add(Calendar.DAY_OF_WEEK, 6);
//        Date lastDayOfWeek = calendar.getTime();
//        System.out.println("Last Day of Week: " + lastDayOfWeek);
//
//        //Arrange
////        GetPositionRequest getPositionRequest =  J_FIXTURE.create(GetPositionRequest.class);
////        getPositionRequest.setCodeEmploye("000121");
////        Mockito.doReturn(List.of(salaireEmploye)).when(defaultProxy).getSalaireEmployes(any());
//        //Act
////        GetPositionResponse getPositionResponse = positionService.getPositionFromService(getPositionRequest);
//        //Assert
//    }
//
//    private static final Dispatcher dispatcher = new Dispatcher() {
//        @NotNull
//        @Override
//        public MockResponse dispatch(@NotNull RecordedRequest recordedRequest) {
//            if (recordedRequest.getRequestUrl().encodedPath().contains("GetSalaireEmployes")) {
//                try {
//                    return new MockResponse().setBody(OBJECT_MAPPER.writeValueAsString(List.of(salaireEmploye)));
//                } catch (JsonProcessingException e) {
//                    return new MockResponse().setResponseCode(500);
//                }
//            } else {
//                return new MockResponse().setResponseCode(500);
//            }
//        }
//    };
}